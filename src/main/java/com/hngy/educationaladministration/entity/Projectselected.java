package com.hngy.educationaladministration.entity;

import lombok.Data;

@Data
public class Projectselected {
    private Long id;

    private Long idStudent;

    private Long idProject;

    private Long stuselectFlag;

    private String remark;
    //自建集合
    private String student_stuNum;
    private String project_name;
    private String project_teachers;

    private Student student; // 那个学生选择的
    private Project project; // 属于那个课题


}
