package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TeacherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public TeacherExample() {
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

    public TeacherExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public TeacherExample orderBy(String ... orderByClauses) {
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

    public TeacherExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public TeacherExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public TeacherExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public static Criteria newAndCreateCriteria() {
        TeacherExample example = new TeacherExample();
        return example.createCriteria();
    }

    public TeacherExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public TeacherExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andIdEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
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

        public Criteria andUsernameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("userName =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("userName = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("userName <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("userName <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("userName >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("userName > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("userName >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("userName <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("userName < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("userName <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("userName like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("userName not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("userName in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("userName not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("pwd is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("pwd =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("pwd = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("pwd <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("pwd <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("pwd >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("pwd > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("pwd >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("pwd >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("pwd <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("pwd < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("pwd <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("pwd <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("pwd like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("pwd not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("pwd in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("pwd not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("pwd between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("pwd not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("gender = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("gender <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("gender > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("gender >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("gender < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("gender <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
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

        public Criteria andIdSectionEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_section = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionNotEqualTo(Long value) {
            addCriterion("id_section <>", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_section <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionGreaterThan(Long value) {
            addCriterion("id_section >", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_section > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionGreaterThanOrEqualTo(Long value) {
            addCriterion("id_section >=", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_section >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionLessThan(Long value) {
            addCriterion("id_section <", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_section < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSectionLessThanOrEqualTo(Long value) {
            addCriterion("id_section <=", value, "idSection");
            return (Criteria) this;
        }

        public Criteria andIdSectionLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
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

        public Criteria andVerifyFlagIsNull() {
            addCriterion("verify_flag is null");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagIsNotNull() {
            addCriterion("verify_flag is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagEqualTo(Integer value) {
            addCriterion("verify_flag =", value, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("verify_flag = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVerifyFlagNotEqualTo(Integer value) {
            addCriterion("verify_flag <>", value, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("verify_flag <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVerifyFlagGreaterThan(Integer value) {
            addCriterion("verify_flag >", value, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("verify_flag > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVerifyFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("verify_flag >=", value, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("verify_flag >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVerifyFlagLessThan(Integer value) {
            addCriterion("verify_flag <", value, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("verify_flag < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVerifyFlagLessThanOrEqualTo(Integer value) {
            addCriterion("verify_flag <=", value, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("verify_flag <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVerifyFlagIn(List<Integer> values) {
            addCriterion("verify_flag in", values, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagNotIn(List<Integer> values) {
            addCriterion("verify_flag not in", values, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagBetween(Integer value1, Integer value2) {
            addCriterion("verify_flag between", value1, value2, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("verify_flag not between", value1, value2, "verifyFlag");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusIsNull() {
            addCriterion("politicalStatus is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusIsNotNull() {
            addCriterion("politicalStatus is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusEqualTo(String value) {
            addCriterion("politicalStatus =", value, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("politicalStatus = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusNotEqualTo(String value) {
            addCriterion("politicalStatus <>", value, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("politicalStatus <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusGreaterThan(String value) {
            addCriterion("politicalStatus >", value, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("politicalStatus > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusGreaterThanOrEqualTo(String value) {
            addCriterion("politicalStatus >=", value, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("politicalStatus >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusLessThan(String value) {
            addCriterion("politicalStatus <", value, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("politicalStatus < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusLessThanOrEqualTo(String value) {
            addCriterion("politicalStatus <=", value, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("politicalStatus <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusLike(String value) {
            addCriterion("politicalStatus like", value, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusNotLike(String value) {
            addCriterion("politicalStatus not like", value, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusIn(List<String> values) {
            addCriterion("politicalStatus in", values, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusNotIn(List<String> values) {
            addCriterion("politicalStatus not in", values, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusBetween(String value1, String value2) {
            addCriterion("politicalStatus between", value1, value2, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalstatusNotBetween(String value1, String value2) {
            addCriterion("politicalStatus not between", value1, value2, "politicalstatus");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("education = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("education <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("education > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("education >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("education < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("education <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNull() {
            addCriterion("degree is null");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNotNull() {
            addCriterion("degree is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeEqualTo(String value) {
            addCriterion("degree =", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("degree = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDegreeNotEqualTo(String value) {
            addCriterion("degree <>", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("degree <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThan(String value) {
            addCriterion("degree >", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("degree > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("degree >=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("degree >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDegreeLessThan(String value) {
            addCriterion("degree <", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("degree < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDegreeLessThanOrEqualTo(String value) {
            addCriterion("degree <=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("degree <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDegreeLike(String value) {
            addCriterion("degree like", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotLike(String value) {
            addCriterion("degree not like", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeIn(List<String> values) {
            addCriterion("degree in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotIn(List<String> values) {
            addCriterion("degree not in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeBetween(String value1, String value2) {
            addCriterion("degree between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotBetween(String value1, String value2) {
            addCriterion("degree not between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andParticipationdateIsNull() {
            addCriterion("participationDate is null");
            return (Criteria) this;
        }

        public Criteria andParticipationdateIsNotNull() {
            addCriterion("participationDate is not null");
            return (Criteria) this;
        }

        public Criteria andParticipationdateEqualTo(Date value) {
            addCriterionForJDBCDate("participationDate =", value, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("participationDate = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParticipationdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("participationDate <>", value, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("participationDate <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParticipationdateGreaterThan(Date value) {
            addCriterionForJDBCDate("participationDate >", value, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("participationDate > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParticipationdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("participationDate >=", value, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("participationDate >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParticipationdateLessThan(Date value) {
            addCriterionForJDBCDate("participationDate <", value, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("participationDate < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParticipationdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("participationDate <=", value, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("participationDate <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParticipationdateIn(List<Date> values) {
            addCriterionForJDBCDate("participationDate in", values, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("participationDate not in", values, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("participationDate between", value1, value2, "participationdate");
            return (Criteria) this;
        }

        public Criteria andParticipationdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("participationDate not between", value1, value2, "participationdate");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyIsNull() {
            addCriterion("administrativeDuty is null");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyIsNotNull() {
            addCriterion("administrativeDuty is not null");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyEqualTo(String value) {
            addCriterion("administrativeDuty =", value, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("administrativeDuty = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyNotEqualTo(String value) {
            addCriterion("administrativeDuty <>", value, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("administrativeDuty <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyGreaterThan(String value) {
            addCriterion("administrativeDuty >", value, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("administrativeDuty > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyGreaterThanOrEqualTo(String value) {
            addCriterion("administrativeDuty >=", value, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("administrativeDuty >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyLessThan(String value) {
            addCriterion("administrativeDuty <", value, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("administrativeDuty < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyLessThanOrEqualTo(String value) {
            addCriterion("administrativeDuty <=", value, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("administrativeDuty <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyLike(String value) {
            addCriterion("administrativeDuty like", value, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyNotLike(String value) {
            addCriterion("administrativeDuty not like", value, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyIn(List<String> values) {
            addCriterion("administrativeDuty in", values, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyNotIn(List<String> values) {
            addCriterion("administrativeDuty not in", values, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyBetween(String value1, String value2) {
            addCriterion("administrativeDuty between", value1, value2, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andAdministrativedutyNotBetween(String value1, String value2) {
            addCriterion("administrativeDuty not between", value1, value2, "administrativeduty");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionIsNull() {
            addCriterion("technicalPosition is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionIsNotNull() {
            addCriterion("technicalPosition is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionEqualTo(String value) {
            addCriterion("technicalPosition =", value, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPosition = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionNotEqualTo(String value) {
            addCriterion("technicalPosition <>", value, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPosition <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionGreaterThan(String value) {
            addCriterion("technicalPosition >", value, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPosition > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionGreaterThanOrEqualTo(String value) {
            addCriterion("technicalPosition >=", value, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPosition >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionLessThan(String value) {
            addCriterion("technicalPosition <", value, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPosition < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionLessThanOrEqualTo(String value) {
            addCriterion("technicalPosition <=", value, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPosition <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionLike(String value) {
            addCriterion("technicalPosition like", value, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionNotLike(String value) {
            addCriterion("technicalPosition not like", value, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionIn(List<String> values) {
            addCriterion("technicalPosition in", values, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionNotIn(List<String> values) {
            addCriterion("technicalPosition not in", values, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionBetween(String value1, String value2) {
            addCriterion("technicalPosition between", value1, value2, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositionNotBetween(String value1, String value2) {
            addCriterion("technicalPosition not between", value1, value2, "technicalposition");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedIsNull() {
            addCriterion("technicalPositionGeted is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedIsNotNull() {
            addCriterion("technicalPositionGeted is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedEqualTo(String value) {
            addCriterion("technicalPositionGeted =", value, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPositionGeted = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedNotEqualTo(String value) {
            addCriterion("technicalPositionGeted <>", value, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPositionGeted <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedGreaterThan(String value) {
            addCriterion("technicalPositionGeted >", value, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPositionGeted > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedGreaterThanOrEqualTo(String value) {
            addCriterion("technicalPositionGeted >=", value, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPositionGeted >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedLessThan(String value) {
            addCriterion("technicalPositionGeted <", value, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPositionGeted < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedLessThanOrEqualTo(String value) {
            addCriterion("technicalPositionGeted <=", value, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("technicalPositionGeted <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedLike(String value) {
            addCriterion("technicalPositionGeted like", value, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedNotLike(String value) {
            addCriterion("technicalPositionGeted not like", value, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedIn(List<String> values) {
            addCriterion("technicalPositionGeted in", values, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedNotIn(List<String> values) {
            addCriterion("technicalPositionGeted not in", values, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedBetween(String value1, String value2) {
            addCriterion("technicalPositionGeted between", value1, value2, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andTechnicalpositiongetedNotBetween(String value1, String value2) {
            addCriterion("technicalPositionGeted not between", value1, value2, "technicalpositiongeted");
            return (Criteria) this;
        }

        public Criteria andDutyIsNull() {
            addCriterion("duty is null");
            return (Criteria) this;
        }

        public Criteria andDutyIsNotNull() {
            addCriterion("duty is not null");
            return (Criteria) this;
        }

        public Criteria andDutyEqualTo(String value) {
            addCriterion("duty =", value, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("duty = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDutyNotEqualTo(String value) {
            addCriterion("duty <>", value, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("duty <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDutyGreaterThan(String value) {
            addCriterion("duty >", value, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("duty > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDutyGreaterThanOrEqualTo(String value) {
            addCriterion("duty >=", value, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("duty >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDutyLessThan(String value) {
            addCriterion("duty <", value, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("duty < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDutyLessThanOrEqualTo(String value) {
            addCriterion("duty <=", value, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("duty <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDutyLike(String value) {
            addCriterion("duty like", value, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyNotLike(String value) {
            addCriterion("duty not like", value, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyIn(List<String> values) {
            addCriterion("duty in", values, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyNotIn(List<String> values) {
            addCriterion("duty not in", values, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyBetween(String value1, String value2) {
            addCriterion("duty between", value1, value2, "duty");
            return (Criteria) this;
        }

        public Criteria andDutyNotBetween(String value1, String value2) {
            addCriterion("duty not between", value1, value2, "duty");
            return (Criteria) this;
        }

        public Criteria andIdcardnoIsNull() {
            addCriterion("IDcardNo is null");
            return (Criteria) this;
        }

        public Criteria andIdcardnoIsNotNull() {
            addCriterion("IDcardNo is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardnoEqualTo(String value) {
            addCriterion("IDcardNo =", value, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("IDcardNo = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdcardnoNotEqualTo(String value) {
            addCriterion("IDcardNo <>", value, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoNotEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("IDcardNo <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdcardnoGreaterThan(String value) {
            addCriterion("IDcardNo >", value, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoGreaterThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("IDcardNo > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdcardnoGreaterThanOrEqualTo(String value) {
            addCriterion("IDcardNo >=", value, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoGreaterThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("IDcardNo >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdcardnoLessThan(String value) {
            addCriterion("IDcardNo <", value, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoLessThanColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("IDcardNo < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdcardnoLessThanOrEqualTo(String value) {
            addCriterion("IDcardNo <=", value, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoLessThanOrEqualToColumn(TeacherWithBLOBs.Column column) {
            addCriterion(new StringBuilder("IDcardNo <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdcardnoLike(String value) {
            addCriterion("IDcardNo like", value, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoNotLike(String value) {
            addCriterion("IDcardNo not like", value, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoIn(List<String> values) {
            addCriterion("IDcardNo in", values, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoNotIn(List<String> values) {
            addCriterion("IDcardNo not in", values, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoBetween(String value1, String value2) {
            addCriterion("IDcardNo between", value1, value2, "idcardno");
            return (Criteria) this;
        }

        public Criteria andIdcardnoNotBetween(String value1, String value2) {
            addCriterion("IDcardNo not between", value1, value2, "idcardno");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private TeacherExample example;

        protected Criteria(TeacherExample example) {
            super();
            this.example = example;
        }

        public TeacherExample example() {
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
        void example(com.hngy.educationaladministration.entity.TeacherExample example);
    }
}