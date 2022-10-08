package com.hngy.educationaladministration.entity.dto;

import com.hngy.educationaladministration.entity.Teacher;
import lombok.Data;

import java.util.ArrayList;

@Data
public class BusinessDTO {

    private String dateOfbirth;

    private String year;

    private String trem;

    private String section;

    private String institute;

    private ArrayList<CompleteWorkload> completeWorkload;

    private String totalWorkload;

    private AcademicAchievements academicAchievements;

    private String awards;

    private String academicGroup;

    private String continuingEducation;

    private String otherJobs;

    private String sick;

    private String leave;

    private Integer beLate;

    private Integer leaveEarly;

    private Integer tuneClass;

    private Integer missingClass;

    private Integer disciplinaryPenalty;

    private String teachingOpinion;

    private String departmentOpinion;

    private String schoolOpinion;

}
