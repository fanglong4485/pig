package com.pig4cloud.pig.datas.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * @author 18210
 */
@Configuration
@EnableWebSocketMessageBroker//开启使用STOMP协议来传输基于代理（message broker）的消息
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //注册两个队列(代理？)，主要用来做消息区分的(在我看来)
        registry.enableSimpleBroker("/topic","/bigScreen");
//        registry.setApplicationDestinationPrefixes("/app");//表示配置一个或多个前缀，通过这些前缀过滤出需要被注解方法处理的消息 (这个注释掉，messagemapping就能接受到请求了)
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //通俗易懂简单的来讲，addEndpoint("/api")就是客户端连接的时候url地址
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}

