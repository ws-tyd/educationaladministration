package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Section;
import com.hngy.educationaladministration.entity.SectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SectionMapper {
    long countByExample(SectionExample example);

    int deleteByExample(SectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Section record);

    int insertSelective(Section record);

    List<Section> selectByExampleSelective(@Param("example") SectionExample example, @Param("selective") Section.Column ... selective);

    List<Section> selectByExampleWithBLOBs(SectionExample example);

    List<Section> selectByExample(SectionExample example);

    Section selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Section.Column ... selective);

    Section selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Section record, @Param("example") SectionExample example);

    int updateByExampleWithBLOBs(@Param("record") Section record, @Param("example") SectionExample example);

    int updateByExample(@Param("record") Section record, @Param("example") SectionExample example);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKeyWithBLOBs(Section record);

    int updateByPrimaryKey(Section record);
}