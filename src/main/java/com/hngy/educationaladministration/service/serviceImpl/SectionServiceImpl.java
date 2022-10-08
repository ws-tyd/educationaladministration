package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.MyClass;
import com.hngy.educationaladministration.entity.Section;
import com.hngy.educationaladministration.entity.SectionExample;
import com.hngy.educationaladministration.entity.Specialty;
import com.hngy.educationaladministration.mapper.SectionMapper;
import com.hngy.educationaladministration.service.SectionService;
import com.hngy.educationaladministration.service.SpecialtyService;
import com.hngy.educationaladministration.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionMapper sectionMapper;
    @Autowired
    SpecialtyService specialtyService;

    @Override
    public Section getSection(long id) {
        return sectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List getSections(long id_institute) {
        SectionExample sectionExample = new SectionExample();
        //等于-1代表是所有  即超管查询
        if(id_institute==-1)
        {
            sectionExample.createCriteria();
            return sectionMapper.selectByExample(sectionExample);
        }
        sectionExample.createCriteria().andIdInstituteEqualTo(id_institute);
        return sectionMapper.selectByExample(sectionExample);
    }

    @Override
    public long delSection(Section section) {
        return sectionMapper.deleteByPrimaryKey(section.getId());
    }

    @Override
    public long updateSection(Section section, String name,long id_institute) throws MyException {
        check(section,id_institute);
        return sectionMapper.updateByPrimaryKeySelective(section);
    }

    @Override
    public long addSection(Section section, long id_institute) {
        if(id_institute!=-1)
            section.setIdInstitute(id_institute);
        return sectionMapper.insert(section);
    }

    //检测修改的教研室是不是当前学院的
    public void check(Section section , Long id_institute) throws MyException {
        //如果为超管则不需要检查有没有权力
        if(id_institute==-1)
        {
            return ;
        }

        if(section.getIdInstitute()!=id_institute)
        {
            throw new MyException("没有权限");
        }
    }
}
