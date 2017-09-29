package com.ssm.service;

import com.ssm.model.Users;

import java.util.List;

public interface UsersService extends IService<Users> {
    /**
     * 根据条件分页查询
     *
     * @param country
     * @param page
     * @param rows
     * @return
     */
    List<Users> selectByCountry(Users country, int page, int rows);

    Users login(String username ,String pswd);

    int insertnoid(Users users);

    List<Users> selectFPage(int page, int rows);
}

