package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.AnnualAssessment;
import com.hngy.educationaladministration.entity.AnnualAssessmentExample;
import com.hngy.educationaladministration.entity.AnnualAssessmentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AnnualAssessmentMapper {
    long countByExample(AnnualAssessmentExample example);

    int deleteByExample(AnnualAssessmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnnualAssessmentWithBLOBs record);

    int insertSelective(AnnualAssessmentWithBLOBs record);

    List<AnnualAssessmentWithBLOBs> selectByExampleWithBLOBs(AnnualAssessmentExample example);

    List<AnnualAssessment> selectByExample(AnnualAssessmentExample example);

    List<AnnualAssessment> selectByYear(Long id_teacher);

    AnnualAssessmentWithBLOBs selectByPrimaryKey(Long id);

    AnnualAssessmentWithBLOBs selectByIdTeacherAndYear(@Param("id_teacher") Long id_teacher, @Param("year") Long year);

    @Select("select COUNT(id_teacher) from t_annualassessment where id_teacher = #{id_teacher} and year = #{year}")
    Long selectFlag(@Param("id_teacher")Long id_teacher, @Param("year")String year);

    int updateByExampleSelective(@Param("record") AnnualAssessmentWithBLOBs record, @Param("example") AnnualAssessmentExample example);

    int updateByExampleWithBLOBs(@Param("record") AnnualAssessmentWithBLOBs record, @Param("example") AnnualAssessmentExample example);

    int updateByExample(@Param("record") AnnualAssessment record, @Param("example") AnnualAssessmentExample example);

    int updateByPrimaryKeySelective(AnnualAssessmentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AnnualAssessmentWithBLOBs record);

    int updateByPrimaryKey(AnnualAssessment record);
}