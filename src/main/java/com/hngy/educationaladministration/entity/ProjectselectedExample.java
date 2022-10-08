package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.List;

public class ProjectselectedExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectselectedExample() {
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

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
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

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
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

        public Criteria andIdStudentIsNull() {
            addCriterion("id_student is null");
            return (Criteria) this;
        }

        public Criteria andIdStudentIsNotNull() {
            addCriterion("id_student is not null");
            return (Criteria) this;
        }

        public Criteria andIdStudentEqualTo(Long value) {
            addCriterion("id_student =", value, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentNotEqualTo(Long value) {
            addCriterion("id_student <>", value, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentGreaterThan(Long value) {
            addCriterion("id_student >", value, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentGreaterThanOrEqualTo(Long value) {
            addCriterion("id_student >=", value, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentLessThan(Long value) {
            addCriterion("id_student <", value, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentLessThanOrEqualTo(Long value) {
            addCriterion("id_student <=", value, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentIn(List<Long> values) {
            addCriterion("id_student in", values, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentNotIn(List<Long> values) {
            addCriterion("id_student not in", values, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentBetween(Long value1, Long value2) {
            addCriterion("id_student between", value1, value2, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdStudentNotBetween(Long value1, Long value2) {
            addCriterion("id_student not between", value1, value2, "idStudent");
            return (Criteria) this;
        }

        public Criteria andIdProjectIsNull() {
            addCriterion("id_project is null");
            return (Criteria) this;
        }

        public Criteria andIdProjectIsNotNull() {
            addCriterion("id_project is not null");
            return (Criteria) this;
        }

        public Criteria andIdProjectEqualTo(Long value) {
            addCriterion("id_project =", value, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectNotEqualTo(Long value) {
            addCriterion("id_project <>", value, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectGreaterThan(Long value) {
            addCriterion("id_project >", value, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectGreaterThanOrEqualTo(Long value) {
            addCriterion("id_project >=", value, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectLessThan(Long value) {
            addCriterion("id_project <", value, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectLessThanOrEqualTo(Long value) {
            addCriterion("id_project <=", value, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectIn(List<Long> values) {
            addCriterion("id_project in", values, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectNotIn(List<Long> values) {
            addCriterion("id_project not in", values, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectBetween(Long value1, Long value2) {
            addCriterion("id_project between", value1, value2, "idProject");
            return (Criteria) this;
        }

        public Criteria andIdProjectNotBetween(Long value1, Long value2) {
            addCriterion("id_project not between", value1, value2, "idProject");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagIsNull() {
            addCriterion("stuSelect_flag is null");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagIsNotNull() {
            addCriterion("stuSelect_flag is not null");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagEqualTo(Long value) {
            addCriterion("stuSelect_flag =", value, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagNotEqualTo(Long value) {
            addCriterion("stuSelect_flag <>", value, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagGreaterThan(Long value) {
            addCriterion("stuSelect_flag >", value, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("stuSelect_flag >=", value, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagLessThan(Long value) {
            addCriterion("stuSelect_flag <", value, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagLessThanOrEqualTo(Long value) {
            addCriterion("stuSelect_flag <=", value, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagIn(List<Long> values) {
            addCriterion("stuSelect_flag in", values, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagNotIn(List<Long> values) {
            addCriterion("stuSelect_flag not in", values, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagBetween(Long value1, Long value2) {
            addCriterion("stuSelect_flag between", value1, value2, "stuselectFlag");
            return (Criteria) this;
        }

        public Criteria andStuselectFlagNotBetween(Long value1, Long value2) {
            addCriterion("stuSelect_flag not between", value1, value2, "stuselectFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
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
}
