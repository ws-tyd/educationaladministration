package com.hngy.educationaladministration.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class SubjectSource {
    private Long id;

    @NotBlank(message = "课题不能为空")
    private String sourcename;

    private String remark;


}