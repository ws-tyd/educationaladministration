package com.hngy.educationaladministration.controller;


import com.hngy.educationaladministration.auth.Authority;
import com.hngy.educationaladministration.auth.Role;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.Class;
import com.hngy.educationaladministration.service.AdminService;
import com.hngy.educationaladministration.service.TeacherService;
import com.hngy.educationaladministration.service.serviceImpl.SpecialtyServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by ChEN on 2019/3/29.
 */
@Authority(roles = {Role.TEACHER})
@SessionAttributes(value = {"admin"})
@RequestMapping("/excel")
@Controller
public class ExcelController {
    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/sss")
    public String fun1() {
        return "sss";
    }


    // 以学院生成 课题一览表;
//    @RequestMapping(value = "/XYKTYLB")
//    @ResponseBody
//    public void export(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Long Idinstitute = AdminController.getIdInstitute(modelMap);
//        List<Institute> institutes = adminService.select_institute(Idinstitute, null);
//        List<Project> projects = adminService.select_project(Idinstitute, null, null);
//
//        //创建工作簿
//        XSSFWorkbook wb = new XSSFWorkbook();
//        //创建一个sheet
//        XSSFSheet sheet = wb.createSheet();
//
//        // 创建单元格样式
//        XSSFCellStyle style1 = wb.createCellStyle();
//        style1.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
//        style1.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
//        style1.setBorderBottom(BorderStyle.THIN); //底边框加黑
//        style1.setBorderLeft(BorderStyle.THIN);  //左边框加黑
//        style1.setBorderRight(BorderStyle.THIN); // 有边框加黑
//        style1.setBorderTop(BorderStyle.THIN); //上边框加黑
//
//        XSSFFont font = wb.createFont();
//        font.setFontName("宋体");
//        font.setBold(true);//粗体显示
//        font.setFontHeightInPoints((short) 16);
//
//        style1.setFont(font);
//
//        XSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
//        style.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
//        style.setBorderBottom(BorderStyle.THIN); //底边框加黑
//        style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
//        style.setBorderRight(BorderStyle.THIN); // 有边框加黑
//        style.setBorderTop(BorderStyle.THIN); //上边框加黑
//
//        XSSFFont font1 = wb.createFont();
//        font1.setFontName("宋体");
//        font1.setFontHeightInPoints((short) 10.5);//设置字体大小
//        style.setFont(font1);
////        为单元格添加背景样式
//        for (int i = 1; i < projects.size() + 3; i++) { //需要6行表格
//            Row row = sheet.createRow(i); //创建行
//            for (int j = 0; j < 7; j++) {//需要6列
//                row.createCell(j).setCellStyle(style);
//            }
//        }
//        //合并单元格
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格，cellRangAddress四个参数，第一个起始行，第二终止行，第三个起始列，第四个终止列
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
//        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));
//        //设置标题栏
//        XSSFRow row = sheet.createRow(0); //获取第一行
//        row.createCell(0).setCellStyle(style1);
//        if (Idinstitute != null) {
//            row.getCell(0).setCellValue("湖南工业职业技术学院 " + institutes.get(0).getInstituteName() + " 毕业设计课题汇总表"); //在第一行中创建一个单元格并赋值
//        } else {
//            row.getCell(0).setCellValue("湖南工业职业技术学院全院毕业设计课题汇总表");
//        }
//        XSSFRow row1 = sheet.getRow(1); //获取第二行，为每一列添加字段
//        row1.getCell(0).setCellValue("序号");
//        row1.getCell(1).setCellValue("专业");
//        row1.getCell(2).setCellValue("指导老师");
//        row1.getCell(3).setCellValue("课   题");
//        row1.getCell(6).setCellValue("报选人数");
//        XSSFRow row2 = sheet.getRow(2); //获取第三行
//        row2.getCell(3).setCellValue("课题名称");
//        row2.getCell(4).setCellValue("课题类型");
//        row2.getCell(5).setCellValue("课题来源");
//
//        int hang = 3;
//        //导入数据
//        for (int j = 0; j < projects.size(); j++, hang++) {
//            XSSFRow rowxx = sheet.getRow(hang);
//            rowxx.getCell(0).setCellValue(hang - 2);
//            rowxx.getCell(1).setCellValue(projects.get(j).getSection_name());
//            rowxx.getCell(2).setCellValue(projects.get(j).getTeachernames());
//            rowxx.getCell(3).setCellValue(projects.get(j).getProjectname());
//            rowxx.getCell(4).setCellValue(projects.get(j).getTypeName());
//            rowxx.getCell(5).setCellValue(projects.get(j).getSourceName());
//            rowxx.getCell(6).setCellValue(" " + projects.get(j).getSelectcount());
//        }
//
//        // 自动列宽
//        sheet.setDefaultRowHeight((short) (2 * 256));
//        setSizeColumn(sheet, 7);
//
//        String filename = "课题一览表.xlsx";
//        if (Idinstitute != null && institutes != null && institutes.size() > 0) filename = institutes.get(0).getInstituteName() + "课题一览表.xlsx";
//        filename = ExcelImportUtils.filenameEncoding(filename, request);
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        OutputStream os = response.getOutputStream();
//        response.setHeader("Content-disposition", "attachment;filename=" + filename);//默认Excel名称
//        wb.write(os);
//        os.flush();
//        os.close();
//    }

    // 自适应宽度(中文支持)
    private void setSizeColumn(XSSFSheet sheet, int size) {
        for (int columnNum = 1; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(columnNum) != null) {
                    XSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, (columnWidth + 4) * 256);
        }
    }

    // 以专业生成 课题一览表;
    @RequestMapping(value = "/ZYKTYLB")
    public void export2(Long section_id, String section_name, Long specialty_id, String specialty_name, HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Specialty> specialties = adminService.select_specialty(null, null, specialty_id, specialty_name);
        List<Static_student> static_students = adminService.select_studentXT_all(null, null, specialty_id, specialty_name, null, null);

        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一个sheet
        XSSFSheet sheet = wb.createSheet();

        // 创建单元格样式
        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
        style1.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style1.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style1.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style1.setBorderTop(BorderStyle.THIN); //上边框加黑

        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setBold(true);//粗体显示
        font.setFontHeightInPoints((short) 16);

        style1.setFont(font);

        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
        style.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style.setBorderTop(BorderStyle.THIN); //上边框加黑
        XSSFFont font1 = wb.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 10.5);//设置字体大小
        style.setFont(font1);

//        为单元格添加背景样式
        for (int i = 1; i < static_students.size() + 2; i++) { //需要6行表格
            Row row = sheet.createRow(i); //创建行
            for (int j = 0; j < 8; j++) {//需要6列
                row.createCell(j).setCellStyle(style);
            }
        }
        //合并单元格s
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格，cellRangAddress四个参数，第一个起始行，第二终止行，第三个起始列，第四个终止列
        //设置标题栏
        XSSFRow row = sheet.createRow(0); //创建第一行
        row.createCell(0).setCellStyle(style1);

        row.getCell(0).setCellValue("信息工程学院" + specialties.get(0).getSpecialtyName() + "专业" + (LocalDate.now().getYear()-2)+"级学生毕业设计课题一览表"); //在第一行中创建一个单元格并赋值
        XSSFRow row1 = sheet.getRow(1); //获取第二行，为每一列添加字段
        row1.getCell(0).setCellValue("序号");
        row1.getCell(1).setCellValue("班级");
        row1.getCell(2).setCellValue("学号");
        row1.getCell(3).setCellValue("姓名");
        row1.getCell(4).setCellValue("性别");
        row1.getCell(5).setCellValue("课题名称");
        row1.getCell(6).setCellValue("指导教师");
        row1.getCell(7).setCellValue("备注");

        int hang = 2;
        //导入数据
        for (int j = 0; j < static_students.size(); j++, hang++) {
            XSSFRow rowxx = sheet.getRow(hang);
            rowxx.getCell(0).setCellValue(hang - 1);
            rowxx.getCell(1).setCellValue(static_students.get(j).getClass_name());
            rowxx.getCell(2).setCellValue(static_students.get(j).getStuNum());
            rowxx.getCell(3).setCellValue(static_students.get(j).getName());
            rowxx.getCell(4).setCellValue(static_students.get(j).getGender());
            rowxx.getCell(5).setCellValue(static_students.get(j).getProjectname());
            rowxx.getCell(6).setCellValue(static_students.get(j).getTeachernames());
            rowxx.getCell(7).setCellValue(" ");
        }

        // 自动列宽
        sheet.setDefaultRowHeight((short) (2 * 256));
        setSizeColumn(sheet, 8);
        String filename = "课题一览表";
        if (specialties != null && specialties.size() > 0)
            filename = specialties.get(0).getSpecialtyName() + "专业课题一览表";
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msexcel");
        OutputStream os = response.getOutputStream();
        String encodeFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".xlsx");
        wb.write(os);
        os.flush();
        os.close();
    }

    // 以班级生成 课题一览表;
    @RequestMapping(value = "/classKTYLB")
//    @ResponseBody
    public void export1(Long class_id, String class_name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Class> classes = adminService.select_class(null, null, null, class_id, class_name);
        List<Static_student> static_students = adminService.select_studentXT_all(null, null, null, null, class_id, class_name);
        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一个sheet
        XSSFSheet sheet = wb.createSheet();

        // 创建单元格样式
        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
        style1.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style1.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style1.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style1.setBorderTop(BorderStyle.THIN); //上边框加黑

        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setBold(true);//粗体显示
        font.setFontHeightInPoints((short) 16);

        style1.setFont(font);

        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
        style.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style.setBorderTop(BorderStyle.THIN); //上边框加黑
        XSSFFont font1 = wb.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 10.5);//设置字体大小
        style.setFont(font1);

//        为单元格添加背景样式
        for (int i = 1; i < static_students.size() + 2; i++) { //需要6行表格
            Row row = sheet.createRow(i); //创建行
            for (int j = 0; j < 8; j++) {//需要6列
                row.createCell(j).setCellStyle(style);
            }
        }
        //合并单元格s
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格，cellRangAddress四个参数，第一个起始行，第二终止行，第三个起始列，第四个终止列
        //设置标题栏
        XSSFRow row = sheet.createRow(0); //创建第一行
        row.createCell(0).setCellStyle(style1);
        row.getCell(0).setCellValue(classes.get(0).getInstitute_name() + classes.get(0).getSpecialty_name() + " 专业" + classes.get(0).getClassName() + "班学生毕业设计课题一览表"); //在第一行中创建一个单元格并赋值
        XSSFRow row1 = sheet.getRow(1); //获取第二行，为每一列添加字段
        row1.getCell(0).setCellValue("序号");
        row1.getCell(1).setCellValue("班级");
        row1.getCell(2).setCellValue("学号");
        row1.getCell(3).setCellValue("姓名");
        row1.getCell(4).setCellValue("性别");
        row1.getCell(5).setCellValue("课题名称");
        row1.getCell(6).setCellValue("指导教师");
        row1.getCell(7).setCellValue("备注");

        int hang = 2;
        //导入数据
        for (int j = 0; j < static_students.size(); j++, hang++) {
            XSSFRow rowxx = sheet.getRow(hang);
            rowxx.getCell(0).setCellValue(hang - 1);
            rowxx.getCell(1).setCellValue(static_students.get(j).getClass_name());
            rowxx.getCell(2).setCellValue(static_students.get(j).getStuNum());
            rowxx.getCell(3).setCellValue(static_students.get(j).getName());
            rowxx.getCell(4).setCellValue(static_students.get(j).getGender());
            rowxx.getCell(5).setCellValue(static_students.get(j).getProjectname());
            rowxx.getCell(6).setCellValue(static_students.get(j).getTeachernames());
            rowxx.getCell(7).setCellValue(" ");
        }

        // 自动列宽
        sheet.setDefaultRowHeight((short) (2 * 256));
        setSizeColumn(sheet, 8);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msexcel");
        OutputStream os = response.getOutputStream();
        String outputName = "课题一览表";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".xlsx");

        wb.write(os);
        os.flush();
        os.close();


    }


    //生成毕业设计课题审查汇总表
    @RequestMapping("/BYSJSCZB")
    public void fun2(HttpSession httpSession, HttpServletResponse response) throws IOException {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        List<Project> projects = teacherService.selecSectionProject(teacher.getSectionName());
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getVerifyprojectFlag() == 0) projects.get(i).setProjectZT("未审核");
            else if (projects.get(i).getVerifyprojectFlag() == 1) projects.get(i).setProjectZT("审核未通过");
            else projects.get(i).setProjectZT("审核通过");
        }

        // 新建工作簿对象
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建sheet对象
        XSSFSheet sheet = wb.createSheet("Sheet1");

        // 创建单元格样式
        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
        style1.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style1.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style1.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style1.setBorderTop(BorderStyle.THIN); //上边框加黑

        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setBold(true);//粗体显示
        font.setFontHeightInPoints((short) 16);

        style1.setFont(font);

        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
        style.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style.setBorderTop(BorderStyle.THIN); //上边框加黑
        XSSFFont font1 = wb.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 12);//设置字体大小
        style.setFont(font1);

//        为单元格添加背景样式
        for (int i = 1; i < projects.size() + 2; i++) { //需要6行表格
            Row row = sheet.createRow(i); //创建行
            for (int j = 0; j < 8; j++) {//需要6列
                row.createCell(j).setCellStyle(style);
            }
        }

        //设置头部单元格样式
        style1 = wb.createCellStyle();
        XSSFFont f = wb.createFont();
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


        CellRangeAddress region = new CellRangeAddress(0, 0, (short) 0, (short) 7);
        sheet.addMergedRegion(region);
        fun8(region, sheet, wb);
        XSSFRow row = sheet.getRow(0);
        row.createCell(0).setCellValue(teacher.getInstituteName() + teacher.getSectionName() + "毕业设计课题审查汇总表");
        row.setHeightInPoints(40); //设置行高
        XSSFCell cell = row.getCell(0);
        cell.setCellStyle(style1); //获取指定行并赋值样式

        XSSFRow row1 = sheet.getRow(1); //获取第二行，为每一列添加字段
        row1.getCell(0).setCellValue("序号");
        row1.getCell(1).setCellValue("课题名称");
        row1.getCell(2).setCellValue("课题类型");
        row1.getCell(3).setCellValue("课题来源");
        row1.getCell(4).setCellValue("适用专业");
        row1.getCell(5).setCellValue("知道教师");
        row1.getCell(6).setCellValue("当前状态");
        row1.getCell(7).setCellValue("备注");


        int hang = 2;
        //导入数据
        for (int j = 0; j < projects.size(); j++, hang++) {
            XSSFRow rowxx = sheet.getRow(hang);
            rowxx.getCell(0).setCellValue(hang - 1);
            rowxx.getCell(1).setCellValue(projects.get(j).getProjectname());
            rowxx.getCell(2).setCellValue(projects.get(j).getSourceName());
            rowxx.getCell(3).setCellValue(projects.get(j).getTypeName());
            rowxx.getCell(4).setCellValue(projects.get(j).getMarchspecialty());
            rowxx.getCell(5).setCellValue(projects.get(j).getTeachernames());
            rowxx.getCell(6).setCellValue(projects.get(j).getProjectZT());
            rowxx.getCell(7).setCellValue(" ");
        }

        // 自动列宽
        sheet.setDefaultRowHeight((short) (2 * 256));
        setSizeColumn(sheet, 8);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msexcel");
        OutputStream os = response.getOutputStream();
        String outputName = "毕业设计课题审查汇总表";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".xlsx");

        wb.write(os);
        os.flush();
        os.close();
    }


    //生成课题选报成功学生一览表
    @RequestMapping("/projectXSYLB/{projectId}")
    public void project_students_excel(@PathVariable Long projectId, String projectName, HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Student> students = teacherService.selectProjectStudents(projectId);
        List<Long> classIds = new ArrayList<>();
        Map<Long, Class> ClassMap = new HashMap<>();
        if(students!=null){
            students.forEach(student -> {
                classIds.add(student.getIdClass());
            });
            ClassMap = teacherService.selectClassIds(classIds);
        }



        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一个sheet
        XSSFSheet sheet = wb.createSheet();

        // 创建单元格样式
        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
        style1.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style1.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style1.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style1.setBorderTop(BorderStyle.THIN); //上边框加黑

        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setBold(true);//粗体显示
        font.setFontHeightInPoints((short) 16);

        style1.setFont(font);

        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //文字水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//文字垂直居中
        style.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style.setBorderTop(BorderStyle.THIN); //上边框加黑
        XSSFFont font1 = wb.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 10.5);//设置字体大小
        style.setFont(font1);

//        为单元格添加背景样式
        for (int i = 1;i < (students!=null?students.size():0) + 2; i++) {
            Row row = sheet.createRow(i); //创建行
            for (int j = 0; j < 8; j++) {//需要6列
                row.createCell(j).setCellStyle(style);
            }
        }
        //合并单元格s
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格，cellRangAddress四个参数，第一个起始行，第二终止行，第三个起始列，第四个终止列
        //设置标题栏
        XSSFRow row = sheet.createRow(0); //创建第一行
        row.createCell(0).setCellStyle(style1);
        row.getCell(0).setCellValue("选报 "+projectName+" 课题学生一览表"); //在第一行中创建一个单元格并赋值
        XSSFRow row1 = sheet.getRow(1); //获取第二行，为每一列添加字段
        row1.getCell(0).setCellValue("序号");
        row1.getCell(1).setCellValue("班级");
        row1.getCell(2).setCellValue("学号");
        row1.getCell(3).setCellValue("姓名");
        row1.getCell(4).setCellValue("性别");
        row1.getCell(5).setCellValue("课题名称");
        row1.getCell(6).setCellValue("备注");

        int hang = 2;
        //导入数据
        for (int j = 0; students!=null&&j < students.size(); j++, hang++) {
            XSSFRow rowxx = sheet.getRow(hang);
            rowxx.getCell(0).setCellValue(hang - 1);
            rowxx.getCell(1).setCellValue(ClassMap.get(students.get(j).getIdClass()).getClassName());
            rowxx.getCell(2).setCellValue(students.get(j).getStunum());
            rowxx.getCell(3).setCellValue(students.get(j).getName());
            rowxx.getCell(4).setCellValue(students.get(j).getGender());
            rowxx.getCell(5).setCellValue(projectName);
            rowxx.getCell(6).setCellValue(" ");
        }

        // 自动列宽
        sheet.setDefaultRowHeight((short) (2 * 256));
        setSizeColumn(sheet, 8);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msexcel");
        OutputStream os = response.getOutputStream();
        String outputName = "学生一览表";
        String encodeFilename = URLEncoder.encode(outputName, StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment; filename=" + encodeFilename + ".xlsx");

        wb.write(os);
        os.flush();
        os.close();
    }


    //设置合并单元格边框
    public void fun8(CellRangeAddress region, XSSFSheet sheet, XSSFWorkbook workBook) {

        BorderStyle borderStyle = BorderStyle.THIN;

        RegionUtil.setBorderBottom(borderStyle, region, sheet); // 下边框
        RegionUtil.setBorderLeft(borderStyle, region, sheet); // 左边框
        RegionUtil.setBorderRight(borderStyle, region, sheet); // 右边框
        RegionUtil.setBorderTop(borderStyle, region, sheet); // 上边框
    }

}
