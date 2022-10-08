package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.AdminMapper;
import com.hngy.educationaladministration.mapper.SadminMapper;
import com.hngy.educationaladministration.mapper.StudentMapper;
import com.hngy.educationaladministration.mapper.TeacherMapper;
import com.hngy.educationaladministration.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicServiceImpl implements PublicService {
    @Autowired
    SadminMapper sadminMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;

    /**
     * 检查用户名是否重复
     *
     * @param username 需要查询的用户名
     */
    @Override
    public void CheckIfTheUsernameIsDuplicated(String username) throws MyException {

        SadminExample sadminExample = new SadminExample();
        AdminExample adminExample = new AdminExample();
        TeacherExample teacherExample = new TeacherExample();
        StudentExample studentExample = new StudentExample();
        //
        adminExample.or().andNameEqualTo(username);
        teacherExample.or().andUsernameEqualTo(username);
        studentExample.or().andUsernameEqualTo(username);
        sadminExample.or().andNameEqualTo(username);

        if(adminMapper.selectByExample(adminExample).size()>0||
                teacherMapper.selectByExample(teacherExample).size()>0||
                studentMapper.selectByExample(studentExample).size()>0||
                sadminMapper.selectByExample(sadminExample).size()>0
        ){
            throw new MyException("用户已存在");
        }

    }
}
