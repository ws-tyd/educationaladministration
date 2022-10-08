package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Class;
import com.hngy.educationaladministration.entity.ClassExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    long countByExample(ClassExample example);

    int deleteByExample(ClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Class record);

    int insertSelective(Class record);

    List<Class> selectByExampleWithBLOBs(ClassExample example);

    List<Class> selectByExample(ClassExample example);

    Class selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByExampleWithBLOBs(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByExample(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKeyWithBLOBs(Class record);

    int updateByPrimaryKey(Class record);

    // 自建
    public List<Class> select_class(@Param("institute_id") Long institute_id, @Param("section_id") Long section_id, @Param("specialty_id") Long specialty_id, @Param("class_id") Long class_id, @Param("class_name") String class_name);

    public List<Class> select_class_by_ids(@Param("classIds")List<Long> classIds);

}
