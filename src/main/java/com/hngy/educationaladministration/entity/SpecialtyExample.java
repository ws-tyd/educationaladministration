package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public SpecialtyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public SpecialtyExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public SpecialtyExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        rows = null;
        offset = null;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows() {
        return this.rows;
    }

    public SpecialtyExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public SpecialtyExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public SpecialtyExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public static Criteria newAndCreateCriteria() {
        SpecialtyExample example = new SpecialtyExample();
        return example.createCriteria();
    }

    public SpecialtyExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public SpecialtyExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameIsNull() {
            addCriterion("specialty_name is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameIsNotNull() {
            addCriterion("specialty_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameEqualTo(String value) {
            addCriterion("specialty_name =", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("specialty_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotEqualTo(String value) {
            addCriterion("specialty_name <>", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("specialty_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameGreaterThan(String value) {
            addCriterion("specialty_name >", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameGreaterThanColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("specialty_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameGreaterThanOrEqualTo(String value) {
            addCriterion("specialty_name >=", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameGreaterThanOrEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("specialty_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameLessThan(String value) {
            addCriterion("specialty_name <", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameLessThanColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("specialty_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameLessThanOrEqualTo(String value) {
            addCriterion("specialty_name <=", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameLessThanOrEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("specialty_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameLike(String value) {
            addCriterion("specialty_name like", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotLike(String value) {
            addCriterion("specialty_name not like", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameIn(List<String> values) {
            addCriterion("specialty_name in", values, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotIn(List<String> values) {
            addCriterion("specialty_name not in", values, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameBetween(String value1, String value2) {
            addCriterion("specialty_name between", value1, value2, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotBetween(String value1, String value2) {
            addCriterion("specialty_name not between", value1, value2, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andIdSectionIsNull() {
            addCriterion("id_section is null");
            return (Criteria) this;
        }

        public Criteria andIdSectionIsNotNull() {
            addCriterion("id_section is not null");
            return (Criteria) this;
        }

        public Criteria andIdSectionEqualTo(Long value) {
            addCriterion("id_section =", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id_section = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionNotEqualTo(Long value) {
            addCriterion("id_section <>", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionNotEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id_section <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionGreaterThan(Long value) {
            addCriterion("id_section >", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionGreaterThanColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id_section > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionGreaterThanOrEqualTo(Long value) {
            addCriterion("id_section >=", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionGreaterThanOrEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id_section >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionLessThan(Long value) {
            addCriterion("id_section <", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionLessThanColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id_section < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionLessThanOrEqualTo(Long value) {
            addCriterion("id_section <=", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionLessThanOrEqualToColumn(Specialty.Column column) {
            addCriterion(new StringBuilder("id_section <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionIn(List<Long> values) {
            addCriterion("id_section in", values, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionNotIn(List<Long> values) {
            addCriterion("id_section not in", values, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionBetween(Long value1, Long value2) {
            addCriterion("id_section between", value1, value2, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionNotBetween(Long value1, Long value2) {
            addCriterion("id_section not between", value1, value2, "idSection");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private SpecialtyExample example;

        protected Criteria(SpecialtyExample example) {
            super();
            this.example = example;
        }

        public SpecialtyExample example() {
            return this.example;
        }

        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            } else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            Criteria add(Criteria add);
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public interface ICriteriaWhen {
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        void example(com.hngy.educationaladministration.entity.SpecialtyExample example);
    }
}