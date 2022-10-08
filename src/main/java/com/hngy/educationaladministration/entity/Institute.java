package com.hngy.educationaladministration.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class Institute {
    private Long id;

    @NotBlank(message = "学院名不能为空")
    @Size(min = 1,max = 99,message = "字符应该在1-100之间")
    private String instituteName;

    private String remark;


}