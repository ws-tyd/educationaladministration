package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Specialty;
import com.hngy.educationaladministration.entity.SpecialtyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecialtyMapper {
    long countByExample(SpecialtyExample example);

    int deleteByExample(SpecialtyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Specialty record);

    int insertSelective(Specialty record);

    List<Specialty> selectByExampleSelective(@Param("example") SpecialtyExample example, @Param("selective") Specialty.Column ... selective);

    List<Specialty> selectByExampleWithBLOBs(SpecialtyExample example);

    List<Specialty> selectByExample(SpecialtyExample example);

    Specialty selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Specialty.Column ... selective);

    Specialty selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByExampleWithBLOBs(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByExample(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByPrimaryKeySelective(Specialty record);

    int updateByPrimaryKeyWithBLOBs(Specialty record);

    int updateByPrimaryKey(Specialty record);

    //自建的方法；
    List<Specialty> select_specialty(@Param("institute_id") Long institute_id, @Param("section_id") Long section_id, @Param("specialty_id") Long specialty_id, @Param("specialty_name") String specialty_name);
}