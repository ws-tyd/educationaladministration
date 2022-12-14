package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.controller.AdminController;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.enumeration.MyEnum;
import com.hngy.educationaladministration.mapper.*;
import com.hngy.educationaladministration.service.SectionService;
import com.hngy.educationaladministration.service.StudentService;
import com.hngy.educationaladministration.service.SubjectService;
import com.hngy.educationaladministration.service.TeacherService;
import com.hngy.educationaladministration.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    GeneralPurposeMapper mapper;
    @Autowired
    SubjectSourceMapper subjectSourceMapper;
    @Autowired
    SubjectTypeMapper subjectTypeMapper;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    SectionService sectionService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    SubjectRelationStudentMapper subjectRelationStudentMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Override
    public List<SubjectSource> selectSubjectSources() {
        return subjectSourceMapper.selectByExample(new SubjectSourceExample());
    }


    @Override
    public int delSubjectSource(long id) {
        return subjectSourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateSubjectSource(SubjectSource source) {
        return subjectSourceMapper.updateByPrimaryKey(source);
    }

    @Override
    public List<SubjectType> selectSubjectTypes() {
        return subjectTypeMapper.selectByExample(new SubjectTypeExample());
    }

    @Override
    public long insertSubjectType(String name) throws MyException {

        SubjectTypeExample subjectTypeExample = new SubjectTypeExample();
        subjectTypeExample.createCriteria().andTypenameEqualTo(name);
        List<SubjectType> types = subjectTypeMapper.selectByExample(subjectTypeExample);
        if(types.size()>0)
        {
            throw new MyException("?????????");
        }
        return mapper.insert("insert into t_projecttype(typeName) values('"+name+"')");
    }

    @Override
    public int delSubjectType(long id) {
        return subjectTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateSubjectType(SubjectType subjectType) {
        return subjectTypeMapper.updateByPrimaryKey(subjectType);
    }

    @Override
    public List<SubjectWithBLOBs> selectSubjects(Integer offset, String keyWord, Long sectionId, Long id_institute) {

        // ????????????????????????
        SubjectExample subjectExample = new SubjectExample();
        //??????????????????
        subjectExample = addCondition(subjectExample,offset,keyWord,sectionId,id_institute);

        if(subjectExample == null)
            return null;

        //?????? offset ??? -1 ???????????? id ??? name ???
        if(offset==-1)
        {
            return subjectMapper.selectByExampleSelective(subjectExample,
                    SubjectWithBLOBs.Column.id,
                    SubjectWithBLOBs.Column.projectname);
        }else{
            return subjectMapper.selectByExampleSelective(subjectExample);
        }

    }



    @Override
    public long selectSubjectsCount(Integer offset, String keyWord, Long sectionId, Long id_institute) {

        // ????????????????????????
        SubjectExample subjectExample = new SubjectExample();
        //??????????????????
        subjectExample = addCondition(subjectExample,offset,keyWord,sectionId,id_institute);

        return subjectMapper.countByExample(subjectExample);
    }

    @Override
    public long insertSubject(SubjectWithBLOBs subject, long id_institute) throws MyException {
        SubjectExample subjectExample = new SubjectExample();
        subjectExample.createCriteria().andProjectnameEqualTo(subject.getProjectname());
        List list = subjectMapper.selectByExample(subjectExample);

        if(list.size()>0){
            throw new MyException("?????????????????????");
        }
        check(subject,id_institute);
        return subjectMapper.insert(subject);
    }

    @Override
    public int delSubject(SubjectWithBLOBs subject,long id_institute) {
        check(subject,id_institute);
        //?????? ????????????
        teacherService.deleteSelected(subject.getId());
        //?????? ??????
        return teacherService.deleteProject(subject.getId());
    }


    @Override
    public int updateSuject(SubjectWithBLOBs subject,long id_institute) {
        check(subject,id_institute);

        Subject subject1 = subjectMapper.selectByPrimaryKey(subject.getId());
        //????????????????????????????????? ??? ????????????????????????
        //?????? ??????????????? ?????? ????????? ?????? ?????????
        if(
                subject1.getVerifyprojectFlag()== MyEnum.PASS.getValue()
                && subject.getVerifyprojectFlag()!= MyEnum.PASS.getValue()
        ){
            SubjectRelationStudentExample srsExample = new SubjectRelationStudentExample();
            srsExample.createCriteria().andIdProjectEqualTo(subject.getId());

            subjectRelationStudentMapper.deleteByExample(srsExample);
        }

        return subjectMapper.updateByPrimaryKey(subject);
    }

    /**
     * get?????????????????????
     * @param id_institute ??????id
     * @return ????????????????????????List
     */
    @Override
    public List<SubjectRelationStudent> getSelectSubjected(Long idProject,long id_institute) {
        List<StudentWithBLOBs> students = studentService.selectStudents(-1,null,null,null,id_institute);
        System.out.println(students);
        List<Long> studnetIds = students.stream().map(Student::getId).collect(Collectors.toList());
        System.out.println(studnetIds);

        if(students==null || students.size()==0)
            return null;
        return subjectRelationStudentMapper.selectByExample(
                new SubjectRelationStudentExample().newAndCreateCriteria()
                        .andIdStudentIn(studnetIds)
                        .when(idProject!=null,criteria -> criteria.andIdProjectEqualTo(idProject))
                        .example()
        );
    }

    /**
     * ??????????????????????????????
     * @param idProject
     * @param id_institute ??????id
     * @return Msg ????????? students ??? statuse
     */
    @Override
    public Msg getStuentBySubject(Long idProject, long id_institute) {
        if(idProject==null)
            return null;

        //??????????????????????????????
        List<SubjectRelationStudent> s = getSelectSubjected(idProject,id_institute);

        // ?????? ????????????????????????????????????
        long count = 0;
        for(int i = 0; i < s.size(); i++){
            if(s.get(i).getStuselectFlag()==3)
                count++;
        }

        List<Long> idStudents = new ArrayList<>();

        Map map = new HashMap<>();

        for(int i = 0, len = s.size(); i < len; i++){

            //????????????id
            idStudents.add(s.get(i).getIdStudent());

            map.put(
                    s.get(i).getIdStudent(),
                    s.get(i).getStuselectFlag()
            );
        }

        if(idStudents==null || idStudents.size()==0){
            return Msg.error("????????????????????????")
                    .add("statuse",map)
                    //?????????????????????
                    .add("count",count);
        }
//        System.out.println("idStudents:"+idStudents);

        List<StudentWithBLOBs> studentWithBLOBs =  studentMapper.selectByExampleSelective(
                new StudentExample().newAndCreateCriteria().andIdIn(idStudents).example(),
                StudentWithBLOBs.Column.id,
                StudentWithBLOBs.Column.idClass,
                StudentWithBLOBs.Column.name,
                StudentWithBLOBs.Column.stunum
        );

        return Msg.success()
                .add("students",studentWithBLOBs)
                .add("statuse",map)
                //?????????????????????
                .add("count",count)
                ;
    }

    @Override
    public SubjectWithBLOBs getSubjectByID(Long id) {

        if(id!=null){
            return subjectMapper.selectByPrimaryKey(id);
        }else{
            return null;
        }

    }

    @Override
    public Integer updateSubjectByid(SubjectWithBLOBs subjectWithBLOBs) {

        return subjectMapper.updateByPrimaryKeySelective(subjectWithBLOBs);
    }

    @Override
    public long insertSubjectSource(String name) throws MyException {

        SubjectSourceExample subjectSourceExample = new SubjectSourceExample();
        subjectSourceExample.createCriteria().andSourcenameEqualTo(name);
        List<SubjectSource> sources = subjectSourceMapper.selectByExample(subjectSourceExample);
        if(sources.size()>0)
        {
            throw new MyException("?????????");
        }
        return mapper.insert("insert into t_projectsource(sourceName) values('"+name+"')");
    }


    private void check(SubjectWithBLOBs subject, long id_institute) {

    }

    private SubjectExample addCondition(SubjectExample subjectExample, Integer offset, String keyWord, Long sectionId, Long id_institute) {

        List<Teacher> teachers = teacherService.selectTeachers(-1,null,sectionId,id_institute);

        if(teachers.size()==0||teachers==null)
            return null;

        List<Long> techerIDs = teachers.stream().map(Teacher::getId).collect(Collectors.toList());

        //??????????????????????????????????????????
        if(id_institute==-1)
        {
            return subjectExample.newAndCreateCriteria()
                    .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andProjectnameLike("%"+keyWord+"%")) //???????????????
                    .andIdTeacherIn(techerIDs)

                    .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andTeachernamesLike("%"+keyWord+"%")) //???????????????
                    .andIdTeacherIn(techerIDs)

                    .example()
                    .when(offset!=-1 && offset!=0,criteria -> criteria.page(offset-1, 10));// ?????????offest-1??????????????????10??????;

        }

        return subjectExample.newAndCreateCriteria()
                .example().or()
                .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andProjectnameLike("%"+keyWord+"%")) //???????????????
                .andIdTeacherIn(techerIDs)

                .example().or()
                .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andTeachernamesLike("%"+keyWord+"%")) //???????????????
                .andIdTeacherIn(techerIDs)

                .example()
                .when(offset!=-1 && offset!=0,criteria -> criteria.page(offset-1, 10));// ?????????offest-1??????????????????10??????;

    }
}
