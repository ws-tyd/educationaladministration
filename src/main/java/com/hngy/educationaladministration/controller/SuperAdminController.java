package com.hngy.educationaladministration.controller;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.auth.Authority;
import com.hngy.educationaladministration.auth.Role;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.service.AdminService;
import com.hngy.educationaladministration.service.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Authority(roles = {Role.SADMIN})
@Controller
@RequestMapping("/sadmin")
public class SuperAdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    CollegeService collegeService;

    @ModelAttribute("id_institute")
    public long getRoleInfo(HttpSession session) throws MyException {
        User user = (User) request.getAttribute("user");
        if(user!=null)
            return user.getId();
        throw new MyException("无法访问");
    }

    /**
     *  在这个controller 添加超管独有的功能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SuperAdminController.class);

    @PostMapping("/updatePwd")
    @ResponseBody
    public Msg updatePwd(
            @RequestBody Sadmin sadmin,
            HttpSession httpSession){
//        LOGGER.info("newPwd:{}",sadmin.getPwd());

        User user = (User)request.getAttribute("user");
        adminService.updateSadminPwdByUserName(
                user.getUserName(),
                sadmin.getPwd()
        );

        return Msg.success();
    }

    /**
     *
     * college  学院   todo 该页面需要改一下传值
     *
     */

    @GetMapping("/CollegeManagement")
    public String collegeManagement(Model model)
    {
        model.addAttribute("Colleges",collegeService.selectColleges());
        return "admin/Department/CollegeManagement";
    }
    @GetMapping("/colleges")
    @ResponseBody
    public Msg getColleges(){
        return Msg.success().add("colleges",collegeService.selectColleges());
    }
    @DeleteMapping("/college")
    @ResponseBody
    public Msg delCollege(@RequestBody Institute institute){
        return Msg.sqlChange(collegeService.delCollege(institute));
    }
    @PutMapping("/college")
    @ResponseBody
    public Msg updateCollege(@RequestBody @Validated Institute institute){
        return Msg.sqlChange(collegeService.updateCollege(institute.getId(),institute.getInstituteName()));
    }
    @PostMapping("/college")
    @ResponseBody
    public Msg addeCollege(@RequestBody @Validated Institute institute){
        return Msg.sqlChange(collegeService.addCollege(institute.getInstituteName()));
    }



    /**
     *  子管理员 管理
     */

    @GetMapping("/AdminManagement")
    public String subadmin(){
        return "admin/AdminManagement";
    }

    @GetMapping("/admin")
    @ResponseBody
    public Msg getAdmins(
            @RequestParam(required = false) Long offset
    ){

        return Msg.success().add("admins",adminService.selectAdmins(offset));
    }
    @PostMapping("/admin")
    @ResponseBody
    public Msg addAdmin(
            @RequestBody @Validated Admin admin
    ){
        return Msg.sqlChange(adminService.addAdmin(admin));
    }
    @DeleteMapping("/admin")
    @ResponseBody
    public Msg delAdmin(
            @RequestBody Admin admin
    ){
        return Msg.sqlChange(adminService.delAdmin(admin));
    }
    @PutMapping("/admin")
    @ResponseBody
    public Msg updateAdmin(
            @RequestBody @Validated Admin admin
    ){
        return Msg.sqlChange(adminService.updateAdmin(admin));
    }
}
