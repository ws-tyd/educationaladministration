package com.hngy.educationaladministration.entity.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AcademicAchievements {

    ArrayList<TextBook> textBook;

    ArrayList<Paper> paper;

    ArrayList<ProjectOrTopics> projectOrTopics;


}
