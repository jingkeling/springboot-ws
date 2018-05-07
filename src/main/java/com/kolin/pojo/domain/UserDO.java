package com.kolin.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @Author jingkeling
 * @Date 2018/5/7 22:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserDO {


    @Id
    private Integer id;

    private String username;

    private String password;
}
