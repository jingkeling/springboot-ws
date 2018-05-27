package com.kolin.component.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @Author jingkeling
 * @Date 2018/5/7 22:07
 */
@Configuration
public class WebsocketConfig {

    /*
     * 注意！！ 这是针对springboot内置tomcat的，
     *
     * 部署war时，外部的tomcat不需要这个
     *
     * 不然会报异常 WebSocket issue : Multiple Endpoints may not be deployed to the same path
     *
     */

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
