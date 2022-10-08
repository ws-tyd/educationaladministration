package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Subject;
import com.hngy.educationaladministration.entity.SubjectExample;
import com.hngy.educationaladministration.entity.SubjectWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SubjectMapper {
    long countByExample(SubjectExample example);

    int deleteByExample(SubjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SubjectWithBLOBs record);

    int insertSelective(SubjectWithBLOBs record);

    List<SubjectWithBLOBs> selectByExampleSelective(@Param("example") SubjectExample example, @Param("selective") SubjectWithBLOBs.Column ... selective);

    List<SubjectWithBLOBs> selectByExampleWithBLOBs(SubjectExample example);

    List<Subject> selectByExample(SubjectExample example);

    SubjectWithBLOBs selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SubjectWithBLOBs.Column ... selective);

    SubjectWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SubjectWithBLOBs record, @Param("example") SubjectExample example);

    int updateByExampleWithBLOBs(@Param("record") SubjectWithBLOBs record, @Param("example") SubjectExample example);

    int updateByExample(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByPrimaryKeySelective(SubjectWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SubjectWithBLOBs record);

    int updateByPrimaryKey(Subject record);

//------------------------------------------------------------------------------------------------
    // 自建查询

    List<Subject> select_ProjectXQ(@Param("institute_id") Long institute_id, @Param("section_id") Long section_id, @Param("name") String name);

    // 根据学院生成 以专业为前提 课题一览表
    List<Subject> select_project(@Param("institute_id") Long institute_id, @Param("section_id") Long section_id, @Param("section_name") String section_name);

    Subject selectById(Long id);

    List<Subject> selectByAll(@Param("specialty") String specialty);

    String selectByprojectType(int id);

    String selectByprojectSource(int id);

    Long selectByclassName(String projectName);

    Long selectByprojectid(String projectName);

    Long selectByselectFlag(Long project_id);

    List<Subject> selectBystudentidprojectName(Long id);

}