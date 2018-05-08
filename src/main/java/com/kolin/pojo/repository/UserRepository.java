package com.kolin.pojo.repository;

import com.kolin.pojo.domain.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author jingkeling
 * @Date 2018/5/8 20:55
 */
public interface UserRepository extends JpaRepository<UserDO, Integer> {


    /**
     * @param username
     * @return
     */
    UserDO findByUsername(String username);

}
