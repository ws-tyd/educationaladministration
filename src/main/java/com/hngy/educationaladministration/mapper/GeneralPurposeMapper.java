package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.SubjectSource;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 手写原生SQL
 */

public interface GeneralPurposeMapper {

    int selectSubjectsCount(@Param("offset") long offset,
                    @Param("keyWord") String keyWord,
                    @Param("sectionId") Long sectionId,
                    @Param("id_institute") Long id_institute);

    List selectSubjects(@Param("offset") long offset,
                    @Param("keyWord") String keyWord,
                    @Param("sectionId") Long sectionId,
                    @Param("id_institute") Long id_institute);


    @Select("${sql}")
    List<LinkedHashMap<String, Object>> selectReturnLinkHashMap(@Param("sql") String sql);

    @Select("${sql}")
    List<HashMap<String, Object>> selectAll(@Param("sql") String sql);

    @Select("${sql}")
    String selectReturnString(@Param("sql") String sql);

    @Select("${sql}")
    String selectReturnObj(@Param("sql") String sql);

    @Select("${sql}")
    SubjectSource select(@Param("sql") String sql);

    @Insert("${sql}")
    int insert(@Param("sql") String sql);

    @Update("${sql}")
    int update(@Param("sql") String sql);

    @Delete("${sql}")
    int delete(@Param("sql") String sql);
}
