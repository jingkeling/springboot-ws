package com.kolin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kolin.component.websocket.WebsocketCenter;
import com.kolin.config.consts.WsConst;
import com.kolin.pojo.VO.ChatVO;
import com.kolin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author jingkeling
 * @Date 2018/5/8 0:17
 */
@RestController
@RequestMapping("chat")
@Slf4j
public class ChatController {


    @Autowired
    private WebsocketCenter websocketCenter;

    @Autowired
    private UserService userService;

    /**
     * 发送聊天信息
     * @param chatVO 前端发送过来的
     * @param request
     * @throws JsonProcessingException
     */
    @PostMapping("sendMessage")
    public void sendMessage(@RequestBody ChatVO chatVO, HttpServletRequest request) throws JsonProcessingException {
        /*final String username = chatVO.getUsername();
        final String message = chatVO.getMessage();*/
        final HttpSession session = request.getSession(false);
        if (session == null) {return;}
        final String username1 = (String) session.getAttribute(WsConst.DEFAULT_SESSION_USERNAME);
        final String avator1 = (String) session.getAttribute(WsConst.DEFAULT_SESSION_AVATOR);
        final String message = chatVO.getMessage();
        final ChatVO chatVO1 = new ChatVO();
        chatVO1.setUsername(username1);
        chatVO1.setMessage(message);
        chatVO1.setAvator(avator1);

        log.info("用户-{}-发送来一条消息，内容是:{}", username1, message);
        websocketCenter.sendMessageAll(chatVO1);



    }

}
