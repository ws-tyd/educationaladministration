package com.hngy.educationaladministration.entity;

import com.hngy.educationaladministration.valid.interfaces.Add;
import com.hngy.educationaladministration.valid.interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Specialty {

    private Long id;

    @NotBlank(groups = {Add.class, Update.class},message = "专业名不能为空")
    private String specialtyName;

    @NotNull(groups = {Add.class, Update.class},message = "请选择教研室")
    private Long idSection;

    private String remark;
    // 自建类
    private String institute_name;// 学院名字
    private String section_name; // 教研室
    private List<java.lang.Class> classes; // 班级集合

    public String getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public List<java.lang.Class> getClasses() {
        return classes;
    }

    public void setClasses(List<java.lang.Class> classes) {
        this.classes = classes;
    }

    public Long getId() {
        return id;
    }


    public enum Column {
        id("id", "id", "BIGINT", false),
        specialtyName("specialty_name", "specialtyName", "VARCHAR", false),
        idSection("id_section", "idSection", "BIGINT", false),
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

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", specialtyName='" + specialtyName + '\'' +
                ", idSection=" + idSection +
                ", remark='" + remark + '\'' +
                ", institute_name='" + institute_name + '\'' +
                ", section_name='" + section_name + '\'' +
                ", classes=" + classes +
                '}';
    }
}