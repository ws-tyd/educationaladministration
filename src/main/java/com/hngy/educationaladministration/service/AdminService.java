package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.entity.Admin;
import com.hngy.educationaladministration.entity.Msg;
import com.hngy.educationaladministration.entity.Sadmin;

import javax.servlet.http.HttpSession;
import java.util.List;
import com.hngy.educationaladministration.entity.Static_student;
import com.hngy.educationaladministration.entity.Subject;
import com.hngy.educationaladministration.entity.Subjectselected;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.Class;


public interface AdminService {

    Msg login(String name, String pwd , HttpSession httpSession);

    Admin selectByName(String name);

    Sadmin selectSadminByName(String name);

    int updatePwdByUserName(String name,String pwd);

    List selectAdmins(Long offset);


    /**
     *  ----------
     */

    int updateSadminPwdByUserName(String name,String pwd);

    int addAdmin(Admin admin);

    int delAdmin(Admin admin);

    int updateAdmin(Admin admin);

    // 学生的增删改查
    public int add_student(String userName, String stuNum, Long id_class, String name, String gender, String pwd);

    public int delete_student(Long student_id);

    public int update_student(Long student_id, String userName, String stuNum, Long id_class, String name, String gender, String pwd);

    public List<Static_student> select_student(Long institute_id, Long specialty_id, Long class_id, Long student_id, String name);

    // 通过学生id 或者 课题id 查 课题的选择 情况
    public List<Subjectselected> select_Projectselected(Long student_id, Long project_id);

    // 已发布课题查询
    public List<Subject> select_ProjectXQ(Long institute_id, Long section_id, String name);
    // 班级的增删改查
    int add_class(Long specialty_id, String class_name);

    int delete_class(Long class_id);

    int update_class(Long class_id, Long specialty_id, String class_name);

    List<Class> select_class(Long institute_id, Long section_id, Long specialty_id, Long class_id, String class_name);// 根据学院id 或者 教研室id  或者 专业方向_id 查询所有班级

    // 根据学院生成 以专业为前提 课题一览表
    List<Project> select_project(Long institute_id, Long section_id, String section_name);

    // 根据 综合条件 ，专业生成 学生选题一览表；
    List<Static_student> select_studentXT_all(Long section_id, String section_name, Long specialty_id, String specialty_name, Long class_id, String class_name);

    // 学院的增删改查
//    int add_institute(String institute_name);
//
//    int delete_institute(Long institute_id);
//
//    int update_institute(Long institute_id, String institute_name);

    List<Institute> select_institute(Long institute_id, String institute_name);


    // 专业方向的增删改查
    int add_specialty(Long section_id, String specialty_name);

    int delete_specialty(Long specialty_id);

    int update_specialty(Long specialty_id, Long section_id, String specialty_name);

    List<Specialty> select_specialty(Long institute_id, Long section_id, Long specialty_id, String specialty_name); // 根据学院id 或者 教研室id 查所有专业方向


}

