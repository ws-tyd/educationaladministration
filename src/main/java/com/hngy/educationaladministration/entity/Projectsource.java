package com.hngy.educationaladministration.entity;

import lombok.Data;

import java.util.List;
@Data
public class Projectsource {
    private Long id;

    private String sourcename;

    private String remark;

    // 自己新建的
    private List<Project> projects;// 这个来源有多少课题集合


}
