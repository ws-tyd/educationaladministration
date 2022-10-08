package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.SubjectSource;
import com.hngy.educationaladministration.entity.SubjectSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectSourceMapper {
    long countByExample(SubjectSourceExample example);

    int deleteByExample(SubjectSourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SubjectSource record);

    int insertSelective(SubjectSource record);

    List<SubjectSource> selectByExampleWithBLOBs(SubjectSourceExample example);

    List<SubjectSource> selectByExample(SubjectSourceExample example);

    SubjectSource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SubjectSource record, @Param("example") SubjectSourceExample example);

    int updateByExampleWithBLOBs(@Param("record") SubjectSource record, @Param("example") SubjectSourceExample example);

    int updateByExample(@Param("record") SubjectSource record, @Param("example") SubjectSourceExample example);

    int updateByPrimaryKeySelective(SubjectSource record);

    int updateByPrimaryKeyWithBLOBs(SubjectSource record);

    int updateByPrimaryKey(SubjectSource record);
}