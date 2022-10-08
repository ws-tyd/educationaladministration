package com.hngy.educationaladministration.controller;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.auth.Authority;
import com.hngy.educationaladministration.auth.Role;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.AdminMapper;
import com.hngy.educationaladministration.mapper.SubjectRelationStudentMapper;
import com.hngy.educationaladministration.service.*;
import com.hngy.educationaladministration.valid.interfaces.Add;
import com.hngy.educationaladministration.valid.interfaces.One;
import com.hngy.educationaladministration.valid.interfaces.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Authority(roles = {Role.ADMIN, Role.SADMIN})
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    CollegeService collegeService;

    @Autowired
    SectionService sectionService;

    @Autowired
    SpecialtyService specialtyService;

    @Autowired
    ClassService classService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ExcelService excelService;

    @Autowired
    SubjectRelationStudentMapper subjectRelationStudentMapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpSession session;

    @ModelAttribute("id_institute")
    public long getRoleInfo() {
        User user = (User) request.getAttribute("user");
//        LOGGER.info("USER:{}",user);
        if (user != null) {
            if (user.getRole().equals("admin")) {
                Institute institute = collegeService.selectCollege(adminMapper.selectByPrimaryKey(user.getId()).getIdInstitute());
                return institute.getId();
            }
            if (user.getRole().equals("sadmin")) {
                return -1;
            }
            return 0;
        } else {
            return 0;
        }
    }

//   admin index page   子管首页

    @GetMapping(value = {"", "/index"})
    public String index() {
        User user = (User) request.getAttribute("user");
//        LOGGER.info("index user:{}",user);

        //这部分还是用了session存储部分信息 后续可能修改
        //根据 user的id 判断 渲染页面
        if (user.getId() == -1) {
            LOGGER.info("超级管理员登录");
            session.setAttribute("instituteName", "超级管理员");
            session.setAttribute("ROLE", "sadmin");
            session.setAttribute("username", user.getUserName());
            return "admin/public-admin-index";
        }

        Institute institute = collegeService.selectCollege(adminMapper.selectByPrimaryKey(user.getId()).getIdInstitute());
        System.out.println(institute.getInstituteName());
        session.setAttribute("instituteName", institute.getInstituteName());
        session.setAttribute("ROLE", "admin");
        session.setAttribute("username", user.getUserName());

        return "admin/public-admin-index";
    }

//    exit      退出登录

    @GetMapping("/exit")
    public String exit(HttpSession httpSession) {
        //将Cookie 中的token 置空
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "login";
    }
// login 在单独Controller

//    updatePwd     更新密码

    @GetMapping("/updatePwd")
    public String updatePwd() {
        return "admin/updatePwd";
    }

    @PostMapping("/updatePwd")
    @ResponseBody
    public Msg updatePwd(
            @RequestBody Admin admin,
            HttpSession httpSession) {

        User user = (User) request.getAttribute("user");
        adminService.updatePwdByUserName(
                user.getUserName(),
                admin.getPwd()
        );

        return Msg.success();
    }


//    教研室

    @GetMapping("/SectionManagement")
    public String section() {
        return "admin/Department/SectionManagement";
    }

    @GetMapping("/sections")
    @ResponseBody
    public Msg getSections(@ModelAttribute("id_institute") long id_institute) {
        return Msg.success().add("sections", sectionService.getSections(id_institute));
    }

    @DeleteMapping("/section")
    @ResponseBody
    public Msg delSection(@RequestBody Section section) {
        return Msg.sqlChange((int) sectionService.delSection(section));
    }

    @PutMapping("/section")
    @ResponseBody
    public Msg updateSection(@RequestBody @Validated Section section, @ModelAttribute("id_institute") long id_institute) throws MyException {
        return Msg.sqlChange((int) sectionService.updateSection(section, section.getSectionName(), id_institute));
    }

    @PostMapping("/section")
    @ResponseBody
    public Msg addSection(@RequestBody @Validated Section section, @ModelAttribute("id_institute") long id_institute) {
        return Msg.sqlChange((int) sectionService.addSection(section, id_institute));
    }


//    专业方向


    @GetMapping("/SpecialtyManagement")
    public String specialty() {
        return "admin/Department/SpecialtyManagement";
    }

    @GetMapping("/specialtys")
    @ResponseBody
    public Msg getSpecialtys(
            @RequestParam Integer offset,
            @RequestParam(required = false) Long sectionId,
            @RequestParam(required = false) String keyWord,
            @ModelAttribute("id_institute") long id_institute) {
        long total = specialtyService.getSpecialtyCount(offset, keyWord, sectionId, id_institute);
        return Msg.success()
                .add("specialtys", specialtyService.getSpecialtys(offset, keyWord, sectionId, id_institute))
                .add("total", total);
    }

    @ResponseBody
    @DeleteMapping("/specialty")
    public Msg delSpecialty(
            @RequestBody Specialty specialty,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) specialtyService.delSpecialty(specialty, id_institute));
    }

    @ResponseBody
    @PutMapping("/specialty")
    public Msg putSpecialty(
            @RequestBody @Validated({Update.class}) Specialty specialty,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) specialtyService.putSpecialty(specialty, id_institute));
    }

    @ResponseBody
    @PostMapping("/specialty")
    public Msg postSpecialty(
            @RequestBody @Validated({Add.class}) Specialty specialty,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) specialtyService.postSpecialty(specialty, id_institute));
    }


//    班级


    @GetMapping("/ClassManagement")
    public String Class() {
        return "admin/Department/ClassManagement";
//    //获取管理员的 学院id
//    public static Long getIdInstitute(ModelMap modelMap) {
//        Subadmin subadmin = (Subadmin) modelMap.get("admin");
//        return subadmin.getIdInstitute();
//    }

    }

    @ResponseBody
    @GetMapping("/classes")
    public Msg getClasses(
            @RequestParam("offset") Integer offset,
            @RequestParam(required = false) Long specialtyId,
            @RequestParam(required = false) String keyWord,
            @ModelAttribute("id_institute") long id_institute) {
        long total = classService.getClassesCount(offset, keyWord, specialtyId, id_institute);
        return Msg.success()
                .add("classes", classService.getClasses(offset, keyWord, specialtyId, id_institute))
                .add("total", total);
    }

    @ResponseBody
    @DeleteMapping("/class")
    public Msg delClass(
            @RequestBody MyClass myClass,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) classService.delClass(myClass, id_institute));
    }

    @ResponseBody
    @PutMapping("/class")
    public Msg putClass(
            @RequestBody @Validated({One.class}) MyClass myClass,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) classService.putClass(myClass, id_institute));
    }

    @ResponseBody
    @PostMapping("/class")
    public Msg postClass(
            @RequestBody @Validated({One.class}) MyClass myClass,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) classService.postClass(myClass, id_institute));
    }


//    课题综合管理


    @GetMapping("/SourceManagement")
    public String source() {
        return "admin/Subject/SourceManagement";
    }

    @ResponseBody
    @GetMapping("/sources")
    public Msg getSources() {
        return Msg.success().add("sources", subjectService.selectSubjectSources());
    }

    @ResponseBody
    @PostMapping("/source")
    public Msg addSource(@RequestBody @Validated SubjectSource source) throws MyException {
        return Msg.sqlChange((int) subjectService.insertSubjectSource(source.getSourcename()));
    }

    @ResponseBody
    @DeleteMapping("/source")
    public Msg delSource(@RequestBody SubjectSource source) throws MyException {
        return Msg.sqlChange(subjectService.delSubjectSource(source.getId()));
    }

    @ResponseBody
    @PutMapping("/source")
    public Msg updateSource(@RequestBody @Validated SubjectSource source) {
        return Msg.sqlChange(subjectService.updateSubjectSource(source));
    }

    //课题类型

    @GetMapping("/TypeManagement")
    public String subjectType() {
        return "admin/Subject/TypeManagement";
    }

    @ResponseBody
    @GetMapping("/sujecttypes")
    public Msg getType() {
        return Msg.success().add("sujecttypes", subjectService.selectSubjectTypes());
    }

    @ResponseBody
    @PostMapping("/sujecttype")
    public Msg addType(@RequestBody @Validated SubjectType type) throws MyException {
        return Msg.sqlChange((int) subjectService.insertSubjectType(type.getTypename()));
    }

    @ResponseBody
    @DeleteMapping("/sujecttype")
    public Msg delType(@RequestBody SubjectType type) throws MyException {
        return Msg.sqlChange(subjectService.delSubjectType(type.getId()));
    }

    @ResponseBody
    @PutMapping("/sujecttype")
    public Msg updateType(@RequestBody @Validated SubjectType type) {
        return Msg.sqlChange(subjectService.updateSubjectType(type));
    }

    //课题管理

    @GetMapping("/SubjectManagement")
    public String Subject() {
        return "admin/Subject/SubjectManagement";
    }

    @ResponseBody
    @GetMapping("/subjects")
    public Msg getSubjects(
            @RequestParam Integer offset,
            @RequestParam(required = false) Long sectionId,
            @RequestParam(required = false) String keyWord,
            @ModelAttribute("id_institute") long id_institute) {

        long total = subjectService.selectSubjectsCount(offset, keyWord, sectionId, id_institute);
        return Msg.success()
                .add("subjects", subjectService.selectSubjects(offset, keyWord, sectionId, id_institute))
                .add("total", total);
    }

    @ResponseBody
    @PostMapping("/subject")
    public Msg addSubject(
            @RequestBody @Validated(Add.class) SubjectWithBLOBs subject,
            @ModelAttribute("id_institute") long id_institute) throws MyException {
        return Msg.sqlChange((int) subjectService.insertSubject(subject, id_institute));
    }

    @ResponseBody
    @DeleteMapping("/subject")
    public Msg delSubject(
            @RequestBody SubjectWithBLOBs subject,
            @ModelAttribute("id_institute") long id_institute) throws MyException {
        return Msg.sqlChange(subjectService.delSubject(subject, id_institute));
    }

    @ResponseBody
    @PutMapping("/subject")
    public Msg updateSubject(
            @RequestBody @Validated(Update.class) SubjectWithBLOBs subject,
            @ModelAttribute("id_institute") long id_institute) throws MyException {
        return Msg.sqlChange(subjectService.updateSuject(subject, id_institute));
    }

    //get学生选题的状态

    @GetMapping("/SRS")
    @ResponseBody
    public Msg getSelectSubjected(
            @ModelAttribute("id_institute") long id_institute
    ) {
        System.out.println(subjectService.getSelectSubjected(null, id_institute));
        return Msg.success().add("SRS", subjectService.getSelectSubjected(null, id_institute));
    }

    //get 选某个课题的所有学生
    @GetMapping("/studentsBySubject")
    @ResponseBody
    public Msg getStuentBySubject(
            @RequestParam("id") Long id,
            @ModelAttribute("id_institute") long id_institute
    ) {
        return subjectService.getStuentBySubject(id, id_institute);
    }

//    教师管理 增删改查

    @GetMapping("/TeacherManagement")
    public String teacher() {
        return "admin/BasicInfo/TeacherManagement";
    }

    @ResponseBody
    @GetMapping("/teachers")
    public Msg getTeachers(
            @RequestParam Integer offset,
            @RequestParam(required = false) Long sectionId,
            @RequestParam(required = false) String keyWord,
            @ModelAttribute("id_institute") long id_institute) {
        long total = teacherService.selectTeachersCount(offset, keyWord, sectionId, id_institute);
        return Msg.success()
                .add("teachers", teacherService.selectTeachers(offset, keyWord, sectionId, id_institute))
                .add("total", total);
    }

    @ResponseBody
    @DeleteMapping("/teacher")
    public Msg delTeacher(
            @RequestBody TeacherWithBLOBs teacher,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) teacherService.delTeacher(teacher, id_institute));
    }

    @ResponseBody
    @PostMapping("/teacher")
    public Msg addTeacher(
            @RequestBody @Validated(Add.class) TeacherWithBLOBs teacher,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) teacherService.addTeacher(teacher, id_institute));
    }

    @ResponseBody
    @PutMapping("/teacher")
    public Msg updateTeacher(
            @RequestBody @Validated({Update.class}) TeacherWithBLOBs teacher,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) teacherService.updateTeacher(teacher, id_institute));
    }

    //教师批量教师导入
    @PostMapping("/TeacherExcel")
    @ResponseBody
    public Msg addTeacherExcel(
            @RequestParam("excel") MultipartFile excelFile,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException, IOException {
        return excelService.teacherExcelImport(excelFile, id_institute);
    }

    //教师批量导入模板
    @GetMapping("/TeacherExcelDemo")
    public void getTeacherExcelDemo(HttpServletResponse response) throws IOException {
        excelService.teacherExcelDownload(response);
    }


//    学生管理


    @GetMapping("/StudentManagement")
    public String student() {
        return "admin/BasicInfo/StudentManagement";
    }

    @ResponseBody
    @GetMapping("/students")
    public Msg getStudents(
            @RequestParam Integer offset,
            @RequestParam(required = false) Long classId,
            @RequestParam(required = false) Long specialtyId,
            @RequestParam(required = false) String keyWord,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        long total = studentService.selectStudentsCount(offset, keyWord, classId, specialtyId, id_institute);
        return Msg.success()
                .add("students", studentService.selectStudents(offset, keyWord, classId, specialtyId, id_institute))
                .add("total", total);
    }

    @ResponseBody
    @DeleteMapping("/student")
    public Msg delStudent(
            @RequestBody StudentWithBLOBs student,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) studentService.delStudent(student, id_institute));
    }

    @ResponseBody
    @PostMapping("/student")
    public Msg addStudent(
            @RequestBody @Validated(Add.class) StudentWithBLOBs student,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) studentService.addStudent(student, id_institute));
    }

    @ResponseBody
    @PutMapping("/student")
    public Msg updateStudent(
            @RequestBody @Validated({Update.class}) StudentWithBLOBs student,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException {
        return Msg.sqlChange((int) studentService.updateStudent(student, id_institute));
    }

    //    批量导入模板

    @GetMapping("/StudentExcelDemo")
    public void getStudentExcelDemo(HttpServletResponse response) throws IOException {
        excelService.studentExcelDownload(response);
    }

    //批量学生导入

    @PostMapping("/StudentExcel")
    @ResponseBody
    public Msg addStudentExcel(
            @RequestParam("excel") MultipartFile excelFile,
            @ModelAttribute("id_institute") long id_institute
    ) throws MyException, IOException {
        return excelService.studentExcelImport(excelFile, id_institute);
    }


//    生成一览表

    //课题一览表
    @GetMapping("/SubjectExcel")
    public void getSubjectExcel(
            HttpServletResponse response,
            HttpServletRequest request,
            @ModelAttribute("id_institute") long id_institute) throws IOException {
        excelService.subjectExcelDownload(response, request, id_institute);
    }

}