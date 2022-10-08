package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Workapproval;
import com.hngy.educationaladministration.entity.WorkapprovalExample;
import com.hngy.educationaladministration.entity.WorkapprovalWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WorkapprovalMapper {
    long countByExample(WorkapprovalExample example);

    int deleteByExample(WorkapprovalExample example);

    int insert(WorkapprovalWithBLOBs record);

    int insertSelective(WorkapprovalWithBLOBs record);

    List<WorkapprovalWithBLOBs> selectByExampleWithBLOBs(WorkapprovalExample example);

    List<WorkapprovalWithBLOBs> selectByApprovalFlag(@Param("id_teacher") Long id_teacher, @Param("appovalFlag")Integer appovalFlag);

    List<Workapproval> selectByExample(WorkapprovalExample example);

    WorkapprovalWithBLOBs selectById(Long id);

    List<WorkapprovalWithBLOBs> selectSectionWork(Long section_id);

    int updateByExampleSelective(@Param("record") WorkapprovalWithBLOBs record, @Param("example") WorkapprovalExample example);

    int updateByExampleWithBLOBs(@Param("record") WorkapprovalWithBLOBs record, @Param("example") WorkapprovalExample example);

    int updateByExample(@Param("record") Workapproval record, @Param("example") WorkapprovalExample example);

    @Delete("delete from t_workapproval where id = #{id} ")
    int deleteWork(Long id);
}