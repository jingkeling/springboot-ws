package com.kolin.controller;

import com.kolin.config.consts.WsConst;
import com.kolin.pojo.domain.UserDO;
import com.kolin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author jingkeling
 * @Date 2018/5/7 22:22
 */
@RestController
@RequestMapping("user")
public class UserConroller {

    @Autowired
    private UserService userService;




    @PostMapping("login")
    public UserDO login(HttpServletRequest request, @RequestParam(value = "username") String username,
                        @RequestParam(value = "password", required = false) String password) {
        System.out.println(username + " logining");
        final HttpSession httpSession = request.getSession();
        httpSession.setAttribute(WsConst.DEFAULT_SESSION_USERNAME, username);

        UserDO avator = userService.login(username);
        if (StringUtils.isEmpty(avator)) {
            return null;
        }
        return avator;
    }
}
