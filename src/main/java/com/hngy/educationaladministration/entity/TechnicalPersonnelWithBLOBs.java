package com.hngy.educationaladministration.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TechnicalPersonnelWithBLOBs extends TechnicalPersonnel {
    private String mainachievements;

    private String attendance;

    private String rewardsandpunishments;

    private String opiniondepartment;

    private String opinionunit;

    private String opiniononself;

    private String remark;


}