package com.kolin.controller;

import com.kolin.config.consts.WsConst;
import com.kolin.pojo.domain.UserDO;
import com.kolin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    /**
     * 登录并注册
     * @param request
     * @param username
     * @param password
     * @return 返回用户信息给前端保存
     */
    @PostMapping("login")
    public UserDO login(HttpServletRequest request, @RequestParam(value = "username") String username,
                        @RequestParam(value = "password", required = false) String password) {
        System.out.println(username + " logining");
        UserDO userDO = userService.loginAndRegister(username);
        if (userDO != null) {
            final HttpSession httpSession = request.getSession();
            httpSession.setAttribute(WsConst.DEFAULT_SESSION_USERNAME, username);
            httpSession.setAttribute(WsConst.DEFAULT_SESSION_AVATOR, userDO.getAvator());
            return userDO;
        }
        return null;
    }
}
