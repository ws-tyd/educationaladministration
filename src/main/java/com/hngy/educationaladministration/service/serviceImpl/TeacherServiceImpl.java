package com.hngy.educationaladministration.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.Class;
import com.hngy.educationaladministration.entity.dto.*;
import com.hngy.educationaladministration.mapper.*;
import com.hngy.educationaladministration.service.PublicService;
import com.hngy.educationaladministration.service.SectionService;
import com.hngy.educationaladministration.service.SubjectService;
import com.hngy.educationaladministration.service.TeacherService;
import com.hngy.educationaladministration.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    WorkapprovalMapper workapprovalMapper;

    @Autowired
    WorkapprovaldataMapper workapprovaldataMapper;

    @Autowired
    DebriefingMapper debriefingMapper;

    @Autowired
    SectionService sectionService;

    @Autowired
    PublicService publicService;

    @Autowired
    WorkloadMapper workloadMapper;

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    SectionMapper sectionMapper;

    @Autowired
    InstituteMapper instituteMapper;


    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);
    @Autowired
    DebriefingYearMapper debriefingYearMapper;

    @Autowired
    AnnualAssessmentMapper annualAssessmentMapper;

    @Autowired
    TechnicalPersonnelMapper technicalPersonnelMapper;

    @Autowired
    StaticMapper staticMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectselectedMapper projectselectedMapper;
    @Autowired
    ClassMapper classMapper;

    @Override
    public int teacherDL(String name, String pwd) {
        TeacherWithBLOBs teacher = selectTeacher(name);
        if (teacher == null) {
            return 300;//账号错误
        } else if (teacher != null && teacher.getPwd().equals(pwd)) {
            return 200;//账号密码正确
        } else {
            return 100;//密码错误
        }
    }

    @Override
    public TeacherWithBLOBs selectTeacher(String username) {
        return teacherMapper.selectTeacherInfo(username);
    }

    @Override
    public List<WorkapprovalWithBLOBs> selectTeacherWorkAll(Long id_teacher) {
        WorkapprovalExample example = new WorkapprovalExample();
        WorkapprovalExample.Criteria criteria = example.createCriteria();
        criteria.andIdTeacherEqualTo(id_teacher);
        return workapprovalMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<WorkapprovalWithBLOBs> selectWorkSuccess(Long id_teacher) {
        return workapprovalMapper.selectByApprovalFlag(id_teacher, 1);
    }

    @Override
    public List<WorkapprovalWithBLOBs> selectWorkFailed(Long id_teacher) {
        return workapprovalMapper.selectByApprovalFlag(id_teacher, 2);
    }

    @Override
    public List<WorkapprovalWithBLOBs> selectWorkSubmitted(Long id_teacher) {
        return workapprovalMapper.selectByApprovalFlag(id_teacher, 0);
    }

    @Override
    public int deleteWorkById(Long id) {
       return workapprovalMapper.deleteWork(id);
    }

    @Override
    public WorkapprovalWithBLOBs selectWorkById(Long id) {
        return workapprovalMapper.selectById(id);
    }

    @Override
    public int teacherUpdetpwd(String username, String oldpwd, String newpwd) {
        TeacherWithBLOBs teacher = selectTeacher(username);
        if (teacher.getPwd().equals(oldpwd)) {
            teacher.setPwd(newpwd);
            TeacherExample example = new TeacherExample();
            TeacherExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(username);
            teacherMapper.updateByExampleSelective(teacher, example);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void teacherupdateInfo(TeacherWithBLOBs teacher) {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(teacher.getId());
        teacherMapper.updateByExampleSelective(teacher, example);
    }

    @Override
    public void insertWordData(Workapprovaldata workapprovaldata) {
        workapprovaldataMapper.insertSelective(workapprovaldata);
    }

    @Override
    public List<Workapprovaldata> selectWorkData(Long section_id) {
        return workapprovaldataMapper.selectSection(section_id);
    }

    @Override
    public Workapprovaldata selectWorkDataById(Long id) {
        return workapprovaldataMapper.selectById(id);
    }

    @Override
    public int selectTermDebriefingFlag(Long id_teacher, Long year, String term) {
        return debriefingMapper.selectFlag(id_teacher, year, term);
    }

    @Override
    public int insertTermDebriefing(DebriefingWithBLOBs debriefingWithBLOBs) {
        return debriefingMapper.insertSelective(debriefingWithBLOBs);
    }

    @Override
    public void updateTermDebriefing(DebriefingWithBLOBs debriefingWithBLOBs) {
        DebriefingExample example = new DebriefingExample();
        DebriefingExample.Criteria criteria = example.createCriteria();
        criteria.andIdTeacherEqualTo(debriefingWithBLOBs.getIdTeacher());
        debriefingMapper.updateByExampleSelective(debriefingWithBLOBs, example);
    }

    @Override
    public List<TeacherWithBLOBs> selectTeachers(Integer offset, String getName, Long sectionid, Long id_institute) {

        // 设置分页查询条件
        TeacherExample teacherExample = new TeacherExample();
//        添加查询条件
        teacherExample = addCondition(teacherExample, offset, id_institute, getName, sectionid);

        if (teacherExample == null)
            return null;

        //如果 offset 为 -1 则只查询 id 和 name 列
        if (offset == -1) {
            return teacherMapper.selectByExampleSelective(teacherExample,
                    TeacherWithBLOBs.Column.id,
                    TeacherWithBLOBs.Column.name);
        } else {
            return teacherMapper.selectByExampleSelective(teacherExample);
        }
    }

    @Override
    public long selectTeachersCount(Integer offset, String getName, Long sectionid, Long id_institute) {

        // 设置分页查询条件
        TeacherExample teacherExample = new TeacherExample();
        //        添加查询条件
        teacherExample = addCondition(teacherExample, offset, id_institute, getName, sectionid);

        if (teacherExample == null)
            return 0;

        return teacherMapper.countByExample(teacherExample);
    }

    @Override
    public long delTeacher(TeacherWithBLOBs teacher, Long id_institute) throws MyException {
        check(teacher, id_institute);
        return teacherMapper.deleteByPrimaryKey(teacher.getId());
    }

    @Override
    public long addTeacher(@Validated TeacherWithBLOBs teacher, Long id_institute) throws MyException {
        publicService.CheckIfTheUsernameIsDuplicated(teacher.getUsername());
        check(teacher, id_institute);
        return teacherMapper.insert(teacher);
    }

    @Override
    public long updateTeacher(TeacherWithBLOBs teacher, Long id_institute) throws MyException {
        check(teacher, id_institute);
        //不更新username
        teacher.setUsername(null);
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }


    /**
     * 保存工作量     将组合表分解保存到数据库
     *
     * @param workloadDTO
     * @return
     */
    @Override
    public long saveWorkload(WorkloadDTO workloadDTO, Teacher teacher) {
        WorkloadWithBLOBs workload = new WorkloadWithBLOBs();

        if (workloadDTO == null)
            return 0;

        // 拆分教学工作量
        if (workloadDTO.getTeachingWork() != null) {
            List<String> classes = new ArrayList<>();
            List<String> courseName = new ArrayList<>();
            List<String> classHourOfPlan = new ArrayList<>();
            List<String> jointClass = new ArrayList<>();
            List<String> extracurricularExperiment = new ArrayList<>();
            List<String> courseDesign = new ArrayList<>();
            List<String> on_campusPractice = new ArrayList<>();
            List<String> off_campusTraining = new ArrayList<>();
            List<String> GraduationPractice = new ArrayList<>();

            ArrayList<TeachingWork> teachingWork = workloadDTO.getTeachingWork();
            for (int i = 0, len = teachingWork.size(); i < len; i++) {
                classes.add(teachingWork.get(i).getClasses());
                courseName.add(teachingWork.get(i).getCourseName());
                classHourOfPlan.add(teachingWork.get(i).getClassHourOfPlan());
                jointClass.add(teachingWork.get(i).getJojintClass());
                extracurricularExperiment.add(teachingWork.get(i).getExtracurricularExperiment());
                courseDesign.add(teachingWork.get(i).getCourseDesign());
                on_campusPractice.add(teachingWork.get(i).getOn_campusPractice());
                off_campusTraining.add(teachingWork.get(i).getOff_campusTraining());
                GraduationPractice.add(teachingWork.get(i).getGraduationPractice());
            }

            workload.setClasses(JSON.toJSONString(classes));
            workload.setCoursename(JSON.toJSONString(courseName));
            workload.setClasshourofplan(JSON.toJSONString(classHourOfPlan));
            workload.setJointclass(JSON.toJSONString(jointClass));
            workload.setExtracurricularexperiment(JSON.toJSONString(extracurricularExperiment));
            workload.setCoursedesign(JSON.toJSONString(courseDesign));
            workload.setOnCampuspractice(JSON.toJSONString(on_campusPractice));
            workload.setOffCampustraining(JSON.toJSONString(off_campusTraining));
            workload.setGraduationpractice(JSON.toJSONString(GraduationPractice));

        }

        //拆分教研科研工作量

        if (workloadDTO.getTseswdas() != null) {
            List<String> rsrProjectName = new ArrayList<>();
            List<String> rsrProjectBrief = new ArrayList<>();
            List<String> rsrProjectStatus = new ArrayList<>();
            List<String> rsrConversionWorkload = new ArrayList<>();
            List<String> rsrPProjectLeader = new ArrayList<>();

            ArrayList<TSESWDA> tseswdas = workloadDTO.getTseswdas();
            for (int i = 0, len = tseswdas.size(); i < len; i++) {
                rsrProjectName.add(tseswdas.get(i).getRsrProjectName());
                rsrProjectBrief.add(tseswdas.get(i).getRsrProjectBrief());
                rsrProjectStatus.add(tseswdas.get(i).getRsrProjectStatus());
                rsrConversionWorkload.add(tseswdas.get(i).getRsrConversionWorkload());
                rsrPProjectLeader.add(tseswdas.get(i).getRsrPProjectLeader());
            }

            workload.setRsrprojectname(JSON.toJSONString(rsrProjectName));
            workload.setRsrprojectbrief(JSON.toJSONString(rsrProjectBrief));
            workload.setRsrprojectstatus(JSON.toJSONString(rsrProjectStatus));
            workload.setRsrconversionworkload(JSON.toJSONString(rsrConversionWorkload));
            workload.setRsrpprojectleader(JSON.toJSONString(rsrPProjectLeader));

        }

        //教师
        workload.setIdTeacher(teacher.getId());

        //补助工作量
        if (workloadDTO.getSubsidyworkloadDATA() != null) {
            TSESWDA s = workloadDTO.getSubsidyworkloadDATA();
            workload.setSprojectname(s.getRsrProjectName());
            workload.setSprojectbrief(s.getRsrProjectBrief());
            workload.setSprojectstatus(s.getRsrProjectStatus());
            workload.setSconversionworkload(s.getRsrConversionWorkload());
            workload.setSpprojectleader(s.getRsrPProjectLeader());
        }

        workload.setSum1(workloadDTO.getSum1());
        workload.setSum2(workloadDTO.getSum2());
        workload.setSum1addsum2(workloadDTO.getSum1AddSum2());
        workload.setTotalsum(workloadDTO.getTotalSum());
        workload.setSubsidyworkload(workloadDTO.getSubsidyworkload());
        workload.setYear(workloadDTO.getYear());
        workload.setTerm(workloadDTO.getTrem());
        workload.setSectionverify(workloadDTO.getSectionVerify());
        workload.setInstituteverify(workloadDTO.getInstituteVerify());

        //不存在插入，存在更新
        WorkloadExample workloadExample = new WorkloadExample()
                .or()
                .andTermEqualTo(workload.getTerm())
                .andYearEqualTo(workload.getYear())
                .andIdTeacherEqualTo(workload.getIdTeacher())
                .example();

        WorkloadWithBLOBs value = workloadMapper.selectOneByExampleWithBLOBs(workloadExample);
        if (value != null) {
            workload.setId(value.getId());
            return workloadMapper.updateByExampleWithBLOBs(workload, workloadExample);
        } else {
            return workloadMapper.insert(workload);
        }
    }

    /**
     * 将工作量拆分 展示到页面去
     *
     * @param teacherID
     * @param year
     * @param term
     * @return
     */
    @Override
    public WorkloadDTO getWorkload(long teacherID, String year, String term) {

        WorkloadDTO workloadDTO = new WorkloadDTO();

        //条件不满足直接为空
        if (year == null || year.length() == 0)
            return null;
        if (term == null || term.length() == 0)
            return null;
        //从数据查询
        WorkloadWithBLOBs workload = workloadMapper.selectOneByExampleWithBLOBs(new WorkloadExample()
                .or()
                .andIdTeacherEqualTo(teacherID)
                .andYearEqualTo(year)
                .andTermEqualTo(term)
                .example());


        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherID);
        Section section = sectionMapper.selectByPrimaryKey(teacher.getIdSection());
        Institute institute = instituteMapper.selectByPrimaryKey(section.getIdInstitute());

        workloadDTO.setInstitute(institute.getInstituteName());
        workloadDTO.setSection(section.getSectionName());

        if (workload == null)
            return workloadDTO;

        List<String> classes = JSON.parseArray(workload.getClasses(), String.class);
        List<String> courseName = JSON.parseArray(workload.getCoursename(), String.class);
        List<String> classHourOfPlan = JSON.parseArray(workload.getClasshourofplan(), String.class);
        List<String> jointClass = JSON.parseArray(workload.getJointclass(), String.class);
        List<String> extracurricularExperiment = JSON.parseArray(workload.getExtracurricularexperiment(), String.class);
        List<String> courseDesign = JSON.parseArray(workload.getCoursedesign(), String.class);
        List<String> on_campusPractice = JSON.parseArray(workload.getOnCampuspractice(), String.class);
        List<String> off_campusTraining = JSON.parseArray(workload.getOffCampustraining(), String.class);
        List<String> GraduationPractice = JSON.parseArray(workload.getGraduationpractice(), String.class);

        ArrayList<TeachingWork> teachingWorks = new ArrayList<>();
        TeachingWork teachingWork = null;
        for (int i = 0, len = classes.size(); i < len; i++) {
            teachingWork = new TeachingWork();

            teachingWork.setClasses(classes.get(i));
            teachingWork.setCourseName(courseName.get(i));
            teachingWork.setClassHourOfPlan(classHourOfPlan.get(i));
            teachingWork.setJojintClass(jointClass.get(i));
            teachingWork.setExtracurricularExperiment(extracurricularExperiment.get(i));
            teachingWork.setCourseDesign(courseDesign.get(i));
            teachingWork.setOn_campusPractice(on_campusPractice.get(i));
            teachingWork.setOff_campusTraining(off_campusTraining.get(i));
            teachingWork.setGraduationPractice(GraduationPractice.get(i));

            teachingWorks.add(teachingWork);
        }
        List<String> rsrProjectName = JSON.parseArray(workload.getRsrprojectname(), String.class);
        List<String> rsrProjectBrief = JSON.parseArray(workload.getRsrprojectbrief(), String.class);
        List<String> rsrProjectStatus = JSON.parseArray(workload.getRsrprojectstatus(), String.class);
        List<String> rsrConversionWorkload = JSON.parseArray(workload.getRsrconversionworkload(), String.class);
        List<String> rsrPProjectLeader = JSON.parseArray(workload.getRsrpprojectleader(), String.class);

        ArrayList<TSESWDA> tseswdas = new ArrayList<>();
        TSESWDA tseswda = null;
        for (int i = 0, len = rsrProjectName.size(); i < len; i++) {
            tseswda = new TSESWDA();

            tseswda.setRsrProjectName(rsrProjectName.get(i));
            tseswda.setRsrProjectBrief(rsrProjectBrief.get(i));
            tseswda.setRsrProjectStatus(rsrProjectStatus.get(i));
            tseswda.setRsrConversionWorkload(rsrConversionWorkload.get(i));
            tseswda.setRsrPProjectLeader(rsrPProjectLeader.get(i));

            tseswdas.add(tseswda);
        }

        //补助工作量
        TSESWDA s = new TSESWDA();
        s.setRsrProjectName(workload.getSprojectname());
        s.setRsrProjectBrief(workload.getSprojectbrief());
        s.setRsrProjectStatus(workload.getSprojectstatus());
        s.setRsrConversionWorkload(workload.getSconversionworkload());
        s.setRsrPProjectLeader(workload.getSpprojectleader());

        workloadDTO.setSubsidyworkloadDATA(s);

        //简单变量赋值
        workloadDTO.setTseswdas(tseswdas);
        workloadDTO.setTeachingWork(teachingWorks);
        workloadDTO.setSum1(workload.getSum1());
        workloadDTO.setSum2(workload.getSum2());
        workloadDTO.setSum1AddSum2(workload.getSum1addsum2());
        workloadDTO.setTotalSum(workload.getTotalsum());
        workloadDTO.setSubsidyworkload(workload.getSubsidyworkload());
        workloadDTO.setYear(workload.getYear());
        workloadDTO.setTrem(workload.getTerm());
        workloadDTO.setSectionVerify(workload.getSectionverify());
        workloadDTO.setInstituteVerify(workload.getInstituteverify());

//        System.out.println("w::"+workloadDTO);


        return workloadDTO;
    }

    /**
     * 保存业务表
     *
     * @param businessDTO
     * @param teacher
     */
    @Override
    public long saveBusiness(BusinessDTO businessDTO, Teacher teacher) {

        BusinessWithBLOBs business = new BusinessWithBLOBs();

        //为空判断
        if (businessDTO == null)
            return 0;

        //完成教学工作量
        if (businessDTO.getCompleteWorkload() != null) {
            ArrayList<String> courseName = new ArrayList<>();
            ArrayList<String> classes = new ArrayList<>();
            ArrayList<String> theoryClass = new ArrayList<>();
            ArrayList<String> guidingExperiment = new ArrayList<>();
            ArrayList<String> guidingDesign = new ArrayList<>();
            ArrayList<String> guidingInternship = new ArrayList<>();
            ArrayList<String> guideComprehensiveExperiment = new ArrayList<>();
            ArrayList<String> totalClass = new ArrayList<>();

            ArrayList<CompleteWorkload> completeWorkloads = businessDTO.getCompleteWorkload();
            CompleteWorkload completeWorkload = null;
            for (int i = 0, len = completeWorkloads.size(); i < len; i++) {
                completeWorkload = completeWorkloads.get(i);

                courseName.add(completeWorkload.getCourseName());
                classes.add(completeWorkload.getClasses());
                theoryClass.add(completeWorkload.getTheoryClass());
                guidingExperiment.add(completeWorkload.getGuidingInternship());
                guidingDesign.add(completeWorkload.getGuidingDesign());
                guidingInternship.add(completeWorkload.getGuidingInternship());
                guideComprehensiveExperiment.add(completeWorkload.getGuideComprehensiveExperiment());
                totalClass.add(completeWorkload.getTotalClass());
            }

            business.setCoursename(JSON.toJSONString(courseName));
            business.setClasses(JSON.toJSONString(classes));
            business.setTheoryclass(JSON.toJSONString(theoryClass));
            business.setGuidingexperiment(JSON.toJSONString(guidingExperiment));
            business.setGuidingdesign(JSON.toJSONString(guidingDesign));
            business.setGuidinginternship(JSON.toJSONString(guidingInternship));
            business.setGuidecomprehensiveexperiment(JSON.toJSONString(guideComprehensiveExperiment));
            business.setTotalclass(JSON.toJSONString(totalClass));
        }

        //学术成果 start
        if (businessDTO.getAcademicAchievements() != null) {
            AcademicAchievements academicAchievements = businessDTO.getAcademicAchievements();

            if (academicAchievements.getTextBook() != null) {

                ArrayList<String> textBookName = new ArrayList<>();
                ArrayList<String> publishingUnitAndTime = new ArrayList<>();
                ArrayList<String> chiefEditor = new ArrayList<>();
                ArrayList<String> jointlyEdited = new ArrayList<>();
                ArrayList<String> planningTextbook = new ArrayList<>();
                ArrayList<String> schoolBasedTextbook = new ArrayList<>();
                ArrayList<String> other = new ArrayList<>();

                ArrayList<TextBook> textBooks = academicAchievements.getTextBook();
                TextBook textBook = null;

                for (int i = 0, len = textBooks.size(); i < len; i++) {
                    textBook = textBooks.get(i);

                    textBookName.add(textBook.getTextbookName());
                    publishingUnitAndTime.add(textBook.getPublishingUnitAndTime());
                    chiefEditor.add(textBook.getChiefEditor());
                    jointlyEdited.add(textBook.getJointlyEdited());
                    planningTextbook.add(textBook.getPlanningTextbook());
                    schoolBasedTextbook.add(textBook.getSchoolBasedTextbook());
                    other.add(textBook.getOther());
                }

                business.setTextbookname(JSON.toJSONString(textBookName));
                business.setPublishingunitandtime(JSON.toJSONString(planningTextbook));
                business.setChiefeditor(JSON.toJSONString(chiefEditor));
                business.setJointlyedited(JSON.toJSONString(jointlyEdited));
                business.setPlanningtextbook(JSON.toJSONString(planningTextbook));
                business.setSchoolbasedtextbook(JSON.toJSONString(schoolBasedTextbook));
                business.setOther(JSON.toJSONString(other));
            }

            if (academicAchievements.getPaper() != null) {

                ArrayList<String> paperTitle = new ArrayList<>();
                ArrayList<String> publishingSituation = new ArrayList<>();
                ArrayList<String> level = new ArrayList<>();
                ArrayList<String> ranking = new ArrayList<>();

                ArrayList<Paper> papers = academicAchievements.getPaper();
                Paper paper = null;
                for (int i = 0, len = papers.size(); i < len; i++) {
                    paper = papers.get(i);

                    paperTitle.add(paper.getPaperTitle());
                    publishingSituation.add(paper.getPublishingSituation());
                    level.add(paper.getLevel());
                    ranking.add(paper.getRanking());
                }

                business.setPapertitle(JSON.toJSONString(paperTitle));
                business.setPublishingsituation(JSON.toJSONString(publishingSituation));
                business.setLevel(JSON.toJSONString(level));
                business.setRanking(JSON.toJSONString(ranking));
            }

            if (academicAchievements.getProjectOrTopics() != null) {
                ArrayList<String> hostSubject = new ArrayList<>();
                ArrayList<String> cooperationUnit = new ArrayList<>();
                ArrayList<String> category = new ArrayList<>();
                ArrayList<String> myRole = new ArrayList<>();

                ArrayList<ProjectOrTopics> projectOrTopics = academicAchievements.getProjectOrTopics();
                ProjectOrTopics topics = null;
                for (int i = 0, len = projectOrTopics.size(); i < len; i++) {
                    topics = projectOrTopics.get(i);

                    hostSubject.add(topics.getHostSubject());
                    hostSubject.add(topics.getCooperationUnit());
                    hostSubject.add(topics.getCategory());
                    hostSubject.add(topics.getMyRole());
                }

                business.setHostsubject(JSON.toJSONString(hostSubject));
                business.setHostsubject(JSON.toJSONString(cooperationUnit));
                business.setHostsubject(JSON.toJSONString(category));
                business.setHostsubject(JSON.toJSONString(myRole));
            }
        }
        // 学术成果     end

        business.setYear(businessDTO.getYear());
        business.setTrem(businessDTO.getTrem());
        business.setIdTeacher(teacher.getId());

        business.setAwards(businessDTO.getAwards());
        business.setAcademicgroup(businessDTO.getAcademicGroup());
        business.setAcademicgroup(businessDTO.getAcademicGroup());
        business.setContinuingeducation(businessDTO.getContinuingEducation());
        business.setOtherjobs(businessDTO.getOtherJobs());
        business.setSick(businessDTO.getSick());
        //leave 保留关键字。。。。。
        business.setCompassionateleave(businessDTO.getLeave());
        business.setBelate(businessDTO.getBeLate());
        business.setLeaveearly(businessDTO.getLeaveEarly());
        business.setTuneclass(businessDTO.getTuneClass());
        business.setMissingclass(businessDTO.getMissingClass());
        business.setDisciplinarypenalty(businessDTO.getDisciplinaryPenalty());
        business.setTeachingopinion(businessDTO.getTeachingOpinion());
        business.setDepartmentopinion(businessDTO.getDepartmentOpinion());
        business.setSchoolopinion(businessDTO.getSchoolOpinion());

        //不存在就插入, 存在就更新
        BusinessExample example = new BusinessExample()
                .or()
                .andTremEqualTo(business.getTrem())
                .andYearEqualTo(business.getYear())
                .andIdTeacherEqualTo(business.getIdTeacher())
                .example();

        BusinessWithBLOBs value = businessMapper.selectOneByExampleWithBLOBs(example);

        if (value != null) {
            business.setId(value.getId());
            return businessMapper.updateByExampleWithBLOBs(business, example);
        } else {
            return businessMapper.insert(business);
        }
    }

    /**
     * 查询业务表
     *
     * @param teacherID
     * @param year
     * @param trem
     */
    @Override
    public Msg getBusiness(Long teacherID, String year, String trem) {

        BusinessDTO businessDTO = new BusinessDTO();

        //条件不满足直接返回空
        if (year == null || year.length() == 0)
            return Msg.error();
        if (trem == null || trem.length() == 0)
            return Msg.error();

        //查询
        BusinessWithBLOBs business = businessMapper.selectOneByExampleWithBLOBs(new BusinessExample()
                .or()
                .andIdTeacherEqualTo(teacherID)
                .andYearEqualTo(year)
                .andTremEqualTo(trem)
                .example()
        );


        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherID);
        Section section = sectionMapper.selectByPrimaryKey(teacher.getIdSection());
        Institute institute = instituteMapper.selectByPrimaryKey(section.getIdInstitute());

        businessDTO.setInstitute(institute.getInstituteName());
        businessDTO.setSection(section.getSectionName());

        if (business == null)
            return Msg.fail().add("businessDTO", businessDTO);

        businessDTO.setYear(business.getYear());
        businessDTO.setTrem(business.getTrem());
        businessDTO.setTotalWorkload(business.getTotalworkload());
        businessDTO.setAwards(business.getAwards());
        businessDTO.setAcademicGroup(business.getAcademicgroup());
        businessDTO.setContinuingEducation(business.getContinuingeducation());
        businessDTO.setOtherJobs(business.getOtherjobs());
        businessDTO.setSick(business.getSick());
        businessDTO.setLeave(business.getCompassionateleave());
        businessDTO.setBeLate(business.getBelate());
        businessDTO.setLeaveEarly(business.getLeaveearly());
        businessDTO.setTuneClass(business.getTuneclass());
        businessDTO.setMissingClass(business.getMissingclass());
        businessDTO.setDisciplinaryPenalty(business.getDisciplinarypenalty());
        businessDTO.setTeachingOpinion(business.getDepartmentopinion());
        businessDTO.setDepartmentOpinion(business.getDepartmentopinion());
        businessDTO.setSchoolOpinion(business.getSchoolopinion());

        //CompleteWorkload
        ArrayList<CompleteWorkload> completeWorkloads = new ArrayList<>();

        List<String> courseName = JSON.parseArray(business.getCoursename(), String.class);
        List<String> classes = JSON.parseArray(business.getClasses(), String.class);
        List<String> theoryClass = JSON.parseArray(business.getTheoryclass(), String.class);
        List<String> guidingExperiment = JSON.parseArray(business.getGuidingexperiment(), String.class);
        List<String> guidingDesign = JSON.parseArray(business.getGuidingdesign(), String.class);
        List<String> guidingInternship = JSON.parseArray(business.getGuidinginternship(), String.class);
        List<String> guideComprehensiveExperiment = JSON.parseArray(business.getGuidecomprehensiveexperiment(), String.class);
        List<String> totalClass = JSON.parseArray(business.getTotalclass(), String.class);

        if (courseName != null) {
            CompleteWorkload completeWorkload = null;
            for (int i = 0, len = courseName.size(); i < len; i++) {
                completeWorkload = new CompleteWorkload();

                completeWorkload.setCourseName(courseName.get(i));
                completeWorkload.setClasses(classes.get(i));
                completeWorkload.setTheoryClass(theoryClass.get(i));
                completeWorkload.setGuidingExperiment(guidingExperiment.get(i));
                completeWorkload.setGuidingDesign(guidingDesign.get(i));
                completeWorkload.setGuidingInternship(guidingInternship.get(i));
                completeWorkload.setGuideComprehensiveExperiment(guideComprehensiveExperiment.get(i));
                completeWorkload.setTotalClass(totalClass.get(i));

                completeWorkloads.add(completeWorkload);
            }
            businessDTO.setCompleteWorkload(completeWorkloads);
        }

        //academicAchievements
        AcademicAchievements academicAchievements = new AcademicAchievements();

        ArrayList<TextBook> textBooks = new ArrayList<>();
        List<String> textbookName = JSON.parseArray(business.getTextbookname(), String.class);
        List<String> publishingUnitAndTime = JSON.parseArray(business.getPublishingsituation(), String.class);
        List<String> chiefEditor = JSON.parseArray(business.getChiefeditor(), String.class);
        List<String> jointlyEdited = JSON.parseArray(business.getJointlyedited(), String.class);
        List<String> planningTextbook = JSON.parseArray(business.getPlanningtextbook(), String.class);
        List<String> schoolBasedTextbook = JSON.parseArray(business.getSchoolbasedtextbook(), String.class);
        List<String> other = JSON.parseArray(business.getOther(), String.class);

        if (textbookName != null) {
            TextBook textBook = null;
            for (int i = 0, len = textbookName.size(); i < len; i++) {
                textBook = new TextBook();

                textBook.setTextbookName(textbookName.get(i));
                textBook.setPublishingUnitAndTime(publishingUnitAndTime.get(i));
                textBook.setChiefEditor(chiefEditor.get(i));
                textBook.setJointlyEdited(jointlyEdited.get(i));
                textBook.setPlanningTextbook(planningTextbook.get(i));
                textBook.setSchoolBasedTextbook(schoolBasedTextbook.get(i));
                textBook.setOther(other.get(i));

                textBooks.add(textBook);
            }
            academicAchievements.setTextBook(textBooks);
        }

        ArrayList<Paper> papers = new ArrayList<>();
        List<String> paperTitle = JSON.parseArray(business.getPapertitle(), String.class);
        List<String> publishingSituation = JSON.parseArray(business.getPublishingsituation(), String.class);
        List<String> level = JSON.parseArray(business.getLevel(), String.class);
        List<String> ranking = JSON.parseArray(business.getRanking(), String.class);

        if (textBooks != null) {
            Paper paper = null;
            for (int i = 0, len = paperTitle.size(); i < len; i++) {
                paper = new Paper();

                paper.setPaperTitle(paperTitle.get(i));
                paper.setPublishingSituation(publishingSituation.get(i));
                paper.setLevel(level.get(i));
                paper.setRanking(ranking.get(i));

                papers.add(paper);
            }
            academicAchievements.setPaper(papers);
        }

        ArrayList<ProjectOrTopics> projectOrTopics = new ArrayList<>();
        List<String> hostSubject = JSON.parseArray(business.getHostsubject(), String.class);
        List<String> cooperationUnit = JSON.parseArray(business.getCooperationunit(), String.class);
        List<String> category = JSON.parseArray(business.getCategory(), String.class);
        List<String> myRole = JSON.parseArray(business.getMyrole(), String.class);

        if (projectOrTopics != null) {
            ProjectOrTopics topics = null;
            for (int i = 0, len = projectOrTopics.size(); i < len; i++) {
                topics = new ProjectOrTopics();

                topics.setHostSubject(hostSubject.get(i));
                topics.setCooperationUnit(cooperationUnit.get(i));
                topics.setCategory(category.get(i));
                topics.setMyRole(myRole.get(i));

                projectOrTopics.add(topics);
            }
            academicAchievements.setProjectOrTopics(projectOrTopics);
        }

        businessDTO.setAcademicAchievements(academicAchievements);


        return Msg.success().add("businessDTO", businessDTO);
    }

    public TeacherExample addCondition(TeacherExample teacherExample, Integer offset, long id_institute, String keyWord, Long sectionid) {
        //代表为超管，则可以查询所有；
        if (id_institute == -1) {
            return teacherExample.newAndCreateCriteria()
                    .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andNameEqualTo(keyWord)) //根据名字查询
                    .when(sectionid != null, criteria -> criteria.andIdSectionEqualTo(sectionid)) //根据指定教研室查询

                    .example().or()
                    .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andUsernameEqualTo(keyWord)) //根据账号查询
                    .when(sectionid != null, criteria -> criteria.andIdSectionEqualTo(sectionid)) //根据指定教研室查询

                    .example()
                    .when(offset != -1 && offset != 0, criteria -> criteria.page(offset - 1, 10));
        }
        //查询出来当前学院的所有教研室
        List<Section> sections = sectionService.getSections(id_institute);

        //提取 id列 列表
        List<Long> sectionIDs = sections.stream().map(Section::getId).collect(Collectors.toList());

        if (sectionIDs.size() == 0 || sectionIDs == null)
            return null;

        return teacherExample.newAndCreateCriteria()
                .example().or()
                .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andNameEqualTo(keyWord)) //根据名字查询
                .when(sectionid != null, criteria -> criteria.andIdSectionEqualTo(sectionid)) //根据指定教研室查询
                .andIdSectionIn(sectionIDs)// 查询的专业只能在当前的学院的教研室下

                .example().or()
                .when(StringUtil.stringIsNotNull(keyWord), criteria -> criteria.andUsernameEqualTo(keyWord)) //根据账号查询
                .when(sectionid != null, criteria -> criteria.andIdSectionEqualTo(sectionid)) //根据指定教研室查询
                .andIdSectionIn(sectionIDs)// 查询的专业只能在当前的学院的教研室下

                .example()
                .when(offset != -1 && offset != 0, criteria -> criteria.page(offset - 1, 10));// 查询第offest-1页数据（每页10条）

    }

    public void check(Teacher teacher, Long id_institute) throws MyException {

        //如果为超管则不需要检查有没有权力
        if (id_institute == -1) {
            return;
        }

        long id_section = teacher.getIdSection();
        List<Section> sections = sectionService.getSections(id_institute);
        //如果被删除的专业是不属于本系教研室则提示没有权限
        for (Section section : sections) {
            if (section.getId() == id_section)
                return;
        }
        throw new MyException("没有权限操作");
    }

    @Override
    public Long selectYearDebriefingFlag(Long id_teacher, Long year) {
        return debriefingYearMapper.selectFlag(id_teacher, year);
    }

    public int insertYearDebriefing(DebriefingYearWithBLOBs debriefingYearWithBLOBs) {
        return debriefingYearMapper.insertSelective(debriefingYearWithBLOBs);
    }

    @Override
    public void updateYearDebriefing(DebriefingYearWithBLOBs debriefingYearWithBLOBs) {
        DebriefingYearExample example = new DebriefingYearExample();
        DebriefingYearExample.Criteria criteria = example.createCriteria();
        criteria.andIdTeacherEqualTo(debriefingYearWithBLOBs.getIdTeacher());
        debriefingYearMapper.updateByExampleSelective(debriefingYearWithBLOBs, example);
    }

    @Override
    public List<DebriefingYear> selectDebriefingByYear() {
        return debriefingYearMapper.selectByYear();
    }

    @Override
    public DebriefingYearWithBLOBs selectYearDebriefingInfo(Long id_teacher, Long year) {
        return debriefingYearMapper.selectByIdAndYear(id_teacher, year);
    }

    @Override
    public List<Debriefing> selectDebriefingTermByYear() {
        return debriefingMapper.selectByYear();
    }

    @Override
    public DebriefingWithBLOBs selectTermDebriefingInfo(Long id_teacher, Long year, String term) {
        return debriefingMapper.selectByIdAndYearTerm(id_teacher, year, term);
    }

    @Override
    public Long selectAnnualAssessmentFlag(Long id_teacher, String year) {
        return annualAssessmentMapper.selectFlag(id_teacher, year);
    }

    @Override
    public int insertAnnualAssessment(AnnualAssessmentWithBLOBs annualAssessmentWithBLOBs) {
        return annualAssessmentMapper.insertSelective(annualAssessmentWithBLOBs);
    }

    @Override
    public int updateAnnualAssessment(AnnualAssessmentWithBLOBs annualAssessmentWithBLOBs) {
        AnnualAssessmentExample example = new AnnualAssessmentExample();
        AnnualAssessmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdTeacherEqualTo(annualAssessmentWithBLOBs.getIdTeacher());
        return annualAssessmentMapper.updateByExampleSelective(annualAssessmentWithBLOBs, example);
    }

    @Override
    public Long selectTechnicalPersonnelFlag(Long id_teacher, Long year) {
        return technicalPersonnelMapper.selectFlag(id_teacher, year);
    }

    @Override
    public int insertTechnicalPersonnel(TechnicalPersonnelWithBLOBs technicalPersonnelWithBLOBs) {
        return technicalPersonnelMapper.insertSelective(technicalPersonnelWithBLOBs);
    }

    @Override
    public int updateTechnicalPersonnel(TechnicalPersonnelWithBLOBs technicalPersonnelWithBLOBs) {
        TechnicalPersonnelExample example = new TechnicalPersonnelExample();
        TechnicalPersonnelExample.Criteria criteria = example.createCriteria();
        criteria.andIdTeacherEqualTo(technicalPersonnelWithBLOBs.getIdTeacher());
        return technicalPersonnelMapper.updateByExampleSelective(technicalPersonnelWithBLOBs, example);
    }

    @Override
    public List<AnnualAssessment> selectAnnualAssessmentByYear(Long id_teacher) {
        return annualAssessmentMapper.selectByYear(id_teacher);
    }

    @Override
    public AnnualAssessmentWithBLOBs selectAnnualAssessmentInfo(Long id_teacher, Long year) {
        return annualAssessmentMapper.selectByIdTeacherAndYear(id_teacher, year);
    }

    @Override
    public List<TechnicalPersonnel> selectTechnicalPersonnelByYear(Long id_teacher) {
        return technicalPersonnelMapper.selectByYear(id_teacher);
    }

    @Override
    public TechnicalPersonnelWithBLOBs selectTechnicalPersonnelInfo(Long id_teacher, Long year) {
        return technicalPersonnelMapper.selectByIdTeacherAndYear(id_teacher, year);
    }

    @Override
    public List<Projectsource> select_allProjectsource() {
        return staticMapper.select_allProjectsource();
    }

    @Override
    public List<Projecttype> select_allProjecttype() {
        return staticMapper.select_allProjecttype();
    }

    @Override
    public List<Specialty> select_allSpecialty(Long section_id) {
        return staticMapper.select_allSpecialty(section_id);
    }

    //通过课题名查找课题
    @Override
    public List<Project> selectProjectByName(String projectName){
        return projectMapper.selectProjectByName(projectName);
    }

    // 通过教师名字查询课题
    @Override
    public List<Project> selectTeacherProject(String name) {
        return teacherMapper.selectTeacherProject(name);
    }

    // 根据课题名字修改发布状态
    @Override
    public void updateProjectFB(Long project_id, int fb) {
        teacherMapper.updateProjectFB(project_id, fb);
    }

    // 以教研室名字查属于该教研室的所有课题
    @Override
    public List<Project> selecSectionProject(String name) {
        return teacherMapper.selecSectionProject(name);
    }

    @Override
    public int insert_project(ProjectWithBLOBs project) {
        return projectMapper.insert(project);
    }

    // 通过教师名字查询已发布的课题
    @Override
    public List<Project> selectTeacherFBProject(String name) {
        return teacherMapper.selectTeacherFBProject(name);
    }

    @Autowired
    SubjectService subjectService;

    @Override
    public Integer deleteProject(Long id) {
        SubjectWithBLOBs subject = subjectService.getSubjectByID(id);
        File file = new File(subject.getFilepath());
        if(file.exists()){
            if(file.delete()){

            }else{
                LOGGER.error("删除失败：{}",subject);
//                throw new MyException("删除课题时文件不存在");
            }

        }
        return projectMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void updateZTprojcet(int zt, Long project_id) {
        teacherMapper.update_projectZT(zt, project_id);
        if (zt == 1) {
            teacherMapper.updateWXstudent(project_id);
            int size = teacherMapper.select_XZstudent(project_id).size();
            teacherMapper.update_projectCount(size, project_id);
        }
    }

    // 根据 课题id，确认 学生id 选报成功；
    @Override
    public void updateXZstudent(Long student_id, Long project_id, Long zt) {
        teacherMapper.updateXZstudent(student_id, project_id, zt);
    }

    // 通过教研室id 查询所有课题；
    @Override
    public List<Project> selectProjecbySection(Long section_id) {

        return teacherMapper.select_allproject(section_id);
    }

    @Override
    public void updateSHprojcet(int zt, long project_id) {
        teacherMapper.update_projectSHZT(zt, project_id);
    }

    @Override
    public Integer selectedSuccessNum(Long project_id) {
        return teacherMapper.selectSuccessNum(project_id);
    }

    @Override
    public void updateProjectCount(Long project_id) {
        teacherMapper.update_projectCount(0, project_id);
    }

    //    @Override
//    public void updateselectnum(Long zt, String project_name) {
//        Long id = staticMapper.select_projectID(project_name);
//        synchronized (this){
//            if(zt == 3){
//                teacherMapper.updateNumAdd(id);
//            }else{
//                teacherMapper.updateNumReduce(id);
//            }
//        }
//
//    }


    @Override
    public int selectAllXB(Long id_teacher) {
        System.out.println(id_teacher);
        return teacherMapper.selectAllXB(id_teacher);
    }

    @Override
    public List<Student> selectProjectStudents(Long project_id) {
        List<Long> student_ids =  projectselectedMapper.select_student_ids(project_id);
        if(student_ids.size()==0){
            return null;
        }
        return  teacherMapper.selectStudentByIds(student_ids);
    }

    @Override
    public Map<Long, Class> selectClassIds(List<Long> classIds) {
        Map map = new HashMap();
        classMapper.select_class_by_ids(classIds).forEach(cl->{
            map.put(cl.getId(),cl);
        });
        return map;
    }

    @Override
    public void deleteSelected(Long project_id) {
        teacherMapper.deleteSelected(project_id);
    }

    @Override
    public void deleteSelectedAll(Long project_id) {
        teacherMapper.deleteSelectedAll(project_id);
    }
}

