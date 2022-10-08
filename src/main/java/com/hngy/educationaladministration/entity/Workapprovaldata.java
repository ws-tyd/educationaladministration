package com.hngy.educationaladministration.entity;

import lombok.Data;

@Data
public class Workapprovaldata {
    private Long id;

    private Long idWorkapproval;

    private String news;

    private String datarar;

    private Integer flag;

    private String remark;


//    自建
    private String workTitle;
    private String userName;


}