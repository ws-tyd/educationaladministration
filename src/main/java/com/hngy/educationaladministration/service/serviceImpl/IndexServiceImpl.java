package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.MyClassMapper;
import com.hngy.educationaladministration.mapper.StudentMapper;
import com.hngy.educationaladministration.mapper.SubjectMapper;
import com.hngy.educationaladministration.mapper.SubjectselectedMapper;
import com.hngy.educationaladministration.service.IndexService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private MyClassMapper classMapper;


    @Autowired
    private SubjectMapper projectMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SubjectselectedMapper selectBystudentid;

    @Override
    public MyClass studentinfo(Long id) {
        return classMapper.selectById(id);
    }

    @Override
    public int updatepassword(String password, Long id) {
        StudentWithBLOBs student = new StudentWithBLOBs();
        student.setId(id);
        student.setPwd(password);
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public MyClass selectByclassName(String className) {
        return classMapper.selectByclassName(className);
    }

    @Override
    public int updateBymodifyinfo(Student student) {
        return studentMapper.updateBymodifyinfo(student);
    }

    @Override
    public Student selectByid(Long id) {
        return studentMapper.selectByid(id);
    }

    @Override
    public Subject indexinfo(Long studentid) {
        List<Subject> project = projectMapper.selectBystudentidprojectName(studentid);
        if (project.size() == 0 || project == null) return null;
        return project.get(0);
    }

    @Override
    public Long projectselectedstuflag(Long id) {
        List<Subjectselected> tprojectselecteds = selectBystudentid.selectBystudentid(id);
        if (tprojectselecteds != null && tprojectselecteds.size() > 0) {
            return tprojectselecteds.get(0).getStuselectFlag();
        }
        return null;
    }


}
