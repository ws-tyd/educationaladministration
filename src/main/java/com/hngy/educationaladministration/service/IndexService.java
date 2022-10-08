package com.hngy.educationaladministration.service;


import com.hngy.educationaladministration.entity.MyClass;
import com.hngy.educationaladministration.entity.Student;
import com.hngy.educationaladministration.entity.Subject;

public interface IndexService {

   public MyClass studentinfo(Long id);

   public int updatepassword(String password, Long id);

   public MyClass selectByclassName(String className);

   public int updateBymodifyinfo(Student student);

   public Student selectByid(Long id);

   public Subject indexinfo(Long studentid);

   public Long projectselectedstuflag(Long id);
}
