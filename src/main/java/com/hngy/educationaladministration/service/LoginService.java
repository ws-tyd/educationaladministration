package com.hngy.educationaladministration.service;


import com.hngy.educationaladministration.entity.Student;

public interface LoginService {

    Student selectByName(String username);
}
