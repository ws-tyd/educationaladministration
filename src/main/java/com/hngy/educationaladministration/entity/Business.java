package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.Data;

@Data
public class Business {
    private Long id;

    private Long idTeacher;

    private String year;

    private String dateofbirth;

    private String trem;

    private String theoryclass;

    private String guidingexperiment;

    private String guidingdesign;

    private String guidinginternship;

    private String guidecomprehensiveexperiment;

    private String totalclass;

    private String totalworkload;

    private String chiefeditor;

    private String jointlyedited;

    private String planningtextbook;

    private String schoolbasedtextbook;

    private String level;

    private String ranking;

    private String hostsubject;

    private String category;

    private String myrole;

    private String sick;

    private String compassionateleave;

    private Integer belate;

    private Integer leaveearly;

    private Integer tuneclass;

    private Integer missingclass;

    private Integer disciplinarypenalty;

    private String teachingopinion;

    private String departmentopinion;

    private String schoolopinion;

    public enum Column {
        id("id", "id", "BIGINT", false),
        idTeacher("id_teacher", "idTeacher", "BIGINT", false),
        year("year", "year", "VARCHAR", false),
        dateofbirth("dateOfBirth", "dateofbirth", "VARCHAR", false),
        trem("trem", "trem", "VARCHAR", false),
        theoryclass("theoryClass", "theoryclass", "VARCHAR", false),
        guidingexperiment("guidingExperiment", "guidingexperiment", "VARCHAR", false),
        guidingdesign("guidingDesign", "guidingdesign", "VARCHAR", false),
        guidinginternship("guidingInternship", "guidinginternship", "VARCHAR", false),
        guidecomprehensiveexperiment("guideComprehensiveExperiment", "guidecomprehensiveexperiment", "VARCHAR", false),
        totalclass("totalClass", "totalclass", "VARCHAR", false),
        totalworkload("totalWorkload", "totalworkload", "VARCHAR", false),
        chiefeditor("chiefEditor", "chiefeditor", "VARCHAR", false),
        jointlyedited("jointlyEdited", "jointlyedited", "VARCHAR", false),
        planningtextbook("planningTextbook", "planningtextbook", "VARCHAR", false),
        schoolbasedtextbook("schoolBasedTextbook", "schoolbasedtextbook", "VARCHAR", false),
        level("level", "level", "VARCHAR", false),
        ranking("ranking", "ranking", "VARCHAR", false),
        hostsubject("hostSubject", "hostsubject", "VARCHAR", false),
        category("category", "category", "VARCHAR", false),
        myrole("myRole", "myrole", "VARCHAR", false),
        sick("sick", "sick", "VARCHAR", false),
        compassionateleave("compassionateLeave", "compassionateleave", "VARCHAR", false),
        belate("beLate", "belate", "INTEGER", false),
        leaveearly("leaveEarly", "leaveearly", "INTEGER", false),
        tuneclass("tuneClass", "tuneclass", "INTEGER", false),
        missingclass("missingClass", "missingclass", "INTEGER", false),
        disciplinarypenalty("disciplinaryPenalty", "disciplinarypenalty", "INTEGER", false),
        teachingopinion("teachingOpinion", "teachingopinion", "VARCHAR", false),
        departmentopinion("departmentOpinion", "departmentopinion", "VARCHAR", false),
        schoolopinion("schoolOpinion", "schoolopinion", "VARCHAR", false),
        classes("classes", "classes", "LONGVARCHAR", false),
        coursename("courseName", "coursename", "LONGVARCHAR", false),
        textbookname("textbookName", "textbookname", "LONGVARCHAR", false),
        publishingunitandtime("publishingUnitAndTime", "publishingunitandtime", "LONGVARCHAR", false),
        other("other", "other", "LONGVARCHAR", false),
        papertitle("paperTitle", "papertitle", "LONGVARCHAR", false),
        publishingsituation("publishingSituation", "publishingsituation", "LONGVARCHAR", false),
        cooperationunit("cooperationUnit", "cooperationunit", "LONGVARCHAR", false),
        awards("awards", "awards", "LONGVARCHAR", false),
        academicgroup("academicGroup", "academicgroup", "LONGVARCHAR", false),
        continuingeducation("ContinuingEducation", "continuingeducation", "LONGVARCHAR", false),
        otherjobs("otherJobs", "otherjobs", "LONGVARCHAR", false),
        bak1("bak1", "bak1", "LONGVARCHAR", false),
        bak2("bak2", "bak2", "LONGVARCHAR", false),
        bak3("bak3", "bak3", "LONGVARCHAR", false),
        bak4("bak4", "bak4", "LONGVARCHAR", false),
        remark("remark", "remark", "LONGVARCHAR", false);

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