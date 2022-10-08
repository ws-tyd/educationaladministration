package com.hngy.educationaladministration;
import com.hngy.educationaladministration.entity.Teacher;
import com.hngy.educationaladministration.mapper.StudentMapper;
import com.hngy.educationaladministration.mapper.TeacherMapper;
import org.junit.Test;
import com.hngy.educationaladministration.mapper.ProjectselectedMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test1 {

    @Autowired
    StudentMapper studentMapper;
    @Test
    public void t() {
//        studentMapper.select_studentXT_all(null, null, null, null, class_id, class_name)
    }

}
