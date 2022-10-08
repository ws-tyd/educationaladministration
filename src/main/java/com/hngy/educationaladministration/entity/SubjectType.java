package com.hngy.educationaladministration.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SubjectType {
    private Long id;

    @NotBlank(message = "课题类型不能为空")
    private String typename;

    private String remark;


}