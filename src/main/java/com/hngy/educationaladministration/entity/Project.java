package com.hngy.educationaladministration.entity;

import lombok.Data;

@Data
public class Project {
    private Long id;

    private Long idProjecttype;

    private Long idProjectsource;

    private Long idTeacher;

    private String projectname;

    private String marchspecialty;

    private String filepath;

    private String teachernames;

    private Integer selectcount;

    private Integer selectFlag;

    private Integer verifyprojectFlag;

    private Integer releaseFlag;

    // 自己新建类，方便查询
    private String sourceName;// 课题来源
    private String typeName;// 课题类型
    private String ProjectZT;// 课题状态 0 未审核 1 审核未通过 2 审核通过
    private String ProjectGB; // 0 未关闭 1 已关闭;

    // 生成一览表用的
    private String teacher_name;
    private String section_name;

}
