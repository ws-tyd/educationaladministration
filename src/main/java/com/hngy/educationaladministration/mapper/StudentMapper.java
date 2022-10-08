package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Static_student;
import com.hngy.educationaladministration.entity.Student;
import com.hngy.educationaladministration.entity.StudentExample;
import com.hngy.educationaladministration.entity.StudentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StudentWithBLOBs record);

    int insertSelective(StudentWithBLOBs record);

    Student selectOneByExample(StudentExample example);

    StudentWithBLOBs selectOneByExampleSelective(@Param("example") StudentExample example, @Param("selective") StudentWithBLOBs.Column ... selective);

    StudentWithBLOBs selectOneByExampleWithBLOBs(StudentExample example);

    List<StudentWithBLOBs> selectByExampleSelective(@Param("example") StudentExample example, @Param("selective") StudentWithBLOBs.Column ... selective);

    List<StudentWithBLOBs> selectByExampleWithBLOBs(StudentExample example);

    List<Student> selectByExample(StudentExample example);

    StudentWithBLOBs selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") StudentWithBLOBs.Column ... selective);

    StudentWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StudentWithBLOBs record, @Param("example") StudentExample example);

    int updateByExampleWithBLOBs(@Param("record") StudentWithBLOBs record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(StudentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(StudentWithBLOBs record);

    int updateByPrimaryKey(Student record);

    int batchInsert(@Param("list") List<StudentWithBLOBs> list);

    int batchInsertSelective(@Param("list") List<StudentWithBLOBs> list, @Param("selective") StudentWithBLOBs.Column ... selective);
//-------------------------------------------------------------------------------------------
    //自建查询
    @Select("select * from t_student where userName = #{username} or stuNum=#{stuNum}")
    Student select_studentbyName(@Param("username") String username, @Param("stuNum") String stuNum);

    List<Static_student> select_Student(@Param("institute_id") Long institute_id, @Param("specialty_id") Long specialty_id, @Param("class_id") Long class_id, @Param("student_id") Long student_id, @Param("name") String name);


    // 根据综合,班级or 班级, 查询 学生；
    public List<Static_student> select_studentXT_all(@Param("section_id") Long section_id, @Param("section_name") String section_name, @Param("specialty_id") Long specialty_id, @Param("specialty_name") String specialty_name, @Param("class_id") Long class_id, @Param("class_name") String class_name);

    Student selectByName(String username);

    int updateBychangepsw(@Param("password") String password, @Param("id") Long id);

    int updateBymodifyinfo(Student student);

    Student selectByid(Long id);

    List<Student> selectByListAll(Long id);

    //根据课题名字查询课题id;
    @Select("SELECT t_project.id FROM t_project WHERE t_project.projectName = #{name}")
    Long select_projectID(@Param("name") String name);

    @Update("UPDATE t_project set selectCount = #{listsize} + 1 where id=#{project_id}")
    void updateNumAdd(@Param("project_id")Long project_id,@Param("listsize")int listsize);

    @Update("UPDATE t_project set selectCount = #{listsize} - 1 where id=#{project_id}")
    void updateNumReduce(@Param("project_id")Long project_id,@Param("listsize")int listsize);
}