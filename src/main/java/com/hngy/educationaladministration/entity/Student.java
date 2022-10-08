package com.hngy.educationaladministration.entity;

import com.hngy.educationaladministration.valid.interfaces.Add;
import com.hngy.educationaladministration.valid.interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Student {
    private Long id;

    @NotBlank(groups = {Add.class},message = "用户名不能为空")
    private String username;

    @NotBlank(groups = {Add.class, Update.class},message = "学号不能为空")
    private String stunum;

    @NotNull(groups = {Add.class, Update.class},message = "班级不能为空")
    private Long idClass;

    @NotBlank(groups = {Add.class, Update.class},message = "姓名不能为空")
    private String name;

    @NotBlank(groups = {Add.class, Update.class},message = "性别不能为空")
    private String gender;

    @NotBlank(groups = {Add.class, Update.class},message = "密码不能为空")
    private String pwd;

    public enum Column {
        id("id", "id", "BIGINT", false),
        username("userName", "username", "VARCHAR", false),
        stunum("stuNum", "stunum", "VARCHAR", false),
        idClass("id_class", "idClass", "BIGINT", false),
        name("name", "name", "VARCHAR", false),
        gender("gender", "gender", "VARCHAR", false),
        pwd("pwd", "pwd", "VARCHAR", false),
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