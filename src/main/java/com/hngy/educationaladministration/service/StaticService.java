package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.Class;

import java.util.List;

public interface StaticService {

    List<Workapprovaltype> selectAllWorkApprovalType();//查询出差类型

    int insertWorkapproval(WorkapprovalWithBLOBs workapprovalWithBLOBs);

    List<WorkapprovalWithBLOBs> selectAllWorkApproval(Long section_id);

    void updateShWorkApproval(WorkapprovalWithBLOBs workapprovalWithBLOBs);

    List<Static_student> seleStudentbyTJ(String name1, String name2, String name3);// 根据 专业，班级，课题名字 等条件查询学生选题情况

    List<Specialty> selectSpercialtyByall(String name);//查询学院下所有专业方向

    List<Class> selectClassByall(String name);//查询学院下所有班级

    // 根据 综合条件 ，专业生成 学生选题一览表；
    List<Static_student> select_studentXT_all(Long section_id, String section_name, Long specialty_id, String specialty_name, Long class_id, String class_name);

    List<Static_student> selectStudents(String name);// 通过学生的 登入名 或 名字 或 学号查询学生

    int CZpwd(String username);// 通过学生的学号 重置密码

    Project selectProjectbyid(Long project_id); // 通过课题的id查询课题

    Project selectProjectbyName(String project_Name);// 通过课题的名字查询课题

    List<Project> selectByConditionTeacher(Long section_id, Long zt, Long id_teacher);//按条件查询教师课题
}
