package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkloadWithBLOBs extends Workload {
    private String classes;

    private String coursename;

    private String classhourofplan;

    private String jointclass;

    private String extracurricularexperiment;

    private String coursedesign;

    private String onCampuspractice;

    private String offCampustraining;

    private String graduationpractice;

    private String rsrprojectname;

    private String rsrprojectbrief;

    private String rsrprojectstatus;

    private String rsrconversionworkload;

    private String rsrpprojectleader;

    private String sprojectname;

    private String sprojectbrief;

    private String sprojectstatus;

    private String sconversionworkload;

    private String spprojectleader;

    private String sectionverify;

    private String instituteverify;

    private String bak1;

    private String bak2;

    private String bak3;

    private String bak4;

    private String remark;

    public enum Column {
        id("id", "id", "BIGINT", false),
        idTeacher("id_teacher", "idTeacher", "BIGINT", false),
        year("year", "year", "VARCHAR", false),
        term("term", "term", "VARCHAR", false),
        sum1("sum1", "sum1", "REAL", false),
        sum2("sum2", "sum2", "REAL", false),
        sum1addsum2("sum1AddSum2", "sum1addsum2", "REAL", false),
        rsrworkload("rsrWorkload", "rsrworkload", "REAL", false),
        subsidyworkload("subsidyworkload", "subsidyworkload", "REAL", false),
        totalsum("totalSum", "totalsum", "REAL", false),
        classes("classes", "classes", "LONGVARCHAR", false),
        coursename("courseName", "coursename", "LONGVARCHAR", false),
        classhourofplan("classHourOfPlan", "classhourofplan", "LONGVARCHAR", false),
        jointclass("jointClass", "jointclass", "LONGVARCHAR", false),
        extracurricularexperiment("extracurricularExperiment", "extracurricularexperiment", "LONGVARCHAR", false),
        coursedesign("courseDesign", "coursedesign", "LONGVARCHAR", false),
        onCampuspractice("on_campusPractice", "onCampuspractice", "LONGVARCHAR", false),
        offCampustraining("off_campusTraining", "offCampustraining", "LONGVARCHAR", false),
        graduationpractice("GraduationPractice", "graduationpractice", "LONGVARCHAR", false),
        rsrprojectname("rsrProjectName", "rsrprojectname", "LONGVARCHAR", false),
        rsrprojectbrief("rsrProjectBrief", "rsrprojectbrief", "LONGVARCHAR", false),
        rsrprojectstatus("rsrProjectStatus", "rsrprojectstatus", "LONGVARCHAR", false),
        rsrconversionworkload("rsrConversionWorkload", "rsrconversionworkload", "LONGVARCHAR", false),
        rsrpprojectleader("rsrPProjectLeader", "rsrpprojectleader", "LONGVARCHAR", false),
        sprojectname("sProjectName", "sprojectname", "LONGVARCHAR", false),
        sprojectbrief("sProjectBrief", "sprojectbrief", "LONGVARCHAR", false),
        sprojectstatus("sProjectStatus", "sprojectstatus", "LONGVARCHAR", false),
        sconversionworkload("sConversionWorkload", "sconversionworkload", "LONGVARCHAR", false),
        spprojectleader("sPProjectLeader", "spprojectleader", "LONGVARCHAR", false),
        sectionverify("sectionVerify", "sectionverify", "LONGVARCHAR", false),
        instituteverify("instituteVerify", "instituteverify", "LONGVARCHAR", false),
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