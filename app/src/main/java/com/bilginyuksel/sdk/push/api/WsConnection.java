package com.bilginyuksel.sdk.push.api;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

public class WsConnection extends Thread {
    public WebSocketListenerImpl webSocketListener;
    protected int maxRetryCount = 3;
    protected int timeoutLimit = 5000;
    protected int firstRetryMilliseconds = 500;

    // If the internet connection is gone, run local alarm system to make the connection
    protected boolean alarmSystemOnline = true;
    public WsConnection(WebSocketListenerImpl webSocketListener){
        this.webSocketListener=webSocketListener;
    }
    @Override
    public void run() {
        super.run();
        WsConnInfo connInfo = WsConnInfo.create("192.168.1.103",8888)
                .scheme("ws")
                .path("handshake")
                .addRequestParameter("clientId","testid")
                .build();

        try {
            WebSocket webSocket = new WebSocketFactory().createSocket(connInfo.getURI());
            webSocket.addListener(webSocketListener);
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
