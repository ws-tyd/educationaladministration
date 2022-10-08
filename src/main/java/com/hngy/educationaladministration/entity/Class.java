package com.hngy.educationaladministration.entity;

import lombok.Data;

@Data
public class Class {
    private Long id;

    private String className;

    private Long idSpecialty;

    private String remark;

    // 自建类
    private String institute_name;// 学院名字
    private String section_name; // 教研室名字
    private String specialty_name; // 专业方向名字


}
