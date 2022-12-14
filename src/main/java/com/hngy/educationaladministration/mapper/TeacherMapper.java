package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.*;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TeacherMapper {
    long countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TeacherWithBLOBs record);

    int insertSelective(TeacherWithBLOBs record);

    Teacher selectOneByExample(TeacherExample example);

    TeacherWithBLOBs selectOneByExampleSelective(@Param("example") TeacherExample example, @Param("selective") TeacherWithBLOBs.Column... selective);

    TeacherWithBLOBs selectOneByExampleWithBLOBs(TeacherExample example);

    List<TeacherWithBLOBs> selectByExampleSelective(@Param("example") TeacherExample example, @Param("selective") TeacherWithBLOBs.Column... selective);

    List<TeacherWithBLOBs> selectByExampleWithBLOBs(TeacherExample example);

    List<Teacher> selectByExample(TeacherExample example);

    TeacherWithBLOBs selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TeacherWithBLOBs.Column... selective);

    TeacherWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TeacherWithBLOBs record, @Param("example") TeacherExample example);

    int updateByExampleWithBLOBs(@Param("record") TeacherWithBLOBs record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(TeacherWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TeacherWithBLOBs record);

    int updateByPrimaryKey(Teacher record);

    int batchInsert(@Param("list") List<TeacherWithBLOBs> list);

    int batchInsertSelective(@Param("list") List<TeacherWithBLOBs> list, @Param("selective") TeacherWithBLOBs.Column... selective);

    TeacherWithBLOBs selectTeacherInfo(String username);

    List<Project> selectTeacherProject(String name);// ?????????????????????????????????

    @Update("UPDATE t_project SET release_flag = #{pd} WHERE id = #{project_id} ")
    void updateProjectFB(@Param("project_id") Long project_id, @Param("pd") int pd);// ??????????????? ?????? ??????????????????

    List<Project> selecSectionProject(String name); // ???????????????????????????

    List<Project> selectTeacherFBProject(String name);// ??????????????????????????????????????????

    // ?????? or ?????? ??????
    @Update("UPDATE t_project set select_flag=#{zt} where id=#{project_id}")
    void update_projectZT(@Param("zt") int zt, @Param("project_id") Long project_id);

    // ????????????id ?????????????????????????????????????????? ???????????????()
    @Update("UPDATE t_projectselected SET stuSelect_flag =2 WHERE stuSelect_flag != 3 and id_project =#{project_id}")
    void updateWXstudent(@Param("project_id") Long project_id);

    // ??????????????????id???????????????????????????
    @Select("SELECT * FROM t_projectselected WHERE id_project = #{project_id} AND stuSelect_flag=3")
    List<Projectselected> select_XZstudent(@Param("project_id") Long project_id);

    // ?????????????????????????????????
    @Update("UPDATE t_project set selectCount=#{count} where id=#{project_id}")
    void update_projectCount(@Param("count") int count, @Param("project_id") Long project_id);

    //  ???????????????id ???????????? ???????????????
    @Update("UPDATE t_projectselected SET stuSelect_flag =#{zt} WHERE id_student = #{student_id} and id_project = #{project_id} ")
    void updateXZstudent(@Param("student_id") Long student_id, @Param("project_id") Long project_id, @Param("zt") Long zt);

    List<Project> select_allproject(Long project_id); // ????????????id ?????????????????????????????????

    // ?????? or ?????? ??????
    @Update("UPDATE t_project set verifyProject_flag=#{zt} where id=#{project_id}")
    void update_projectSHZT(@Param("zt") int zt, @Param("project_id") Long project_id);

    @Delete("delete from t_projectselected where id_project = #{project_id} and stuSelect_flag != 3")
    void deleteSelected(Long project_id);

    @Delete("delete from t_projectselected where id_project = #{project_id}")
    void deleteSelectedAll(Long project_id);

    @Select("SELECT COUNT(*) FROM t_projectselected where id_project = #{project_id} and stuSelect_flag = 3 ")
    Integer selectSuccessNum(Long project_id);

    @Select("select IFNULL(sum(selectCount),0) from t_teacher, t_project where t_teacher.id = t_project.id_teacher and id_teacher = #{id_teacher} ")
    int selectAllXB(Long id_teacher);

    List<Project> selectProjectByConditionId(@Param("section_id")Long section_id, @Param("zt") Long zt, @Param("id_teacher") Long id_teacher);

    List<Student> selectStudentByIds(@Param("student_ids") List<Long> student_ids);

//    @Update("UPDATE t_project set selectCount = selectCount + 1 where id=#{project_id}")
//    void updateNumAdd(Long project_id);
//
//    @Update("UPDATE t_project set selectCount = selectCount - 1 where id=#{project_id}")
//    void updateNumReduce(Long project_id);

}