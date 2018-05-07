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


    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
