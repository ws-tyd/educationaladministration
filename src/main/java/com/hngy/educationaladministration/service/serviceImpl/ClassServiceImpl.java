package com.hngy.educationaladministration.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.MyClassMapper;
import com.hngy.educationaladministration.service.ClassService;
import com.hngy.educationaladministration.service.SpecialtyService;
import com.hngy.educationaladministration.service.StudentService;
import com.hngy.educationaladministration.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    MyClassMapper myClassMapper;
    @Autowired
    SpecialtyService specialtyService;
    @Autowired
    StudentService studentService;

    @Override
    public List getClasses(Integer offset, String getName, Long specialtyid, Long id_institute) {

        // 设置分页查询条件
        MyClassExample myClassExample = new MyClassExample();
        //  添加查询条件
        myClassExample = addCondition(myClassExample,offset,id_institute,getName,specialtyid);

        if(myClassExample==null)
            return null;

        //如果 offset 为 -1 则只查询 id 和 name 列
        if(offset==-1)
        {
            return myClassMapper.selectByExampleSelective(myClassExample,
                    MyClass.Column.id,
                    MyClass.Column.className);
        }else{
            return myClassMapper.selectByExampleSelective(myClassExample);
        }

    }

    @Override
    public long getClassesCount(Integer offset, String getName, Long specialtyid, Long id_institute) {
        // 设置分页查询条件
        MyClassExample myClassExample = new MyClassExample();
        //        添加查询条件
        myClassExample = addCondition(myClassExample,offset,id_institute,getName,specialtyid);

        if(myClassExample==null)
            return 0;

        return myClassMapper.countByExample(myClassExample);

    }

    @Override
    public long delClass(MyClass myClass, Long id_institute) throws MyException {
        checkSpecialtyId(myClass,id_institute);
        return myClassMapper.deleteByPrimaryKey(myClass.getId());
    }

    @Override
    public long putClass(MyClass myClass, Long id_institute) throws MyException {
        checkSpecialtyId(myClass,id_institute);
        return myClassMapper.updateByPrimaryKey(myClass);
    }

    @Override
    public long postClass(MyClass myClass, Long id_institute) throws MyException {
        checkSpecialtyId(myClass,id_institute);
        long count = getClassesCount(1,myClass.getClassName(),null, id_institute);
        if(count!=0)
            throw new MyException("该班级已存在");
        return myClassMapper.insert(myClass);
    }

    @Override
    public long delClassBySpecialty(Long specialtyid) {
        return myClassMapper.deleteByExample(new MyClassExample().createCriteria().andIdSpecialtyEqualTo(specialtyid).example());
    }

    public MyClassExample addCondition(MyClassExample myClassExample,Integer offset , Long id_institute , String keyWord , Long id_specialty){

        //代表为超管，则可以查询所有；
        if(id_institute==-1)
        {
            return myClassExample.newAndCreateCriteria()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andClassNameEqualTo(keyWord)) //根据关键字查询
                    .when(id_specialty!=null,criteria -> criteria.andIdSpecialtyEqualTo(id_specialty)) //根据指定教研室查询
                    .example()
                    .when(offset!=-1 && offset != 0,criteria -> criteria.page(offset-1, 10));
        }

        //查询出来当前学院的所有专业
        List<Specialty> specialties = specialtyService.getSpecialtys(-1,null,null,id_institute);

        //提取 id列 列表
        List<Long> specialtiesIDs = specialties.stream().map(Specialty::getId).collect(Collectors.toList());

        if(specialtiesIDs.size()==0||specialtiesIDs==null)
            return null;

        return myClassExample.newAndCreateCriteria()
                .when(StringUtil.stringIsNotNull(keyWord),criteria -> criteria.andClassNameEqualTo(keyWord)) //根据关键字查询
                .when(id_specialty!=null,criteria -> criteria.andIdSpecialtyEqualTo(id_specialty)) //根据指定教研室查询
                // 查询的专业只能在当前的学院的教研室下
                .andIdSpecialtyIn(specialtiesIDs)
                .example()
                .when(offset!=-1 && offset != 0,criteria -> criteria.page(offset-1, 10));// 查询第offest-1页数据（每页10条）

    }

    public void checkSpecialtyId(MyClass myClass ,Long id_institute) throws MyException {
        //如果为超管则不需要检查有没有权力
        if(id_institute==-1)
        {
            return ;
        }

        long id_specialty = myClass.getIdSpecialty();
        List<Specialty> specialtyes = specialtyService.getSpecialtys(1,null,null,id_institute);
        //如果被删除的专业是不属于本系教研室则提示没有权限
        for(Specialty specialty :specialtyes)
        {
            if(specialty.getId()==id_specialty)
                return;
        }
        throw new MyException("没有权限操作");
    }

}
