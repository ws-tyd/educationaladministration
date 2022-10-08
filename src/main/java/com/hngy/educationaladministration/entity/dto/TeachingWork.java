package com.hngy.educationaladministration.entity.dto;

import lombok.Data;

/**
 * 教学工作量
 */

@Data
public class TeachingWork {

    String classes;

    String courseName;

    String classHourOfPlan;

    String jojintClass;

    String extracurricularExperiment;

    String courseDesign;

    String on_campusPractice;

    String off_campusTraining;

    String GraduationPractice;
}
