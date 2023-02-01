package com.jonas.module.websocket.server;

import com.jonas.module.websocket.config.WebSocketConfigurator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;

@Slf4j
@Component
@ServerEndpoint(value = "/ws/server", configurator = WebSocketConfigurator.class)
public class WebSocketServer {

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        Map<String, Object> userProperties = session.getUserProperties();
        // 获取ip
        String ipAddr = (String) userProperties.get(WebSocketConfigurator.IP_ADDR);
        log.info("WS回调打开, ip:{}", ipAddr);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        Map<String, Object> userProperties = session.getUserProperties();
        // 获取ip
        String ipAddr = (String) userProperties.get(WebSocketConfigurator.IP_ADDR);
        log.info("WS回调关闭, ip:{}", ipAddr);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        // 收到客户端的消息
        log.info("WS回调关闭, message:{}", message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket session:[{}]发生错误，", session.getId(), error);
    }
}
