package com.kolin.component.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolin.config.consts.WsConst;
import com.kolin.pojo.VO.ChatVO;
import com.kolin.service.UserService;
import com.kolin.utils.ApplicationContextRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author jingkeling
 * @Date 2018/5/7 22:30
 */
@Component
@ServerEndpoint(value = "/websocket/{param}", configurator = HttpSessionConfiguratior.class)
@Slf4j
public class WebsocketCenter extends Object {


    private String username;

    private static Integer unsafeCount = 0;
    private static AtomicInteger safeCount = new AtomicInteger(0);

    /**
     * 用户名，websocket的session
     */
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    public WebsocketCenter() {
        unsafeCount++;
        safeCount.incrementAndGet();
        System.out.println("---WebscoketCenter构建了一次---,");
        System.out.println("unsafeCount = "+unsafeCount);
        System.out.println("safeCount = "+safeCount.get());

    }


    /**
     * 连接成功事件
     *
     * @param session
     * @param config
     * @param param
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam(value = "param") String param) {
        if (StringUtils.isEmpty(param)) { param = "";}
        else { System.out.println("拿到的参数是: " + param);}
        final HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        username = (String) httpSession.getAttribute(WsConst.DEFAULT_SESSION_USERNAME);
        sessionMap.put(username, session);
        log.info("【websocket消息】-{}-登录， 当前在线总人数是：{}",username, sessionMap.size());
    }

    /**
     * 接收消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("【weboscket消息】浏览器发来消息，message = {}",  message);

    }

    @OnClose
    public void onClose() {
        sessionMap.remove(username);
        System.out.println("用户　"+ username + "退出～");
        log.info("【websocket消息】-{}-连接断开，当前在线总人数:{}", username, sessionMap.size());
        final ApplicationContext applicatioContext = ApplicationContextRegister.getApplicatioContext();
        final UserService userService = applicatioContext.getBean(UserService.class);

//        userService.logout(username);
    }


    public Boolean sendMessageToOne(String username1, String message) {
        log.info("【websocket消息】指定发送， message = {}", message);
        final Session session = sessionMap.get(username1);
        if (session == null) {
            System.out.println("指定用户没有连接");
            return false;
        }
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 群发
     *
     * @param chatVO
     * @throws JsonProcessingException
     */
    public void sendMessageAll(ChatVO chatVO) throws JsonProcessingException {
        log.info("【websocket消息】-{}-发送所有人", chatVO.getUsername() );
        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(chatVO);
        sessionMap.forEach((key, value)-> {
            try {
                value.getBasicRemote().sendText(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}

