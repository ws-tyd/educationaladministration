package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.SubjectRelationStudent;
import com.hngy.educationaladministration.entity.SubjectRelationStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectRelationStudentMapper {
    long countByExample(SubjectRelationStudentExample example);

    int deleteByExample(SubjectRelationStudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SubjectRelationStudent record);

    int insertSelective(SubjectRelationStudent record);

    List<SubjectRelationStudent> selectByExampleSelective(@Param("example") SubjectRelationStudentExample example, @Param("selective") SubjectRelationStudent.Column ... selective);

    List<SubjectRelationStudent> selectByExampleWithBLOBs(SubjectRelationStudentExample example);

    List<SubjectRelationStudent> selectByExample(SubjectRelationStudentExample example);

    SubjectRelationStudent selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SubjectRelationStudent.Column ... selective);

    SubjectRelationStudent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SubjectRelationStudent record, @Param("example") SubjectRelationStudentExample example);

    int updateByExampleWithBLOBs(@Param("record") SubjectRelationStudent record, @Param("example") SubjectRelationStudentExample example);

    int updateByExample(@Param("record") SubjectRelationStudent record, @Param("example") SubjectRelationStudentExample example);

    int updateByPrimaryKeySelective(SubjectRelationStudent record);

    int updateByPrimaryKeyWithBLOBs(SubjectRelationStudent record);

    int updateByPrimaryKey(SubjectRelationStudent record);
}