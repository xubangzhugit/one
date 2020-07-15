package com.example.websocket2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by tym on 2018/10/20 0020.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig2 implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatMessageHandler(),"/websocket2").addInterceptors(chatInterceptor());
        // 如果浏览器不支持websocket，使用socketjs模拟websocket的连接
        // registry.addHandler(chatMessageHandler(), "/sockjs/websocket2").addInterceptors(new ChatHandshakeInterceptor()).withSockJS();
    }

    @Bean
    public TextWebSocketHandler chatMessageHandler(){
        return new ChatMessageHandler();
    }

    @Bean
    public ChatHandshakeInterceptor chatInterceptor(){
        return new ChatHandshakeInterceptor();
    }
}
