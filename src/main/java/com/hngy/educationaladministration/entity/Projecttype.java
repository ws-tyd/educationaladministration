package com.hngy.educationaladministration.entity;

import lombok.Data;

import java.util.List;
@Data
public class Projecttype {
    private Long id;

    private String typename;

    private String remark;

    // 自己新建
    private List<Project> projects;// 这个课题类型下有多少课题集合


}
