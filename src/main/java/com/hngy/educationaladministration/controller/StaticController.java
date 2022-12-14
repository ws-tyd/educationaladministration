package com.hngy.educationaladministration.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hngy.educationaladministration.auth.Authority;
import com.hngy.educationaladministration.auth.Role;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.Class;
import com.hngy.educationaladministration.service.SectionService;
import com.hngy.educationaladministration.service.StaticService;
import com.hngy.educationaladministration.service.SubjectService;
import com.hngy.educationaladministration.service.TeacherService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.deepoove.poi.XWPFTemplate;

@Authority(roles = {Role.TEACHER})
@Controller
@RequestMapping("/static")
public class StaticController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticController.class);

    @Autowired
    StaticService staticService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    SectionService sectionService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/upload_work_page")
    public String uploadWork() {
        return "teacher/workapproval/upload";
    }

    @GetMapping("/texaminePage")
    public String examine() {
        return "teacher/texamine";
    }

    //??????????????????
    @GetMapping("/work_type")
    @ResponseBody
    public Msg fun1() {
        List<Workapprovaltype> list = staticService.selectAllWorkApprovalType();
        return Msg.success().add("workInfo", list);
    }

    //??????????????????
    @PostMapping("/upload_work")
    @ResponseBody
    public Msg fun2(String idType, String worktitle, String beginDate, String endDate, String location,
                    String member, HttpSession httpSession) throws ParseException {
        //??????????????????
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");

        //????????????????????????
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(beginDate);
        Date end = sdf.parse(endDate);

        WorkapprovalWithBLOBs workapprovalWithBLOBs = new WorkapprovalWithBLOBs();
        workapprovalWithBLOBs.setIdType(Long.parseLong(idType));
        workapprovalWithBLOBs.setIdTeacher(teacher.getId());
        workapprovalWithBLOBs.setWorktitle(worktitle);
        workapprovalWithBLOBs.setBeginDate(start);
        workapprovalWithBLOBs.setEndDate(end);
        workapprovalWithBLOBs.setLocation(location);
        workapprovalWithBLOBs.setMember(member);
        workapprovalWithBLOBs.setWorkcontent("??????");
        //0????????????1???????????????2???????????????
        workapprovalWithBLOBs.setAppovalflag(0);
        workapprovalWithBLOBs.setSumbitdate(currentTime);


        int i = staticService.insertWorkapproval(workapprovalWithBLOBs);
        return Msg.success();
    }

    // ?????????
    //  ?????????????????????
    //??????????????????????????????????????????????????????????????????
    @GetMapping("/examine")
    @ResponseBody
    public Msg fun3(Integer pn, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        PageHelper.startPage(pn, 9);
        List<WorkapprovalWithBLOBs> list = staticService.selectAllWorkApproval(teacher.getIdSection());
        PageInfo page = new PageInfo(list, 5);
        return Msg.success().add("workInfo", page);
    }

    //???????????????????????????
    @PostMapping("/SH_work")
    @ResponseBody
    public Msg fun4(Long zt, Long workID, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        WorkapprovalWithBLOBs workapprovalWithBLOBs = teacherService.selectWorkById(workID);
        if (zt == 1) {
            workapprovalWithBLOBs.setAppovalflag(1);
        } else {
            workapprovalWithBLOBs.setAppovalflag(2);
        }
        Date date = new Date();
        workapprovalWithBLOBs.setAppovaldate(date);
        workapprovalWithBLOBs.setIdSubadmin(teacher.getId());
        staticService.updateShWorkApproval(workapprovalWithBLOBs);
        return Msg.success();
    }

    //???????????????????????????????????????
    @GetMapping("/down_technical_personnel")
    public void fun5(Long year, HttpSession httpSession, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");


        response.setCharacterEncoding("utf-8");
        response.setContentType("application/ms-word");
        OutputStream output = response.getOutputStream();
        String outputName = "?????????????????????????????????????????????";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());

        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".docx");

        TechnicalPersonnelWithBLOBs technicalPersonnelWithBLOBs = teacherService.selectTechnicalPersonnelInfo(teacher.getId(), year);

        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                put("name", teacher.getName());
                put("gerder", teacher.getGender());
                put("IDcard", teacher.getIdcardno());
                put("instituteName", teacher.getInstituteName());
                put("administrativeDuty", teacher.getAdministrativeduty());
                put("technicalPosition", teacher.getTechnicalposition());
                put("technicalPositionGeted", teacher.getTechnicalpositiongeted());
                put("year", technicalPersonnelWithBLOBs.getYear());
                put("mainAchievements", technicalPersonnelWithBLOBs.getMainachievements());
                put("attendance", technicalPersonnelWithBLOBs.getAttendance());
                put("rewardsAndPunishments", technicalPersonnelWithBLOBs.getRewardsandpunishments());

            }
        };


        File path = new File(ResourceUtils.getURL("target").getPath());
        String savePath = path.getAbsolutePath() + "\\classes\\static\\model\\";
        String modelName = "zyjsrykhb.docx";
        XWPFTemplate template = XWPFTemplate.compile(savePath + modelName)
                .render(datas);

        BufferedOutputStream out;
        out = new BufferedOutputStream(output);
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

    @GetMapping("/down_annual_assessment")
    public void fun6(Long year, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/ms-word");
        OutputStream output = response.getOutputStream();
        String outputName = "?????????????????????????????????";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());

        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".docx");

        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        AnnualAssessmentWithBLOBs assessment = teacherService.selectAnnualAssessmentInfo(teacher.getId(), year);

        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                put("name", teacher.getName());
                put("gender", teacher.getGender());
                put("education", teacher.getEducation());
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String time = sdf.format(teacher.getParticipationdate());
                System.out.println(teacher.getParticipationdate());
                put("date", teacher.getParticipationdate());
                put("administrativeDuty", teacher.getAdministrativeduty());
                put("technicalPosition", teacher.getTechnicalposition());
                put("politicalStatus", teacher.getPoliticalstatus());
                put("year", assessment.getYear());
                put("personalSummary", assessment.getPersonalsummary());

            }
        };

        File path = new File(ResourceUtils.getURL("target").getPath());
        String wordPath = path.getAbsolutePath() + "\\classes\\static\\model\\";
        String modelName = "ndkh.docx";


        XWPFTemplate template = XWPFTemplate.compile(wordPath + modelName)
                .render(datas);

        try {
            BufferedOutputStream out = new BufferedOutputStream(output);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //?????????????????????
    @GetMapping("/down_year_debriefing")
    public void fun7(Long year, HttpSession httpSession, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");

        //???????????????????????????????????????
        DebriefingYearWithBLOBs debriefingYear = teacherService.selectYearDebriefingInfo(teacher.getId(), year);


        // ?????????????????????
        XSSFWorkbook workBook = new XSSFWorkbook();
        // ??????sheet??????
        XSSFSheet sheet = workBook.createSheet("Sheet1");

        sheet.setColumnWidth(0, 15 * 256);//?????????1??????

        sheet.setColumnWidth(6, 24 * 256);//?????????1??????


        //?????????????????????
        XSSFRow row = sheet.createRow(0);
        CellRangeAddress region = new CellRangeAddress(0, 0, (short) 0, (short) 6);  //??????????????????
        sheet.addMergedRegion(region);
        //?????????????????????
        fun8(region, sheet, workBook);

        row.createCell(0).setCellValue("??????????????????" + year + "???????????????");
        row.setHeightInPoints(40); //????????????

        //???????????????????????????
        XSSFCellStyle style1 = workBook.createCellStyle();
        XSSFFont f = workBook.createFont();
        f.setFontHeightInPoints((short) 18);//??????
        f.setFontName("??????_GB2312");//????????????
        f.setBold(true);//??????
        style1.setFont(f);
        style1.setAlignment(HorizontalAlignment.CENTER);//????????????
        style1.setVerticalAlignment(VerticalAlignment.CENTER);//????????????
        style1.setBorderBottom(BorderStyle.THIN);//?????????
        style1.setBorderBottom(BorderStyle.THIN);//?????????
        style1.setBorderLeft(BorderStyle.THIN);//?????????
        style1.setBorderTop(BorderStyle.THIN);//?????????
        style1.setBorderRight(BorderStyle.THIN);//?????????
        row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        cell.setCellStyle(style1); //??????????????????????????????


        //???????????????????????????
        XSSFCellStyle style2 = workBook.createCellStyle();
        f = workBook.createFont();
        f.setFontHeightInPoints((short) 12);//??????
        f.setFontName("??????_GB2312");//????????????
        f.setBold(true);//??????
        style2.setFont(f);
        style2.setAlignment(HorizontalAlignment.CENTER);//????????????
        style2.setVerticalAlignment(VerticalAlignment.CENTER);//????????????
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);

        // ?????????,?????????
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("??? ???");
        cell.setCellStyle(style2); //??????????????????????????????

        region = new CellRangeAddress(1, 1, (short) 1, (short) 3);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(1);
        cell.setCellValue(teacher.getInstituteName());
        cell.setCellStyle(style2); //??????????????????????????????

        cell = row.createCell(4);
        cell.setCellValue("?????????");
        cell.setCellStyle(style2); //??????????????????????????????

        region = new CellRangeAddress(1, 1, (short) 4, (short) 5);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(6);
        cell.setCellValue(teacher.getName());
        cell.setCellStyle(style2); //??????????????????????????????
        row.setHeightInPoints(30);


        //?????????????????????
        XSSFCellStyle style3 = workBook.createCellStyle();
        f = workBook.createFont();
        f.setFontHeightInPoints((short) 12);//??????
        f.setFontName("??????_GB2312");//????????????
        style3.setFont(f);
        style3.setWrapText(true);
        style3.setAlignment(HorizontalAlignment.CENTER);//????????????
        style3.setVerticalAlignment(VerticalAlignment.CENTER);//????????????
        style3.setBorderBottom(BorderStyle.THIN);//?????????
        style3.setBorderLeft(BorderStyle.THIN);//?????????
        style3.setBorderRight(BorderStyle.THIN);//?????????
        style3.setBorderTop(BorderStyle.THIN);//?????????


        //??????????????????
        //??????????????????
        String teachingTask = debriefingYear.getTeachingtask();
        String[] aa = teachingTask.split("/");

        row = sheet.createRow(2);
        region = new CellRangeAddress(2, 2 + aa.length, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(0);
        cell.setCellValue("????????????");
        cell.setCellStyle(style3); //????????????

        cell = row.createCell(1);
        cell.setCellValue("??????");
        cell.setCellStyle(style2);
        region = new CellRangeAddress(2, 2, (short) 2, (short) 6);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(2);
        cell.setCellValue("????????????");
        cell.setCellStyle(style2);
        row.setHeightInPoints(30);

        //??????????????????
        int index = 3;
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);
        }


        //?????????????????????
        String achievementsInScientificResearch = debriefingYear.getAchievementsinscientificresearch();
        aa = achievementsInScientificResearch.split("/");

        row = sheet.createRow(index);
        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);

        //?????????????????????
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("???????????????");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);
        }


        //????????????????????????
        String otherWork = debriefingYear.getOtherwork();
        aa = otherWork.split("/");

        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);

        //????????????????????????
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("??????????????????");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);

        }


        //??????????????????
        String winAward = debriefingYear.getWinaward();
        aa = winAward.split("/");

        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);


        //??????????????????
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("????????????");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);

        }


        String summary = debriefingYear.getSummary();
        row = sheet.createRow(index);
        cell = row.createCell(0);
        cell.setCellValue("??????????????????(?????????100???)");
        cell.setCellStyle(style3);
        region = new CellRangeAddress(index, index, (short) 1, (short) 6);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(1);
        cell.setCellValue(summary);
        cell.setCellStyle(style3);
        row.setHeightInPoints(150);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msexcel");
        OutputStream output = response.getOutputStream();
        String outputName = teacher.getInstituteName() + year + "???????????????";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());

        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".xlsx");


        // ???excel?????????????????????
        BufferedOutputStream outputStream = null;
        outputStream = new BufferedOutputStream(output);
        workBook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workBook.close();
    }

    //???????????????????????????
    public void fun8(CellRangeAddress region, XSSFSheet sheet, XSSFWorkbook workBook) {

        BorderStyle borderStyle = BorderStyle.THIN;

        RegionUtil.setBorderBottom(borderStyle, region, sheet); // ?????????
        RegionUtil.setBorderLeft(borderStyle, region, sheet); // ?????????
        RegionUtil.setBorderRight(borderStyle, region, sheet); // ?????????
        RegionUtil.setBorderTop(borderStyle, region, sheet); // ?????????
    }

    //?????????????????????
    @GetMapping("/down_debriefing")
    public void fun9(Long year, String term, HttpSession httpSession, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");

        //???????????????????????????,??????????????????
        DebriefingWithBLOBs debriefing = teacherService.selectTermDebriefingInfo(teacher.getId(), year, term);


        // ?????????????????????
        XSSFWorkbook workBook = new XSSFWorkbook();
        // ??????sheet??????
        XSSFSheet sheet = workBook.createSheet("Sheet1");

        sheet.setColumnWidth(0, 15 * 256);//?????????1??????

        sheet.setColumnWidth(6, 24 * 256);//?????????1??????


        //?????????????????????
        XSSFRow row = sheet.createRow(0);
        CellRangeAddress region = new CellRangeAddress(0, 0, (short) 0, (short) 6);  //??????????????????
        sheet.addMergedRegion(region);
        //?????????????????????
        fun8(region, sheet, workBook);

        row.createCell(0).setCellValue(teacher.getInstituteName() + year + term + "???????????????");
        row.setHeightInPoints(40); //????????????

        //???????????????????????????
        XSSFCellStyle style1 = workBook.createCellStyle();
        XSSFFont f = workBook.createFont();
        f.setFontHeightInPoints((short) 18);//??????
        f.setFontName("??????_GB2312");//????????????
        f.setBold(true);//??????
        style1.setFont(f);
        style1.setAlignment(HorizontalAlignment.CENTER);//????????????
        style1.setVerticalAlignment(VerticalAlignment.CENTER);//????????????
        style1.setBorderBottom(BorderStyle.THIN);//?????????
        style1.setBorderLeft(BorderStyle.THIN);//?????????
        style1.setBorderTop(BorderStyle.THIN);//?????????
        style1.setBorderRight(BorderStyle.THIN);//?????????
        row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        cell.setCellStyle(style1); //??????????????????????????????


        //???????????????????????????
        XSSFCellStyle style2 = workBook.createCellStyle();
        f = workBook.createFont();
        f.setFontHeightInPoints((short) 12);//??????
        f.setFontName("??????_GB2312");//????????????
        f.setBold(true);//??????
        style2.setFont(f);
        style2.setAlignment(HorizontalAlignment.CENTER);//????????????
        style2.setVerticalAlignment(VerticalAlignment.CENTER);//????????????
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);

        // ?????????,?????????
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("??? ???");
        cell.setCellStyle(style2); //??????????????????????????????

        region = new CellRangeAddress(1, 1, (short) 1, (short) 3);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(1);
        cell.setCellValue(teacher.getInstituteName());
        cell.setCellStyle(style2); //??????????????????????????????

        cell = row.createCell(4);
        cell.setCellValue("?????????");
        cell.setCellStyle(style2); //??????????????????????????????

        region = new CellRangeAddress(1, 1, (short) 4, (short) 5);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(6);
        cell.setCellValue(teacher.getName());
        cell.setCellStyle(style2); //??????????????????????????????
        row.setHeightInPoints(30);


        //?????????????????????
        XSSFCellStyle style3 = workBook.createCellStyle();
        f = workBook.createFont();
        f.setFontHeightInPoints((short) 12);//??????
        f.setFontName("??????_GB2312");//????????????
        style3.setFont(f);
        style3.setWrapText(true);
        style3.setAlignment(HorizontalAlignment.CENTER);//????????????
        style3.setVerticalAlignment(VerticalAlignment.CENTER);//????????????
        style3.setBorderBottom(BorderStyle.THIN);//?????????
        style3.setBorderLeft(BorderStyle.THIN);//?????????
        style3.setBorderRight(BorderStyle.THIN);//?????????
        style3.setBorderTop(BorderStyle.THIN);//?????????


        //??????????????????
        //??????????????????
        String teachingTask = debriefing.getTeachingtask();
        String[] aa = teachingTask.split("/");

        row = sheet.createRow(2);
        region = new CellRangeAddress(2, 2 + aa.length, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(0);
        cell.setCellValue("????????????");
        cell.setCellStyle(style3); //????????????

        cell = row.createCell(1);
        cell.setCellValue("??????");
        cell.setCellStyle(style2);
        region = new CellRangeAddress(2, 2, (short) 2, (short) 6);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(2);
        cell.setCellValue("????????????");
        cell.setCellStyle(style2);
        row.setHeightInPoints(30);

        //??????????????????
        int index = 3;
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);
        }


        //?????????????????????
        String achievementsInScientificResearch = debriefing.getAchievementsinscientificresearch();
        aa = achievementsInScientificResearch.split("/");

        row = sheet.createRow(index);
        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);

        //?????????????????????
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("???????????????");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);
        }


        //????????????????????????
        String otherWork = debriefing.getOtherwork();
        aa = otherWork.split("/");

        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);

        //????????????????????????
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("??????????????????");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);

        }


        //??????????????????
        String winAward = debriefing.getWinaward();
        aa = winAward.split("/");

        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);


        //??????????????????
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("????????????");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);

        }


        String summary = debriefing.getSummary();
        row = sheet.createRow(index);
        cell = row.createCell(0);
        cell.setCellValue("??????????????????(?????????100???)");
        cell.setCellStyle(style3);
        region = new CellRangeAddress(index, index, (short) 1, (short) 6);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(1);
        cell.setCellValue(summary);
        cell.setCellStyle(style3);
        row.setHeightInPoints(150);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msexcel");
        OutputStream output = response.getOutputStream();
        String outputName = teacher.getInstituteName() + year + term + "???????????????";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());

        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".xlsx");


        // ???excel?????????????????????
        BufferedOutputStream outputStream = null;
        outputStream = new BufferedOutputStream(output);
        workBook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workBook.close();

    }


    //??????????????????
    //?????????????????????????????????
    @GetMapping("/sectionProject")
    public String fun10(ModelMap modelMap, Model model, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("teacherInfo");
        List<Project> projects = teacherService.selectTeacherFBProject(teacher.getName());

        List<Integer> selectnum = new ArrayList<Integer>();
        int successNum = 0, num = 0;

        if (projects != null) {
            for (int i = 0; i < projects.size(); i++) {
                num = teacherService.selectedSuccessNum(projects.get(i).getId());
                selectnum.add(num);
                successNum += num;
                if (projects.get(i).getSelectFlag() == 0) projects.get(i).setProjectGB("??????");
                else if (projects.get(i).getSelectFlag() == 1) projects.get(i).setProjectGB("?????????");
            }
        }

        int allNnm = teacherService.selectAllXB(teacher.getId());
        modelMap.addAttribute("Myproject", projects);
        model.addAttribute("number", selectnum);
        model.addAttribute("successNum", successNum);
        model.addAttribute("allNum", allNnm);
        return "teacher/graduation/section_xb/subjectclass";
    }

    // ??????????????????????????????
    @GetMapping("/topicsinfoto")
    public String fun11(ModelMap modelMap, Model model, String projectName) {

        List<Static_student> static_students = staticService.seleStudentbyTJ("", "", projectName);
        Project project = staticService.selectProjectbyName(projectName);
        //????????????????????????????????????
        if(project.getSelectFlag() == 1){
            //??????
            model.addAttribute("flag", 1);
        }else {
            //??????
            model.addAttribute("flag", 0);
        }
        model.addAttribute("projectId",project.getId());
        model.addAttribute("projectName",projectName);
        System.out.println(static_students);
        modelMap.addAttribute("Myproject", static_students);
        return "teacher/graduation/section_xb/topicsinfoto";
    }

    // ??????????????????????????? ???????????????
    @PostMapping("/DK_project")
    @ResponseBody
    public String fun12(String zt, String project_id) {
        int s1 = Integer.parseInt(zt);
        Long s2 = Long.valueOf(project_id);
        teacherService.updateZTprojcet(s1, s2);
        //?????????????????????
        if(s1 == 1){
            teacherService.deleteSelected(s2);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("pd", "" + 1);
        return JSONObject.toJSONString(map);
    }

    // ??????????????????????????????
    @RequestMapping("/QRstudentXB")
    @ResponseBody
    public String fun13(String studet_id, Long project_id, String zt) {
        Long s1 = Long.valueOf(studet_id);
        Long s3 = Long.valueOf(zt);
        teacherService.updateXZstudent(s1, project_id, s3);
//        teacherService.updateselectnum(s3, project_name);
        Map<String, String> map = new HashMap<String, String>();
        map.put("pd", "" + 1);
        return JSONObject.toJSONString(map);
    }

    @GetMapping("/Classindex")
    public String fun14(ModelMap modelMap, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        String institute_name = teacher.getInstituteName();//????????????
        List<Specialty> specialties = staticService.selectSpercialtyByall(institute_name);
        List<Class> classes = staticService.selectClassByall(institute_name);

        modelMap.addAttribute("Spercialtys", specialties);
        modelMap.addAttribute("Classs", classes);
        return "teacher/graduation/classxq/Classxq";
    }

    // ?????? ??????????????? ?????????????????????????????????
    @PostMapping("/SelectAll")
    @ResponseBody
    public List<Static_student> fun15(String Spercialty, String csa) {
        List<Static_student> static_students = staticService.select_studentXT_all(null, null, null, Spercialty, null, csa);
        Collections.sort(static_students, new Comparator<Static_student>() {
            @Override
            public int compare(Static_student o1, Static_student o2) {
                return (Integer.valueOf(o1.getStuNum()) - Integer.valueOf(o2.getStuNum()));
            }
        });

        for (int i = 0; i < static_students.size(); i++) {
            if (static_students.get(i).getStuselectFlag().equals("0")) static_students.get(i).setStuselectFlag("?????????");
            if (static_students.get(i).getStuselectFlag().equals("1")) static_students.get(i).setStuselectFlag("???????????????");
            if (static_students.get(i).getStuselectFlag().equals("2")) static_students.get(i).setStuselectFlag("???????????????");
            if (static_students.get(i).getStuselectFlag().equals("3")) static_students.get(i).setStuselectFlag("????????????");
        }
        return static_students;
    }

    @GetMapping("/czstudentpwd")
    public String fun16() {
        return "teacher/graduation/reStudentpwd";
    }


    // ??????????????? id??????????????????????????????
    @GetMapping("/selectStudent")
    public String getStudent(String username, ModelMap modelMap) {
        modelMap.addAttribute("Students", staticService.selectStudents(username));

        return "teacher/graduation/reStudentpwd";
    }

    // ??????????????????
    @PostMapping("/czpwd")
    @ResponseBody
    public String updetpwd(String username) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("pd", "" + staticService.CZpwd(username));
        return JSONObject.toJSONString(map);
    }

    // ?????????
    //  ?????????????????????
    @RequestMapping("/tandexamine")
    public String fun7(ModelMap modelMap, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        List<Project> list_project = teacherService.selectProjecbySection(teacher.getIdSection());

        for (int i = 0; i < list_project.size(); i++) {
            if (list_project.get(i).getVerifyprojectFlag() == 0) list_project.get(i).setProjectZT("?????????");
            if (list_project.get(i).getVerifyprojectFlag() == 1) list_project.get(i).setProjectZT("???????????????");
            if (list_project.get(i).getVerifyprojectFlag() == 2) list_project.get(i).setProjectZT("????????????");
        }

        List<Teacher> teacherInfo = teacherService.selectTeachers(-1,null, teacher.getIdSection(),
                sectionService.getSection(teacher.getIdSection()).getIdInstitute());
        modelMap.addAttribute("list_teahcer", teacherInfo);
        modelMap.addAttribute("list_project", list_project);
        return "teacher/graduation/tandexamine";
    }

    @Authority(roles = {Role.STUDENT, Role.TEACHER})
    // ????????????
    @RequestMapping(value = "/file_download")
    public void fileDownload(String project_id, String project_Name, HttpServletResponse response) throws IOException {
        Project project = null;
        if (project_id != null && !project_id.equals("null")) {
            Long projectID = Long.valueOf(project_id);
            project = staticService.selectProjectbyid(projectID);
        } else {
            if (project_Name != null && !project_Name.equals("null")) {
                project = staticService.selectProjectbyName(project_Name);
            }
        }

        if (project != null) { // ?????????????????? ???????????????
            String filePath = project.getFilepath();
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            System.out.println();
            String fileName = file.getName();
            System.out.println(fileName + "****");
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentLength(100000);
//
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            String encodeFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
//            headers.setContentDispositionFormData("attachment", encodeFilename);


            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download");
            String outputName = fileName;
            String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename );

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }if (os != null){
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //????????????????????????????????????
    @GetMapping("/condition_teacher")
    @ResponseBody
    public Msg fun10(Long zt, Long id_teacher, HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        List<Project> list_project = staticService.selectByConditionTeacher(teacher.getIdSection(), zt, id_teacher);
        for (int i = 0; i < list_project.size(); i++) {
            if (list_project.get(i).getVerifyprojectFlag() == 0) list_project.get(i).setProjectZT("?????????");
            if (list_project.get(i).getVerifyprojectFlag() == 1) list_project.get(i).setProjectZT("???????????????");
            if (list_project.get(i).getVerifyprojectFlag() == 2) list_project.get(i).setProjectZT("????????????");
        }
        return Msg.success().add("projectList", list_project);
    }


    // ????????????
    @PostMapping("/SH_project")
    @ResponseBody
    public String fun9(String zt, String projectID) {
        int s1 = Integer.parseInt(zt);
        Long s2 = Long.valueOf(projectID);
        teacherService.updateSHprojcet(s1, s2);
        Map<String, String> map = new HashMap<String, String>();
        map.put("pd", "" + 1);
        return JSONObject.toJSONString(map);
    }
    // ??????????????????
    @PostMapping("/SH_project2")
    @ResponseBody
    public Msg fun18(@RequestBody String p){
        ProjectId project_id = JSON.parseObject(p, ProjectId.class);
        if(project_id.getProject_id().length==0){
            return Msg.fail();
        }
        for(int i=0;i<project_id.getProject_id().length;i++){
            int s1 = Integer.parseInt(project_id.getZt());
            Long s2 = Long.valueOf(project_id.getProject_id()[i]);
            teacherService.updateSHprojcet(s1, s2);
        }
        return Msg.success();
    }
    // ??????????????????
    @PostMapping("/reject_project2")
    @ResponseBody
    public Msg fun19(@RequestBody String p){
        ProjectId project_id = JSON.parseObject(p, ProjectId.class);
        if (project_id.getProject_id().length==0){
            return Msg.error();
        }
        System.out.println(project_id.getZt());
        System.out.println(Arrays.toString(project_id.getProject_id()));
        for(int i=0;i<project_id.getProject_id().length;i++){
            Project project = staticService.selectProjectbyid((long)Integer.parseInt(project_id.getProject_id()[i]));
            if(project.getReleaseFlag() == 1 || project.getSelectcount() != 0){
                return Msg.fail();
            }
        }
        for(int i=0;i<project_id.getProject_id().length;i++){
            Project project = staticService.selectProjectbyid((long)Integer.parseInt(project_id.getProject_id()[i]));
            SubjectWithBLOBs subject = new SubjectWithBLOBs();
            subject.setId(project.getId());
            //?????????????????? 0
            subject.setSelectFlag(0);
            if(Integer.parseInt(project_id.getZt())==0){
                subject.setVerifyprojectFlag(1);
            }else{
                subject.setVerifyprojectFlag(0);
            }
            subject.setReleaseFlag(0);
            subjectService.updateSubjectByid(subject);
        }

        return Msg.success();
    }
    //??????
    @GetMapping("/reject_project")
    @ResponseBody
    public Msg fun17(Long project_id){
        Project project = staticService.selectProjectbyid(project_id);
        if(project.getReleaseFlag() == 1 || project.getSelectcount() != 0){
          return Msg.fail();
        }else{
            SubjectWithBLOBs subject = new SubjectWithBLOBs();
            subject.setId(project.getId());

            //?????????????????? 0
            subject.setSelectFlag(0);
            subject.setVerifyprojectFlag(0);
            subject.setReleaseFlag(0);

            subjectService.updateSubjectByid(subject);

            return Msg.success();
        }

    }

}
