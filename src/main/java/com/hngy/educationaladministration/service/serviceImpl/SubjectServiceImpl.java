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
            throw new MyException("已存在");
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

        // 设置分页查询条件
        SubjectExample subjectExample = new SubjectExample();
        //添加查询条件
        subjectExample = addCondition(subjectExample,offset,keyWord,sectionId,id_institute);

        if(subjectExample == null)
            return null;

        //如果 offset 为 -1 则只查询 id 和 name 列
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

        // 设置分页查询条件
        SubjectExample subjectExample = new SubjectExample();
        //添加查询条件
        subjectExample = addCondition(subjectExample,offset,keyWord,sectionId,id_institute);

        return subjectMapper.countByExample(subjectExample);
    }

    @Override
    public long insertSubject(SubjectWithBLOBs subject, long id_institute) throws MyException {
        SubjectExample subjectExample = new SubjectExample();
        subjectExample.createCriteria().andProjectnameEqualTo(subject.getProjectname());
        List list = subjectMapper.selectByExample(subjectExample);

        if(list.size()>0){
            throw new MyException("该课题已经存在");
        }
        check(subject,id_institute);
        return subjectMapper.insert(subject);
    }

    @Override
    public int delSubject(SubjectWithBLOBs subject,long id_institute) {
        check(subject,id_institute);
        //调用 清楚学生
        teacherService.deleteSelected(subject.getId());
        //删除 课题
        return teacherService.deleteProject(subject.getId());
    }


    @Override
    public int updateSuject(SubjectWithBLOBs subject,long id_institute) {
        check(subject,id_institute);

        Subject subject1 = subjectMapper.selectByPrimaryKey(subject.getId());
        //如果修改了课题审核状态 ， 则需要把学生移除
        //例如 从审核通过 改为 不通过 或着 未审核
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
     * get学生选题的状态
     * @param id_institute 学院id
     * @return 学生和课题关系的List
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
     * 选某个课题的所有学生
     * @param idProject
     * @param id_institute 学院id
     * @return Msg 里面有 students ， statuse
     */
    @Override
    public Msg getStuentBySubject(Long idProject, long id_institute) {
        if(idProject==null)
            return null;

        //选该课题所有学生关系
        List<SubjectRelationStudent> s = getSelectSubjected(idProject,id_institute);

        // 统计 选该课题并且被老师同意的
        long count = 0;
        for(int i = 0; i < s.size(); i++){
            if(s.get(i).getStuselectFlag()==3)
                count++;
        }

        List<Long> idStudents = new ArrayList<>();

        Map map = new HashMap<>();

        for(int i = 0, len = s.size(); i < len; i++){

            //存入学生id
            idStudents.add(s.get(i).getIdStudent());

            map.put(
                    s.get(i).getIdStudent(),
                    s.get(i).getStuselectFlag()
            );
        }

        if(idStudents==null || idStudents.size()==0){
            return Msg.error("没有学生选这门课")
                    .add("statuse",map)
                    //被老师选的人数
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
                //被老师选的人数
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
            throw new MyException("已存在");
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

        //代表为超管，则可以查询所有；
        if(id_institute==-1)
        {
            return subjectExample.newAndCreateCriteria()
                    .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andProjectnameLike("%"+keyWord+"%")) //根据课题名
                    .andIdTeacherIn(techerIDs)

                    .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andTeachernamesLike("%"+keyWord+"%")) //根据教师名
                    .andIdTeacherIn(techerIDs)

                    .example()
                    .when(offset!=-1 && offset!=0,criteria -> criteria.page(offset-1, 10));// 查询第offest-1页数据（每页10条）;

        }

        return subjectExample.newAndCreateCriteria()
                .example().or()
                .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andProjectnameLike("%"+keyWord+"%")) //根据课题名
                .andIdTeacherIn(techerIDs)

                .example().or()
                .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andTeachernamesLike("%"+keyWord+"%")) //根据教师名
                .andIdTeacherIn(techerIDs)

                .example()
                .when(offset!=-1 && offset!=0,criteria -> criteria.page(offset-1, 10));// 查询第offest-1页数据（每页10条）;

    }
}
