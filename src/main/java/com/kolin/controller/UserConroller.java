package com.kolin.controller;

import com.kolin.config.consts.WsConst;
import com.kolin.pojo.domain.UserDO;
import com.kolin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
                        @RequestParam(value = "password") String password) {
        System.out.println(username + " logining");
        UserDO userDO = userService.loginAndRegister(username, password);
        if (userDO == null) { return null;}

        final HttpSession httpSession = request.getSession();
        httpSession.setAttribute(WsConst.DEFAULT_SESSION_USERNAME, username);
        httpSession.setAttribute(WsConst.DEFAULT_SESSION_AVATOR, userDO.getAvator());
        return userDO;

    }

    @GetMapping("findAll")
    public List<UserDO> findAll() {
        List<UserDO> list = userService.findAll();
        return list;
    }

    @GetMapping("signout/{username}")
    public void signout(@PathVariable(name = "username") String username) {
        userService.signout(username);
    }
}
