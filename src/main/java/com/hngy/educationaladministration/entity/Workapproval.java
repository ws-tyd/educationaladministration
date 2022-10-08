package com.hngy.educationaladministration.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class Workapproval {
    private Long id;

    private Long idType;

    private Long idTeacher;

    private String worktitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String location;

    private String member;

    private Integer appovalflag;

    private Date sumbitdate;

    private Date appovaldate;

    private Long idSubadmin;

    //自建
    private String typeName;//出差类型名称
    private String userName;//教师名称


}