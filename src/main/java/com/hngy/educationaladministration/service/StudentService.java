package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.*;

import java.util.List;
import java.util.Map;

public interface StudentService {

    //分页查询
    List<StudentWithBLOBs> selectStudents(Integer offset, String keyWord, Long classId , Long specialtyId, Long id_institute) throws MyException;

    //分页查询的总量
    long selectStudentsCount(Integer offset, String keyWord, Long classId,Long specialtyId, Long id_institute);

    long delStudent(StudentWithBLOBs student, Long id_institute) throws MyException;

    long addStudent(StudentWithBLOBs student,Long id_institute) throws MyException;

    long updateStudent(StudentWithBLOBs student,Long id_institute) throws MyException;

    long delStudentByClass(Long classId);

    List<Student> selectByName(String username);

    void updateselectnumReduce(String project_name);

    void updateselectnumAdd(String project_name);


}
