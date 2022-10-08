package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.*;
import com.hngy.educationaladministration.entity.Class;
import com.hngy.educationaladministration.entity.dto.BusinessDTO;
import com.hngy.educationaladministration.entity.dto.WorkloadDTO;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    Teacher selectTeacher(String username);//以用户登入id查询

    int teacherDL(String name, String pwd);// 教师登入

    //出差申请方向
    List<WorkapprovalWithBLOBs> selectTeacherWorkAll(Long id_teacher);//根据教师id查询出差申请

    List<WorkapprovalWithBLOBs> selectWorkSuccess(Long id_teacher);//根据教师id查询出差成功申请

    List<WorkapprovalWithBLOBs> selectWorkFailed(Long id_teacher);//根据教师id查询出差失败申请

    List<WorkapprovalWithBLOBs> selectWorkSubmitted(Long id_teacher);//根据教师id查询已提交申请

    WorkapprovalWithBLOBs selectWorkById(Long id); //通过id查询出差申请

    List<Workapprovaldata> selectWorkData(Long section_id);//查询本教研室内发布的报告

    Workapprovaldata selectWorkDataById(Long id);//通过id查询出差报告

    void insertWordData(Workapprovaldata workapprovaldata);//填写出差报告

    int deleteWorkById(Long id);//删除申请失败的出差报告



    //--- subadmin 部分

    //分页查询
    List selectTeachers(Integer offset, String getName, Long sectionid, Long id_institute);

    //分页查询的总量
    long selectTeachersCount(Integer offset, String getName, Long sectionid, Long id_institute);

    long delTeacher(TeacherWithBLOBs teacher, Long id_institute) throws MyException;

    long addTeacher(TeacherWithBLOBs teacher,Long id_institute) throws MyException;

    long updateTeacher(TeacherWithBLOBs teacher,Long id_institute) throws MyException;

    // 工作量表

    /**
     * 保存工作量     将组合表分解保存到数据库
     * @param workloadDTO
     * @return
     */
    long saveWorkload(WorkloadDTO workloadDTO,Teacher teacher);

    /**
     * 将工作量拆分 展示到页面去
     */
    WorkloadDTO getWorkload(long teacherID , String year , String trem);

    //业务表
    /**
     * 保存业务表
     */
    long saveBusiness(BusinessDTO businessDTO,Teacher teacher);

    /**
     * 查询业务表
     */

    //报表方向

    /**
     *
     * @param debriefingWithBLOBs
     * @return
     */

    int selectTermDebriefingFlag(Long id_teacher, Long year, String term);//查询学期指定教师学期述职表是否填写

    int insertTermDebriefing(DebriefingWithBLOBs debriefingWithBLOBs);//填写学期述职表

    void updateTermDebriefing(DebriefingWithBLOBs debriefingWithBLOBs);//更新学期述职表

    Msg getBusiness(Long teacherID,String year,String trem);

    Long selectYearDebriefingFlag(Long id_teacher, Long year);//查询指定教师年度述职表是否填写

    int insertYearDebriefing(DebriefingYearWithBLOBs debriefingYearWithBLOBs);//填写年度述职表

    void updateYearDebriefing(DebriefingYearWithBLOBs debriefingYearWithBLOBs);//更新年度述职表

    List<DebriefingYear> selectDebriefingByYear();//获取年度述职中所有年

    List<Debriefing> selectDebriefingTermByYear();//获取学期述职中所有年

    DebriefingYearWithBLOBs selectYearDebriefingInfo(Long id_teacher, Long year);//获取当前教师，当前年份的年度述职信息

    DebriefingWithBLOBs selectTermDebriefingInfo(Long id_teacher, Long year, String term);//获取当前教师，当前年份的学期述职信息

    Long selectAnnualAssessmentFlag(Long id_teacher, String year);//查询该教师年度考核登记表是否填写

    int insertAnnualAssessment(AnnualAssessmentWithBLOBs annualAssessmentWithBLOBs);//填写年度考核登记表

    int updateAnnualAssessment(AnnualAssessmentWithBLOBs annualAssessmentWithBLOBs);//更新年度考核登记表

    Long selectTechnicalPersonnelFlag(Long id_teacher, Long year);//查询该教师年度专业技术人员考核表是否填写

    int insertTechnicalPersonnel(TechnicalPersonnelWithBLOBs technicalPersonnelWithBLOBs);//填写年度专业技术人员考核表

    int updateTechnicalPersonnel(TechnicalPersonnelWithBLOBs technicalPersonnelWithBLOBs);//更新年度专业技术人员考核表

    List<AnnualAssessment> selectAnnualAssessmentByYear(Long id_teacher);//获取年度考核年份

    AnnualAssessmentWithBLOBs selectAnnualAssessmentInfo(Long id_teacher, Long year);//获取当前教师，当前年份的年度考核信息

    List<TechnicalPersonnel> selectTechnicalPersonnelByYear(Long id_teacher);//获取年度专业技术人员考核表年份

    TechnicalPersonnelWithBLOBs selectTechnicalPersonnelInfo(Long id_teacher, Long year);//获取当前教师，当前年份的年度专业技术人员考核信息


    //毕业设计方向
    List<Projectsource> select_allProjectsource(); // 查询所有课题来源

    List<Projecttype> select_allProjecttype();// 查询所有课题类型

    List<Specialty> select_allSpecialty(Long section_id);// 以教研室id 查询 教研室下的所有专业方向

    List<Project> selectProjectByName(String projectName);//通过课题名查找课题

    List<Project> selectTeacherProject(String name);// 通过教师名字查询课题

    void updateProjectFB(Long project_id, int fb);// 根据课题名字修改发布状态

    List<Project> selecSectionProject(String name); // 以教研室名字查属于该教研室的所有课题

    int insert_project(ProjectWithBLOBs project); // 插入一个新的课题

    List<Project> selectTeacherFBProject(String name); // 通过教师名字查询 自己已发布的课题

    void updateZTprojcet(int zt, Long project_id); // 修改打开 还是 关闭状态

    void updateXZstudent(Long student_id, Long project_id, Long zt);// 根据 课题id，确认 学生id 选报成功；

    List<Project> selectProjecbySection(Long section_id); // 通过教研室id 查询所有课题；

    void updateSHprojcet(int zt, long projet_id); // 修改 通过审核还是不通过审核

//    void updateselectnum(Long zt, String project_name);//更新选题人数

    Integer selectedSuccessNum(Long project_id);//查询课题下成功选报人数

    void deleteSelected(Long project_id);//关闭选题，删除没有被选取的学生

    Integer deleteProject(Long id);//删除指定课程

    int selectAllXB(Long id_teacher);//查询指定老师下所有选报学生

    List<Student> selectProjectStudents(Long project_id);//查询指定课题下的所有选报成功的学生

    Map<Long, Class> selectClassIds(List<Long> classIds);

    void deleteSelectedAll(Long project_id);//取消发布课题，删除课题下所有学生

    void updateProjectCount(Long project_id);//取消发布课题后，更改课题选课人数

    //教师信息方向
    int teacherUpdetpwd(String username, String oldpwd, String newpwd);//修改密码

    void teacherupdateInfo(TeacherWithBLOBs teacher);

}
