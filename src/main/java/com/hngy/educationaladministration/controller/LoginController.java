package com.hngy.educationaladministration.controller;

import com.hngy.educationaladministration.auth.Authority;
import com.hngy.educationaladministration.auth.Role;
import com.hngy.educationaladministration.entity.Msg;
import com.hngy.educationaladministration.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    AdminService adminService;

    @GetMapping("/cs")
    public String cs() {
        return "cs";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Msg login(String name, String pwd, HttpSession httpSession) {
        name = name.trim();
//        LOGGER.info("{}--{}",name,pwd);
        return adminService.login(name, pwd, httpSession);

    }
}
