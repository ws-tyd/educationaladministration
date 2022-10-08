package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.List;

public class MyClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public MyClassExample() {
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

    public MyClassExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public MyClassExample orderBy(String ... orderByClauses) {
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

    public MyClassExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public MyClassExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public MyClassExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public static Criteria newAndCreateCriteria() {
        MyClassExample example = new MyClassExample();
        return example.createCriteria();
    }

    public MyClassExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public MyClassExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        public Criteria andIdEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(MyClass.Column column) {
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

        public Criteria andClassNameIsNull() {
            addCriterion("class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("class_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("class_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("class_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("class_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("class_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("class_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyIsNull() {
            addCriterion("id_specialty is null");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyIsNotNull() {
            addCriterion("id_specialty is not null");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyEqualTo(Long value) {
            addCriterion("id_specialty =", value, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id_specialty = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyNotEqualTo(Long value) {
            addCriterion("id_specialty <>", value, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyNotEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id_specialty <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyGreaterThan(Long value) {
            addCriterion("id_specialty >", value, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyGreaterThanColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id_specialty > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyGreaterThanOrEqualTo(Long value) {
            addCriterion("id_specialty >=", value, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyGreaterThanOrEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id_specialty >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyLessThan(Long value) {
            addCriterion("id_specialty <", value, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyLessThanColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id_specialty < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyLessThanOrEqualTo(Long value) {
            addCriterion("id_specialty <=", value, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyLessThanOrEqualToColumn(MyClass.Column column) {
            addCriterion(new StringBuilder("id_specialty <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyIn(List<Long> values) {
            addCriterion("id_specialty in", values, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyNotIn(List<Long> values) {
            addCriterion("id_specialty not in", values, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyBetween(Long value1, Long value2) {
            addCriterion("id_specialty between", value1, value2, "idSpecialty");
            return (Criteria) this;
        }

        public Criteria andIdSpecialtyNotBetween(Long value1, Long value2) {
            addCriterion("id_specialty not between", value1, value2, "idSpecialty");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private MyClassExample example;

        protected Criteria(MyClassExample example) {
            super();
            this.example = example;
        }

        public MyClassExample example() {
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
        void example(com.hngy.educationaladministration.entity.MyClassExample example);
    }
}