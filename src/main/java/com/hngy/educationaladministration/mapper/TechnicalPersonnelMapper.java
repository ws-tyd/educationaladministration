package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.TechnicalPersonnel;
import com.hngy.educationaladministration.entity.TechnicalPersonnelExample;
import com.hngy.educationaladministration.entity.TechnicalPersonnelWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TechnicalPersonnelMapper {
    long countByExample(TechnicalPersonnelExample example);

    int deleteByExample(TechnicalPersonnelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TechnicalPersonnelWithBLOBs record);

    int insertSelective(TechnicalPersonnelWithBLOBs record);

    List<TechnicalPersonnelWithBLOBs> selectByExampleWithBLOBs(TechnicalPersonnelExample example);

    List<TechnicalPersonnel> selectByExample(TechnicalPersonnelExample example);

    TechnicalPersonnelWithBLOBs selectByPrimaryKey(Long id);

    Long selectFlag(@Param("id_teacher")Long id_teacher, @Param("year") Long year);

    List<TechnicalPersonnel> selectByYear(Long id_teacher);

    TechnicalPersonnelWithBLOBs selectByIdTeacherAndYear(@Param("id_teacher") Long id_teacher, @Param("year") Long year);

    int updateByExampleSelective(@Param("record") TechnicalPersonnelWithBLOBs record, @Param("example") TechnicalPersonnelExample example);

    int updateByExampleWithBLOBs(@Param("record") TechnicalPersonnelWithBLOBs record, @Param("example") TechnicalPersonnelExample example);

    int updateByExample(@Param("record") TechnicalPersonnel record, @Param("example") TechnicalPersonnelExample example);

    int updateByPrimaryKeySelective(TechnicalPersonnelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TechnicalPersonnelWithBLOBs record);

    int updateByPrimaryKey(TechnicalPersonnel record);
}