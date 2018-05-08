package com.kolin.service.impl;

import com.kolin.pojo.domain.UserDO;
import com.kolin.pojo.repository.UserRepository;
import com.kolin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        final UserDO byUsername = userRepository.findByUsername(username);

        return byUsername;
    }
}
