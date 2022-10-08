package com.hngy.educationaladministration.service.serviceImpl;

import com.alibaba.excel.EasyExcel;
import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.Msg;
import com.hngy.educationaladministration.entity.excel.StudentExcel;
import com.hngy.educationaladministration.entity.excel.SubjectExcel;
import com.hngy.educationaladministration.entity.excel.TeacherExcel;
import com.hngy.educationaladministration.service.ExcelService;
import com.hngy.educationaladministration.utils.StudentExcelUtil;
import com.hngy.educationaladministration.utils.SubjectExcelUtil;
import com.hngy.educationaladministration.utils.TeacherExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class ExcelServiceImpl implements ExcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Autowired
    TeacherExcelUtil teacherExcelUtil;
    @Autowired
    StudentExcelUtil studentExcelUtil;
    @Autowired
    SubjectExcelUtil subjectExcelUtil;

    /**
     * 教师导入
     *
     * @param excelFile
     * @param id_institute
     */
    @Override
    public Msg teacherExcelImport(MultipartFile excelFile, long id_institute) throws IOException {
        if(excelFile==null)
        {
            throw new MyException("文件上传失败");
        }
        String name = excelFile.getOriginalFilename();
        LOGGER.info("Excel文件名{}",name);
        if(
                name.indexOf("xlsx")<0 &&
                        name.indexOf("xls")<0
        ){
            throw new MyException("文件格式有问题！");
        }
        //使用easyExcel
        //给监听器初始化
        teacherExcelUtil.init();
        teacherExcelUtil.setId_institute(id_institute);

        //excel读取
        EasyExcel.read(excelFile.getInputStream(), TeacherExcel.class, teacherExcelUtil).sheet().doRead();

        //如果 出现异常 则显示导入多少教师，和没导入的教师message
        if(teacherExcelUtil.isP())
        {
            return Msg.error(teacherExcelUtil.getExceptionMessage().toString())
                    .add("count",teacherExcelUtil.getCount())
                    .add("countMax",teacherExcelUtil.getCountMax())
                    .add("errorData",teacherExcelUtil.getErrorData());
        }

        return Msg.success();
    }

    /**
     * 教师模板下载
     *
     * @param response
     */
    @Override
    public void teacherExcelDownload(HttpServletResponse response) throws IOException {
        //名字
        String encodeFilename = URLEncoder.encode("教师导入模板", StandardCharsets.UTF_8.toString());

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodeFilename + ".xlsx");

        // 模板样例数据
        ArrayList<TeacherExcel> teacherExcels = new ArrayList<>();
        TeacherExcel teacherExcel = new TeacherExcel();
        teacherExcel.setName("张三");
        teacherExcel.setGender("男");
        teacherExcel.setSectionName("软件教研室");
        teacherExcel.setUsername("430181199903035022");
        teacherExcel.setVerifyFlag("教师");
        teacherExcels.add(teacherExcel);

        // 这里 需要指定写用哪个class去读 TeacherExcel.class
        EasyExcel.write(response.getOutputStream())
                // 这里放入动态头
                .head(TeacherExcelUtil.head()).sheet("模板")
                // table的时候 传入class 并且设置needHead =false
                .table().head(TeacherExcel.class)
                .needHead(Boolean.FALSE)
                .doWrite(teacherExcels);//写入空数据
    }

    /**
     * 学生导入
     *
     * @param excelFile
     * @param id_institute
     */
    @Override
    public Msg studentExcelImport(MultipartFile excelFile, long id_institute) throws IOException {
        System.out.println(1);
        if(excelFile==null)
        {
            throw new MyException("文件上传失败");
        }
        String name = excelFile.getOriginalFilename();
        LOGGER.info("Excel文件名{}",name);
        if(
                name.indexOf("xlsx")<0 &&
                        name.indexOf("xls")<0
        ){
            System.out.println(2);
            throw new MyException("文件格式有问题！");
        }
        //使用easyExcel
        //给监听器初始化
        studentExcelUtil.init();
        studentExcelUtil.setId_institute(id_institute);

        long start = System.currentTimeMillis();
        //excel读取
        EasyExcel.read(excelFile.getInputStream(), StudentExcel.class, studentExcelUtil).sheet().doRead();
        LOGGER.info("用时：{}",System.currentTimeMillis()-start);

        //如果 出现异常 则显示导入多少教师，和没导入的教师message

        if(studentExcelUtil.isP())
        {
            return Msg.error(studentExcelUtil.getExceptionMessage().toString())
                    .add("count",studentExcelUtil.getCount())
                    .add("countMax",studentExcelUtil.getCountMax())
                    .add("errorData",studentExcelUtil.getErrorData());
        }

        return Msg.success();
    }

    /**
     * 学生模板下载
     *
     * @param response
     */
    @Override
    public void studentExcelDownload(HttpServletResponse response) throws IOException {

        //名字
        String encodeFilename = URLEncoder.encode("学生导入模板", StandardCharsets.UTF_8.toString());

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodeFilename + ".xlsx");

        // 模板样例数据
        ArrayList<StudentExcel> studentExcels = new ArrayList<>();
        StudentExcel studentExcel = new StudentExcel();

//        studentExcel.setUsername("4301013123123121");
        studentExcel.setUsername("计软2017-1-1");

        studentExcel.setGender("男");

//         studentExcel.setClassName("云应一班");
        studentExcel.setClassName("计软2017-1");

        studentExcel.setName("李斯");
        studentExcel.setStunum("8");
        studentExcels.add(studentExcel);

        // 这里 需要指定写用哪个class去读 TeacherExcel.class
        EasyExcel.write(response.getOutputStream())
                .sheet("模板")
                // table的时候 传入class 并且设置needHead =false
                .table().head(StudentExcel.class)
                .doWrite(studentExcels);//写入数据

    }

    /**
     * 课题一览表
     *  @param response
     * @param request
     * @param id_institute
     */
    @Override
    public void subjectExcelDownload(HttpServletResponse response, HttpServletRequest request, long id_institute) throws IOException {
        //获取学院名字
        String instituteName = (String) request.getSession().getAttribute("instituteName");
        String nameSuffix = "课题一览表";
        if(id_institute==-1){
            instituteName= "";
            nameSuffix = "课题一览总表" ;
        }
        //名字转码
        String encodeFilename = URLEncoder.encode(instituteName+nameSuffix, StandardCharsets.UTF_8.toString());

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodeFilename + ".xlsx");

        // 这里 需要指定写用哪个class去读
        EasyExcel.write(response.getOutputStream())
                .head(subjectExcelUtil.head())
                .sheet("一览表")
                // table的时候 传入class 并且设置needHead =false
                .table()
                .head(SubjectExcel.class)
                .needHead(Boolean.FALSE)//是否写入头
                .doWrite(subjectExcelUtil.getSubjectExcelList(id_institute));//写入数据
    }
}
