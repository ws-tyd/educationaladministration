package com.hngy.educationaladministration.entity;

import lombok.Data;

/**
 * 课题信息pojo
 */
@Data
public class topicsinfo {
    private Long project_id;
    private String marchSpecialty;
    private String teacherNames;
    private String projectName;
    private String projectType;
    private String projectSource;
    private int selectFlag;


}
