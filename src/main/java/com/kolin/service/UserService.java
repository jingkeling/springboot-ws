package com.kolin.service;

import com.kolin.pojo.domain.UserDO;

import java.util.List;

/**
 * @Author jingkeling
 * @Date 2018/5/8 20:51
 */
public interface UserService {


    UserDO login(String username);


    void logout(String username);

    Boolean isOnline(String username);

    UserDO save(UserDO userDO);

    void changeOnline(String username, Integer online);


    /**
     * 1、登录，
     * 2、如果没有帐号就注册
     * 3、如果已经登录了，就不允许登录
     * @param username
     * @return
     */
    UserDO loginAndRegister(String username, String password);

    List<UserDO> findAll();
}
