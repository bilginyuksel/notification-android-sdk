package com.bilginyuksel.sdk.push.api;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.net.URI;

public class WebSocketConnectionThread extends RetryableConnectionThread {

    public WebSocketConnectionThread(WsConnInfo wsConnInfo) {
        super(wsConnInfo);
    }

    @Override
    protected WebSocket makeConnection() throws Exception {
        URI connectionUri = wsConnInfo.getURI();

        WebSocket websocket = new WebSocketFactory().createSocket(connectionUri);
        websocket.connect();
        return websocket;
    }
}
