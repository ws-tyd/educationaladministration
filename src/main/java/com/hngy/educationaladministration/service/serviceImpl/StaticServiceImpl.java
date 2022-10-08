package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.Class;
import com.hngy.educationaladministration.mapper.*;
import com.hngy.educationaladministration.service.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaticServiceImpl implements StaticService {

    @Autowired
    WorkapprovaltypeMapper workapprovaltypeMapper;

    @Autowired
    WorkapprovalMapper workapprovalMapper;

    @Autowired
    StaticMapper staticMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ProjectselectedMapper projectselectedMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    TeacherMapper teacherMapper;


    @Override
    public List<Workapprovaltype> selectAllWorkApprovalType() {
        return workapprovaltypeMapper.selectByExample(null);
    }

    @Override
    public int insertWorkapproval(WorkapprovalWithBLOBs workapprovalWithBLOBs) {
        return workapprovalMapper.insertSelective(workapprovalWithBLOBs);
    }

    @Override
    public List<WorkapprovalWithBLOBs> selectAllWorkApproval(Long section_id) {
        return workapprovalMapper.selectSectionWork(section_id);
    }

    @Override
    public void updateShWorkApproval(WorkapprovalWithBLOBs workapprovalWithBLOBs) {
        WorkapprovalExample example = new WorkapprovalExample();
        WorkapprovalExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(workapprovalWithBLOBs.getId());
        workapprovalMapper.updateByExampleSelective(workapprovalWithBLOBs, example);
    }

    @Override
    public List<Static_student> seleStudentbyTJ(String name1, String name2, String name3) {
        return staticMapper.seleStudentbyTJ(name1, name2, name3);
    }

    @Override
    public List<Specialty> selectSpercialtyByall(String name) {
        name = "信息工程学院";
        return staticMapper.selectSpercialtyByall(name);
    }

    @Override
    public List<Class> selectClassByall(String name) {
        name = "信息工程学院";
        return staticMapper.selectClassByall(name);
    }

    @Override
    public List<Static_student> select_studentXT_all(Long section_id, String section_name, Long specialty_id, String specialty_name, Long class_id, String class_name) {
        List<Static_student> static_students = studentMapper.select_studentXT_all(section_id, section_name, specialty_id, specialty_name, class_id, class_name);
        if (static_students != null && static_students.size() > 0) {
            for (int i = 0; i < static_students.size(); i++) {
                List<Projectselected> projectselecteds = select_Projectselected(static_students.get(i).getId(), null);
                if (projectselecteds != null && projectselecteds.size() > 0) {
                    static_students.get(i).setStuselectFlag("" + projectselecteds.get(0).getStuselectFlag());
                    static_students.get(i).setProjectname(projectselecteds.get(0).getProject_name());
                    static_students.get(i).setTeachernames(projectselecteds.get(0).getProject_teachers());
                } else {
                    static_students.get(i).setProjectname("未选题");
                    static_students.get(i).setTeachernames("");
                }
            }
        }
        return static_students;
    }

    // 通过学生id 或者 课题id 查 课题的选择 情况
    public List<Projectselected> select_Projectselected(Long student_id, Long project_id) {
        return projectselectedMapper.select_Projectselected(student_id, project_id);
    }

    @Override
    public List<Static_student> selectStudents(String name) {
        return staticMapper.seleStudentbyName(name);
    }

    @Override
    public int CZpwd(String username) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStunumEqualTo(username);
        List<Student> students = studentMapper.selectByExample(studentExample);
        if (students.size() > 0) {
            Student student = students.get(0);
            student.setPwd("000000");
            studentMapper.updateByPrimaryKey(student);
            return 1;
        }
        return 0;
    }

    // 通过id 查课题
    @Override
    public Project selectProjectbyid(Long project_id) {
        return projectMapper.selectByPrimaryKey(project_id);
    }

    @Override
    public Project selectProjectbyName(String project_Name) {
        ProjectExample projectExample=new ProjectExample();
        ProjectExample.Criteria criteria=projectExample.createCriteria();
        criteria.andProjectnameEqualTo(project_Name);
        List<Project> projects=projectMapper.selectByExample(projectExample);
        if(projects.size()>0)return projects.get(0);
        else return null;
    }

    @Override
    public List<Project> selectByConditionTeacher(Long section_id, Long zt, Long id_teacher) {

            return teacherMapper.selectProjectByConditionId(section_id, zt, id_teacher);

    }
}
