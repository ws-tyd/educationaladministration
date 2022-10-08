package com.hngy.educationaladministration.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DebriefingWithBLOBs extends Debriefing {
    private String teachingtask;

    private String achievementsinscientificresearch;

    private String otherwork;

    private String winaward;

    private String summary;

    private String remark;



}