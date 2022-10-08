package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.MyClass;

import java.util.List;

public interface ClassService {

    List<MyClass> getClasses(Integer offset, String getName, Long specialtyid, Long id_institute);
    long getClassesCount(Integer offset, String getName, Long specialtyid, Long id_institute);
    long delClass(MyClass myClass, Long id_institute) throws MyException;
    long putClass(MyClass myClass, Long id_institute) throws MyException;
    long postClass(MyClass myClass, Long id_institute) throws MyException;

    long delClassBySpecialty(Long specialtyid);
}
