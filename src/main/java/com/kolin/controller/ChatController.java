package com.kolin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kolin.component.websocket.WebsocketCenter;
import com.kolin.pojo.VO.ChatVO;
import com.kolin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("sendMessage")
    public void sendMessage(@RequestBody ChatVO chatVO) throws JsonProcessingException {
        final String username = chatVO.getUsername();
        final String message = chatVO.getMessage();
        log.info("用户-{}-发送来一条消息，内容是:{}", username, message);
//        Boolean isOnline = userService.isOnline(username);
        websocketCenter.sendMessageAll(chatVO);


    }

}
