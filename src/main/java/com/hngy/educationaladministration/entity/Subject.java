package com.hngy.educationaladministration.entity;

import com.hngy.educationaladministration.valid.interfaces.Add;
import com.hngy.educationaladministration.valid.interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Subject {
    private Long id;

    @NotNull(message = "请选择课题类型" , groups = {Add.class, Update.class} )
    private Long idProjecttype;

    @NotNull(message = "请选择课题来源" , groups = {Add.class,Update.class})
    private Long idProjectsource;

    @NotNull(message = "请选择发布教师" , groups = {Add.class,Update.class})
    private Long idTeacher;

    @Size(min = 1,max = 200,message = "课题名应该在1-200之间",groups = {Add.class,Update.class})
    @NotNull(message = "请输入课题名" , groups = {Add.class,Update.class})
    private String projectname;

    @Size(min = 1,max = 200,message = "专业名应该在1-200之间",groups = {Add.class,Update.class})
    @NotNull(message = "请选择适用专业" , groups = {Add.class,Update.class})
    private String marchspecialty;

    //TODO 课题上传问题
    private String filepath;

    @NotNull(message = "请填写指导老师" , groups = {Add.class,Update.class})
    @Size(min = 1,max = 200,message = "指导老师名应该在1-200之间",groups = {Add.class,Update.class})
    private String teachernames;

    private Integer selectcount;

    @NotNull(message = "请选择可选状态" , groups = {Add.class,Update.class})
    private Integer selectFlag;

    @NotNull(message = "请选择课题状态" , groups = {Add.class,Update.class})
    private Integer verifyprojectFlag;

    @NotNull(message = "请选择发布状态" , groups = {Add.class,Update.class})
    private Integer releaseFlag;

    public enum Column {
        id("id", "id", "BIGINT", false),
        idProjecttype("id_projectType", "idProjecttype", "BIGINT", false),
        idProjectsource("id_projectSource", "idProjectsource", "BIGINT", false),
        idTeacher("id_teacher", "idTeacher", "BIGINT", false),
        projectname("projectName", "projectname", "VARCHAR", false),
        marchspecialty("marchSpecialty", "marchspecialty", "VARCHAR", false),
        filepath("filePath", "filepath", "VARCHAR", false),
        teachernames("teacherNames", "teachernames", "VARCHAR", false),
        selectcount("selectCount", "selectcount", "INTEGER", false),
        selectFlag("select_flag", "selectFlag", "INTEGER", false),
        verifyprojectFlag("verifyProject_flag", "verifyprojectFlag", "INTEGER", false),
        releaseFlag("release_flag", "releaseFlag", "INTEGER", false),
        remark("remark", "remark", "LONGVARCHAR", false),
        bak1("bak1", "bak1", "LONGVARCHAR", false),
        bak2("bak2", "bak2", "LONGVARCHAR", false),
        bak3("bak3", "bak3", "LONGVARCHAR", false),
        bak4("bak4", "bak4", "LONGVARCHAR", false);

        private static final String BEGINNING_DELIMITER = "\"";

        private static final String ENDING_DELIMITER = "\"";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }




    //    --------------------------------------------------------------------------------------------------------------------------------
    // 自己新建类，方便查询
    private String sourceName;// 课题来源
    private String typeName;// 课题类型
    private String ProjectZT;// 课题状态 0 未审核 1 审核未通过 2 审核通过
    private String ProjectGB; // 0 未关闭 1 已关闭;

    // 生成一览表用的
    private String teacher_name;
    private String section_name;



}