package com.hngy.educationaladministration.entity.enumeration;

import lombok.Getter;

/**
 *  枚举状态
 */
public enum MyEnum {

    /**
     * 课题三个状态 的 枚举
     */

    OPEN("开启",0),
    CLOSE("关闭",1),

    NO_AUDIT("未审核",0),
    NOT_PASS("审核未通过",1),
    PASS("审核通过",2),

    NOT_RELEASE("未发布",0),
    RELEASE("发布",1),

    /**
     * 学生选题状态
     */

    STU_NOT_SELECT("未选题",0),
    STU_TO_BE_AUDIT("选题待审核",1),
    STU_NOT_PASS("选题未通过",2),
    STU_PASS("选题通过",3),

            ;


    private String name;

    @Getter
    private Integer value;

    MyEnum(String name, Integer value){
        this.name = name;
        this.value = value;
    }


}
