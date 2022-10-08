package com.hngy.educationaladministration.service;

import com.hngy.educationaladministration.Exception.MyException;

public interface PublicService {

    /**
     *
     * 检查用户名是否重复
     * @param username 需要查询的用户名
     */
    public void CheckIfTheUsernameIsDuplicated(String username) throws MyException;
}
