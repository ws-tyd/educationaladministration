package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.DebriefingYear;
import com.hngy.educationaladministration.entity.DebriefingYearExample;
import com.hngy.educationaladministration.entity.DebriefingYearWithBLOBs;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DebriefingYearMapper {
    long countByExample(DebriefingYearExample example);

    int deleteByExample(DebriefingYearExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DebriefingYearWithBLOBs record);

    int insertSelective(DebriefingYearWithBLOBs record);

    List<DebriefingYearWithBLOBs> selectByExampleWithBLOBs(DebriefingYearExample example);

    List<DebriefingYear> selectByExample(DebriefingYearExample example);

    List<DebriefingYear> selectByYear();

    DebriefingYearWithBLOBs selectByIdAndYear(@Param("id_teacher") Long id_teacher, @Param("year") Long year);

    DebriefingYearWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DebriefingYearWithBLOBs record, @Param("example") DebriefingYearExample example);

    int updateByExampleWithBLOBs(@Param("record") DebriefingYearWithBLOBs record, @Param("example") DebriefingYearExample example);

    int updateByExample(@Param("record") DebriefingYear record, @Param("example") DebriefingYearExample example);

    int updateByPrimaryKeySelective(DebriefingYearWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DebriefingYearWithBLOBs record);

    int updateByPrimaryKey(DebriefingYear record);

    @Select("SELECT COUNT(#{id_teacher}) FROM t_debriefingofyear where year = #{year}")
    Long selectFlag(@Param("id_teacher") Long id_teacher, @Param("year") Long year);
}