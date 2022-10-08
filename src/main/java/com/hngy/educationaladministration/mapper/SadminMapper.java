package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Sadmin;
import com.hngy.educationaladministration.entity.SadminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SadminMapper {
    long countByExample(SadminExample example);

    int deleteByExample(SadminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sadmin record);

    int insertSelective(Sadmin record);

    List<Sadmin> selectByExampleSelective(@Param("example") SadminExample example, @Param("selective") Sadmin.Column ... selective);

    List<Sadmin> selectByExampleWithBLOBs(SadminExample example);

    List<Sadmin> selectByExample(SadminExample example);

    Sadmin selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Sadmin.Column ... selective);

    Sadmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sadmin record, @Param("example") SadminExample example);

    int updateByExampleWithBLOBs(@Param("record") Sadmin record, @Param("example") SadminExample example);

    int updateByExample(@Param("record") Sadmin record, @Param("example") SadminExample example);

    int updateByPrimaryKeySelective(Sadmin record);

    int updateByPrimaryKeyWithBLOBs(Sadmin record);

    int updateByPrimaryKey(Sadmin record);
}