package com.hngy.educationaladministration.controller;

import com.hngy.educationaladministration.auth.Authority;
import com.hngy.educationaladministration.auth.Role;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.mapper.StudentMapper;
import com.hngy.educationaladministration.mapper.SubjectMapper;
import com.hngy.educationaladministration.mapper.SubjectselectedMapper;
import com.hngy.educationaladministration.service.*;
import com.hngy.educationaladministration.service.serviceImpl.IndexServiceImpl;
import com.hngy.educationaladministration.service.serviceImpl.LoginServiceImpl;
import com.hngy.educationaladministration.service.serviceImpl.SubjectServiceImpl;
import com.hngy.educationaladministration.utils.TokenUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Authority(roles = {Role.STUDENT})
@Controller
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    IndexServiceImpl indexService;

    @Autowired
    AdminService adminService;



    @Autowired
    TopicsService topicsService;

    //Session????????????
    private final Integer SAVE_TIME = 60*60*24;


    @GetMapping("/login")
    public String login(){
        return "student/login";
    }


    @PostMapping(value = "/login")
    @ResponseBody
    public String login(String name, String pwd, Model model, HttpServletResponse response) {
        name = name.trim();
        List<Student> student = studentService.selectByName(name);
        if (student.size() >= 1) {
            if (student.get(0).getPwd().equals(pwd)) {
                request.getSession().setAttribute("student",student.get(0));
                request.getSession().setMaxInactiveInterval(SAVE_TIME);
                User user = new User();
                //-1???????????????
                user.setId(1L);
                user.setRole("student");
                user.setUserName(name);
                //??????Token ?????? Cookie
                Cookie cookie = new Cookie("token", TokenUtil.createToken(
                        user
                ));
                //???Cookie?????????js??????
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
                model.addAttribute("student", student.get(0));
                return "200";
            }else {
                return "0";

            }
        } else {
            return "300";
        }
    }


    @RequestMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        Student student = (Student) request.getSession().getAttribute("student");
        Subject project = indexService.indexinfo(student.getId());
        String str = null;
        Long flag = null;
        if (project == null) {
            model.addAttribute("projectName", "????????????");
            model.addAttribute("flag", "?????????");
            model.addAttribute("teacher", "???");
            httpSession.removeAttribute("");
        } else {
            httpSession.setAttribute("XZproject", project.getProjectname());
            model.addAttribute("XZproject", project.getProjectname());
            model.addAttribute("projectName", project.getProjectname());
            flag = indexService.projectselectedstuflag(student.getId());
            if (flag == 0L) {
                str = "?????????";
            } else if (flag == 1L) {
                str = "???????????????";
            } else if (flag == 2L) {
                str = "???????????????";
            } else if (flag == 3L) {
                str = "????????????";
            }
            model.addAttribute("flag", str);
            model.addAttribute("teacher", project.getTeachernames());
            request.getSession().setAttribute("filePath",project.getFilepath());
        }
        //????????????????????????????????????
        model.addAttribute("path","1");
        //?????????????????????????????????
//        request.getSession().setAttribute("modifyFlag",0);

        return "student/index";
    }



    /**
     * ??????????????????
     */
    @RequestMapping("/studentinfo")
    public String studentinfo(Model model) {
        Student student = (Student) request.getSession().getAttribute("student");
        MyClass idclass = indexService.studentinfo(student.getIdClass());
        model.addAttribute("tclass", idclass);
        return "student/studentinfo";
    }

    /**
     * ????????????????????????????????????????????????
     */
    @RequestMapping("/modifyinfo")
    public String modifyinfo(Model model) {
        Student student = (Student) request.getSession().getAttribute("student");
        MyClass idclass = indexService.studentinfo(student.getIdClass());
        model.addAttribute("tclass", idclass);

        return "student/modifyinfo";
    }


    /**
     * ??????????????????
     * ????????????(className)??????
     *
     */
    /*@RequestMapping(value = "/modifyinfodao", method = RequestMethod.PUT)
    @ResponseBody
    public String modifyinfodao(Student student, String className, Model model) {
        Student Tstudent = (Student) request.getSession().getAttribute("student");
        MyClass Tclass = indexService.selectByclassName(className);
        int count = -1;
        student.setIdClass(Tclass.getId());
        System.out.println("******"+Tstudent.toString());
        System.out.println("******"+student.toString());

        if (student.getStunum().equals(Tstudent.getStunum())) {
            student.setStunum(null);
        }
        if (student.getIdClass().equals(Tstudent.getIdClass())) {
            student.setIdClass(null);
        }
        if (student.getName().equals(Tstudent.getName())) {
            student.setName(null);
        }
        if (student.getGender().equals(Tstudent.getGender())) {
            student.setGender(null);
        }
        if (student.getGender() == null && student.getName() == null && student.getIdClass() == null && student.getStunum() == null) {

        } else {
            student.setId(Tstudent.getId());
            count = indexService.updateBymodifyinfo(student);
            student = indexService.selectByid(Tstudent.getId());
            model.addAttribute("student", student);
        }
        if (count > 0) {
            request.getSession().setAttribute("student",student);
            request.getSession().setAttribute("modifyFlag",1);
            return "200";
        } else {
            request.getSession().setAttribute("modifyFlag",0);
            return "201";
        }
    }*/



    /**
     *   ????????????(????????????)
     */
    @RequestMapping("/changepsw")
    public String changepsw() {
        return "student/changepsw";
    }


    /**
     * 200????????????
     * 201?????????????????????
     * 202????????????????????????
     * 203??????????????????
     * 204????????????
     */
    @RequestMapping(value = "/changepassword", method = RequestMethod.PUT)
    @ResponseBody
    public String changepswdao(String oldpassword, String newpassword, String newpassword1) {
        if(!verifypassword(newpassword)){
            return "206";
        }
        if(!verifypassword(newpassword1)){
            return "206";
        }
        Student student = (Student) request.getSession().getAttribute("student");
        Student studentdao = loginService.selectByName(student.getUsername());
        int result;
        if (newpassword.equals(newpassword1) && !newpassword.equals("") && !newpassword1.equals("")) {
            if (studentdao.getPwd().equals(oldpassword)) {

                if(oldpassword.equals(newpassword)){
                    return "205";
                }else{
                    result = indexService.updatepassword(newpassword, student.getId());
                    if (result > 0) {
                        return "200";
                    }else{
                        return "204";
                    }
                }
            } else {
                return "201";
            }
        } else if (newpassword.equals("") && newpassword1.equals("")) {
            return "202";
        }
        return "203";
    }


    //????????????
    public boolean verifypassword(String password){
        if(password.length() < 6 || password.length() > 16){
            return false;
        }
        for(int i = 0;i < password.length();i++){
            if(!(password.charAt(i)>='A' && password.charAt(i)<='Z')){
                if(!(password.charAt(i)>='a' && password.charAt(i)<='z')){
                    if(!(password.charAt(i)>='0' && password.charAt(i)<='9')){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //??????
    //??????Session??????
    @RequestMapping("/exit")
    public String exit(HttpServletResponse response,HttpSession httpSession) {
//        httpSession.setAttribute("XZproject", null);
//       ??????Session
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        //???Cookie ??????token ??????
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "student/login";
    }




    /**
     * ????????????????????????
     */
    @RequestMapping("/classinfo")
    public String classinfo(Model model,HttpSession httpSession) {

        Student student = (Student) request.getSession().getAttribute("student");
        removeSession();
        List<Static_student> list =  adminService.select_student(null, null, student.getIdClass(), null, null);

        System.out.println(list);
        model.addAttribute("list", list);
        return "student/classinfo";
    }


    /**
     * ????????????
     */
    @RequestMapping("/topics")
    public String Topics(Model model,HttpSession httpSession) {
        Student student = (Student) request.getSession().getAttribute("student");
        removeSession();
        List<topicsinfo> topicsinfolist = topicsService.topics(student.getIdClass());
        System.out.println(topicsinfolist);
        model.addAttribute("topicsinfolist", topicsinfolist);
        return "student/topicsinfo";
    }


    /**
     * ??????????????????
     */
    @RequestMapping("/topicsto")
    public String Topicsto(Long project_id,int selectFlag,String projectName, Model model, HttpSession httpSession) {
        List<topicsto> topicstos = topicsService.topicsinfo(project_id);
        Student student = (Student) request.getSession().getAttribute("student");
        Long flag = topicsService.state(student);
        Long flagto = topicsService.flag(project_id);
        if (flagto != 0) {
            flag = 3L;
        }
        model.addAttribute("selectFlag",selectFlag);
        model.addAttribute("flag", flag);
        model.addAttribute("topicstos", topicstos);
        model.addAttribute("projectName", projectName);
        model.addAttribute("project_id", project_id);
        model.addAttribute("XZproject", httpSession.getAttribute("XZproject"));
        return "student/topicsinfoto";
    }


    /**
     * ????????????
     */
    @Autowired
    SubjectselectedMapper subjectselectedMapper;
    @RequestMapping("/enroll")
    @ResponseBody
    public String enroll(Long project_id, HttpSession httpSession) {
        String projectName = topicsService.selectprojectname(project_id);
        Student student = (Student) request.getSession().getAttribute("student");
        List<Subjectselected> subjectselected = subjectselectedMapper.selectBystudentid(student.getId());


        if(subjectselected.size() == 0){
            studentService.updateselectnumAdd(projectName);
            topicsService.insertproject(projectName, student.getId());
            httpSession.setAttribute("XZproject", projectName);
            return "200";
        }else {
            return "201";
        }

    }

    /**
     * ????????????
     */
    @RequestMapping("/cancel")
    @ResponseBody()
    public String cancel(Long project_id, Model model, HttpSession httpSession) {
        System.out.println(1);
        String projectName = topicsService.selectprojectname(project_id);
        Student student = (Student) request.getSession().getAttribute("student");
        List<Subjectselected> subjectselected = subjectselectedMapper.selectBystudentid(student.getId());

        if (subjectselected != null && subjectselected.size() != 0 && subjectselected.get(0).getStuselectFlag() != 3
                && project_id.equals(subjectselected.get(0).getIdProject())) {
            topicsService.deleteprojectselectedid(student.getId());
            httpSession.removeAttribute("XZproject");
            model.addAttribute("XZproject", null);
            httpSession.setAttribute("XZproject", null);
            studentService.updateselectnumReduce(projectName);
            return "200";

        } else {
            return "203";
        }
    }

    /**
     * ??????session??????????????????
     */
    public void removeSession(){
        Student student = (Student) request.getSession().getAttribute("student");
        Subject project = indexService.indexinfo(student.getId());
        if(project==null){
            request.getSession().removeAttribute("XZproject");
        }
    }




}
