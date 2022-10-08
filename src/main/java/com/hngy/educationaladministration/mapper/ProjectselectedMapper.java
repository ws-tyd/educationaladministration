package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Projectselected;
import com.hngy.educationaladministration.entity.ProjectselectedExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectselectedMapper {
    long countByExample(ProjectselectedExample example);

    int deleteByExample(ProjectselectedExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Projectselected record);

    int insertSelective(Projectselected record);

    List<Projectselected> selectByExampleWithBLOBs(ProjectselectedExample example);

    List<Projectselected> selectByExample(ProjectselectedExample example);

    Projectselected selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Projectselected record, @Param("example") ProjectselectedExample example);

    int updateByExampleWithBLOBs(@Param("record") Projectselected record, @Param("example") ProjectselectedExample example);

    int updateByExample(@Param("record") Projectselected record, @Param("example") ProjectselectedExample example);

    int updateByPrimaryKeySelective(Projectselected record);

    int updateByPrimaryKeyWithBLOBs(Projectselected record);

    int updateByPrimaryKey(Projectselected record);

    // 自建查询
    List<Projectselected> select_Projectselected(@Param("student_id") Long student_id, @Param("project_id") Long project_id);

    List<Long> select_student_ids(@Param("project_id") Long project_id);

}
