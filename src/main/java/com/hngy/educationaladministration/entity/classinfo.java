package com.hngy.educationaladministration.entity;

import lombok.Data;

/**
 * 保存班级选题信息pojo
 */

@Data
public class classinfo {

    private Long id;
    private String className;
    private String stuNum;
    private String name;
    private String projectName;
    private String teacherNames;
    private Long stuSelectFlag;


}
