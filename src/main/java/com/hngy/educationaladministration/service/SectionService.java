package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.Section;

import java.util.List;

public interface SectionService {
    Section getSection(long id);
    List<Section> getSections(long id_institute);
    long delSection(Section section);
    long updateSection(Section section,String name,long id_institute)throws MyException;
    long addSection(Section section,long id);

}
