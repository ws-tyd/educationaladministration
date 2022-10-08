package com.hngy.educationaladministration.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnnualAssessmentWithBLOBs extends AnnualAssessment {
    private String personalsummary;

    private String opiniondepartment;

    private String opinion1;

    private String opinion2;

    private String opiniononself;

    private String remark;

}