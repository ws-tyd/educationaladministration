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

    //查询出差类型
    @GetMapping("/work_type")
    @ResponseBody
    public Msg fun1() {
        List<Workapprovaltype> list = staticService.selectAllWorkApprovalType();
        return Msg.success().add("workInfo", list);
    }

    //提交出差申请
    @PostMapping("/upload_work")
    @ResponseBody
    public Msg fun2(String idType, String worktitle, String beginDate, String endDate, String location,
                    String member, HttpSession httpSession) throws ParseException {
        //获取教师信息
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");

        //字符串转换为时间
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
        workapprovalWithBLOBs.setWorkcontent("待定");
        //0待审核，1审核通过，2审核未通过
        workapprovalWithBLOBs.setAppovalflag(0);
        workapprovalWithBLOBs.setSumbitdate(currentTime);


        int i = staticService.insertWorkapproval(workapprovalWithBLOBs);
        return Msg.success();
    }

    // 审核员
    //  审核员查看审核
    //需要更改，现所查无条件，更改为按教研室或学院
    @GetMapping("/examine")
    @ResponseBody
    public Msg fun3(Integer pn, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        PageHelper.startPage(pn, 9);
        List<WorkapprovalWithBLOBs> list = staticService.selectAllWorkApproval(teacher.getIdSection());
        PageInfo page = new PageInfo(list, 5);
        return Msg.success().add("workInfo", page);
    }

    //审核员审核出差申请
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

    //下载年度专业技术人员考核表
    @GetMapping("/down_technical_personnel")
    public void fun5(Long year, HttpSession httpSession, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");


        response.setCharacterEncoding("utf-8");
        response.setContentType("application/ms-word");
        OutputStream output = response.getOutputStream();
        String outputName = "专业技术人员考核表（一年一张）";
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
        String outputName = "年度考核表（一年一张）";
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

    //下载年度述职表
    @GetMapping("/down_year_debriefing")
    public void fun7(Long year, HttpSession httpSession, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");

        //查询指定年份的年度述职信息
        DebriefingYearWithBLOBs debriefingYear = teacherService.selectYearDebriefingInfo(teacher.getId(), year);


        // 新建工作簿对象
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 创建sheet对象
        XSSFSheet sheet = workBook.createSheet("Sheet1");

        sheet.setColumnWidth(0, 15 * 256);//设置第1列宽

        sheet.setColumnWidth(6, 24 * 256);//设置第1列宽


        //创建行，头标题
        XSSFRow row = sheet.createRow(0);
        CellRangeAddress region = new CellRangeAddress(0, 0, (short) 0, (short) 6);  //合并单元格，
        sheet.addMergedRegion(region);
        //合并单元格边框
        fun8(region, sheet, workBook);

        row.createCell(0).setCellValue("信息工程学院" + year + "年度述职表");
        row.setHeightInPoints(40); //设置行高

        //设置头部单元格样式
        XSSFCellStyle style1 = workBook.createCellStyle();
        XSSFFont f = workBook.createFont();
        f.setFontHeightInPoints((short) 18);//字号
        f.setFontName("仿宋_GB2312");//设置字体
        f.setBold(true);//加粗
        style1.setFont(f);
        style1.setAlignment(HorizontalAlignment.CENTER);//左右居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        style1.setBorderBottom(BorderStyle.THIN);//下边框
        style1.setBorderBottom(BorderStyle.THIN);//下边框
        style1.setBorderLeft(BorderStyle.THIN);//左边框
        style1.setBorderTop(BorderStyle.THIN);//上边框
        style1.setBorderRight(BorderStyle.THIN);//右边框
        row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        cell.setCellStyle(style1); //获取指定行并赋值样式


        //设置标题单元格样式
        XSSFCellStyle style2 = workBook.createCellStyle();
        f = workBook.createFont();
        f.setFontHeightInPoints((short) 12);//字号
        f.setFontName("仿宋_GB2312");//设置字体
        f.setBold(true);//加粗
        style2.setFont(f);
        style2.setAlignment(HorizontalAlignment.CENTER);//左右居中
        style2.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);

        // 创建行,标题行
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("部 门");
        cell.setCellStyle(style2); //获取指定行并赋值样式

        region = new CellRangeAddress(1, 1, (short) 1, (short) 3);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(1);
        cell.setCellValue(teacher.getInstituteName());
        cell.setCellStyle(style2); //获取指定行并赋值样式

        cell = row.createCell(4);
        cell.setCellValue("填报人");
        cell.setCellStyle(style2); //获取指定行并赋值样式

        region = new CellRangeAddress(1, 1, (short) 4, (short) 5);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(6);
        cell.setCellValue(teacher.getName());
        cell.setCellStyle(style2); //获取指定行并赋值样式
        row.setHeightInPoints(30);


        //设置单元格样式
        XSSFCellStyle style3 = workBook.createCellStyle();
        f = workBook.createFont();
        f.setFontHeightInPoints((short) 12);//字号
        f.setFontName("仿宋_GB2312");//设置字体
        style3.setFont(f);
        style3.setWrapText(true);
        style3.setAlignment(HorizontalAlignment.CENTER);//左右居中
        style3.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        style3.setBorderBottom(BorderStyle.THIN);//下边框
        style3.setBorderLeft(BorderStyle.THIN);//左边框
        style3.setBorderRight(BorderStyle.THIN);//右边框
        style3.setBorderTop(BorderStyle.THIN);//上边框


        //创建表格主体
        //获取教学任务
        String teachingTask = debriefingYear.getTeachingtask();
        String[] aa = teachingTask.split("/");

        row = sheet.createRow(2);
        region = new CellRangeAddress(2, 2 + aa.length, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(0);
        cell.setCellValue("教学任务");
        cell.setCellStyle(style3); //赋值样式

        cell = row.createCell(1);
        cell.setCellValue("序号");
        cell.setCellStyle(style2);
        region = new CellRangeAddress(2, 2, (short) 2, (short) 6);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(2);
        cell.setCellValue("具体内容");
        cell.setCellStyle(style2);
        row.setHeightInPoints(30);

        //填充教学任务
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


        //获取科研及成果
        String achievementsInScientificResearch = debriefingYear.getAchievementsinscientificresearch();
        aa = achievementsInScientificResearch.split("/");

        row = sheet.createRow(index);
        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);

        //填充科研及成果
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("科研及成果");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);
        }


        //获取其它方面工作
        String otherWork = debriefingYear.getOtherwork();
        aa = otherWork.split("/");

        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);

        //填充其它方面工作
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("其它方面工作");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);

        }


        //获取获奖情况
        String winAward = debriefingYear.getWinaward();
        aa = winAward.split("/");

        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);


        //填充获奖情况
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("获奖情况");
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
        cell.setCellValue("工作亮点小结(不超过100字)");
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
        String outputName = teacher.getInstituteName() + year + "年度述职表";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());

        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".xlsx");


        // 写excel需要使用输出流
        BufferedOutputStream outputStream = null;
        outputStream = new BufferedOutputStream(output);
        workBook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workBook.close();
    }

    //设置合并单元格边框
    public void fun8(CellRangeAddress region, XSSFSheet sheet, XSSFWorkbook workBook) {

        BorderStyle borderStyle = BorderStyle.THIN;

        RegionUtil.setBorderBottom(borderStyle, region, sheet); // 下边框
        RegionUtil.setBorderLeft(borderStyle, region, sheet); // 左边框
        RegionUtil.setBorderRight(borderStyle, region, sheet); // 右边框
        RegionUtil.setBorderTop(borderStyle, region, sheet); // 上边框
    }

    //下载学期述职表
    @GetMapping("/down_debriefing")
    public void fun9(Long year, String term, HttpSession httpSession, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");

        //查询指定年份的年度,学期述职信息
        DebriefingWithBLOBs debriefing = teacherService.selectTermDebriefingInfo(teacher.getId(), year, term);


        // 新建工作簿对象
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 创建sheet对象
        XSSFSheet sheet = workBook.createSheet("Sheet1");

        sheet.setColumnWidth(0, 15 * 256);//设置第1列宽

        sheet.setColumnWidth(6, 24 * 256);//设置第1列宽


        //创建行，头标题
        XSSFRow row = sheet.createRow(0);
        CellRangeAddress region = new CellRangeAddress(0, 0, (short) 0, (short) 6);  //合并单元格，
        sheet.addMergedRegion(region);
        //合并单元格边框
        fun8(region, sheet, workBook);

        row.createCell(0).setCellValue(teacher.getInstituteName() + year + term + "学期述职表");
        row.setHeightInPoints(40); //设置行高

        //设置头部单元格样式
        XSSFCellStyle style1 = workBook.createCellStyle();
        XSSFFont f = workBook.createFont();
        f.setFontHeightInPoints((short) 18);//字号
        f.setFontName("仿宋_GB2312");//设置字体
        f.setBold(true);//加粗
        style1.setFont(f);
        style1.setAlignment(HorizontalAlignment.CENTER);//左右居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        style1.setBorderBottom(BorderStyle.THIN);//下边框
        style1.setBorderLeft(BorderStyle.THIN);//左边框
        style1.setBorderTop(BorderStyle.THIN);//上边框
        style1.setBorderRight(BorderStyle.THIN);//右边框
        row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        cell.setCellStyle(style1); //获取指定行并赋值样式


        //设置标题单元格样式
        XSSFCellStyle style2 = workBook.createCellStyle();
        f = workBook.createFont();
        f.setFontHeightInPoints((short) 12);//字号
        f.setFontName("仿宋_GB2312");//设置字体
        f.setBold(true);//加粗
        style2.setFont(f);
        style2.setAlignment(HorizontalAlignment.CENTER);//左右居中
        style2.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);

        // 创建行,标题行
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("部 门");
        cell.setCellStyle(style2); //获取指定行并赋值样式

        region = new CellRangeAddress(1, 1, (short) 1, (short) 3);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(1);
        cell.setCellValue(teacher.getInstituteName());
        cell.setCellStyle(style2); //获取指定行并赋值样式

        cell = row.createCell(4);
        cell.setCellValue("填报人");
        cell.setCellStyle(style2); //获取指定行并赋值样式

        region = new CellRangeAddress(1, 1, (short) 4, (short) 5);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(6);
        cell.setCellValue(teacher.getName());
        cell.setCellStyle(style2); //获取指定行并赋值样式
        row.setHeightInPoints(30);


        //设置单元格样式
        XSSFCellStyle style3 = workBook.createCellStyle();
        f = workBook.createFont();
        f.setFontHeightInPoints((short) 12);//字号
        f.setFontName("仿宋_GB2312");//设置字体
        style3.setFont(f);
        style3.setWrapText(true);
        style3.setAlignment(HorizontalAlignment.CENTER);//左右居中
        style3.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        style3.setBorderBottom(BorderStyle.THIN);//下边框
        style3.setBorderLeft(BorderStyle.THIN);//左边框
        style3.setBorderRight(BorderStyle.THIN);//右边框
        style3.setBorderTop(BorderStyle.THIN);//上边框


        //创建表格主体
        //获取教学任务
        String teachingTask = debriefing.getTeachingtask();
        String[] aa = teachingTask.split("/");

        row = sheet.createRow(2);
        region = new CellRangeAddress(2, 2 + aa.length, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(0);
        cell.setCellValue("教学任务");
        cell.setCellStyle(style3); //赋值样式

        cell = row.createCell(1);
        cell.setCellValue("序号");
        cell.setCellStyle(style2);
        region = new CellRangeAddress(2, 2, (short) 2, (short) 6);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);
        cell = row.createCell(2);
        cell.setCellValue("具体内容");
        cell.setCellStyle(style2);
        row.setHeightInPoints(30);

        //填充教学任务
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


        //获取科研及成果
        String achievementsInScientificResearch = debriefing.getAchievementsinscientificresearch();
        aa = achievementsInScientificResearch.split("/");

        row = sheet.createRow(index);
        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);

        //填充科研及成果
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("科研及成果");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);
        }


        //获取其它方面工作
        String otherWork = debriefing.getOtherwork();
        aa = otherWork.split("/");

        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);

        //填充其它方面工作
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("其它方面工作");
            cell.setCellStyle(style3);
            region = new CellRangeAddress(index, index, (short) 2, (short) 6);
            sheet.addMergedRegion(region);
            fun8(region, sheet, workBook);
            cell = row.createCell(2);
            cell.setCellValue(aa[j]);
            cell.setCellStyle(style3);
            row.setHeightInPoints(30);

        }


        //获取获奖情况
        String winAward = debriefing.getWinaward();
        aa = winAward.split("/");

        region = new CellRangeAddress(index, index + aa.length - 1, (short) 0, (short) 0);
        sheet.addMergedRegion(region);
        fun8(region, sheet, workBook);


        //填充获奖情况
        for (int j = 0; j < aa.length; index++, j++) {
            row = sheet.createRow(index);
            cell = row.createCell(1);
            cell.setCellValue(j + 1);
            cell.setCellStyle(style3);
            cell = row.createCell(0);
            cell.setCellValue("获奖情况");
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
        cell.setCellValue("工作亮点小结(不超过100字)");
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
        String outputName = teacher.getInstituteName() + year + term + "学期述职表";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());

        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".xlsx");


        // 写excel需要使用输出流
        BufferedOutputStream outputStream = null;
        outputStream = new BufferedOutputStream(output);
        workBook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workBook.close();

    }


    //毕业设计管理
    //加载教师课题已发布页面
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
                if (projects.get(i).getSelectFlag() == 0) projects.get(i).setProjectGB("可选");
                else if (projects.get(i).getSelectFlag() == 1) projects.get(i).setProjectGB("不可选");
            }
        }

        int allNnm = teacherService.selectAllXB(teacher.getId());
        modelMap.addAttribute("Myproject", projects);
        model.addAttribute("number", selectnum);
        model.addAttribute("successNum", successNum);
        model.addAttribute("allNum", allNnm);
        return "teacher/graduation/section_xb/subjectclass";
    }

    // 加载课题学生选报页面
    @GetMapping("/topicsinfoto")
    public String fun11(ModelMap modelMap, Model model, String projectName) {

        List<Static_student> static_students = staticService.seleStudentbyTJ("", "", projectName);
        Project project = staticService.selectProjectbyName(projectName);
        //判断课题是否属于关闭状态
        if(project.getSelectFlag() == 1){
            //关闭
            model.addAttribute("flag", 1);
        }else {
            //打开
            model.addAttribute("flag", 0);
        }
        model.addAttribute("projectId",project.getId());
        model.addAttribute("projectName",projectName);
        System.out.println(static_students);
        modelMap.addAttribute("Myproject", static_students);
        return "teacher/graduation/section_xb/topicsinfoto";
    }

    // 管理课题状态，是否 关闭与打开
    @PostMapping("/DK_project")
    @ResponseBody
    public String fun12(String zt, String project_id) {
        int s1 = Integer.parseInt(zt);
        Long s2 = Long.valueOf(project_id);
        teacherService.updateZTprojcet(s1, s2);
        //如果是关闭操作
        if(s1 == 1){
            teacherService.deleteSelected(s2);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("pd", "" + 1);
        return JSONObject.toJSONString(map);
    }

    // 确认该学生选报该课题
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
        String institute_name = teacher.getInstituteName();//学院名字
        List<Specialty> specialties = staticService.selectSpercialtyByall(institute_name);
        List<Class> classes = staticService.selectClassByall(institute_name);

        modelMap.addAttribute("Spercialtys", specialties);
        modelMap.addAttribute("Classs", classes);
        return "teacher/graduation/classxq/Classxq";
    }

    // 根据 专业，班级 等信息查询学生选题情况
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
            if (static_students.get(i).getStuselectFlag().equals("0")) static_students.get(i).setStuselectFlag("未选题");
            if (static_students.get(i).getStuselectFlag().equals("1")) static_students.get(i).setStuselectFlag("选题待审核");
            if (static_students.get(i).getStuselectFlag().equals("2")) static_students.get(i).setStuselectFlag("选题未通过");
            if (static_students.get(i).getStuselectFlag().equals("3")) static_students.get(i).setStuselectFlag("选题通过");
        }
        return static_students;
    }

    @GetMapping("/czstudentpwd")
    public String fun16() {
        return "teacher/graduation/reStudentpwd";
    }


    // 通过学生的 id，名字，学号查询学生
    @GetMapping("/selectStudent")
    public String getStudent(String username, ModelMap modelMap) {
        modelMap.addAttribute("Students", staticService.selectStudents(username));

        return "teacher/graduation/reStudentpwd";
    }

    // 重置学生密码
    @PostMapping("/czpwd")
    @ResponseBody
    public String updetpwd(String username) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("pd", "" + staticService.CZpwd(username));
        return JSONObject.toJSONString(map);
    }

    // 审核员
    //  审核员查看审核
    @RequestMapping("/tandexamine")
    public String fun7(ModelMap modelMap, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        List<Project> list_project = teacherService.selectProjecbySection(teacher.getIdSection());

        for (int i = 0; i < list_project.size(); i++) {
            if (list_project.get(i).getVerifyprojectFlag() == 0) list_project.get(i).setProjectZT("未审核");
            if (list_project.get(i).getVerifyprojectFlag() == 1) list_project.get(i).setProjectZT("审核未通过");
            if (list_project.get(i).getVerifyprojectFlag() == 2) list_project.get(i).setProjectZT("审核通过");
        }

        List<Teacher> teacherInfo = teacherService.selectTeachers(-1,null, teacher.getIdSection(),
                sectionService.getSection(teacher.getIdSection()).getIdInstitute());
        modelMap.addAttribute("list_teahcer", teacherInfo);
        modelMap.addAttribute("list_project", list_project);
        return "teacher/graduation/tandexamine";
    }

    @Authority(roles = {Role.STUDENT, Role.TEACHER})
    // 课题下载
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

        if (project != null) { // 如果课题存在 则开始传送
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

    //审核页面按照条件查询课题
    @GetMapping("/condition_teacher")
    @ResponseBody
    public Msg fun10(Long zt, Long id_teacher, HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        List<Project> list_project = staticService.selectByConditionTeacher(teacher.getIdSection(), zt, id_teacher);
        for (int i = 0; i < list_project.size(); i++) {
            if (list_project.get(i).getVerifyprojectFlag() == 0) list_project.get(i).setProjectZT("未审核");
            if (list_project.get(i).getVerifyprojectFlag() == 1) list_project.get(i).setProjectZT("审核未通过");
            if (list_project.get(i).getVerifyprojectFlag() == 2) list_project.get(i).setProjectZT("审核通过");
        }
        return Msg.success().add("projectList", list_project);
    }


    // 审核课题
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
    // 审核多个课题
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
    // 驳回多个课题
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
            //修改后状态置 0
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
    //驳回
    @GetMapping("/reject_project")
    @ResponseBody
    public Msg fun17(Long project_id){
        Project project = staticService.selectProjectbyid(project_id);
        if(project.getReleaseFlag() == 1 || project.getSelectcount() != 0){
          return Msg.fail();
        }else{
            SubjectWithBLOBs subject = new SubjectWithBLOBs();
            subject.setId(project.getId());

            //修改后状态置 0
            subject.setSelectFlag(0);
            subject.setVerifyprojectFlag(0);
            subject.setReleaseFlag(0);

            subjectService.updateSubjectByid(subject);

            return Msg.success();
        }

    }

}
