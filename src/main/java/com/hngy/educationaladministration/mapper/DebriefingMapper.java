package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DebriefingMapper {
    long countByExample(DebriefingExample example);

    int deleteByExample(DebriefingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DebriefingWithBLOBs record);

    int insertSelective(DebriefingWithBLOBs record);

    List<DebriefingWithBLOBs> selectByExampleWithBLOBs(DebriefingExample example);

    List<Debriefing> selectByExample(DebriefingExample example);

    List<Debriefing> selectByYear();

    DebriefingWithBLOBs selectByIdAndYearTerm(@Param("id_teacher") Long id_teacher, @Param("year") Long year, @Param("term") String term);

    DebriefingWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DebriefingWithBLOBs record, @Param("example") DebriefingExample example);

    int updateByExampleWithBLOBs(@Param("record") DebriefingWithBLOBs record, @Param("example") DebriefingExample example);

    int updateByExample(@Param("record") Debriefing record, @Param("example") DebriefingExample example);

    int updateByPrimaryKeySelective(DebriefingWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DebriefingWithBLOBs record);

    int updateByPrimaryKey(Debriefing record);

    @Select("SELECT COUNT(#{id_teacher}) FROM t_debriefing where year = #{year} and term = #{term}")
    int selectFlag(@Param("id_teacher") Long id_teacher, @Param("year") Long year,  @Param("term") String term);
}