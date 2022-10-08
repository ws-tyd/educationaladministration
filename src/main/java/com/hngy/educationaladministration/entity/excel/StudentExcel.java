package com.hngy.educationaladministration.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.hngy.educationaladministration.valid.interfaces.One;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StudentExcel {

    @NumberFormat("#")
    @ExcelProperty(value = "账号", index = 0)
    private String username;

    @NumberFormat("#")
    @ExcelProperty(value = "学号", index = 1)
    private String stunum;

    @ExcelProperty(value = "班级", index = 2)
    private String className;

    @NumberFormat("#")
    @ExcelProperty(value = "姓名", index = 3)
    private String name;

    @ExcelProperty(value = "性别", index = 4)
    private String gender;

//    @ExcelProperty(value = "密码", index = 5)
//    private String pwd;

}