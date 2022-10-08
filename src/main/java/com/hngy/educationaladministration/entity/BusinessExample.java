package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.List;

public class BusinessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public BusinessExample() {
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

    public BusinessExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public BusinessExample orderBy(String ... orderByClauses) {
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

    public BusinessExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public BusinessExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public BusinessExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public static Criteria newAndCreateCriteria() {
        BusinessExample example = new BusinessExample();
        return example.createCriteria();
    }

    public BusinessExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public BusinessExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andIdEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
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

        public Criteria andIdTeacherIsNull() {
            addCriterion("id_teacher is null");
            return (Criteria) this;
        }

        public Criteria andIdTeacherIsNotNull() {
            addCriterion("id_teacher is not null");
            return (Criteria) this;
        }

        public Criteria andIdTeacherEqualTo(Long value) {
            addCriterion("id_teacher =", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherNotEqualTo(Long value) {
            addCriterion("id_teacher <>", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThan(Long value) {
            addCriterion("id_teacher >", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThanOrEqualTo(Long value) {
            addCriterion("id_teacher >=", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThan(Long value) {
            addCriterion("id_teacher <", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThanOrEqualTo(Long value) {
            addCriterion("id_teacher <=", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherIn(List<Long> values) {
            addCriterion("id_teacher in", values, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherNotIn(List<Long> values) {
            addCriterion("id_teacher not in", values, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherBetween(Long value1, Long value2) {
            addCriterion("id_teacher between", value1, value2, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherNotBetween(Long value1, Long value2) {
            addCriterion("id_teacher not between", value1, value2, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andDateofbirthIsNull() {
            addCriterion("dateOfBirth is null");
            return (Criteria) this;
        }

        public Criteria andDateofbirthIsNotNull() {
            addCriterion("dateOfBirth is not null");
            return (Criteria) this;
        }

        public Criteria andDateofbirthEqualTo(String value) {
            addCriterion("dateOfBirth =", value, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("dateOfBirth = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDateofbirthNotEqualTo(String value) {
            addCriterion("dateOfBirth <>", value, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("dateOfBirth <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDateofbirthGreaterThan(String value) {
            addCriterion("dateOfBirth >", value, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("dateOfBirth > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDateofbirthGreaterThanOrEqualTo(String value) {
            addCriterion("dateOfBirth >=", value, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("dateOfBirth >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDateofbirthLessThan(String value) {
            addCriterion("dateOfBirth <", value, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("dateOfBirth < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDateofbirthLessThanOrEqualTo(String value) {
            addCriterion("dateOfBirth <=", value, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("dateOfBirth <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDateofbirthLike(String value) {
            addCriterion("dateOfBirth like", value, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthNotLike(String value) {
            addCriterion("dateOfBirth not like", value, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthIn(List<String> values) {
            addCriterion("dateOfBirth in", values, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthNotIn(List<String> values) {
            addCriterion("dateOfBirth not in", values, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthBetween(String value1, String value2) {
            addCriterion("dateOfBirth between", value1, value2, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andDateofbirthNotBetween(String value1, String value2) {
            addCriterion("dateOfBirth not between", value1, value2, "dateofbirth");
            return (Criteria) this;
        }

        public Criteria andTremIsNull() {
            addCriterion("trem is null");
            return (Criteria) this;
        }

        public Criteria andTremIsNotNull() {
            addCriterion("trem is not null");
            return (Criteria) this;
        }

        public Criteria andTremEqualTo(String value) {
            addCriterion("trem =", value, "trem");
            return (Criteria) this;
        }

        public Criteria andTremEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("trem = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTremNotEqualTo(String value) {
            addCriterion("trem <>", value, "trem");
            return (Criteria) this;
        }

        public Criteria andTremNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("trem <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTremGreaterThan(String value) {
            addCriterion("trem >", value, "trem");
            return (Criteria) this;
        }

        public Criteria andTremGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("trem > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTremGreaterThanOrEqualTo(String value) {
            addCriterion("trem >=", value, "trem");
            return (Criteria) this;
        }

        public Criteria andTremGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("trem >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTremLessThan(String value) {
            addCriterion("trem <", value, "trem");
            return (Criteria) this;
        }

        public Criteria andTremLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("trem < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTremLessThanOrEqualTo(String value) {
            addCriterion("trem <=", value, "trem");
            return (Criteria) this;
        }

        public Criteria andTremLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("trem <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTremLike(String value) {
            addCriterion("trem like", value, "trem");
            return (Criteria) this;
        }

        public Criteria andTremNotLike(String value) {
            addCriterion("trem not like", value, "trem");
            return (Criteria) this;
        }

        public Criteria andTremIn(List<String> values) {
            addCriterion("trem in", values, "trem");
            return (Criteria) this;
        }

        public Criteria andTremNotIn(List<String> values) {
            addCriterion("trem not in", values, "trem");
            return (Criteria) this;
        }

        public Criteria andTremBetween(String value1, String value2) {
            addCriterion("trem between", value1, value2, "trem");
            return (Criteria) this;
        }

        public Criteria andTremNotBetween(String value1, String value2) {
            addCriterion("trem not between", value1, value2, "trem");
            return (Criteria) this;
        }

        public Criteria andTheoryclassIsNull() {
            addCriterion("theoryClass is null");
            return (Criteria) this;
        }

        public Criteria andTheoryclassIsNotNull() {
            addCriterion("theoryClass is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryclassEqualTo(String value) {
            addCriterion("theoryClass =", value, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("theoryClass = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTheoryclassNotEqualTo(String value) {
            addCriterion("theoryClass <>", value, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("theoryClass <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTheoryclassGreaterThan(String value) {
            addCriterion("theoryClass >", value, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("theoryClass > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTheoryclassGreaterThanOrEqualTo(String value) {
            addCriterion("theoryClass >=", value, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("theoryClass >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTheoryclassLessThan(String value) {
            addCriterion("theoryClass <", value, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("theoryClass < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTheoryclassLessThanOrEqualTo(String value) {
            addCriterion("theoryClass <=", value, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("theoryClass <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTheoryclassLike(String value) {
            addCriterion("theoryClass like", value, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassNotLike(String value) {
            addCriterion("theoryClass not like", value, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassIn(List<String> values) {
            addCriterion("theoryClass in", values, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassNotIn(List<String> values) {
            addCriterion("theoryClass not in", values, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassBetween(String value1, String value2) {
            addCriterion("theoryClass between", value1, value2, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andTheoryclassNotBetween(String value1, String value2) {
            addCriterion("theoryClass not between", value1, value2, "theoryclass");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentIsNull() {
            addCriterion("guidingExperiment is null");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentIsNotNull() {
            addCriterion("guidingExperiment is not null");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentEqualTo(String value) {
            addCriterion("guidingExperiment =", value, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingExperiment = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentNotEqualTo(String value) {
            addCriterion("guidingExperiment <>", value, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingExperiment <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentGreaterThan(String value) {
            addCriterion("guidingExperiment >", value, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingExperiment > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentGreaterThanOrEqualTo(String value) {
            addCriterion("guidingExperiment >=", value, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingExperiment >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentLessThan(String value) {
            addCriterion("guidingExperiment <", value, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingExperiment < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentLessThanOrEqualTo(String value) {
            addCriterion("guidingExperiment <=", value, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingExperiment <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentLike(String value) {
            addCriterion("guidingExperiment like", value, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentNotLike(String value) {
            addCriterion("guidingExperiment not like", value, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentIn(List<String> values) {
            addCriterion("guidingExperiment in", values, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentNotIn(List<String> values) {
            addCriterion("guidingExperiment not in", values, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentBetween(String value1, String value2) {
            addCriterion("guidingExperiment between", value1, value2, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingexperimentNotBetween(String value1, String value2) {
            addCriterion("guidingExperiment not between", value1, value2, "guidingexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignIsNull() {
            addCriterion("guidingDesign is null");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignIsNotNull() {
            addCriterion("guidingDesign is not null");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignEqualTo(String value) {
            addCriterion("guidingDesign =", value, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingDesign = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingdesignNotEqualTo(String value) {
            addCriterion("guidingDesign <>", value, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingDesign <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingdesignGreaterThan(String value) {
            addCriterion("guidingDesign >", value, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingDesign > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingdesignGreaterThanOrEqualTo(String value) {
            addCriterion("guidingDesign >=", value, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingDesign >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingdesignLessThan(String value) {
            addCriterion("guidingDesign <", value, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingDesign < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingdesignLessThanOrEqualTo(String value) {
            addCriterion("guidingDesign <=", value, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingDesign <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidingdesignLike(String value) {
            addCriterion("guidingDesign like", value, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignNotLike(String value) {
            addCriterion("guidingDesign not like", value, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignIn(List<String> values) {
            addCriterion("guidingDesign in", values, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignNotIn(List<String> values) {
            addCriterion("guidingDesign not in", values, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignBetween(String value1, String value2) {
            addCriterion("guidingDesign between", value1, value2, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidingdesignNotBetween(String value1, String value2) {
            addCriterion("guidingDesign not between", value1, value2, "guidingdesign");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipIsNull() {
            addCriterion("guidingInternship is null");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipIsNotNull() {
            addCriterion("guidingInternship is not null");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipEqualTo(String value) {
            addCriterion("guidingInternship =", value, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingInternship = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipNotEqualTo(String value) {
            addCriterion("guidingInternship <>", value, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingInternship <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipGreaterThan(String value) {
            addCriterion("guidingInternship >", value, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingInternship > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipGreaterThanOrEqualTo(String value) {
            addCriterion("guidingInternship >=", value, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingInternship >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipLessThan(String value) {
            addCriterion("guidingInternship <", value, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingInternship < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipLessThanOrEqualTo(String value) {
            addCriterion("guidingInternship <=", value, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guidingInternship <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipLike(String value) {
            addCriterion("guidingInternship like", value, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipNotLike(String value) {
            addCriterion("guidingInternship not like", value, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipIn(List<String> values) {
            addCriterion("guidingInternship in", values, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipNotIn(List<String> values) {
            addCriterion("guidingInternship not in", values, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipBetween(String value1, String value2) {
            addCriterion("guidingInternship between", value1, value2, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidinginternshipNotBetween(String value1, String value2) {
            addCriterion("guidingInternship not between", value1, value2, "guidinginternship");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentIsNull() {
            addCriterion("guideComprehensiveExperiment is null");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentIsNotNull() {
            addCriterion("guideComprehensiveExperiment is not null");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentEqualTo(String value) {
            addCriterion("guideComprehensiveExperiment =", value, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guideComprehensiveExperiment = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentNotEqualTo(String value) {
            addCriterion("guideComprehensiveExperiment <>", value, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guideComprehensiveExperiment <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentGreaterThan(String value) {
            addCriterion("guideComprehensiveExperiment >", value, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guideComprehensiveExperiment > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentGreaterThanOrEqualTo(String value) {
            addCriterion("guideComprehensiveExperiment >=", value, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guideComprehensiveExperiment >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentLessThan(String value) {
            addCriterion("guideComprehensiveExperiment <", value, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guideComprehensiveExperiment < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentLessThanOrEqualTo(String value) {
            addCriterion("guideComprehensiveExperiment <=", value, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("guideComprehensiveExperiment <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentLike(String value) {
            addCriterion("guideComprehensiveExperiment like", value, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentNotLike(String value) {
            addCriterion("guideComprehensiveExperiment not like", value, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentIn(List<String> values) {
            addCriterion("guideComprehensiveExperiment in", values, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentNotIn(List<String> values) {
            addCriterion("guideComprehensiveExperiment not in", values, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentBetween(String value1, String value2) {
            addCriterion("guideComprehensiveExperiment between", value1, value2, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andGuidecomprehensiveexperimentNotBetween(String value1, String value2) {
            addCriterion("guideComprehensiveExperiment not between", value1, value2, "guidecomprehensiveexperiment");
            return (Criteria) this;
        }

        public Criteria andTotalclassIsNull() {
            addCriterion("totalClass is null");
            return (Criteria) this;
        }

        public Criteria andTotalclassIsNotNull() {
            addCriterion("totalClass is not null");
            return (Criteria) this;
        }

        public Criteria andTotalclassEqualTo(String value) {
            addCriterion("totalClass =", value, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalClass = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalclassNotEqualTo(String value) {
            addCriterion("totalClass <>", value, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalClass <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalclassGreaterThan(String value) {
            addCriterion("totalClass >", value, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalClass > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalclassGreaterThanOrEqualTo(String value) {
            addCriterion("totalClass >=", value, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalClass >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalclassLessThan(String value) {
            addCriterion("totalClass <", value, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalClass < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalclassLessThanOrEqualTo(String value) {
            addCriterion("totalClass <=", value, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalClass <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalclassLike(String value) {
            addCriterion("totalClass like", value, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassNotLike(String value) {
            addCriterion("totalClass not like", value, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassIn(List<String> values) {
            addCriterion("totalClass in", values, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassNotIn(List<String> values) {
            addCriterion("totalClass not in", values, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassBetween(String value1, String value2) {
            addCriterion("totalClass between", value1, value2, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalclassNotBetween(String value1, String value2) {
            addCriterion("totalClass not between", value1, value2, "totalclass");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadIsNull() {
            addCriterion("totalWorkload is null");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadIsNotNull() {
            addCriterion("totalWorkload is not null");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadEqualTo(String value) {
            addCriterion("totalWorkload =", value, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalWorkload = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalworkloadNotEqualTo(String value) {
            addCriterion("totalWorkload <>", value, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalWorkload <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalworkloadGreaterThan(String value) {
            addCriterion("totalWorkload >", value, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalWorkload > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalworkloadGreaterThanOrEqualTo(String value) {
            addCriterion("totalWorkload >=", value, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalWorkload >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalworkloadLessThan(String value) {
            addCriterion("totalWorkload <", value, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalWorkload < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalworkloadLessThanOrEqualTo(String value) {
            addCriterion("totalWorkload <=", value, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalWorkload <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalworkloadLike(String value) {
            addCriterion("totalWorkload like", value, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadNotLike(String value) {
            addCriterion("totalWorkload not like", value, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadIn(List<String> values) {
            addCriterion("totalWorkload in", values, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadNotIn(List<String> values) {
            addCriterion("totalWorkload not in", values, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadBetween(String value1, String value2) {
            addCriterion("totalWorkload between", value1, value2, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andTotalworkloadNotBetween(String value1, String value2) {
            addCriterion("totalWorkload not between", value1, value2, "totalworkload");
            return (Criteria) this;
        }

        public Criteria andChiefeditorIsNull() {
            addCriterion("chiefEditor is null");
            return (Criteria) this;
        }

        public Criteria andChiefeditorIsNotNull() {
            addCriterion("chiefEditor is not null");
            return (Criteria) this;
        }

        public Criteria andChiefeditorEqualTo(String value) {
            addCriterion("chiefEditor =", value, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("chiefEditor = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andChiefeditorNotEqualTo(String value) {
            addCriterion("chiefEditor <>", value, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("chiefEditor <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andChiefeditorGreaterThan(String value) {
            addCriterion("chiefEditor >", value, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("chiefEditor > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andChiefeditorGreaterThanOrEqualTo(String value) {
            addCriterion("chiefEditor >=", value, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("chiefEditor >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andChiefeditorLessThan(String value) {
            addCriterion("chiefEditor <", value, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("chiefEditor < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andChiefeditorLessThanOrEqualTo(String value) {
            addCriterion("chiefEditor <=", value, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("chiefEditor <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andChiefeditorLike(String value) {
            addCriterion("chiefEditor like", value, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorNotLike(String value) {
            addCriterion("chiefEditor not like", value, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorIn(List<String> values) {
            addCriterion("chiefEditor in", values, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorNotIn(List<String> values) {
            addCriterion("chiefEditor not in", values, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorBetween(String value1, String value2) {
            addCriterion("chiefEditor between", value1, value2, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andChiefeditorNotBetween(String value1, String value2) {
            addCriterion("chiefEditor not between", value1, value2, "chiefeditor");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedIsNull() {
            addCriterion("jointlyEdited is null");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedIsNotNull() {
            addCriterion("jointlyEdited is not null");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedEqualTo(String value) {
            addCriterion("jointlyEdited =", value, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("jointlyEdited = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJointlyeditedNotEqualTo(String value) {
            addCriterion("jointlyEdited <>", value, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("jointlyEdited <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJointlyeditedGreaterThan(String value) {
            addCriterion("jointlyEdited >", value, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("jointlyEdited > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJointlyeditedGreaterThanOrEqualTo(String value) {
            addCriterion("jointlyEdited >=", value, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("jointlyEdited >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJointlyeditedLessThan(String value) {
            addCriterion("jointlyEdited <", value, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("jointlyEdited < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJointlyeditedLessThanOrEqualTo(String value) {
            addCriterion("jointlyEdited <=", value, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("jointlyEdited <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJointlyeditedLike(String value) {
            addCriterion("jointlyEdited like", value, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedNotLike(String value) {
            addCriterion("jointlyEdited not like", value, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedIn(List<String> values) {
            addCriterion("jointlyEdited in", values, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedNotIn(List<String> values) {
            addCriterion("jointlyEdited not in", values, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedBetween(String value1, String value2) {
            addCriterion("jointlyEdited between", value1, value2, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andJointlyeditedNotBetween(String value1, String value2) {
            addCriterion("jointlyEdited not between", value1, value2, "jointlyedited");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookIsNull() {
            addCriterion("planningTextbook is null");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookIsNotNull() {
            addCriterion("planningTextbook is not null");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookEqualTo(String value) {
            addCriterion("planningTextbook =", value, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("planningTextbook = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookNotEqualTo(String value) {
            addCriterion("planningTextbook <>", value, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("planningTextbook <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookGreaterThan(String value) {
            addCriterion("planningTextbook >", value, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("planningTextbook > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookGreaterThanOrEqualTo(String value) {
            addCriterion("planningTextbook >=", value, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("planningTextbook >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookLessThan(String value) {
            addCriterion("planningTextbook <", value, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("planningTextbook < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookLessThanOrEqualTo(String value) {
            addCriterion("planningTextbook <=", value, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("planningTextbook <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookLike(String value) {
            addCriterion("planningTextbook like", value, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookNotLike(String value) {
            addCriterion("planningTextbook not like", value, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookIn(List<String> values) {
            addCriterion("planningTextbook in", values, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookNotIn(List<String> values) {
            addCriterion("planningTextbook not in", values, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookBetween(String value1, String value2) {
            addCriterion("planningTextbook between", value1, value2, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andPlanningtextbookNotBetween(String value1, String value2) {
            addCriterion("planningTextbook not between", value1, value2, "planningtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookIsNull() {
            addCriterion("schoolBasedTextbook is null");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookIsNotNull() {
            addCriterion("schoolBasedTextbook is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookEqualTo(String value) {
            addCriterion("schoolBasedTextbook =", value, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolBasedTextbook = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookNotEqualTo(String value) {
            addCriterion("schoolBasedTextbook <>", value, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolBasedTextbook <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookGreaterThan(String value) {
            addCriterion("schoolBasedTextbook >", value, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolBasedTextbook > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookGreaterThanOrEqualTo(String value) {
            addCriterion("schoolBasedTextbook >=", value, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolBasedTextbook >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookLessThan(String value) {
            addCriterion("schoolBasedTextbook <", value, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolBasedTextbook < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookLessThanOrEqualTo(String value) {
            addCriterion("schoolBasedTextbook <=", value, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolBasedTextbook <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookLike(String value) {
            addCriterion("schoolBasedTextbook like", value, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookNotLike(String value) {
            addCriterion("schoolBasedTextbook not like", value, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookIn(List<String> values) {
            addCriterion("schoolBasedTextbook in", values, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookNotIn(List<String> values) {
            addCriterion("schoolBasedTextbook not in", values, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookBetween(String value1, String value2) {
            addCriterion("schoolBasedTextbook between", value1, value2, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andSchoolbasedtextbookNotBetween(String value1, String value2) {
            addCriterion("schoolBasedTextbook not between", value1, value2, "schoolbasedtextbook");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("level = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("level <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("level > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("level >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("level < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("level <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andRankingIsNull() {
            addCriterion("ranking is null");
            return (Criteria) this;
        }

        public Criteria andRankingIsNotNull() {
            addCriterion("ranking is not null");
            return (Criteria) this;
        }

        public Criteria andRankingEqualTo(String value) {
            addCriterion("ranking =", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("ranking = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRankingNotEqualTo(String value) {
            addCriterion("ranking <>", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("ranking <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThan(String value) {
            addCriterion("ranking >", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("ranking > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThanOrEqualTo(String value) {
            addCriterion("ranking >=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("ranking >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRankingLessThan(String value) {
            addCriterion("ranking <", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("ranking < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRankingLessThanOrEqualTo(String value) {
            addCriterion("ranking <=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("ranking <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRankingLike(String value) {
            addCriterion("ranking like", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotLike(String value) {
            addCriterion("ranking not like", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingIn(List<String> values) {
            addCriterion("ranking in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotIn(List<String> values) {
            addCriterion("ranking not in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingBetween(String value1, String value2) {
            addCriterion("ranking between", value1, value2, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotBetween(String value1, String value2) {
            addCriterion("ranking not between", value1, value2, "ranking");
            return (Criteria) this;
        }

        public Criteria andHostsubjectIsNull() {
            addCriterion("hostSubject is null");
            return (Criteria) this;
        }

        public Criteria andHostsubjectIsNotNull() {
            addCriterion("hostSubject is not null");
            return (Criteria) this;
        }

        public Criteria andHostsubjectEqualTo(String value) {
            addCriterion("hostSubject =", value, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("hostSubject = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostsubjectNotEqualTo(String value) {
            addCriterion("hostSubject <>", value, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("hostSubject <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostsubjectGreaterThan(String value) {
            addCriterion("hostSubject >", value, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("hostSubject > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostsubjectGreaterThanOrEqualTo(String value) {
            addCriterion("hostSubject >=", value, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("hostSubject >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostsubjectLessThan(String value) {
            addCriterion("hostSubject <", value, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("hostSubject < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostsubjectLessThanOrEqualTo(String value) {
            addCriterion("hostSubject <=", value, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("hostSubject <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostsubjectLike(String value) {
            addCriterion("hostSubject like", value, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectNotLike(String value) {
            addCriterion("hostSubject not like", value, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectIn(List<String> values) {
            addCriterion("hostSubject in", values, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectNotIn(List<String> values) {
            addCriterion("hostSubject not in", values, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectBetween(String value1, String value2) {
            addCriterion("hostSubject between", value1, value2, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andHostsubjectNotBetween(String value1, String value2) {
            addCriterion("hostSubject not between", value1, value2, "hostsubject");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("category = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("category <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("category > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("category >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("category < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("category <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andMyroleIsNull() {
            addCriterion("myRole is null");
            return (Criteria) this;
        }

        public Criteria andMyroleIsNotNull() {
            addCriterion("myRole is not null");
            return (Criteria) this;
        }

        public Criteria andMyroleEqualTo(String value) {
            addCriterion("myRole =", value, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("myRole = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMyroleNotEqualTo(String value) {
            addCriterion("myRole <>", value, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("myRole <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMyroleGreaterThan(String value) {
            addCriterion("myRole >", value, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("myRole > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMyroleGreaterThanOrEqualTo(String value) {
            addCriterion("myRole >=", value, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("myRole >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMyroleLessThan(String value) {
            addCriterion("myRole <", value, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("myRole < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMyroleLessThanOrEqualTo(String value) {
            addCriterion("myRole <=", value, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("myRole <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMyroleLike(String value) {
            addCriterion("myRole like", value, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleNotLike(String value) {
            addCriterion("myRole not like", value, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleIn(List<String> values) {
            addCriterion("myRole in", values, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleNotIn(List<String> values) {
            addCriterion("myRole not in", values, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleBetween(String value1, String value2) {
            addCriterion("myRole between", value1, value2, "myrole");
            return (Criteria) this;
        }

        public Criteria andMyroleNotBetween(String value1, String value2) {
            addCriterion("myRole not between", value1, value2, "myrole");
            return (Criteria) this;
        }

        public Criteria andSickIsNull() {
            addCriterion("sick is null");
            return (Criteria) this;
        }

        public Criteria andSickIsNotNull() {
            addCriterion("sick is not null");
            return (Criteria) this;
        }

        public Criteria andSickEqualTo(String value) {
            addCriterion("sick =", value, "sick");
            return (Criteria) this;
        }

        public Criteria andSickEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sick = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSickNotEqualTo(String value) {
            addCriterion("sick <>", value, "sick");
            return (Criteria) this;
        }

        public Criteria andSickNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sick <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSickGreaterThan(String value) {
            addCriterion("sick >", value, "sick");
            return (Criteria) this;
        }

        public Criteria andSickGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sick > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSickGreaterThanOrEqualTo(String value) {
            addCriterion("sick >=", value, "sick");
            return (Criteria) this;
        }

        public Criteria andSickGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sick >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSickLessThan(String value) {
            addCriterion("sick <", value, "sick");
            return (Criteria) this;
        }

        public Criteria andSickLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sick < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSickLessThanOrEqualTo(String value) {
            addCriterion("sick <=", value, "sick");
            return (Criteria) this;
        }

        public Criteria andSickLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sick <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSickLike(String value) {
            addCriterion("sick like", value, "sick");
            return (Criteria) this;
        }

        public Criteria andSickNotLike(String value) {
            addCriterion("sick not like", value, "sick");
            return (Criteria) this;
        }

        public Criteria andSickIn(List<String> values) {
            addCriterion("sick in", values, "sick");
            return (Criteria) this;
        }

        public Criteria andSickNotIn(List<String> values) {
            addCriterion("sick not in", values, "sick");
            return (Criteria) this;
        }

        public Criteria andSickBetween(String value1, String value2) {
            addCriterion("sick between", value1, value2, "sick");
            return (Criteria) this;
        }

        public Criteria andSickNotBetween(String value1, String value2) {
            addCriterion("sick not between", value1, value2, "sick");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveIsNull() {
            addCriterion("compassionateLeave is null");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveIsNotNull() {
            addCriterion("compassionateLeave is not null");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveEqualTo(String value) {
            addCriterion("compassionateLeave =", value, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("compassionateLeave = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveNotEqualTo(String value) {
            addCriterion("compassionateLeave <>", value, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("compassionateLeave <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveGreaterThan(String value) {
            addCriterion("compassionateLeave >", value, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("compassionateLeave > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveGreaterThanOrEqualTo(String value) {
            addCriterion("compassionateLeave >=", value, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("compassionateLeave >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveLessThan(String value) {
            addCriterion("compassionateLeave <", value, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("compassionateLeave < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveLessThanOrEqualTo(String value) {
            addCriterion("compassionateLeave <=", value, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("compassionateLeave <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveLike(String value) {
            addCriterion("compassionateLeave like", value, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveNotLike(String value) {
            addCriterion("compassionateLeave not like", value, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveIn(List<String> values) {
            addCriterion("compassionateLeave in", values, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveNotIn(List<String> values) {
            addCriterion("compassionateLeave not in", values, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveBetween(String value1, String value2) {
            addCriterion("compassionateLeave between", value1, value2, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andCompassionateleaveNotBetween(String value1, String value2) {
            addCriterion("compassionateLeave not between", value1, value2, "compassionateleave");
            return (Criteria) this;
        }

        public Criteria andBelateIsNull() {
            addCriterion("beLate is null");
            return (Criteria) this;
        }

        public Criteria andBelateIsNotNull() {
            addCriterion("beLate is not null");
            return (Criteria) this;
        }

        public Criteria andBelateEqualTo(Integer value) {
            addCriterion("beLate =", value, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("beLate = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBelateNotEqualTo(Integer value) {
            addCriterion("beLate <>", value, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("beLate <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBelateGreaterThan(Integer value) {
            addCriterion("beLate >", value, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("beLate > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBelateGreaterThanOrEqualTo(Integer value) {
            addCriterion("beLate >=", value, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("beLate >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBelateLessThan(Integer value) {
            addCriterion("beLate <", value, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("beLate < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBelateLessThanOrEqualTo(Integer value) {
            addCriterion("beLate <=", value, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("beLate <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBelateIn(List<Integer> values) {
            addCriterion("beLate in", values, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateNotIn(List<Integer> values) {
            addCriterion("beLate not in", values, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateBetween(Integer value1, Integer value2) {
            addCriterion("beLate between", value1, value2, "belate");
            return (Criteria) this;
        }

        public Criteria andBelateNotBetween(Integer value1, Integer value2) {
            addCriterion("beLate not between", value1, value2, "belate");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyIsNull() {
            addCriterion("leaveEarly is null");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyIsNotNull() {
            addCriterion("leaveEarly is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyEqualTo(Integer value) {
            addCriterion("leaveEarly =", value, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("leaveEarly = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLeaveearlyNotEqualTo(Integer value) {
            addCriterion("leaveEarly <>", value, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("leaveEarly <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLeaveearlyGreaterThan(Integer value) {
            addCriterion("leaveEarly >", value, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("leaveEarly > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLeaveearlyGreaterThanOrEqualTo(Integer value) {
            addCriterion("leaveEarly >=", value, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("leaveEarly >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLeaveearlyLessThan(Integer value) {
            addCriterion("leaveEarly <", value, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("leaveEarly < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLeaveearlyLessThanOrEqualTo(Integer value) {
            addCriterion("leaveEarly <=", value, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("leaveEarly <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLeaveearlyIn(List<Integer> values) {
            addCriterion("leaveEarly in", values, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyNotIn(List<Integer> values) {
            addCriterion("leaveEarly not in", values, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyBetween(Integer value1, Integer value2) {
            addCriterion("leaveEarly between", value1, value2, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andLeaveearlyNotBetween(Integer value1, Integer value2) {
            addCriterion("leaveEarly not between", value1, value2, "leaveearly");
            return (Criteria) this;
        }

        public Criteria andTuneclassIsNull() {
            addCriterion("tuneClass is null");
            return (Criteria) this;
        }

        public Criteria andTuneclassIsNotNull() {
            addCriterion("tuneClass is not null");
            return (Criteria) this;
        }

        public Criteria andTuneclassEqualTo(Integer value) {
            addCriterion("tuneClass =", value, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("tuneClass = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTuneclassNotEqualTo(Integer value) {
            addCriterion("tuneClass <>", value, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("tuneClass <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTuneclassGreaterThan(Integer value) {
            addCriterion("tuneClass >", value, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("tuneClass > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTuneclassGreaterThanOrEqualTo(Integer value) {
            addCriterion("tuneClass >=", value, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("tuneClass >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTuneclassLessThan(Integer value) {
            addCriterion("tuneClass <", value, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("tuneClass < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTuneclassLessThanOrEqualTo(Integer value) {
            addCriterion("tuneClass <=", value, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("tuneClass <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTuneclassIn(List<Integer> values) {
            addCriterion("tuneClass in", values, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassNotIn(List<Integer> values) {
            addCriterion("tuneClass not in", values, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassBetween(Integer value1, Integer value2) {
            addCriterion("tuneClass between", value1, value2, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andTuneclassNotBetween(Integer value1, Integer value2) {
            addCriterion("tuneClass not between", value1, value2, "tuneclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassIsNull() {
            addCriterion("missingClass is null");
            return (Criteria) this;
        }

        public Criteria andMissingclassIsNotNull() {
            addCriterion("missingClass is not null");
            return (Criteria) this;
        }

        public Criteria andMissingclassEqualTo(Integer value) {
            addCriterion("missingClass =", value, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("missingClass = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMissingclassNotEqualTo(Integer value) {
            addCriterion("missingClass <>", value, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("missingClass <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMissingclassGreaterThan(Integer value) {
            addCriterion("missingClass >", value, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("missingClass > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMissingclassGreaterThanOrEqualTo(Integer value) {
            addCriterion("missingClass >=", value, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("missingClass >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMissingclassLessThan(Integer value) {
            addCriterion("missingClass <", value, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("missingClass < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMissingclassLessThanOrEqualTo(Integer value) {
            addCriterion("missingClass <=", value, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("missingClass <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMissingclassIn(List<Integer> values) {
            addCriterion("missingClass in", values, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassNotIn(List<Integer> values) {
            addCriterion("missingClass not in", values, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassBetween(Integer value1, Integer value2) {
            addCriterion("missingClass between", value1, value2, "missingclass");
            return (Criteria) this;
        }

        public Criteria andMissingclassNotBetween(Integer value1, Integer value2) {
            addCriterion("missingClass not between", value1, value2, "missingclass");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyIsNull() {
            addCriterion("disciplinaryPenalty is null");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyIsNotNull() {
            addCriterion("disciplinaryPenalty is not null");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyEqualTo(Integer value) {
            addCriterion("disciplinaryPenalty =", value, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("disciplinaryPenalty = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyNotEqualTo(Integer value) {
            addCriterion("disciplinaryPenalty <>", value, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("disciplinaryPenalty <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyGreaterThan(Integer value) {
            addCriterion("disciplinaryPenalty >", value, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("disciplinaryPenalty > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyGreaterThanOrEqualTo(Integer value) {
            addCriterion("disciplinaryPenalty >=", value, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("disciplinaryPenalty >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyLessThan(Integer value) {
            addCriterion("disciplinaryPenalty <", value, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("disciplinaryPenalty < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyLessThanOrEqualTo(Integer value) {
            addCriterion("disciplinaryPenalty <=", value, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("disciplinaryPenalty <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyIn(List<Integer> values) {
            addCriterion("disciplinaryPenalty in", values, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyNotIn(List<Integer> values) {
            addCriterion("disciplinaryPenalty not in", values, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyBetween(Integer value1, Integer value2) {
            addCriterion("disciplinaryPenalty between", value1, value2, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andDisciplinarypenaltyNotBetween(Integer value1, Integer value2) {
            addCriterion("disciplinaryPenalty not between", value1, value2, "disciplinarypenalty");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionIsNull() {
            addCriterion("teachingOpinion is null");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionIsNotNull() {
            addCriterion("teachingOpinion is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionEqualTo(String value) {
            addCriterion("teachingOpinion =", value, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("teachingOpinion = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTeachingopinionNotEqualTo(String value) {
            addCriterion("teachingOpinion <>", value, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("teachingOpinion <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTeachingopinionGreaterThan(String value) {
            addCriterion("teachingOpinion >", value, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("teachingOpinion > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTeachingopinionGreaterThanOrEqualTo(String value) {
            addCriterion("teachingOpinion >=", value, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("teachingOpinion >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTeachingopinionLessThan(String value) {
            addCriterion("teachingOpinion <", value, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("teachingOpinion < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTeachingopinionLessThanOrEqualTo(String value) {
            addCriterion("teachingOpinion <=", value, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("teachingOpinion <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTeachingopinionLike(String value) {
            addCriterion("teachingOpinion like", value, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionNotLike(String value) {
            addCriterion("teachingOpinion not like", value, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionIn(List<String> values) {
            addCriterion("teachingOpinion in", values, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionNotIn(List<String> values) {
            addCriterion("teachingOpinion not in", values, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionBetween(String value1, String value2) {
            addCriterion("teachingOpinion between", value1, value2, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andTeachingopinionNotBetween(String value1, String value2) {
            addCriterion("teachingOpinion not between", value1, value2, "teachingopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionIsNull() {
            addCriterion("departmentOpinion is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionIsNotNull() {
            addCriterion("departmentOpinion is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionEqualTo(String value) {
            addCriterion("departmentOpinion =", value, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("departmentOpinion = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionNotEqualTo(String value) {
            addCriterion("departmentOpinion <>", value, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("departmentOpinion <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionGreaterThan(String value) {
            addCriterion("departmentOpinion >", value, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("departmentOpinion > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionGreaterThanOrEqualTo(String value) {
            addCriterion("departmentOpinion >=", value, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("departmentOpinion >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionLessThan(String value) {
            addCriterion("departmentOpinion <", value, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("departmentOpinion < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionLessThanOrEqualTo(String value) {
            addCriterion("departmentOpinion <=", value, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("departmentOpinion <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionLike(String value) {
            addCriterion("departmentOpinion like", value, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionNotLike(String value) {
            addCriterion("departmentOpinion not like", value, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionIn(List<String> values) {
            addCriterion("departmentOpinion in", values, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionNotIn(List<String> values) {
            addCriterion("departmentOpinion not in", values, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionBetween(String value1, String value2) {
            addCriterion("departmentOpinion between", value1, value2, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andDepartmentopinionNotBetween(String value1, String value2) {
            addCriterion("departmentOpinion not between", value1, value2, "departmentopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionIsNull() {
            addCriterion("schoolOpinion is null");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionIsNotNull() {
            addCriterion("schoolOpinion is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionEqualTo(String value) {
            addCriterion("schoolOpinion =", value, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolOpinion = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolopinionNotEqualTo(String value) {
            addCriterion("schoolOpinion <>", value, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionNotEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolOpinion <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolopinionGreaterThan(String value) {
            addCriterion("schoolOpinion >", value, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionGreaterThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolOpinion > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolopinionGreaterThanOrEqualTo(String value) {
            addCriterion("schoolOpinion >=", value, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionGreaterThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolOpinion >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolopinionLessThan(String value) {
            addCriterion("schoolOpinion <", value, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionLessThanColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolOpinion < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolopinionLessThanOrEqualTo(String value) {
            addCriterion("schoolOpinion <=", value, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionLessThanOrEqualToColumn(BusinessWithBLOBs.Column column) {
            addCriterion(new StringBuilder("schoolOpinion <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSchoolopinionLike(String value) {
            addCriterion("schoolOpinion like", value, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionNotLike(String value) {
            addCriterion("schoolOpinion not like", value, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionIn(List<String> values) {
            addCriterion("schoolOpinion in", values, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionNotIn(List<String> values) {
            addCriterion("schoolOpinion not in", values, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionBetween(String value1, String value2) {
            addCriterion("schoolOpinion between", value1, value2, "schoolopinion");
            return (Criteria) this;
        }

        public Criteria andSchoolopinionNotBetween(String value1, String value2) {
            addCriterion("schoolOpinion not between", value1, value2, "schoolopinion");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private BusinessExample example;

        protected Criteria(BusinessExample example) {
            super();
            this.example = example;
        }

        public BusinessExample example() {
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
        void example(com.hngy.educationaladministration.entity.BusinessExample example);
    }
}