package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.entity.Student;
import com.hngy.educationaladministration.mapper.StudentMapper;
import com.hngy.educationaladministration.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    StudentMapper studentDao;

    @Override
    public Student selectByName(String username) {
        return studentDao.selectByName(username);
    }
}
