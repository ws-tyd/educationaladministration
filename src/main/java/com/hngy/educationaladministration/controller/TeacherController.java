package com.hngy.educationaladministration.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hngy.educationaladministration.auth.Authority;
import com.hngy.educationaladministration.auth.Role;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.dto.BusinessDTO;
import com.hngy.educationaladministration.entity.dto.TeachingWork;
import com.hngy.educationaladministration.entity.dto.WorkloadDTO;
import com.hngy.educationaladministration.service.AdminService;
import com.hngy.educationaladministration.service.SubjectService;
import com.hngy.educationaladministration.service.TeacherService;
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
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


@Authority(roles = {Role.TEACHER})
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = {"", "/loginPage"})
    public String loginPage() {
        return "teacher/login";
    }

    @GetMapping("/index")
    public String homePage() {
        return "teacher/public-teacher-index";
    }

    @GetMapping("/updatePwd")
    public String updatePwd() {
        return "teacher/teacherInfo/updatePwd";
    }

    @GetMapping("/teacherInfo")
    public String teacherInfo() {
        return "teacher/teacherInfo/teacherinfo";
    }

    @GetMapping("/modifyinfo")
    public String modifyInfo() {
        return "teacher/teacherInfo/updateinfo";
    }

    @GetMapping("/workapprovalinfo")
    public String workInfo() {
        return "teacher/workapproval/winfo";
    }

    @GetMapping("/workapprovaldata")
    public String workData() {
        return "teacher/workapproval/wdata";
    }

    @GetMapping("/seeworkdata")
    public String seeWorkData() {
        return "teacher/workapproval/seewdata";
    }

    //??????????????????
    @GetMapping("/term_debriefing")
    public String termDebriefing() {
        return "teacher/fillouttable/termdebriefing";
    }

    @GetMapping("/year_debriefing")
    public String yearDebriefing() {
        return "teacher/fillouttable/yeardebriefing";
    }

    @GetMapping("/annual_assessment")
    public String annualAssessment() {
        return "teacher/fillouttable/annualassessment";
    }

    @GetMapping("/work_load")
    public String workLoad() {
        return "teacher/fillouttable/workload";
    }

    @GetMapping("/technical_personnel")
    public String technicalPersonnel() {
        return "teacher/fillouttable/technicalpersonnel";
    }

    @GetMapping("/term_business")
    public String termBusiness() {
        return "teacher/fillouttable/termbusiness";
    }

    //??????????????????
    @GetMapping("/show_year_debriefing")
    public String showYearDebriefing() {
        return "teacher/showtable/yeardebriefing";
    }

    @GetMapping("/show_term_debriefing")
    public String showTermDebriefing() {
        return "teacher/showtable/termdebriefing";
    }

    @GetMapping("/show_annual_assessment")
    public String showAnnualAssessment() {
        return "teacher/showtable/annualassessment";
    }

    @GetMapping("/show_technical_personnel")
    public String showTechnicalPersonnel() {
        return "teacher/showtable/technicalpersonnel";
    }

    @GetMapping("/show_workload")
    public String showWorkLoad() {
        return "teacher/showtable/workload";
    }

    @GetMapping("/exit")
    public String exit(HttpServletResponse response) {
        //???Cookie ??????token ??????
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }

    //????????????
    @GetMapping("/print_term_debriefing")
    public String printYearDebriefing(Long year, String term, Model model) {
        model.addAttribute("year", year);
        model.addAttribute("term", term);
        return "teacher/showtable/print/termdebriefing";
    }

    @GetMapping("/print_year_debriefing")
    public String printTermDebriefing(Long year, Model model) {
        model.addAttribute("year", year);
        return "teacher/showtable/print/yeardebriefing";
    }


    @GetMapping("/login")
    @ResponseBody
    public Msg login(String name, String pwd, HttpSession httpSession, HttpServletResponse response) throws ParseException {
        name = name.trim();
        int flag = teacherService.teacherDL(name, pwd);
        if (flag == 200) {
            User user = new User();
            //-1???????????????
            user.setId(0L);
            user.setRole("teacher");
            user.setUserName(name);
            //??????Token ?????? Cookie
            Cookie cookie = new Cookie("token", TokenUtil.createToken(
                    user
            ));
            //???Cookie?????????js??????
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);


            Teacher teacher = teacherService.selectTeacher(name);

            httpSession.setAttribute("teacherInfo", teacher);
            httpSession.setMaxInactiveInterval(3600);
        }
        return Msg.success().add("info", flag);
    }

    //??????????????????
    //??????????????????
    @PostMapping("/teacherupdetpwd")
    @ResponseBody
    public Msg fun6(String oldpwd, String newpwd, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        int flag = teacherService.teacherUpdetpwd(teacher.getUsername(), oldpwd, newpwd);
        return Msg.success().add("flag", flag);
    }

    //??????????????????
    @PostMapping("/teacherupdeteinfo")
    @ResponseBody
    public Msg updateinfo(String name, String gender, HttpSession httpSession) {
        TeacherWithBLOBs teacher = (TeacherWithBLOBs) httpSession.getAttribute("teacherInfo");
        teacher.setName(name);
        teacher.setGender(gender);
        teacherService.teacherupdateInfo(teacher);
        return Msg.success();
    }


    //??????????????????
    //????????????????????????????????????
    @GetMapping("/select_work_all")
    @ResponseBody
    public Msg fun1(HttpSession httpSession) {
        TeacherWithBLOBs teacher = (TeacherWithBLOBs) httpSession.getAttribute("teacherInfo");
        List<WorkapprovalWithBLOBs> list = teacherService.selectTeacherWorkAll(teacher.getId());
        return Msg.success().add("workinfo", list);
    }

    //????????????????????????????????????
    @GetMapping("/select_work_success")
    @ResponseBody
    public Msg fun2(HttpSession httpSession) {
        TeacherWithBLOBs teacher = (TeacherWithBLOBs) httpSession.getAttribute("teacherInfo");
        List<WorkapprovalWithBLOBs> list = teacherService.selectWorkSuccess(teacher.getId());
        return Msg.success().add("workinfo", list);
    }

    //????????????????????????????????????
    @GetMapping("/select_work_failed")
    @ResponseBody
    public Msg fun3(HttpSession httpSession) {
        TeacherWithBLOBs teacher = (TeacherWithBLOBs) httpSession.getAttribute("teacherInfo");
        List<WorkapprovalWithBLOBs> list = teacherService.selectWorkFailed(teacher.getId());
        return Msg.success().add("workinfo", list);
    }

    //?????????????????????????????????
    @GetMapping("/select_work_submitted")
    @ResponseBody
    public Msg fun4(HttpSession httpSession) {
        TeacherWithBLOBs teacher = (TeacherWithBLOBs) httpSession.getAttribute("teacherInfo");
        List<WorkapprovalWithBLOBs> list = teacherService.selectWorkSubmitted(teacher.getId());
        return Msg.success().add("workinfo", list);
    }

    //?????????????????????????????????
    @PostMapping("/delete_work")
    @ResponseBody
    public Msg deleteWork(Long id) {
        teacherService.deleteWorkById(id);
        return Msg.success();
    }

    //????????????????????????
    @GetMapping("/fillworkapproval")
    public String fun5(Long id, Model model) throws ParseException {
        WorkapprovalWithBLOBs workapproval = teacherService.selectWorkById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String start = sdf.format(workapproval.getBeginDate());
        String end = sdf.format(workapproval.getEndDate());
        String time = start + " - " + end;
        model.addAttribute("workapproval", workapproval);
        model.addAttribute("time", time);
        return "teacher/workapproval/fillwdata";
    }


    //??????????????????
    @PostMapping("/fill_in_w")
    @ResponseBody
    public Msg fun7(@RequestParam("id_work") Long idWork, @RequestParam("news") String news, @RequestParam("flag") Integer flag,
                    @RequestParam("file") MultipartFile file) throws IOException {
        //??????file??????????????????
        if (file.isEmpty()) {
            return Msg.error();
        }

        String fileName = file.getOriginalFilename();// ???????????????????????????
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        File path = new File(ResourceUtils.getURL("target").getPath());
        String savePath = path.getAbsolutePath() + "\\classes\\static\\model";
        String saveFileName = savePath + "\\" + fileName;

//        String path = "D:/test";//??????????????????
        File targetFile = new File(savePath);
        if (!targetFile.getParentFile().exists()) { //?????????????????????????????????
            targetFile.getParentFile().mkdir();
        }
        file.transferTo(new File(targetFile, fileName)); // ??????????????????

        Workapprovaldata workapprovaldata = new Workapprovaldata();
        workapprovaldata.setIdWorkapproval(idWork);
        workapprovaldata.setNews(news);
        workapprovaldata.setDatarar(saveFileName);
        //flag == 0 ??????  flag == 1??????
        workapprovaldata.setFlag(flag);

        teacherService.insertWordData(workapprovaldata);

        return Msg.success();
    }

    //??????????????????
    @GetMapping("/select_work_data")
    @ResponseBody
    public Msg fun8(Integer pn, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        PageHelper.startPage(pn, 9);
        List<Workapprovaldata> list = teacherService.selectWorkData(teacher.getIdSection());
        PageInfo page = new PageInfo(list, 5);
        return Msg.success().add("dataInfo", page);
    }

    //??????????????????
    @RequestMapping(value = "/file_download")
    public ResponseEntity<byte[]> downloadFile(String dataId, HttpServletRequest req, HttpServletResponse response) throws IOException {

        Workapprovaldata workapprovaldata = null;

        if (dataId != null) {
            Long id = Long.valueOf(dataId);
            workapprovaldata = teacherService.selectWorkDataById(id);
        }

        if (workapprovaldata != null) {
            String filePath = workapprovaldata.getDatarar();
            //??????????????????
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            String fileName = file.getName();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            String encodeFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
            headers.setContentDispositionFormData("attachment", encodeFilename);

            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        }
        return null;
    }

    //????????????
    @PostMapping("/upload_term_debriefing")
    @ResponseBody
    public Msg fun9(String year, String term, String teachingTask, String scientificResearch,
                    String otherWork, String winAward, String summary, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        DebriefingWithBLOBs debriefingWithBLOBs = new DebriefingWithBLOBs();
        debriefingWithBLOBs.setIdTeacher(teacher.getId());
        debriefingWithBLOBs.setYear(Long.parseLong(year));
        debriefingWithBLOBs.setTerm(term);
        debriefingWithBLOBs.setTeachingtask(teachingTask);
        debriefingWithBLOBs.setAchievementsinscientificresearch(scientificResearch);
        debriefingWithBLOBs.setOtherwork(otherWork);
        debriefingWithBLOBs.setWinaward(winAward);
        debriefingWithBLOBs.setSummary(summary);
        int flag = teacherService.selectTermDebriefingFlag(teacher.getId(), Long.parseLong(year), term);
        if (flag == 1) {
            teacherService.updateTermDebriefing(debriefingWithBLOBs);
        } else {
            int i = teacherService.insertTermDebriefing(debriefingWithBLOBs);
        }
        return Msg.success();
    }

    // ??????????????????

    @GetMapping("/wordload")
    public String wordloadPage() {
        return "teacher/table/workload";
    }


    @GetMapping("/wordloadData")
    @ResponseBody
    public Msg wordloadData(
            @RequestParam("year") String year,
            @RequestParam("trem") String trem
    ) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacherInfo");

        return Msg.success()
                .add("teacher", teacher)
                .add("workloadDTO", teacherService.getWorkload(teacher.getId(), year, trem));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    HttpServletRequest request;

    @PostMapping("/wordload")
    @ResponseBody
    public Msg wordloadSave(
            @RequestBody WorkloadDTO workloadDTO
    ) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacherInfo");
//        LOGGER.info("{}",workloadDTO);
        teacherService.saveWorkload(workloadDTO, teacher);
        return Msg.success();
    }

    // ???????????????
    @GetMapping("/business")
    public String businessPage() {
        return "teacher/table/business";
    }

    @GetMapping("/businessData")
    @ResponseBody
    public Msg businessData(
            @RequestParam("year") String year,
            @RequestParam("trem") String trem
    ) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacherInfo");
        return teacherService.getBusiness(teacher.getId(), year, trem)
                .add("teacher", teacher);
    }

    @PostMapping("/business")
    @ResponseBody
    public Msg saveBusiness(
            @RequestBody BusinessDTO businessDTO
    ) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacherInfo");
        return Msg.sqlChange((int) teacherService.saveBusiness(businessDTO, teacher));
    }

    //????????????
    @PostMapping("/upload_year_debriefing")
    @ResponseBody
    public Msg fun10(String year, String teachingTask, String scientificResearch,
                     String otherWork, String winAward, String summary, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        DebriefingYearWithBLOBs debriefingYear = new DebriefingYearWithBLOBs();
        debriefingYear.setIdTeacher(teacher.getId());
        debriefingYear.setYear(Long.parseLong(year));
        debriefingYear.setTeachingtask(teachingTask);
        debriefingYear.setAchievementsinscientificresearch(scientificResearch);
        debriefingYear.setOtherwork(otherWork);
        debriefingYear.setWinaward(winAward);
        debriefingYear.setSummary(summary);
        Long flag = teacherService.selectYearDebriefingFlag(teacher.getId(), Long.parseLong(year));
        if (flag == 1) {
            teacherService.updateYearDebriefing(debriefingYear);
        } else {
            int i = teacherService.insertYearDebriefing(debriefingYear);
        }

        return Msg.success();
    }

    //???????????????????????????
    @GetMapping("/select_debriefing_year")
    @ResponseBody
    public Msg fun11() {
        List<DebriefingYear> list = teacherService.selectDebriefingByYear();

//         ???????????????
        Collections.sort(list, new Comparator<DebriefingYear>() {
            @Override
            public int compare(DebriefingYear o1, DebriefingYear o2) {
                return (int) (o2.getYear() - o1.getYear());
            }
        });
        return Msg.success().add("year", list);
    }


    //???????????????????????????????????????
    @GetMapping("/select_debriefing_year_info")
    @ResponseBody
    public Msg fun12(Long year, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        DebriefingYearWithBLOBs debriefingYear = teacherService.selectYearDebriefingInfo(teacher.getId(), year);
        return Msg.success().add("debriefingInfo", debriefingYear);
    }

    //???????????????????????????
    @GetMapping("select_debriefing_term")
    @ResponseBody
    public Msg fun13() {
        List<Debriefing> list = teacherService.selectDebriefingTermByYear();
        List<Long> temp = new ArrayList<>();
        //?????????????????????
        for (Debriefing s : list) {
            if (!temp.contains(s.getYear())) {
                temp.add(s.getYear());
            }
        }
        return Msg.success().add("year", temp);
    }

    //???????????????????????????????????????
    @GetMapping("/select_debriefing_term_info")
    @ResponseBody
    public Msg fun14(Long year, String term, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        DebriefingWithBLOBs debriefing = teacherService.selectTermDebriefingInfo(teacher.getId(), year, term);
        return Msg.success().add("debriefingInfo", debriefing);
    }

    //????????????
    @PostMapping("/upload_annual_assessment")
    @ResponseBody
    public Msg fun15(String personalSummary, String year, String remark, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        AnnualAssessmentWithBLOBs assessment = new AnnualAssessmentWithBLOBs();
        assessment.setIdTeacher(teacher.getId());
        assessment.setPersonalsummary(personalSummary);
        assessment.setYear(year);
        assessment.setRemark(remark);
        Long flag = teacherService.selectAnnualAssessmentFlag(teacher.getId(), year);
        if (flag == 1) {
            int i = teacherService.updateAnnualAssessment(assessment);
        } else {
            int i = teacherService.insertAnnualAssessment(assessment);
        }
        return Msg.success();
    }

    //?????????????????????????????????
    @PostMapping("/upload_technical_personnel")
    @ResponseBody
    public Msg fun16(String year, String mainAchievements, String attendance, String rewardsAndPunishments, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        TechnicalPersonnelWithBLOBs technicalPersonnelWithBLOBs = new TechnicalPersonnelWithBLOBs();
        technicalPersonnelWithBLOBs.setIdTeacher(teacher.getId());
        technicalPersonnelWithBLOBs.setYear(year);
        technicalPersonnelWithBLOBs.setMainachievements(mainAchievements);
        technicalPersonnelWithBLOBs.setAttendance(attendance);
        technicalPersonnelWithBLOBs.setRewardsandpunishments(rewardsAndPunishments);
        Long flag = teacherService.selectTechnicalPersonnelFlag(teacher.getId(), Long.parseLong(year));
        if (flag == 1) {
            int i = teacherService.updateTechnicalPersonnel(technicalPersonnelWithBLOBs);
        } else {
            int i = teacherService.insertTechnicalPersonnel(technicalPersonnelWithBLOBs);
        }
        return Msg.success();
    }

    //????????????????????????
    @GetMapping("/select_annual_assessment")
    @ResponseBody
    public Msg fun17(HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        List<AnnualAssessment> list = teacherService.selectAnnualAssessmentByYear(teacher.getId());
        if (list.isEmpty()) {
            return Msg.fail();
        } else {
            return Msg.success().add("year", list);
        }
    }

    //??????????????????????????????
    @GetMapping("/select_annualassessment_year_info")
    @ResponseBody
    public Msg fun18(Long year, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        AnnualAssessmentWithBLOBs assessment = teacherService.selectAnnualAssessmentInfo(teacher.getId(), year);
        return Msg.success().add("assessmentInfo", assessment);
    }

    //??????????????????????????????????????????
    @GetMapping("/select_technical_personnel_year")
    @ResponseBody
    public Msg fun18(HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        List<TechnicalPersonnel> list = teacherService.selectTechnicalPersonnelByYear(teacher.getId());
        if (list.isEmpty()) {
            return Msg.fail();
        } else {
            return Msg.success().add("year", list);
        }
    }

    //??????????????????????????????????????????
    @GetMapping("/select_technicalpersonnel_year_info")
    @ResponseBody
    public Msg fun19(Long year, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        TechnicalPersonnelWithBLOBs technicalPersonnelWithBLOBs = teacherService.selectTechnicalPersonnelInfo(teacher.getId(), year);
        return Msg.success().add("technicalPersonnel", technicalPersonnelWithBLOBs);
    }

    // ??????????????????

    // ????????????????????????
    @GetMapping("/upload_topic_page")
    public String uploadTopic(ModelMap modelMap, HttpSession httpSession) {
        TeacherWithBLOBs teacher = (TeacherWithBLOBs) httpSession.getAttribute("teacherInfo");

        List<Projecttype> projecttypes = teacherService.select_allProjecttype();
        List<Projectsource> projectsources = teacherService.select_allProjectsource();
        List<Specialty> specialties = teacherService.select_allSpecialty(teacher.getIdSection());

        modelMap.addAttribute("projecttypes", projecttypes);
        modelMap.addAttribute("projectsources", projectsources);
        modelMap.addAttribute("specialties", specialties);
        return "teacher/graduation/upload";
    }



    // ????????????
    @PostMapping("/up_project")
    @ResponseBody
    public Msg fun20(String projectName, Long idProjecttype, Long idProjectsource, String marchspecialty, String teachernames, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession httpSession) throws IOException {
        if (file == null) {
            return Msg.fail().add("msg","??????????????????");
        }
        if(teacherService.selectProjectByName(projectName).size()>0){
            System.out.println("????????????");
            return Msg.fail().add("msg","??????????????????");
        }

        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");

        ServletContext servletContext = request.getSession().getServletContext();

        String uploadFileName = file.getOriginalFilename(); // ???????????????????????????
        System.out.println(uploadFileName);

        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf(File.separator) + 1);
        System.out.println(uploadFileName);

        File path = new File(ResourceUtils.getURL("target").getPath());
        String savePath =
                path.getAbsolutePath() + File.separator+"classes+"+File.separator+"static"
                                    +File.separator+"model"+File.separator + teacher.getId();
        String saveFileName = savePath +File.separator + uploadFileName;


        File dirs = new File(savePath);
        //?????????????????????????????????????????????????????????
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        file.transferTo(new File(dirs, uploadFileName)); // ??????????????????
        System.out.println(teachernames);
        ProjectWithBLOBs project = new ProjectWithBLOBs();
        project.setProjectname(projectName);
        project.setIdProjecttype(idProjecttype);
        project.setIdProjectsource(idProjectsource);
        project.setIdTeacher(teacher.getId());
        project.setFilepath(saveFileName);
        project.setMarchspecialty(marchspecialty.trim());
        project.setTeachernames(teachernames);
        project.setSelectcount(0);
        project.setSelectFlag(0);
        project.setVerifyprojectFlag(0);
        project.setReleaseFlag(0);

        int i = teacherService.insert_project(project);
        return Msg.success();
    }

    //?????????????????????????????????
    @GetMapping("/cxmyProject")
    public String fun21(ModelMap modelMap, HttpSession httpSession)  {

        TeacherWithBLOBs teacher = (TeacherWithBLOBs) httpSession.getAttribute("teacherInfo");

        List<Projecttype> projecttypes = teacherService.select_allProjecttype();
        List<Projectsource> projectsources = teacherService.select_allProjectsource();
        List<Specialty> specialties = teacherService.select_allSpecialty(teacher.getIdSection());

        modelMap.addAttribute("projecttypes", projecttypes);
        modelMap.addAttribute("projectsources", projectsources);
        modelMap.addAttribute("specialties", specialties);

        List<Project> projects = teacherService.selectTeacherProject(teacher.getName());

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getVerifyprojectFlag() == 0) projects.get(i).setProjectZT("?????????");
            else if (projects.get(i).getVerifyprojectFlag() == 1) projects.get(i).setProjectZT("???????????????");
            else projects.get(i).setProjectZT("????????????");
        }
        modelMap.addAttribute("Myproject", projects);
        return "teacher/graduation/section_xq/index";
    }

    // ?????????????????????????????????????????????
    @PostMapping("/fb_project")
    @ResponseBody
    public String fun8(Long project_id, String pd,HttpSession httpSession) {

        int s = Integer.parseInt(pd);
        teacherService.updateProjectFB(project_id, s);
        if (s == 0) {
            teacherService.deleteSelectedAll(project_id);
            teacherService.updateProjectCount(project_id);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("pd", "" + 1);
        return JSONObject.toJSONString(map);
    }


    @PostMapping("del_project")
    @ResponseBody
    public Msg deleteProject(Long id) {
        teacherService.deleteProject(id);
        return Msg.success();
    }

    @PostMapping("/updateSubject")
    @ResponseBody
    public Msg updateProject(Long id, String projectName, Long idProjecttype, Long idProjectsource, String marchspecialty, String teachernames, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpSession httpSession) throws IOException {

        //?????? teacherNames ??????
        TeacherWithBLOBs teacher = (TeacherWithBLOBs) request.getSession().getAttribute("teacherInfo");
        String teacherName = teacher.getName();
        if (teachernames == null || teachernames.trim().length() == 0) {
            teachernames = teacherName;
        } else {
            teachernames = teacherName + "&" + teachernames;
        }

        SubjectWithBLOBs subject = null;

        //???????????? ??? 0 ????????? ???????????????
        long size = file.getSize();
        //???????????????????????????
        if (file == null || size == 0) {
            subject = new SubjectWithBLOBs();

            subject.setId(id);
            subject.setProjectname(projectName);
            subject.setIdProjecttype(idProjecttype);
            subject.setIdProjectsource(idProjectsource);
            subject.setMarchspecialty(marchspecialty);
            subject.setTeachernames(teachernames);

            //?????????????????? 0
            subject.setSelectFlag(0);
            subject.setVerifyprojectFlag(0);
            subject.setReleaseFlag(0);

            subjectService.updateSubjectByid(subject);

            return Msg.success();

        } else {

            //????????????
            SubjectWithBLOBs subject1 = subjectService.getSubjectByID(id);
            //??????????????????
            String oldPath = subject1.getFilepath();
            File oldFile = new File(oldPath);

            //???????????????????????????
            if (oldFile.exists()) {
                //????????????
                if (oldFile.delete()) {

                } else {
                    return Msg.fail();
                }
            }

            ServletContext servletContext = request.getSession().getServletContext();

            String uploadFileName = file.getOriginalFilename(); // ???????????????????????????

            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf(File.separator) + 1);

            File path = new File(ResourceUtils.getURL("target").getPath());
//            String savePath = path.getAbsolutePath() + "\\classes\\static\\model\\" + teacher.getId();
//            String saveFileName = savePath + "\\" + uploadFileName;
            String savePath =
                    path.getAbsolutePath() + File.separator+"classes+"+File.separator+"static"
                            +File.separator+"model"+File.separator + teacher.getId();
            String saveFileName = savePath +File.separator + uploadFileName;



















            File dirs = new File(savePath);
            //?????????????????????????????????????????????????????????
            if (!dirs.exists()) {
                dirs.mkdirs();
            }
            file.transferTo(new File(dirs, uploadFileName)); // ??????????????????

            SubjectWithBLOBs project = subject1;
            project.setProjectname(projectName);
            project.setIdProjecttype(idProjecttype);
            project.setIdProjectsource(idProjectsource);
            project.setFilepath(saveFileName);
            project.setMarchspecialty(marchspecialty.trim());
            project.setTeachernames(teachernames);
            //?????????????????? 0
            project.setSelectFlag(0);
            project.setVerifyprojectFlag(0);
            project.setReleaseFlag(0);

            int i = subjectService.updateSubjectByid(project);
            return Msg.success();

        }
    }

    @GetMapping("/getSubjectById")
    @ResponseBody
    public Msg getss(Long id) {
        SubjectWithBLOBs subject = subjectService.getSubjectByID(id);
        System.out.println(subject);
        subject.setFilepath(subject.getFilepath().substring(subject.getFilepath().lastIndexOf("\\") + 1));

        String[] teachers = subject.getTeachernames().split("&");
        String teacher2 = "";
        if (teachers.length >= 2) {
            teacher2 = teachers[teachers.length - 1];
        }

        subject.setTeachernames(teacher2);

        return Msg.success()
                .add("subject", subject)
                ;
    }

    //????????????????????????????????????????????????
    @GetMapping("/cxallProject")
    public String fun7(ModelMap modelMap, HttpSession httpSession) {

        Teacher teacher = (Teacher) httpSession.getAttribute("teacherInfo");
        List<Project> projects = teacherService.selecSectionProject(teacher.getSectionName());
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getVerifyprojectFlag() == 0) projects.get(i).setProjectZT("?????????");
            else if (projects.get(i).getVerifyprojectFlag() == 1) projects.get(i).setProjectZT("???????????????");
            else projects.get(i).setProjectZT("????????????");
        }
        modelMap.addAttribute("allproject", projects);
        return "teacher/graduation/section_xq/subjectinfoto";
    }
}




