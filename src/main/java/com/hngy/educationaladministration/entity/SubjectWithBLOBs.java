package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SubjectWithBLOBs extends Subject {
    private String remark;

    private String bak1;

    private String bak2;

    private String bak3;

    private String bak4;

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