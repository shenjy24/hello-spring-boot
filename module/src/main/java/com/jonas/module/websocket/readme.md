### 一.Maven依赖

1.服务端所需依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

2.Java客户端所需依赖

```xml
<dependency>
    <groupId>org.java-websocket</groupId>
    <artifactId>Java-WebSocket</artifactId>
    <version>1.5.3</version>
</dependency>
```

3.SpringBoot版本：2.7.8

### 二.服务端代码

1.使用 Filter 获取客户端IP

```java
package com.jonas.module.websocket.config;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import static com.jonas.module.websocket.config.WebSocketConfigurator.IP_ATTR;

@Component
public class IpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getSession().setAttribute(IP_ATTR,  request.getRemoteAddr() + ":" + request.getRemotePort());
        filterChain.doFilter(request, servletResponse);
    }
}
```

2.注入 ServerEndpointExporter 依赖，会自动注册使用了 @ServerEndpoint 注解声明的 WebSocket Endpoint

```java
package com.jonas.module.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
```

3.配置 ServerEndpointConfig.Configurator ，在 modifyHandshake 方法中设置自定义属性。

```java
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
```

4.服务端代码实现

```java
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
```

三.客户端实现

1.Java客户端示例

```java
package com.jonas.module.websocket.client;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;

@Slf4j
public class MyWebSocketClient extends WebSocketClient {

    private static final String WS_URL = "ws://localhost:18083/ws/server";

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("Open a WebSocket connection on client. ");
    }

    @Override
    public void onMessage(String s) {
        log.info("WebSocketClient receives a message: " + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("Close a WebSocket connection on client. ");
    }

    @Override
    public void onError(Exception e) {
        log.error("WebSocketClient exception. ", e);
    }

    public static void main(String[] args) {
        try {
            URI recognizeUri = new URI(WS_URL);
            MyWebSocketClient client = new MyWebSocketClient(recognizeUri);
            client.connect();

            Thread.sleep(2000);
            ReadyState readyState = client.getReadyState();
            if (readyState != ReadyState.OPEN) {
                log.error("连接失败");
                return;
            }
            client.send("This is a message from client. ");
            client.close();
        } catch (Exception e) {
            log.error("连接异常", e);
        }
    }
}
```

2.H5客户端示例

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>WebSocket的H5客户端</title>
</head>
<body>
    Welcome
    <br/>
    <input id="text" type="text"/>
    <button onclick="send()">发送消息</button>
    <hr/>
    <button onclick="closeWebSocket()">关闭WebSocket连接</button>
    <hr/>
    <div id="message"></div>
</body>
<script type="text/javascript">
    let websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        //改成你的地址
        websocket = new WebSocket("ws://localhost:18083/ws/server");
    } else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功");
    }
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log(event);
        setMessageInnerHTML(event);
    }
    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }
    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
    //发送消息
    function send() {
        let message = document.getElementById('text').value;
        websocket.send('{"msg":"' + message + '"}');
        setMessageInnerHTML(message + "&#13;");
    }
</script>
</html>
```

