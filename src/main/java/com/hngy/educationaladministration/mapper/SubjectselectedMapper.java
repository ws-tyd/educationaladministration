package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Subjectselected;
import com.hngy.educationaladministration.entity.SubjectselectedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectselectedMapper {
    long countByExample(SubjectselectedExample example);

    int deleteByExample(SubjectselectedExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Subjectselected record);

    int insertSelective(Subjectselected record);

    List<Subjectselected> selectByExampleWithBLOBs(SubjectselectedExample example);

    List<Subjectselected> selectByExample(SubjectselectedExample example);

    Subjectselected selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Subjectselected record, @Param("example") SubjectselectedExample example);

    int updateByExampleWithBLOBs(@Param("record") Subjectselected record, @Param("example") SubjectselectedExample example);

    int updateByExample(@Param("record") Subjectselected record, @Param("example") SubjectselectedExample example);

    int updateByPrimaryKeySelective(Subjectselected record);

    int updateByPrimaryKeyWithBLOBs(Subjectselected record);

    int updateByPrimaryKey(Subjectselected record);

    // 自建查询
    List<Subjectselected> select_Projectselected(@Param("student_id") Long student_id,@Param("project_id") Long project_id);

    List<Subjectselected> selectBystudentid(Long id);

    List<Subjectselected> selectByprojectid(Long id);

    int insertenroll(@Param("projectid") Long projectid, @Param("studentid") Long studentid);

    int deleteBystudentId(Long id);

}