package com.jonas.module.websocket.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Enumeration;
import java.util.Map;

public class WebSocketConfigurator extends ServerEndpointConfig.Configurator {
    public static final String IP_ADDR = "IP.ADDR";
    public static final String IP_ATTR = "client_ip";

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        Map<String, Object> attributes = sec.getUserProperties();
        HttpSession session = (HttpSession) request.getHttpSession();
        if (session != null) {
            attributes.put(IP_ADDR, session.getAttribute(IP_ATTR));
            Enumeration<String> names = session.getAttributeNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                attributes.put(name, session.getAttribute(name));
            }
        }
    }
}
