package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.SubjectType;
import com.hngy.educationaladministration.entity.SubjectTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectTypeMapper {
    long countByExample(SubjectTypeExample example);

    int deleteByExample(SubjectTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SubjectType record);

    int insertSelective(SubjectType record);

    List<SubjectType> selectByExampleWithBLOBs(SubjectTypeExample example);

    List<SubjectType> selectByExample(SubjectTypeExample example);

    SubjectType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SubjectType record, @Param("example") SubjectTypeExample example);

    int updateByExampleWithBLOBs(@Param("record") SubjectType record, @Param("example") SubjectTypeExample example);

    int updateByExample(@Param("record") SubjectType record, @Param("example") SubjectTypeExample example);

    int updateByPrimaryKeySelective(SubjectType record);

    int updateByPrimaryKeyWithBLOBs(SubjectType record);

    int updateByPrimaryKey(SubjectType record);
}