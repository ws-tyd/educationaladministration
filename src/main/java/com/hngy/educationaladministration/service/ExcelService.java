package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.entity.Msg;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelService {

    /**
     * 教师导入
     */

   Msg teacherExcelImport(MultipartFile excelFile,long id_institute) throws IOException;


    /**
     * 教师模板下载
     */

    void teacherExcelDownload(HttpServletResponse response) throws IOException;

    /**
     * 学生导入
     */

    Msg studentExcelImport(MultipartFile excelFile,long id_institute) throws IOException;

    /**
     * 学生模板下载
     */

    void studentExcelDownload(HttpServletResponse response) throws IOException;

    /**
     * 课题一览表
     */

    void subjectExcelDownload(HttpServletResponse response, HttpServletRequest request, long id_institute) throws IOException;

}
