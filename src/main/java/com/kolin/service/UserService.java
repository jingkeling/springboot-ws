package com.kolin.service;

import com.kolin.pojo.domain.UserDO;

/**
 * @Author jingkeling
 * @Date 2018/5/8 20:51
 */
public interface UserService {


    UserDO login(String username);


    void logout(String username);

    Boolean isOnline(String username);

    UserDO save(UserDO userDO);
}
