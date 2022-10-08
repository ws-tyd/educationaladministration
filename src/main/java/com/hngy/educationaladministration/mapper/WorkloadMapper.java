package com.hngy.educationaladministration.mapper;

import com.hngy.educationaladministration.entity.Workload;
import com.hngy.educationaladministration.entity.WorkloadExample;
import com.hngy.educationaladministration.entity.WorkloadWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkloadMapper {
    long countByExample(WorkloadExample example);

    int deleteByExample(WorkloadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WorkloadWithBLOBs record);

    int insertSelective(WorkloadWithBLOBs record);

    Workload selectOneByExample(WorkloadExample example);

    WorkloadWithBLOBs selectOneByExampleSelective(@Param("example") WorkloadExample example, @Param("selective") WorkloadWithBLOBs.Column ... selective);

    WorkloadWithBLOBs selectOneByExampleWithBLOBs(WorkloadExample example);

    List<WorkloadWithBLOBs> selectByExampleSelective(@Param("example") WorkloadExample example, @Param("selective") WorkloadWithBLOBs.Column ... selective);

    List<WorkloadWithBLOBs> selectByExampleWithBLOBs(WorkloadExample example);

    List<Workload> selectByExample(WorkloadExample example);

    WorkloadWithBLOBs selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") WorkloadWithBLOBs.Column ... selective);

    WorkloadWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorkloadWithBLOBs record, @Param("example") WorkloadExample example);

    int updateByExampleWithBLOBs(@Param("record") WorkloadWithBLOBs record, @Param("example") WorkloadExample example);

    int updateByExample(@Param("record") Workload record, @Param("example") WorkloadExample example);

    int updateByPrimaryKeySelective(WorkloadWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WorkloadWithBLOBs record);

    int updateByPrimaryKey(Workload record);
}