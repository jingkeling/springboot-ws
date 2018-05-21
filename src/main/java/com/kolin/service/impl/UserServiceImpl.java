package com.kolin.service.impl;

import com.kolin.pojo.domain.UserDO;
import com.kolin.pojo.repository.UserRepository;
import com.kolin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void changeOnline(String username, Integer online) {
        final UserDO byUsername = userRepository.findByUsername(username);
        byUsername.setIsOnline(online);
    }

    @Override
    public UserDO loginAndRegister(String username, String password) {
        UserDO userDO = userRepository.findByUsername(username);

        //如果没有该帐号
        if (userDO == null) {
            Random random = new Random();
            userDO = new UserDO();
            userDO.setUsername(username);
            userDO.setPassword(password);
            userDO.setId(random.nextInt());
            userDO.setAvator("http://i1.bvimg.com/626277/89998b3d06f0bbb4.jpg");
            userDO.setIsOnline(1);
            userRepository.save(userDO);
            return userDO;

        } else if ( !userDO.getPassword().equals(password)) {
            //如果密码不正确
            return null;
        } else {
            userDO.setIsLogin(1);
            return userDO;
        }

    }

    @Override
    public List<UserDO> findAll() {
        final List<UserDO> all = userRepository.findAll(new Sort(Sort.Direction.DESC));
        return all;
    }

    @Override
    public void signout(String username) {
        final UserDO userDO = userRepository.findByUsername(username);
        if (userDO != null) {
            userDO.setIsLogin(0);
        }

    }
}
