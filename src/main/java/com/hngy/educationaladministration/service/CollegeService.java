package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.entity.Institute;

import java.util.List;

public interface CollegeService {

    List<Institute> selectColleges();

    Institute selectCollege(long id);

    int delCollege(Institute institute);

    int updateCollege(long id,String name);

    int addCollege(String name);
}
