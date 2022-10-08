package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.MyClassMapper;
import com.hngy.educationaladministration.mapper.StudentMapper;
import com.hngy.educationaladministration.mapper.SubjectMapper;
import com.hngy.educationaladministration.mapper.SubjectselectedMapper;

import com.hngy.educationaladministration.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private MyClassMapper classMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SubjectselectedMapper subjectselectedMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查看班级选报信息
     */
    @Override
    public List<classinfo> classinfo(Long id) {
        MyClass className = classMapper.selectById(id);//查到班级名字
        List<Student> students = studentMapper.selectByListAll(id);//根据student表里班级id查到用户所在班级的所有人
        List<Subjectselected> projectselectedlist = new ArrayList<>();
        List<Subject> projectlist = new ArrayList<>();
        Subjectselected projectselected = null;
        Subject project = null;
        for (int i = 0; i < students.size(); i++) {
            List<Subjectselected> tprojectselecteds = subjectselectedMapper.selectBystudentid(students.get(i).getId());
            if (tprojectselecteds != null && tprojectselecteds.size() > 0)
                projectselected = tprojectselecteds.get(0);
            if (projectselected != null) {
                projectselectedlist.add(projectselected);
            } else {
                projectselected = new Subjectselected();
                projectselected.setId(0L);
                projectselected.setIdProject(0L);
                projectselected.setIdStudent(0L);
                projectselected.setStuselectFlag(0L);
                projectselectedlist.add(projectselected);
            }
        }
        for (int i = 0; i < projectselectedlist.size(); i++) {
            if (projectselectedlist.get(i).getId() != 0) {
                projectlist.add(subjectMapper.selectById(projectselectedlist.get(i).getIdProject()));
            } else {
                project = new Subject();
                project.setProjectname("无");
                project.setTeachernames("无");
                projectlist.add(project);
            }
        }
        List<classinfo> infolist = new ArrayList<>();
        classinfo info = null;
        for (int i = 0; i < projectlist.size(); i++) {
            info = new classinfo();
            info.setId(students.get(i).getId());
            info.setClassName(className.getClassName());
            info.setName(students.get(i).getName());
            info.setStuNum(students.get(i).getStunum());
            info.setProjectName(projectlist.get(i).getProjectname());
            info.setTeacherNames(projectlist.get(i).getTeachernames());
            info.setStuSelectFlag(projectselectedlist.get(i).getStuselectFlag());
            infolist.add(info);
        }
        return infolist;
    }

    /**
     * 查看课题信息
     * 通过classid
     */
    @Override
    public List<topicsinfo> topics(Long id) {
        String specialty = classMapper.selectByspecialty(id);
        List<Subject> projectlist = subjectMapper.selectByAll("%" + specialty + "%");
        List<topicsinfo> topicsinfolist = new ArrayList<>();
        topicsinfo info = null;
        for (int i = 0; i < projectlist.size(); i++) {
            info = new topicsinfo();
            info.setProject_id(projectlist.get(i).getId());
            info.setProjectType(subjectMapper.selectByprojectType(projectlist.get(i).getIdProjecttype().intValue()));
            info.setProjectSource(subjectMapper.selectByprojectSource(projectlist.get(i).getIdProjectsource().intValue()));
            info.setProjectName(projectlist.get(i).getProjectname());
            info.setSelectFlag(projectlist.get(i).getSelectFlag());
            info.setMarchSpecialty(projectlist.get(i).getMarchspecialty());
            info.setTeacherNames(projectlist.get(i).getTeachernames());
            topicsinfolist.add(info);
        }
        return topicsinfolist;
    }

    /**
     * 课题具体信息
     */
    @Override
    public List<topicsto> topicsinfo(Long project_id) {
        List<Subjectselected> listed = subjectselectedMapper.selectByprojectid(project_id);//中间表
        request.getSession().setAttribute("listsize",listed.size());

        List<topicsto> tolist = new ArrayList<>();
        topicsto to = null;
        Student student = null;
        for (int i = 0; i < listed.size(); i++) {
            to = new topicsto();
            student = studentMapper.selectByid(listed.get(i).getIdStudent());
            if(student == null)
                continue;
            to.setStuNum(student.getStunum());
            to.setName(student.getName());
            to.setStuSelectFlag(listed.get(i).getStuselectFlag());
            to.setClassName(classMapper.selectById(student.getIdClass()).getClassName());
            tolist.add(to);
        }
        return tolist;
    }

    /**
     * 获取该用户是否选报了课题
     */

    public Long state(Student student) {
        Subjectselected project = null;
        List<Subjectselected> tprojectselecteds = subjectselectedMapper.selectBystudentid(student.getId());
        if (tprojectselecteds != null && tprojectselecteds.size() > 0)
            project = tprojectselecteds.get(0);
        if (project == null) {
            return 0L;
        } else
            return project.getStuselectFlag();
    }


    /**
     * 添加选题
     */
    @Override
    public void insertproject(String project, Long studentid) {
        Long projectid = subjectMapper.selectByprojectid(project);
        int flagid = subjectselectedMapper.insertenroll(projectid, studentid);
    }

    /**
     * 删除选题
     */
    @Override
    public int deleteprojectselectedid(Long id) {
        int flag = subjectselectedMapper.deleteBystudentId(id);
        return flag;
    }

    /**
     * 查询课题名
     *
     */
    public String selectprojectname(Long id){
        String project_Name = subjectMapper.selectById(id).getProjectname();
        return project_Name;
    }


    /**
     * 获取该题是否可选
     */
    @Override
    public Long flag(Long project_id) {
        return subjectMapper.selectByselectFlag(project_id);
    }
}
