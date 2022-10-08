package com.hngy.educationaladministration.utils;

public class StringUtil {

    /**
     * 字符串 空串 和 null 判断
     * @param str
     * @return false 为空 true 不为空
     */
    public static boolean stringIsNotNull(String str)
    {
        if(str == null || str.isEmpty())
            return false;
        return true;
    }
}
