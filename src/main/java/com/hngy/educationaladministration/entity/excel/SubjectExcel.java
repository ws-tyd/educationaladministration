package com.hngy.educationaladministration.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.hngy.educationaladministration.valid.interfaces.Add;
import com.hngy.educationaladministration.valid.interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SubjectExcel {

//    @ExcelProperty(index = 0)
    private Long id;

    @ExcelProperty(index = 1)
    private String marchSpecialty;

    @ExcelProperty(index = 2)
    private String teacherNames;

    @ExcelProperty(index = 3)
    private String projectName;

    @ExcelProperty(index = 4)
    private String projectType;

    @ExcelProperty(index = 5)
    private String projectSource;

    @ExcelProperty(index = 6)
    private Integer selectCount;

}