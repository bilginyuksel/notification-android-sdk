package com.bilginyuksel.sdk.push.api;

import com.bilginyuksel.sdk.push.NotificationSender;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

public class WsConnection extends Thread {
    private final NotificationSender notificationSender;

    public WsConnection(NotificationSender notificationSender){
        this.notificationSender= notificationSender;
    }

    @Override
    public void run() {
        super.run();
        WsConnInfo connInfo = WsConnInfo.create("192.168.1.38",8888)
                .scheme("ws")
                .path("handshake")
                .addRequestParameter("clientId","testid")
                .build();

        try {
            WebSocket webSocket = new WebSocketFactory().createSocket(connInfo.getURI());
            webSocket.addListener(new WebSocketListenerImpl(notificationSender));
            webSocket.connect();
        } catch (IOException | WebSocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }
}
