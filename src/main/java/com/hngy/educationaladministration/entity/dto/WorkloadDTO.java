package com.hngy.educationaladministration.entity.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 *  一张工作量表的数据
 */

//todo 年份 和 学期的数据为存储
@Data
public class WorkloadDTO {

    private String section;

    private String institute;

    String year;

    String trem;

    Float sum1;

    Float sum2;

    Float sum1AddSum2;

    ArrayList<TeachingWork> teachingWork ;

    ArrayList<TSESWDA> tseswdas ;

    String rsrWorkload;

    TSESWDA subsidyworkloadDATA;

    Float subsidyworkload;

    Float totalSum;

    String sectionVerify;

    String instituteVerify;
}
