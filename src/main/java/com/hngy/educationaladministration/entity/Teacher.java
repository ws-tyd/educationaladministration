package com.hngy.educationaladministration.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hngy.educationaladministration.valid.interfaces.Add;
import com.hngy.educationaladministration.valid.interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Data
public class Teacher {
    private Long id;

    @NotBlank(message = "账号不能为空",groups = {Add.class})
    @Size(min = 1,max = 99,message = "字符应该在1-100之间")
    private String username;

    @NotBlank(message = "姓名不能为空",groups = {Add.class, Update.class})
    @Size(min = 1,max = 99,message = "字符应该在1-100之间")
    private String name;

    @NotBlank(message = "密码不能为空",groups = {Add.class, Update.class})
    @Size(min = 1,max = 99,message = "字符应该在1-100之间")
    private String pwd;

    @NotBlank(message = "请选择性别",groups = {Add.class, Update.class})
    private String gender;

    @NotNull(message = "请选择教研室",groups = {Add.class, Update.class})
    private Long idSection;

    @NotNull(message = "请选择身份",groups = {Add.class, Update.class})
    private Integer verifyFlag;

    private String politicalstatus;

    private String education;

    private String degree;

    private String participationdate;

    private String administrativeduty;

    private String technicalposition;

    private String technicalpositiongeted;

    private String duty;

    private String idcardno;

    //    自建类别，方便查询
    private String sectionName;//这个老师 属于那个教研室
    private String instituteName;// 学院

    public enum Column {
        id("id", "id", "BIGINT", false),
        username("userName", "username", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        pwd("pwd", "pwd", "VARCHAR", false),
        gender("gender", "gender", "VARCHAR", false),
        idSection("id_section", "idSection", "BIGINT", false),
        verifyFlag("verify_flag", "verifyFlag", "INTEGER", false),
        politicalstatus("politicalStatus", "politicalstatus", "VARCHAR", false),
        education("education", "education", "VARCHAR", false),
        degree("degree", "degree", "VARCHAR", false),
        participationdate("participationDate", "participationdate", "VARCHAR", false),
        administrativeduty("administrativeDuty", "administrativeduty", "VARCHAR", false),
        technicalposition("technicalPosition", "technicalposition", "VARCHAR", false),
        technicalpositiongeted("technicalPositionGeted", "technicalpositiongeted", "VARCHAR", false),
        duty("duty", "duty", "VARCHAR", false),
        idcardno("IDcardNo", "idcardno", "VARCHAR", false),
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

        public static Column[] excludes(Column ... excludes) {
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
}