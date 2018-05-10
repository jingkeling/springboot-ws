package com.kolin.service.impl;

import com.kolin.pojo.domain.UserDO;
import com.kolin.pojo.repository.UserRepository;
import com.kolin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @Author jingkeling
 * @Date 2018/5/8 20:51
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDO login(String username) {
        final UserDO user = userRepository.findByUsername(username);
        // TODO: 改成optional
        if (user == null) { return null;}
        if (user.getIsOnline() == null || user.getIsOnline() == 0) {
            user.setIsOnline(1);
            return user;
        } else {
            return null;
        }

    }

    /**
     * 登出
     */
    @Override
    public void logout(String username) {
        final UserDO user = userRepository.findByUsername(username);
        user.setIsOnline(0);
    }

    @Override
    public Boolean isOnline(String username) {
        /*final UserDO userdo = userRepository.findByUsername(username);
        final Integer isOnline = userdo.getIsOnline();*/
        return true;
    }

    @Override
    public UserDO save(UserDO userDO) {
        UserDO save = userRepository.save(userDO);
        return save;
    }

    @Override
    public UserDO loginAndRegister(String username) {
        UserDO userDO = userRepository.findByUsername(username);
        if (userDO == null) {
            Random random = new Random();
            userDO = new UserDO();
            userDO.setUsername(username);
            userDO.setId(random.nextInt());
            userDO.setAvator("http://i1.bvimg.com/626277/89998b3d06f0bbb4.jpg");
            userRepository.save(userDO);
            return userDO;
        } else if (userDO.getIsOnline() == 1) {
            return null;
        } else {
            return userDO;
        }

    }
}
