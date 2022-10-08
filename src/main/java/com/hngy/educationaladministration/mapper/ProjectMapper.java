package com.hngy.educationaladministration.mapper;


import com.hngy.educationaladministration.entity.Project;
import com.hngy.educationaladministration.entity.ProjectExample;
import com.hngy.educationaladministration.entity.ProjectWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProjectMapper {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectWithBLOBs record);

    int insertSelective(ProjectWithBLOBs record);

    List<ProjectWithBLOBs> selectByExampleWithBLOBs(ProjectExample example);

    List<Project> selectByExample(ProjectExample example);

    ProjectWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectWithBLOBs record, @Param("example") ProjectExample example);

    int updateByExampleWithBLOBs(@Param("record") ProjectWithBLOBs record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(ProjectWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProjectWithBLOBs record);

    int updateByPrimaryKey(Project record);

    // 自建查询

    List<Project> select_ProjectXQ(@Param("institute_id") Long institute_id, @Param("section_id") Long section_id, @Param("name") String name);

    List<Project> selectProjectByName(@Param("projectName")String projectName);

    // 根据学院生成 以专业为前提 课题一览表
    List<Project> select_project(@Param("institute_id") Long institute_id, @Param("section_id") Long section_id, @Param("section_name") String section_name);



}
