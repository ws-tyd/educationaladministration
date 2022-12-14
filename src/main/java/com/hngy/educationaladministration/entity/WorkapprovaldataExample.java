package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.List;

public class WorkapprovaldataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkapprovaldataExample() {
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

        public Criteria andIdWorkapprovalIsNull() {
            addCriterion("id_workapproval is null");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalIsNotNull() {
            addCriterion("id_workapproval is not null");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalEqualTo(Long value) {
            addCriterion("id_workapproval =", value, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalNotEqualTo(Long value) {
            addCriterion("id_workapproval <>", value, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalGreaterThan(Long value) {
            addCriterion("id_workapproval >", value, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalGreaterThanOrEqualTo(Long value) {
            addCriterion("id_workapproval >=", value, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalLessThan(Long value) {
            addCriterion("id_workapproval <", value, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalLessThanOrEqualTo(Long value) {
            addCriterion("id_workapproval <=", value, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalIn(List<Long> values) {
            addCriterion("id_workapproval in", values, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalNotIn(List<Long> values) {
            addCriterion("id_workapproval not in", values, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalBetween(Long value1, Long value2) {
            addCriterion("id_workapproval between", value1, value2, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andIdWorkapprovalNotBetween(Long value1, Long value2) {
            addCriterion("id_workapproval not between", value1, value2, "idWorkapproval");
            return (Criteria) this;
        }

        public Criteria andNewsIsNull() {
            addCriterion("news is null");
            return (Criteria) this;
        }

        public Criteria andNewsIsNotNull() {
            addCriterion("news is not null");
            return (Criteria) this;
        }

        public Criteria andNewsEqualTo(String value) {
            addCriterion("news =", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsNotEqualTo(String value) {
            addCriterion("news <>", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsGreaterThan(String value) {
            addCriterion("news >", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsGreaterThanOrEqualTo(String value) {
            addCriterion("news >=", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsLessThan(String value) {
            addCriterion("news <", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsLessThanOrEqualTo(String value) {
            addCriterion("news <=", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsLike(String value) {
            addCriterion("news like", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsNotLike(String value) {
            addCriterion("news not like", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsIn(List<String> values) {
            addCriterion("news in", values, "news");
            return (Criteria) this;
        }

        public Criteria andNewsNotIn(List<String> values) {
            addCriterion("news not in", values, "news");
            return (Criteria) this;
        }

        public Criteria andNewsBetween(String value1, String value2) {
            addCriterion("news between", value1, value2, "news");
            return (Criteria) this;
        }

        public Criteria andNewsNotBetween(String value1, String value2) {
            addCriterion("news not between", value1, value2, "news");
            return (Criteria) this;
        }

        public Criteria andDatararIsNull() {
            addCriterion("dataRar is null");
            return (Criteria) this;
        }

        public Criteria andDatararIsNotNull() {
            addCriterion("dataRar is not null");
            return (Criteria) this;
        }

        public Criteria andDatararEqualTo(String value) {
            addCriterion("dataRar =", value, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararNotEqualTo(String value) {
            addCriterion("dataRar <>", value, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararGreaterThan(String value) {
            addCriterion("dataRar >", value, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararGreaterThanOrEqualTo(String value) {
            addCriterion("dataRar >=", value, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararLessThan(String value) {
            addCriterion("dataRar <", value, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararLessThanOrEqualTo(String value) {
            addCriterion("dataRar <=", value, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararLike(String value) {
            addCriterion("dataRar like", value, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararNotLike(String value) {
            addCriterion("dataRar not like", value, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararIn(List<String> values) {
            addCriterion("dataRar in", values, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararNotIn(List<String> values) {
            addCriterion("dataRar not in", values, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararBetween(String value1, String value2) {
            addCriterion("dataRar between", value1, value2, "datarar");
            return (Criteria) this;
        }

        public Criteria andDatararNotBetween(String value1, String value2) {
            addCriterion("dataRar not between", value1, value2, "datarar");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
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