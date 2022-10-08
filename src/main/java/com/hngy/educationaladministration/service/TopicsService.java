package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.entity.Student;
import com.hngy.educationaladministration.entity.classinfo;
import com.hngy.educationaladministration.entity.topicsinfo;
import com.hngy.educationaladministration.entity.topicsto;

import java.util.List;

public interface TopicsService {

    public List<classinfo> classinfo(Long id);

    public List<topicsinfo> topics(Long id);

    public List<topicsto> topicsinfo(Long project_id);

    public Long state(Student student);

    public void insertproject(String projectName, Long id);

    public int deleteprojectselectedid(Long id);

    public Long flag(Long project_id);

    public String selectprojectname(Long id);
}
