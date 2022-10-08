package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.entity.Institute;
import com.hngy.educationaladministration.entity.InstituteExample;
import com.hngy.educationaladministration.entity.Section;
import com.hngy.educationaladministration.mapper.InstituteMapper;
import com.hngy.educationaladministration.service.CollegeService;
import com.hngy.educationaladministration.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    InstituteMapper instituteMapper;
    @Autowired
    SectionService sectionService;

    @Override
    public List<Institute> selectColleges() {
        InstituteExample instituteExample = new InstituteExample();
        instituteExample.createCriteria().andIdIsNotNull();
        return instituteMapper.selectByExample(instituteExample);
    }

    @Override
    public Institute selectCollege(long id) {
        return instituteMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delCollege(Institute institute) {
        return instituteMapper.deleteByPrimaryKey(institute.getId());
    }

    @Override
    public int updateCollege(long id,String name) {
        Institute institute = new Institute();
        institute.setId(id);
        institute.setInstituteName(name);
        return instituteMapper.updateByPrimaryKey(institute);
    }

    @Override
    public int addCollege(String name) {
        Institute institute = new Institute();
        institute.setInstituteName(name);
        return instituteMapper.insert(institute);
    }
}
