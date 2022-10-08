package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.*;

import java.util.List;

public interface SubjectService {
    //课题来源

    List<SubjectSource> selectSubjectSources();

    long insertSubjectSource(String name) throws MyException;

    int delSubjectSource(long id);

    int updateSubjectSource(SubjectSource source);

    //课题类型

    List<SubjectType> selectSubjectTypes();

    long insertSubjectType(String name) throws MyException;

    int delSubjectType(long id);

    int updateSubjectType(SubjectType subjectType);

    //课题管理

    List<SubjectWithBLOBs> selectSubjects(Integer offset, String keyWord, Long sectionId, Long id_institute);

    long selectSubjectsCount(Integer offset, String keyWord, Long sectionId, Long id_institute);

    long insertSubject(SubjectWithBLOBs subjectWithBLOBs, long id_institute) throws MyException;

    int delSubject(SubjectWithBLOBs subject,long id_institute);

    int updateSuject(SubjectWithBLOBs subject,long id_institute);

    /**
     * get学生选题的状态
     * @param id_institute
     * @return
     */
    List<SubjectRelationStudent> getSelectSubjected(Long idProject,long id_institute);

    /**
     * 选某个课题的所有学生
     * @param idProject
     * @return
     */
    Msg getStuentBySubject(Long idProject , long id_institute);

    /**
     * 根据ID 查询 课题
     */

    SubjectWithBLOBs getSubjectByID(Long id);

    /**
     * 教师根据ID 选择性更新
     */

    Integer updateSubjectByid(SubjectWithBLOBs subjectWithBLOBs);
}
