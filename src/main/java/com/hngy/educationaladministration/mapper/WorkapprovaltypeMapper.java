package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Workapprovaltype;
import com.hngy.educationaladministration.entity.WorkapprovaltypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkapprovaltypeMapper {
    long countByExample(WorkapprovaltypeExample example);

    int deleteByExample(WorkapprovaltypeExample example);

    int insert(Workapprovaltype record);

    int insertSelective(Workapprovaltype record);

    List<Workapprovaltype> selectByExampleWithBLOBs(WorkapprovaltypeExample example);

    List<Workapprovaltype> selectByExample(WorkapprovaltypeExample example);

    int updateByExampleSelective(@Param("record") Workapprovaltype record, @Param("example") WorkapprovaltypeExample example);

    int updateByExampleWithBLOBs(@Param("record") Workapprovaltype record, @Param("example") WorkapprovaltypeExample example);

    int updateByExample(@Param("record") Workapprovaltype record, @Param("example") WorkapprovaltypeExample example);
}