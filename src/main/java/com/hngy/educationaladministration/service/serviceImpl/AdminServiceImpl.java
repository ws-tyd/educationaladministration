package com.hngy.educationaladministration.service.serviceImpl;

import com.hngy.educationaladministration.entity.Class;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.*;
import com.hngy.educationaladministration.service.AdminService;
import com.hngy.educationaladministration.service.PublicService;
import com.hngy.educationaladministration.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    SadminMapper sadminMapper;

    @Autowired
    GeneralPurposeMapper mapper;

    @Autowired
    PublicService publicService;

    //Session 保存时间（秒）
    private final Integer SAVE_TIME = 60*60*24;

    //超管 和 子管用一个登录
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SubjectselectedMapper subjectselectedMapper;

    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ClassMapper classMapper;


    @Autowired
    SpecialtyMapper specialtyMapper;

    @Autowired
    InstituteMapper instituteMapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Override
    public Msg login(String name, String pwd , HttpSession httpSession) {

        Sadmin sadmin = selectSadminByName(name);
        if(sadmin!=null){
            //验证密码是否与数据库中的相同
            if(sadmin.getPwd().equals(pwd)){
                User user = new User();
                //-1表示为超管
                user.setId(-1L);
                user.setRole("sadmin");
                user.setUserName(sadmin.getName());
                //生成Token 存到 Cookie
                Cookie cookie = new Cookie("token",TokenUtil.createToken(
                        user
                ));
                //该Cookie无法被js读取
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
                return Msg.success();
            }
            return Msg.error("密码错误");
        }else {
            Admin admin = selectByName(name);
            if(admin == null){
                return Msg.error("账号不存在");
            }else {
                if(admin != null && admin.getPwd().equals(pwd)){
                    User user = new User();
                    user.setId(admin.getId());
                    user.setRole("admin");
                    user.setUserName(admin.getName());
                    Cookie cookie = new Cookie("token",TokenUtil.createToken(
                            user
                    ));
                    cookie.setPath("/");
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return Msg.success();//账号密码正确
                }
                return Msg.error("密码错误");
            }

        }


    }



    @Override
    public Admin selectByName(String name) {
        //创建一个 Admin 实例 ，添加条件 为 Name == name
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andNameEqualTo(name);
        //根据实例 来查询
        List<Admin> admin = adminMapper.selectByExample(adminExample);
        if( admin==null || admin.size()==0 ) {
            return null;
        }
        return admin.get(0);
    }

    @Override
    public Sadmin selectSadminByName(String name) {
        SadminExample sadminExample = new SadminExample();
        sadminExample.createCriteria().andNameEqualTo(name);
        List<Sadmin> sadmin = sadminMapper.selectByExample(sadminExample);
        if( sadmin==null|| sadmin.size()==0 )
            return null;
        return sadmin.get(0);
    }

    @Override
    public int updatePwdByUserName(String name, String pwd) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andNameEqualTo(name);
        Admin admin = new Admin();
        admin.setPwd(pwd);
        adminMapper.updateByExampleSelective(admin,adminExample);

        return 0;
    }

    @Override
    public int updateSadminPwdByUserName(String name, String pwd) {
        SadminExample sadminExample = new SadminExample();
        sadminExample.createCriteria().andNameEqualTo(name);
        Sadmin sadmin = new Sadmin();
        sadmin.setPwd(pwd);
        return sadminMapper.updateByExampleSelective(sadmin, sadminExample);
    }

    // 班级的增加
    @Override
    public int add_class(Long specialty_id, String class_name) {
        List<Class> classes = select_class(null, null, null, null, class_name);
        if (classes == null || classes.size() == 0) {
            Class class1 = new Class();
            class1.setClassName(class_name);
            class1.setIdSpecialty(specialty_id);
            return classMapper.insert(class1);
        }
        return 0;
    }

    @Override
    public int addAdmin(Admin admin) {
        //添加的时候检测又没用户名重复的
        publicService.CheckIfTheUsernameIsDuplicated(admin.getName());
        return adminMapper.insert(admin);
    }

    @Override
    public int delAdmin(Admin admin) {
        return adminMapper.deleteByPrimaryKey(admin.getId());
    }

    @Override
    public int updateAdmin(Admin admin) {
        //不更新username
        admin.setName(null);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public List selectAdmins(Long offset) {



        if(offset!=null && offset==-1){
            return adminMapper.selectByExampleSelective(
                    new AdminExample(),
                    Admin.Column.id
            );
        }

        return adminMapper.selectByExample(
                new AdminExample()
        );
    }


    // 学生的增删改查
    @Override
    public int add_student(String userName, String stuNum, Long id_class, String name, String gender, String pwd){
        Student ss = studentMapper.select_studentbyName(userName, stuNum);
        if (ss == null) {
            StudentWithBLOBs student = new StudentWithBLOBs();
            student.setUsername(userName);
            student.setStunum(stuNum);
            student.setIdClass(id_class);
            student.setName(name);
            student.setGender(gender);
            student.setPwd(pwd);
            return studentMapper.insert(student);
        }
        return 0;
    }
    public int delete_class(Long class_id) {
        return classMapper.deleteByPrimaryKey(class_id);
    }

    @Override
    public int update_class(Long class_id, Long specialty_id, String class_name) {
        Class class1 = new Class();
        class1.setId(class_id);
        class1.setIdSpecialty(specialty_id);
        class1.setClassName(class_name);
        return classMapper.updateByPrimaryKeySelective(class1);
    }

    @Override
    public List<Project> select_project(Long institute_id, Long section_id, String section_name) {
        return projectMapper.select_project(institute_id, null, null);
    }

    @Override
    public List<Static_student> select_studentXT_all(Long section_id, String section_name, Long specialty_id, String specialty_name, Long class_id, String class_name) {
        List<Static_student> static_students = studentMapper.select_studentXT_all(section_id, section_name, specialty_id, specialty_name, class_id, class_name);
        if (static_students != null && static_students.size() > 0) {
            for (int i = 0; i < static_students.size(); i++) {
                List<Subjectselected> projectselecteds = select_Projectselected(static_students.get(i).getId(), null);
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


    @Override
    public List<Class> select_class(Long institute_id, Long section_id, Long specialty_id, Long class_id, String class_name) {
        return classMapper.select_class(institute_id, section_id, specialty_id, class_id, class_name);
    }

    // 专业方向的增删改查
    @Override
    public int add_specialty(Long section_id, String specialty_name) {
        List<Specialty> specialties = select_specialty(null, null, null, specialty_name);
        if (specialties == null || specialties.size() == 0) {
            Specialty specialty = new Specialty();
            specialty.setIdSection(section_id);
            specialty.setSpecialtyName(specialty_name);
            return specialtyMapper.insert(specialty);
        }
        return 0;
    }

    @Override
    public int delete_student(Long student_id) {
        return studentMapper.deleteByPrimaryKey(student_id);
    }

    @Override
    public int update_student(Long student_id, String userName, String stuNum, Long id_class, String name, String gender, String pwd) {
        Student student = new Student();
        student.setId(student_id);
        student.setUsername(userName);
        student.setStunum(stuNum);
        student.setIdClass(id_class);
        student.setName(name);
        student.setGender(gender);
        student.setPwd(pwd);
        return studentMapper.updateByPrimaryKeySelective((StudentWithBLOBs) student);
    }

    @Override
    public List<Static_student> select_student(Long institute_id, Long specialty_id, Long class_id, Long student_id, String name) {
        List<Static_student> static_students = studentMapper.select_Student(institute_id, specialty_id, class_id, student_id, name);
        if (static_students != null) {
            for (int i = 0; i < static_students.size(); i++) {
                Static_student static_student = static_students.get(i);

                List<Subjectselected> projectselected = select_Projectselected(static_student.getId(), null);

                if (projectselected != null && projectselected.size() != 0) {
                    String s = "";
                    if (projectselected.get(0).getStuselectFlag() == 1) s = "选题待审核";
                    else if (projectselected.get(0).getStuselectFlag() == 2) s = "选题未通过";
                    else s = "选题通过";
                    static_student.setStuselectFlag(s);
                    static_student.setProjectname(projectselected.get(0).getProject_name());
                    static_student.setTeachernames(projectselected.get(0).getProject_teachers());


                }
            }
            return static_students;
        }
        return null;
    }


    // 通过学生id 或者 课题id 查 课题的选择 情况
    public List<Subjectselected> select_Projectselected(Long student_id, Long project_id) {
        return subjectselectedMapper.select_Projectselected(student_id, project_id);
    }

    public List<Subject> select_ProjectXQ(Long institute_id, Long section_id, String name) {
        List<Subject> projects = subjectMapper.select_ProjectXQ(institute_id, section_id, name);
        if (projects != null) {
            for (int i = 0; i < projects.size(); i++) {
                if (projects.get(i).getSelectFlag() == 0) projects.get(i).setProjectGB("可选");
                else if (projects.get(i).getSelectFlag() == 1) projects.get(i).setProjectGB("不可选");
            }
        }
        return projects;
    }
    public int delete_specialty(Long specialty_id) {
        return specialtyMapper.deleteByPrimaryKey(specialty_id);
    }

    @Override
    public int update_specialty(Long specialty_id, Long section_id, String specialty_name) {
        Specialty specialty = new Specialty();
        specialty.setId(specialty_id);
        specialty.setIdSection(section_id);
        specialty.setSpecialtyName(specialty_name);

        return specialtyMapper.updateByPrimaryKeySelective(specialty);
    }

    @Override
    public List<Specialty> select_specialty(Long institute_id, Long section_id, Long specialty_id, String specialty_name) {
        return specialtyMapper.select_specialty(institute_id, section_id, specialty_id, specialty_name); // 根据学院id 或者 教研室id 查所有专业方向
    }

    @Override
    public List<Institute> select_institute(Long institute_id, String institute_name) {
        InstituteExample instituteExample = new InstituteExample();
        InstituteExample.Criteria criteria = instituteExample.createCriteria();
        if (institute_id != null && institute_id != 0) criteria.andIdEqualTo(institute_id);
        if (institute_name != null && institute_name.length() > 0) criteria.andInstituteNameEqualTo(institute_name);
        return instituteMapper.selectByExample(instituteExample);
    }



}
