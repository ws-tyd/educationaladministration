package com.hngy.educationaladministration.entity;

import lombok.Data;

@Data
public class Static_student {
    private Long id;//学生id
    private String userName; // 学生登入账号
    private String stuNum;// 学号 计软S2016-3-40
    private String name;// 学生名字
    private String gender;// 性别
    private String pwd; // 密码
    private String class_name; // 班级名称
    private String specialty_name; // 专业方向名称
    private String section_name; // 教研室名称
    private String institute_name; // 学院名称
    private String projectname;// 选择的课题名称
    private String projectid;// 课题 id
    private String teachernames="";// 课题的老师名字
    private String filepath; // 课题路径
    private String stuselectFlag = "未选题"; // 当前状态 0 未选题，1 选题待审核，2 选题未通过，3 选题通过



}
