package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Business;
import com.hngy.educationaladministration.entity.BusinessExample;
import com.hngy.educationaladministration.entity.BusinessWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessMapper {
    long countByExample(BusinessExample example);

    int deleteByExample(BusinessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessWithBLOBs record);

    int insertSelective(BusinessWithBLOBs record);

    Business selectOneByExample(BusinessExample example);

    BusinessWithBLOBs selectOneByExampleSelective(@Param("example") BusinessExample example, @Param("selective") BusinessWithBLOBs.Column ... selective);

    BusinessWithBLOBs selectOneByExampleWithBLOBs(BusinessExample example);

    List<BusinessWithBLOBs> selectByExampleSelective(@Param("example") BusinessExample example, @Param("selective") BusinessWithBLOBs.Column ... selective);

    List<BusinessWithBLOBs> selectByExampleWithBLOBs(BusinessExample example);

    List<Business> selectByExample(BusinessExample example);

    BusinessWithBLOBs selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") BusinessWithBLOBs.Column ... selective);

    BusinessWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessWithBLOBs record, @Param("example") BusinessExample example);

    int updateByExampleWithBLOBs(@Param("record") BusinessWithBLOBs record, @Param("example") BusinessExample example);

    int updateByExample(@Param("record") Business record, @Param("example") BusinessExample example);

    int updateByPrimaryKeySelective(BusinessWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BusinessWithBLOBs record);

    int updateByPrimaryKey(Business record);
}