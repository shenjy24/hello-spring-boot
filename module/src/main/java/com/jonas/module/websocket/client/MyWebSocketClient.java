package com.jonas.module.websocket.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class MyWebSocketClient extends WebSocketClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyWebSocketClient.class);

    private static final String WS_URL = "ws://localhost:18083/ws/server";

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        LOGGER.info("Open a WebSocket connection on client. ");
    }

    @Override
    public void onMessage(String s) {
        LOGGER.info("WebSocketClient receives a message: " + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        LOGGER.info("Close a WebSocket connection on client. ");
    }

    @Override
    public void onError(Exception e) {
        LOGGER.error("WebSocketClient exception. ", e);
    }

    public static void main(String[] args) {
        try {
            URI recognizeUri = new URI(WS_URL);
            MyWebSocketClient client = new MyWebSocketClient(recognizeUri);
            client.connect();

            Thread.sleep(2000);
            ReadyState readyState = client.getReadyState();
            if (readyState != ReadyState.OPEN) {
                LOGGER.error("连接失败");
                return;
            }
            client.send("This is a message from client. ");
            client.close();
        } catch (Exception e) {
            LOGGER.error("连接异常", e);
        }
    }
}
