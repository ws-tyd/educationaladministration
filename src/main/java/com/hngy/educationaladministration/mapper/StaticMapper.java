package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.Class;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ChEN on 2019/1/9.
 */
public interface StaticMapper {

    //查询所有的课题来源
    @Select("select * from t_projectsource")
    List<Projectsource> select_allProjectsource();

    //查询所有的课题类型
    @Select("select * from t_projecttype")
    List<Projecttype> select_allProjecttype();

    //根据课题名字查询课题id;
    @Select("SELECT t_project.id FROM t_project WHERE t_project.projectName = #{name}")
    Long select_projectID(@Param("name") String name);

    // 插入课题
    int insert_project(Project project);

    List<Specialty> select_allSpecialty(Long section);   //查询教研室下的所有专业方向;

    List<Specialty> selectSpercialtyByall(String name);//查询学院下所有专业方向

    List<Class> selectClassByall(String name);//查询学院下所有班级

    List<Static_student> seleStudentbyName(String name);// 根据**名字 查询学生

    List<Static_student> seleStudentbyTJ(@Param("name1") String name1, @Param("name2") String name2, @Param("name3") String name3);// 根据条件 查询学生

}
