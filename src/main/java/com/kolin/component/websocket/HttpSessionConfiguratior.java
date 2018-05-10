package com.kolin.component.websocket;

import com.kolin.config.consts.WsConst;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 这里为了让websocket拿到登录时候设置的httpsession
 *
 * @Author jingkeling
 * @Date 2018/5/7 22:32
 */
public class HttpSessionConfiguratior extends ServerEndpointConfig.Configurator {

    /**
     * 注意，没有登录信息的话，weboscket每次连接都会空指针
     * （这种情况一般是服务端重启了，因为如果前端刷新了cookie还在，服务端session可以拿到）
     *
     */


    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);
        System.out.println("--每次websocket连接，HttpSession进入ws配置里一次--");
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        if (httpSession == null || StringUtils.isEmpty(httpSession.getAttribute(WsConst.DEFAULT_SESSION_USERNAME))) {
            System.out.println("！！注意，当前websocket连接没有httpsession");
        }
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
