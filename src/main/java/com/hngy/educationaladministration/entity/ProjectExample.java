package com.hngy.educationaladministration.entity;

import java.util.ArrayList;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectExample() {
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

        public Criteria andIdProjecttypeIsNull() {
            addCriterion("id_projectType is null");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeIsNotNull() {
            addCriterion("id_projectType is not null");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeEqualTo(Long value) {
            addCriterion("id_projectType =", value, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeNotEqualTo(Long value) {
            addCriterion("id_projectType <>", value, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeGreaterThan(Long value) {
            addCriterion("id_projectType >", value, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeGreaterThanOrEqualTo(Long value) {
            addCriterion("id_projectType >=", value, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeLessThan(Long value) {
            addCriterion("id_projectType <", value, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeLessThanOrEqualTo(Long value) {
            addCriterion("id_projectType <=", value, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeIn(List<Long> values) {
            addCriterion("id_projectType in", values, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeNotIn(List<Long> values) {
            addCriterion("id_projectType not in", values, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeBetween(Long value1, Long value2) {
            addCriterion("id_projectType between", value1, value2, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjecttypeNotBetween(Long value1, Long value2) {
            addCriterion("id_projectType not between", value1, value2, "idProjecttype");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceIsNull() {
            addCriterion("id_projectSource is null");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceIsNotNull() {
            addCriterion("id_projectSource is not null");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceEqualTo(Long value) {
            addCriterion("id_projectSource =", value, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceNotEqualTo(Long value) {
            addCriterion("id_projectSource <>", value, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceGreaterThan(Long value) {
            addCriterion("id_projectSource >", value, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceGreaterThanOrEqualTo(Long value) {
            addCriterion("id_projectSource >=", value, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceLessThan(Long value) {
            addCriterion("id_projectSource <", value, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceLessThanOrEqualTo(Long value) {
            addCriterion("id_projectSource <=", value, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceIn(List<Long> values) {
            addCriterion("id_projectSource in", values, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceNotIn(List<Long> values) {
            addCriterion("id_projectSource not in", values, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceBetween(Long value1, Long value2) {
            addCriterion("id_projectSource between", value1, value2, "idProjectsource");
            return (Criteria) this;
        }

        public Criteria andIdProjectsourceNotBetween(Long value1, Long value2) {
            addCriterion("id_projectSource not between", value1, value2, "idProjectsource");
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

        public Criteria andIdTeacherNotEqualTo(Long value) {
            addCriterion("id_teacher <>", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThan(Long value) {
            addCriterion("id_teacher >", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherGreaterThanOrEqualTo(Long value) {
            addCriterion("id_teacher >=", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThan(Long value) {
            addCriterion("id_teacher <", value, "idTeacher");
            return (Criteria) this;
        }

        public Criteria andIdTeacherLessThanOrEqualTo(Long value) {
            addCriterion("id_teacher <=", value, "idTeacher");
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

        public Criteria andProjectnameIsNull() {
            addCriterion("projectName is null");
            return (Criteria) this;
        }

        public Criteria andProjectnameIsNotNull() {
            addCriterion("projectName is not null");
            return (Criteria) this;
        }

        public Criteria andProjectnameEqualTo(String value) {
            addCriterion("projectName =", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotEqualTo(String value) {
            addCriterion("projectName <>", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameGreaterThan(String value) {
            addCriterion("projectName >", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameGreaterThanOrEqualTo(String value) {
            addCriterion("projectName >=", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLessThan(String value) {
            addCriterion("projectName <", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLessThanOrEqualTo(String value) {
            addCriterion("projectName <=", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLike(String value) {
            addCriterion("projectName like", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotLike(String value) {
            addCriterion("projectName not like", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameIn(List<String> values) {
            addCriterion("projectName in", values, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotIn(List<String> values) {
            addCriterion("projectName not in", values, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameBetween(String value1, String value2) {
            addCriterion("projectName between", value1, value2, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotBetween(String value1, String value2) {
            addCriterion("projectName not between", value1, value2, "projectname");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyIsNull() {
            addCriterion("marchSpecialty is null");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyIsNotNull() {
            addCriterion("marchSpecialty is not null");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyEqualTo(String value) {
            addCriterion("marchSpecialty =", value, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyNotEqualTo(String value) {
            addCriterion("marchSpecialty <>", value, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyGreaterThan(String value) {
            addCriterion("marchSpecialty >", value, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyGreaterThanOrEqualTo(String value) {
            addCriterion("marchSpecialty >=", value, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyLessThan(String value) {
            addCriterion("marchSpecialty <", value, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyLessThanOrEqualTo(String value) {
            addCriterion("marchSpecialty <=", value, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyLike(String value) {
            addCriterion("marchSpecialty like", value, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyNotLike(String value) {
            addCriterion("marchSpecialty not like", value, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyIn(List<String> values) {
            addCriterion("marchSpecialty in", values, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyNotIn(List<String> values) {
            addCriterion("marchSpecialty not in", values, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyBetween(String value1, String value2) {
            addCriterion("marchSpecialty between", value1, value2, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andMarchspecialtyNotBetween(String value1, String value2) {
            addCriterion("marchSpecialty not between", value1, value2, "marchspecialty");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNull() {
            addCriterion("filePath is null");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNotNull() {
            addCriterion("filePath is not null");
            return (Criteria) this;
        }

        public Criteria andFilepathEqualTo(String value) {
            addCriterion("filePath =", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotEqualTo(String value) {
            addCriterion("filePath <>", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThan(String value) {
            addCriterion("filePath >", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThanOrEqualTo(String value) {
            addCriterion("filePath >=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThan(String value) {
            addCriterion("filePath <", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThanOrEqualTo(String value) {
            addCriterion("filePath <=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLike(String value) {
            addCriterion("filePath like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotLike(String value) {
            addCriterion("filePath not like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathIn(List<String> values) {
            addCriterion("filePath in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotIn(List<String> values) {
            addCriterion("filePath not in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathBetween(String value1, String value2) {
            addCriterion("filePath between", value1, value2, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotBetween(String value1, String value2) {
            addCriterion("filePath not between", value1, value2, "filepath");
            return (Criteria) this;
        }

        public Criteria andTeachernamesIsNull() {
            addCriterion("teacherNames is null");
            return (Criteria) this;
        }

        public Criteria andTeachernamesIsNotNull() {
            addCriterion("teacherNames is not null");
            return (Criteria) this;
        }

        public Criteria andTeachernamesEqualTo(String value) {
            addCriterion("teacherNames =", value, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesNotEqualTo(String value) {
            addCriterion("teacherNames <>", value, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesGreaterThan(String value) {
            addCriterion("teacherNames >", value, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesGreaterThanOrEqualTo(String value) {
            addCriterion("teacherNames >=", value, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesLessThan(String value) {
            addCriterion("teacherNames <", value, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesLessThanOrEqualTo(String value) {
            addCriterion("teacherNames <=", value, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesLike(String value) {
            addCriterion("teacherNames like", value, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesNotLike(String value) {
            addCriterion("teacherNames not like", value, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesIn(List<String> values) {
            addCriterion("teacherNames in", values, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesNotIn(List<String> values) {
            addCriterion("teacherNames not in", values, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesBetween(String value1, String value2) {
            addCriterion("teacherNames between", value1, value2, "teachernames");
            return (Criteria) this;
        }

        public Criteria andTeachernamesNotBetween(String value1, String value2) {
            addCriterion("teacherNames not between", value1, value2, "teachernames");
            return (Criteria) this;
        }

        public Criteria andSelectcountIsNull() {
            addCriterion("selectCount is null");
            return (Criteria) this;
        }

        public Criteria andSelectcountIsNotNull() {
            addCriterion("selectCount is not null");
            return (Criteria) this;
        }

        public Criteria andSelectcountEqualTo(Integer value) {
            addCriterion("selectCount =", value, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountNotEqualTo(Integer value) {
            addCriterion("selectCount <>", value, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountGreaterThan(Integer value) {
            addCriterion("selectCount >", value, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("selectCount >=", value, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountLessThan(Integer value) {
            addCriterion("selectCount <", value, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountLessThanOrEqualTo(Integer value) {
            addCriterion("selectCount <=", value, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountIn(List<Integer> values) {
            addCriterion("selectCount in", values, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountNotIn(List<Integer> values) {
            addCriterion("selectCount not in", values, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountBetween(Integer value1, Integer value2) {
            addCriterion("selectCount between", value1, value2, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectcountNotBetween(Integer value1, Integer value2) {
            addCriterion("selectCount not between", value1, value2, "selectcount");
            return (Criteria) this;
        }

        public Criteria andSelectFlagIsNull() {
            addCriterion("select_flag is null");
            return (Criteria) this;
        }

        public Criteria andSelectFlagIsNotNull() {
            addCriterion("select_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSelectFlagEqualTo(Integer value) {
            addCriterion("select_flag =", value, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagNotEqualTo(Integer value) {
            addCriterion("select_flag <>", value, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagGreaterThan(Integer value) {
            addCriterion("select_flag >", value, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("select_flag >=", value, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagLessThan(Integer value) {
            addCriterion("select_flag <", value, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagLessThanOrEqualTo(Integer value) {
            addCriterion("select_flag <=", value, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagIn(List<Integer> values) {
            addCriterion("select_flag in", values, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagNotIn(List<Integer> values) {
            addCriterion("select_flag not in", values, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagBetween(Integer value1, Integer value2) {
            addCriterion("select_flag between", value1, value2, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andSelectFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("select_flag not between", value1, value2, "selectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagIsNull() {
            addCriterion("verifyProject_flag is null");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagIsNotNull() {
            addCriterion("verifyProject_flag is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagEqualTo(Integer value) {
            addCriterion("verifyProject_flag =", value, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagNotEqualTo(Integer value) {
            addCriterion("verifyProject_flag <>", value, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagGreaterThan(Integer value) {
            addCriterion("verifyProject_flag >", value, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("verifyProject_flag >=", value, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagLessThan(Integer value) {
            addCriterion("verifyProject_flag <", value, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagLessThanOrEqualTo(Integer value) {
            addCriterion("verifyProject_flag <=", value, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagIn(List<Integer> values) {
            addCriterion("verifyProject_flag in", values, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagNotIn(List<Integer> values) {
            addCriterion("verifyProject_flag not in", values, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagBetween(Integer value1, Integer value2) {
            addCriterion("verifyProject_flag between", value1, value2, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andVerifyprojectFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("verifyProject_flag not between", value1, value2, "verifyprojectFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagIsNull() {
            addCriterion("release_flag is null");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagIsNotNull() {
            addCriterion("release_flag is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagEqualTo(Integer value) {
            addCriterion("release_flag =", value, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagNotEqualTo(Integer value) {
            addCriterion("release_flag <>", value, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagGreaterThan(Integer value) {
            addCriterion("release_flag >", value, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("release_flag >=", value, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagLessThan(Integer value) {
            addCriterion("release_flag <", value, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagLessThanOrEqualTo(Integer value) {
            addCriterion("release_flag <=", value, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagIn(List<Integer> values) {
            addCriterion("release_flag in", values, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagNotIn(List<Integer> values) {
            addCriterion("release_flag not in", values, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagBetween(Integer value1, Integer value2) {
            addCriterion("release_flag between", value1, value2, "releaseFlag");
            return (Criteria) this;
        }

        public Criteria andReleaseFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("release_flag not between", value1, value2, "releaseFlag");
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
