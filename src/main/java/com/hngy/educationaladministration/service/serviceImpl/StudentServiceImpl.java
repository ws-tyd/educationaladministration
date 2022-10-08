package com.hngy.educationaladministration.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.MyClassMapper;
import com.hngy.educationaladministration.mapper.SpecialtyMapper;
import com.hngy.educationaladministration.mapper.StudentMapper;
import com.hngy.educationaladministration.service.ClassService;
import com.hngy.educationaladministration.service.PublicService;
import com.hngy.educationaladministration.service.SpecialtyService;
import com.hngy.educationaladministration.service.StudentService;
import com.hngy.educationaladministration.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ClassService classService;
    @Autowired
    SpecialtyService specialtyService;
    @Autowired
    SpecialtyMapper specialtyMapper;
    @Autowired
    MyClassMapper myClassMapper;
    @Autowired
    PublicService publicService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public List<StudentWithBLOBs> selectStudents(Integer offset, String keyWord, Long classId, Long specialtyId, Long id_institute) throws MyException {

        // 设置分页查询条件
        StudentExample studentExample = new StudentExample();
        //添加查询条件
        studentExample = addCondition(studentExample,offset,keyWord,classId,specialtyId,id_institute);

        if(studentExample ==null)
            return null;

        //如果 offset 为 -1 则只查询 id 和 name 列
        if(offset==-1)
        {
            return studentMapper.selectByExampleSelective(studentExample,
                    StudentWithBLOBs.Column.id,
                    StudentWithBLOBs.Column.name);
        }else{
            return studentMapper.selectByExampleSelective(studentExample);
        }

    }

    @Override
    public long selectStudentsCount(Integer offset, String keyWord, Long classId,Long specialtyId, Long id_institute) {

        // 设置分页查询条件
        StudentExample studentExample = new StudentExample();
        //添加查询条件
        studentExample = addCondition(studentExample,offset,keyWord,classId,specialtyId,id_institute);

        if(studentExample==null)
            return 0;

        return studentMapper.countByExample(studentExample);

    }

    @Override
    public long delStudent(StudentWithBLOBs student, Long id_institute) throws MyException {
        check(student,id_institute);
        return studentMapper.deleteByPrimaryKey(student.getId());
    }

    @Override
    public long addStudent(StudentWithBLOBs student, Long id_institute) throws MyException {
        publicService.CheckIfTheUsernameIsDuplicated(student.getUsername());
        return studentMapper.insert(student);
    }

    @Override
    public long updateStudent(StudentWithBLOBs student, Long id_institute) throws MyException {
        check(student,id_institute);
        //不更新username
        student.setUsername(null);
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public long delStudentByClass(Long classId) {
        return studentMapper.deleteByExample(new StudentExample().createCriteria().andIdClassEqualTo(classId).example());
    }

    public List<Student> selectByName(String username) {
//        return studentMapper.selectByExample();
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        return studentMapper.selectByExample(studentExample);
    }


    public StudentExample addCondition(StudentExample studentExample,Integer offset, String keyWord, Long classId, Long specialtyId,long id_institute)
    {

        List<MyClass> classes = classService.getClasses(-1,null,specialtyId,id_institute);

        List<Long> classesIDs = classes.stream().map(MyClass::getId).collect(Collectors.toList());

        if(classesIDs.size()==0 || classesIDs==null)
            return null;

        //代表为超管，则可以查询所有；
        if(id_institute==-1)
        {
            return studentExample.newAndCreateCriteria()
                    .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andNameEqualTo(keyWord)) //根据姓名
                    .when(classId!=null,criteria -> criteria.andIdClassEqualTo(classId)) //根据指定Class查询
                    .when(classesIDs!=null&&classesIDs.size()>0,
                            criteria -> criteria.andIdClassIn(classesIDs),// 按指定专业的班级查询
                            criteria -> criteria.andIdEqualTo(0L))

                    .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andUsernameEqualTo(keyWord)) //根据登录名查询
                    .when(classId!=null,criteria -> criteria.andIdClassEqualTo(classId)) //根据指定Class查询
                    .when(classesIDs!=null&&classesIDs.size()>0,
                            criteria -> criteria.andIdClassIn(classesIDs),// 按指定专业的班级查询
                            criteria -> criteria.andIdEqualTo(0L))

                    .example().or()
                    .when(classId!=null,criteria -> criteria.andIdClassEqualTo(classId)) //根据指定Class查询
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andStunumEqualTo(keyWord)) // 根据学号查询
                    .when(classesIDs!=null&&classesIDs.size()>0,
                            criteria -> criteria.andIdClassIn(classesIDs),// 按指定专业的班级查询
                            criteria -> criteria.andIdEqualTo(0L))

                    .example()
                    .when(offset!=-1 && offset != 0,criteria -> criteria.page(offset-1, 10));// 查询第offest-1页数据（每页10条）;
        }


        return studentExample.newAndCreateCriteria()
                .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andNameEqualTo(keyWord)) //根据姓名
                    .when(classId!=null,criteria -> criteria.andIdClassEqualTo(classId)) //根据指定Class查询
                    .when(classesIDs!=null&&classesIDs.size()>0,
                            criteria -> criteria.andIdClassIn(classesIDs),// 查询的学生只能在当前的学院的班级下
                            criteria -> criteria.andIdEqualTo(0L))

                .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andUsernameEqualTo(keyWord)) //根据登录名查询
                    .when(classId!=null,criteria -> criteria.andIdClassEqualTo(classId)) //根据指定Class查询
                    .when(classesIDs!=null&&classesIDs.size()>0,
                            criteria -> criteria.andIdClassIn(classesIDs),// 查询的学生只能在当前的学院的班级下
                            criteria -> criteria.andIdEqualTo(0L))

                .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andStunumEqualTo(keyWord)) // 根据学号查询
                    .when(classId!=null,criteria -> criteria.andIdClassEqualTo(classId)) //根据指定Class查询
                    .when(classesIDs!=null&&classesIDs.size()>0,
                        criteria -> criteria.andIdClassIn(classesIDs),// 查询的学生只能在当前的学院的班级下
                        criteria -> criteria.andIdEqualTo(0L))

                .example()
                .when(offset!=-1 && offset != 0,criteria -> criteria.page(offset-1, 10));// 查询第offest-1页数据（每页10条）;
    }

    public void check(StudentWithBLOBs student ,Long id_institute) throws MyException {
        //懒得写
    }





    public void updateselectnumAdd(String project_name) {
        Long id = studentMapper.select_projectID(project_name);
        int listsize = (int)request.getSession().getAttribute("listsize");
        synchronized (this){
            studentMapper.updateNumAdd(id,listsize);
        }

    }
    public void updateselectnumReduce(String project_name) {
        Long id = studentMapper.select_projectID(project_name);
        int listsize = (int)request.getSession().getAttribute("listsize");
        synchronized (this){
            studentMapper.updateNumReduce(id,listsize);
        }

    }




}
