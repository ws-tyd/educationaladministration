package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.List;

public class WorkloadExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public WorkloadExample() {
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

    public WorkloadExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public WorkloadExample orderBy(String ... orderByClauses) {
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

    public WorkloadExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public WorkloadExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public WorkloadExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public static Criteria newAndCreateCriteria() {
        WorkloadExample example = new WorkloadExample();
        return example.createCriteria();
    }

    public WorkloadExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public WorkloadExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andIdEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
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

        public Criteria andIdTeacherEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherNotEqualTo(Long value) {
            addCriterion("id_teacher <>", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherNotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThan(Long value) {
            addCriterion("id_teacher >", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThanOrEqualTo(Long value) {
            addCriterion("id_teacher >=", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThan(Long value) {
            addCriterion("id_teacher <", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("id_teacher < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThanOrEqualTo(Long value) {
            addCriterion("id_teacher <=", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
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

        public Criteria andYearEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("year < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
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

        public Criteria andTermIsNull() {
            addCriterion("term is null");
            return (Criteria) this;
        }

        public Criteria andTermIsNotNull() {
            addCriterion("term is not null");
            return (Criteria) this;
        }

        public Criteria andTermEqualTo(String value) {
            addCriterion("term =", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("term = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTermNotEqualTo(String value) {
            addCriterion("term <>", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("term <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTermGreaterThan(String value) {
            addCriterion("term >", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermGreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("term > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTermGreaterThanOrEqualTo(String value) {
            addCriterion("term >=", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermGreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("term >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTermLessThan(String value) {
            addCriterion("term <", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("term < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTermLessThanOrEqualTo(String value) {
            addCriterion("term <=", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("term <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTermLike(String value) {
            addCriterion("term like", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotLike(String value) {
            addCriterion("term not like", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermIn(List<String> values) {
            addCriterion("term in", values, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotIn(List<String> values) {
            addCriterion("term not in", values, "term");
            return (Criteria) this;
        }

        public Criteria andTermBetween(String value1, String value2) {
            addCriterion("term between", value1, value2, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotBetween(String value1, String value2) {
            addCriterion("term not between", value1, value2, "term");
            return (Criteria) this;
        }

        public Criteria andSum1IsNull() {
            addCriterion("sum1 is null");
            return (Criteria) this;
        }

        public Criteria andSum1IsNotNull() {
            addCriterion("sum1 is not null");
            return (Criteria) this;
        }

        public Criteria andSum1EqualTo(Float value) {
            addCriterion("sum1 =", value, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1EqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1NotEqualTo(Float value) {
            addCriterion("sum1 <>", value, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1NotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1GreaterThan(Float value) {
            addCriterion("sum1 >", value, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1GreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1GreaterThanOrEqualTo(Float value) {
            addCriterion("sum1 >=", value, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1GreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1LessThan(Float value) {
            addCriterion("sum1 <", value, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1LessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1LessThanOrEqualTo(Float value) {
            addCriterion("sum1 <=", value, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1LessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1In(List<Float> values) {
            addCriterion("sum1 in", values, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1NotIn(List<Float> values) {
            addCriterion("sum1 not in", values, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1Between(Float value1, Float value2) {
            addCriterion("sum1 between", value1, value2, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum1NotBetween(Float value1, Float value2) {
            addCriterion("sum1 not between", value1, value2, "sum1");
            return (Criteria) this;
        }

        public Criteria andSum2IsNull() {
            addCriterion("sum2 is null");
            return (Criteria) this;
        }

        public Criteria andSum2IsNotNull() {
            addCriterion("sum2 is not null");
            return (Criteria) this;
        }

        public Criteria andSum2EqualTo(Float value) {
            addCriterion("sum2 =", value, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2EqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum2 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum2NotEqualTo(Float value) {
            addCriterion("sum2 <>", value, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2NotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum2 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum2GreaterThan(Float value) {
            addCriterion("sum2 >", value, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2GreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum2 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum2GreaterThanOrEqualTo(Float value) {
            addCriterion("sum2 >=", value, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2GreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum2 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum2LessThan(Float value) {
            addCriterion("sum2 <", value, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2LessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum2 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum2LessThanOrEqualTo(Float value) {
            addCriterion("sum2 <=", value, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2LessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum2 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum2In(List<Float> values) {
            addCriterion("sum2 in", values, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2NotIn(List<Float> values) {
            addCriterion("sum2 not in", values, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2Between(Float value1, Float value2) {
            addCriterion("sum2 between", value1, value2, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum2NotBetween(Float value1, Float value2) {
            addCriterion("sum2 not between", value1, value2, "sum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2IsNull() {
            addCriterion("sum1AddSum2 is null");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2IsNotNull() {
            addCriterion("sum1AddSum2 is not null");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2EqualTo(Float value) {
            addCriterion("sum1AddSum2 =", value, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2EqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1AddSum2 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1addsum2NotEqualTo(Float value) {
            addCriterion("sum1AddSum2 <>", value, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2NotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1AddSum2 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1addsum2GreaterThan(Float value) {
            addCriterion("sum1AddSum2 >", value, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2GreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1AddSum2 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1addsum2GreaterThanOrEqualTo(Float value) {
            addCriterion("sum1AddSum2 >=", value, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2GreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1AddSum2 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1addsum2LessThan(Float value) {
            addCriterion("sum1AddSum2 <", value, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2LessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1AddSum2 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1addsum2LessThanOrEqualTo(Float value) {
            addCriterion("sum1AddSum2 <=", value, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2LessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("sum1AddSum2 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSum1addsum2In(List<Float> values) {
            addCriterion("sum1AddSum2 in", values, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2NotIn(List<Float> values) {
            addCriterion("sum1AddSum2 not in", values, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2Between(Float value1, Float value2) {
            addCriterion("sum1AddSum2 between", value1, value2, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andSum1addsum2NotBetween(Float value1, Float value2) {
            addCriterion("sum1AddSum2 not between", value1, value2, "sum1addsum2");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadIsNull() {
            addCriterion("rsrWorkload is null");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadIsNotNull() {
            addCriterion("rsrWorkload is not null");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadEqualTo(Float value) {
            addCriterion("rsrWorkload =", value, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("rsrWorkload = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRsrworkloadNotEqualTo(Float value) {
            addCriterion("rsrWorkload <>", value, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadNotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("rsrWorkload <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRsrworkloadGreaterThan(Float value) {
            addCriterion("rsrWorkload >", value, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadGreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("rsrWorkload > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRsrworkloadGreaterThanOrEqualTo(Float value) {
            addCriterion("rsrWorkload >=", value, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadGreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("rsrWorkload >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRsrworkloadLessThan(Float value) {
            addCriterion("rsrWorkload <", value, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadLessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("rsrWorkload < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRsrworkloadLessThanOrEqualTo(Float value) {
            addCriterion("rsrWorkload <=", value, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadLessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("rsrWorkload <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRsrworkloadIn(List<Float> values) {
            addCriterion("rsrWorkload in", values, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadNotIn(List<Float> values) {
            addCriterion("rsrWorkload not in", values, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadBetween(Float value1, Float value2) {
            addCriterion("rsrWorkload between", value1, value2, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andRsrworkloadNotBetween(Float value1, Float value2) {
            addCriterion("rsrWorkload not between", value1, value2, "rsrworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadIsNull() {
            addCriterion("subsidyworkload is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadIsNotNull() {
            addCriterion("subsidyworkload is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadEqualTo(Float value) {
            addCriterion("subsidyworkload =", value, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("subsidyworkload = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadNotEqualTo(Float value) {
            addCriterion("subsidyworkload <>", value, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadNotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("subsidyworkload <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadGreaterThan(Float value) {
            addCriterion("subsidyworkload >", value, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadGreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("subsidyworkload > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadGreaterThanOrEqualTo(Float value) {
            addCriterion("subsidyworkload >=", value, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadGreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("subsidyworkload >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadLessThan(Float value) {
            addCriterion("subsidyworkload <", value, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadLessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("subsidyworkload < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadLessThanOrEqualTo(Float value) {
            addCriterion("subsidyworkload <=", value, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadLessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("subsidyworkload <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadIn(List<Float> values) {
            addCriterion("subsidyworkload in", values, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadNotIn(List<Float> values) {
            addCriterion("subsidyworkload not in", values, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadBetween(Float value1, Float value2) {
            addCriterion("subsidyworkload between", value1, value2, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andSubsidyworkloadNotBetween(Float value1, Float value2) {
            addCriterion("subsidyworkload not between", value1, value2, "subsidyworkload");
            return (Criteria) this;
        }

        public Criteria andTotalsumIsNull() {
            addCriterion("totalSum is null");
            return (Criteria) this;
        }

        public Criteria andTotalsumIsNotNull() {
            addCriterion("totalSum is not null");
            return (Criteria) this;
        }

        public Criteria andTotalsumEqualTo(Float value) {
            addCriterion("totalSum =", value, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalSum = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalsumNotEqualTo(Float value) {
            addCriterion("totalSum <>", value, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumNotEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalSum <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalsumGreaterThan(Float value) {
            addCriterion("totalSum >", value, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumGreaterThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalSum > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalsumGreaterThanOrEqualTo(Float value) {
            addCriterion("totalSum >=", value, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumGreaterThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalSum >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalsumLessThan(Float value) {
            addCriterion("totalSum <", value, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumLessThanColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalSum < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalsumLessThanOrEqualTo(Float value) {
            addCriterion("totalSum <=", value, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumLessThanOrEqualToColumn(WorkloadWithBLOBs.Column column) {
            addCriterion(new StringBuilder("totalSum <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTotalsumIn(List<Float> values) {
            addCriterion("totalSum in", values, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumNotIn(List<Float> values) {
            addCriterion("totalSum not in", values, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumBetween(Float value1, Float value2) {
            addCriterion("totalSum between", value1, value2, "totalsum");
            return (Criteria) this;
        }

        public Criteria andTotalsumNotBetween(Float value1, Float value2) {
            addCriterion("totalSum not between", value1, value2, "totalsum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private WorkloadExample example;

        protected Criteria(WorkloadExample example) {
            super();
            this.example = example;
        }

        public WorkloadExample example() {
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
        void example(com.hngy.educationaladministration.entity.WorkloadExample example);
    }
}