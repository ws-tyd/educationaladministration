package com.hngy.educationaladministration.entity;

public class Subjectselected {
    private Long id;

    private Long idStudent;

    private Long idProject;

    private Long stuSelectFlag;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public Long getStuselectFlag() {
        return stuSelectFlag;
    }

    public void setStuselectFlag(Long stuSelectFlag) {
        this.stuSelectFlag = stuSelectFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    //自建集合
    private String student_stuNum;
    private String project_name;
    private String project_teachers;

    private Student student; // 那个学生选择的
    private Subject project; // 属于那个课题


    public String getProject_teachers() {
        return project_teachers;
    }

    public void setProject_teachers(String project_teachers) {
        this.project_teachers = project_teachers;
    }

    public String getStudent_stuNum() {
        return student_stuNum;
    }

    public void setStudent_stuNum(String student_stuNum) {
        this.student_stuNum = student_stuNum;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public String toString() {
        return "Subjectselected{" +
                "id=" + id +
                ", idStudent=" + idStudent +
                ", idProject=" + idProject +
                ", stuSelectFlag=" + stuSelectFlag +
                ", remark='" + remark + '\'' +
                ", student_stuNum='" + student_stuNum + '\'' +
                ", project_name='" + project_name + '\'' +
                ", project_teachers='" + project_teachers + '\'' +
                ", student=" + student +
                ", project=" + project +
                '}';
    }
}