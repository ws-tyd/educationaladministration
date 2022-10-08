package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.MyClass;
import com.hngy.educationaladministration.entity.MyClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyClassMapper {
    long countByExample(MyClassExample example);

    int deleteByExample(MyClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MyClass record);

    int insertSelective(MyClass record);

    List<MyClass> selectByExampleSelective(@Param("example") MyClassExample example, @Param("selective") MyClass.Column ... selective);

    List<MyClass> selectByExampleWithBLOBs(MyClassExample example);

    List<MyClass> selectByExample(MyClassExample example);

    MyClass selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") MyClass.Column ... selective);

    MyClass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MyClass record, @Param("example") MyClassExample example);

    int updateByExampleWithBLOBs(@Param("record") MyClass record, @Param("example") MyClassExample example);

    int updateByExample(@Param("record") MyClass record, @Param("example") MyClassExample example);

    int updateByPrimaryKeySelective(MyClass record);

    int updateByPrimaryKeyWithBLOBs(MyClass record);

    int updateByPrimaryKey(MyClass record);


//    --------------------------------------------------------------------------------------

    // 自建
    public List<Class> select_class(@Param("institute_id") Long institute_id, @Param("section_id") Long section_id, @Param("specialty_id") Long specialty_id, @Param("class_id") Long class_id, @Param("class_name") String class_name);

    MyClass selectById(Long id);

    MyClass selectByclassName(String className);

    String selectByspecialty(Long id);
}