package com.hngy.educationaladministration.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.SectionMapper;
import com.hngy.educationaladministration.mapper.SpecialtyMapper;
import com.hngy.educationaladministration.service.ClassService;
import com.hngy.educationaladministration.service.SectionService;
import com.hngy.educationaladministration.service.SpecialtyService;
import com.hngy.educationaladministration.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired(required = false)
    SpecialtyMapper specialtyMapper;
    @Autowired
    SectionService sectionService;
    @Autowired
    SectionMapper sectionMapper;
    @Autowired
    ClassService classService;
    @Override
    public List getSpecialtys(Integer offset, String getName, Long sectionId, Long id_institute) {
        // 设置分页查询条件
        SpecialtyExample specialtyExample = new SpecialtyExample();
        // 添加相应的查询条件
        specialtyExample = addCondition(specialtyExample,offset,id_institute,getName,sectionId);

        //如果 offset 为 -1 则只查询 id 和 name 列
        if(offset==-1)
        {
            return specialtyMapper.selectByExampleSelective(specialtyExample,
                    Specialty.Column.id,
                    Specialty.Column.specialtyName);
        }else{
            return specialtyMapper.selectByExample(specialtyExample);
        }

    }

    @Override
    public long getSpecialtyCount(Integer offset, String getName, Long sectionId, Long id_institute) {

        // 设置分页查询条件
        SpecialtyExample specialtyExample = new SpecialtyExample();
        // 添加相应的查询条件
        specialtyExample = addCondition(specialtyExample,offset,id_institute,getName,sectionId);

        return specialtyMapper.countByExample(specialtyExample);
    }

    @Override
    public long delSpecialty(Specialty specialty, Long id_institute) throws MyException {
        checkSectionId(specialty , id_institute);
        return specialtyMapper.deleteByPrimaryKey(specialty.getId());
    }

    @Override
    public long putSpecialty(Specialty specialty,Long id_institute) throws MyException {
        checkSectionId(specialty,id_institute);
        return specialtyMapper.updateByPrimaryKey(specialty);
    }

    @Override
    public long postSpecialty(Specialty specialty,Long id_institute) throws MyException {
        checkSectionId(specialty,id_institute);
        long count = getSpecialtyCount(1,specialty.getSpecialtyName(),null, id_institute);
        if(count!=0)
            throw new MyException("该专业已存在");
        return specialtyMapper.insert(specialty);
    }

    @Override
    public long delSpecialtyBySection(Long sectionid) {
        return specialtyMapper.deleteByExample(new SpecialtyExample().createCriteria().andIdSectionEqualTo(sectionid).example());
    }

    public SpecialtyExample addCondition(SpecialtyExample specialtyExample,Integer offset ,Long id_institute,String keyWord,Long sectionId)
    {
        //代表为超管，则条件为空
        if(id_institute==-1)
        {
            return specialtyExample.newAndCreateCriteria()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andSpecialtyNameEqualTo(keyWord)) //根据关键字查询
                    .when(sectionId!=null,criteria -> criteria.andIdSectionEqualTo(sectionId)) //根据指定教研室查询
                    .example()
                    .when(offset!=-1 && offset != 0,criteria -> criteria.page(offset-1, 10));
        }

        //查询出来当前学院的所有教研室
        List<Section> sections = sectionService.getSections(id_institute);
        //提取 id列 列表
        List<Long> sectionIDs = sections.stream().map(Section::getId).collect(Collectors.toList());

        if(sectionIDs!=null){
            return specialtyExample.newAndCreateCriteria()
                    .when(StringUtil.stringIsNotNull(keyWord),criteria -> criteria.andSpecialtyNameEqualTo(keyWord)) //根据关键字查询
                    .when(sectionId!=null,criteria -> criteria.andIdSectionEqualTo(sectionId)) //根据指定教研室查询
                    .andIdSectionIn(sectionIDs) // 查询的专业只能在当前的学院的教研室下
                    .example()
                    .when(offset!=-1 && offset != 0,criteria -> criteria.page(offset-1, 10));// 查询第offest-1页数据（每页10条）
        }

        //如果当前学院没有教研室则设置一个达不到的条件
        specialtyExample.createCriteria().andIdEqualTo((long) -1);
        return specialtyExample;
    }

    public void checkSectionId(Specialty specialty ,Long id_institute) throws MyException {

        //如果为超管则不需要检查有没有权力
        if(id_institute==-1)
        {
            return ;
        }

        long id_section = specialty.getIdSection();
        List<Section> sections = sectionService.getSections(id_institute);
        //如果被删除的专业是不属于本系教研室则提示没有权限
        for(Section section :sections)
        {
            if(section.getId()==id_section)
                return;
        }
        throw new MyException("没有权限操作");
    }
}
