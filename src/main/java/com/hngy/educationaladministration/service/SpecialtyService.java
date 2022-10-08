package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.Specialty;

import java.util.List;

public interface SpecialtyService {

    List getSpecialtys(Integer offset, String getName, Long sectionid, Long id_institute);
    long getSpecialtyCount(Integer offset, String getName, Long sectionid, Long id_institute);
    long delSpecialty(Specialty id, Long id_institute) throws MyException;
    long putSpecialty(Specialty specialty,Long id_institute) throws MyException;
    long postSpecialty(Specialty specialty,Long id_institute) throws MyException;

    long delSpecialtyBySection(Long sectionid);
}
