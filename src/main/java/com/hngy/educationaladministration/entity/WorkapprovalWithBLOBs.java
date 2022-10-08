package com.hngy.educationaladministration.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkapprovalWithBLOBs extends Workapproval {
    private String workcontent;

    private String remark;

    private String bak1;

    private String bak2;

    private String bak3;

    private String bak4;

    public String getWorkcontent() {
        return workcontent;
    }


}