package com.hngy.educationaladministration.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.hngy.educationaladministration.valid.interfaces.One;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TeacherExcel  {

    @NumberFormat("#")
    @ExcelProperty(index = 0)
    private String username;

    @NumberFormat("#")
    @ExcelProperty(index = 1)
    private String name;

//    @NumberFormat("#")
//    @ExcelProperty(index = 2)
//    private String pwd;

    @ExcelProperty(index = 2)
    private String gender;

    @ExcelProperty(index = 3)
    private String sectionName;

    @ExcelProperty(index = 4)
    private String verifyFlag;


}
