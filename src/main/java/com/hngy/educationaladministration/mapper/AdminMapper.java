package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Admin;
import com.hngy.educationaladministration.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExampleSelective(@Param("example") AdminExample example, @Param("selective") Admin.Column ... selective);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Admin.Column ... selective);

    Admin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}