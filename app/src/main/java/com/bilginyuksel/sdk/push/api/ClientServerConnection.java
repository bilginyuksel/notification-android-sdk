package com.bilginyuksel.sdk.push.api;

import android.content.Context;
import android.net.InetAddresses;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.bilginyuksel.sdk.push.NotificationSender;
import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ClientServerConnection implements WebSocketListener {
    private final String TAG = ClientServerConnection.class.getSimpleName();

    private final String hostname;
    private final String path;
    private final int port;

    private WebSocket notificationSocket;

    private final Context context;

    private LocalDateTime createTime;
    private LocalDateTime lastNotificationTime;

    // Run this on debug mode
    public ClientServerConnection(Context context) {
        this(context, "192.168.1.61", 8888, "handshake");
//        this("localhost", 8888, "handshake");
    }

    public ClientServerConnection(Context context, String hostname, int port, String path) {
        this.context = context;
        this.hostname = hostname;
        this.path = path;
        this.port = port;
    }

    // Handshake process between server and client also initializes to output/input channels
    public void connect() throws IOException, URISyntaxException, WebSocketException {
        URI connectionUri = new URI(
                String.format(Locale.ENGLISH, "ws://%s:%d/%s?clientId=testid", hostname, port, path));

        notificationSocket = new WebSocketFactory().createSocket(connectionUri);
        notificationSocket.addListener(this);
        notificationSocket.connect();

    }

    @Override
    public void onStateChanged(WebSocket websocket, WebSocketState newState) throws Exception {

    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        Log.d(TAG, "onConnected: Connection established successfully");
    }

    @Override
    public void onConnectError(WebSocket websocket, WebSocketException cause) throws Exception {
        Log.e(TAG, "onConnectError: " + cause.getError().toString());
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {

    }

    @Override
    public void onFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onContinuationFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onTextFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onBinaryFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onCloseFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onTextMessage(WebSocket websocket, String text) throws Exception {
        Log.i(TAG, "onTextMessage: String text message sent= " + text);
        NotificationSender.send(context,text);
    }

    @Override
    public void onTextMessage(WebSocket websocket, byte[] data) throws Exception {
        Log.i(TAG, "onTextMessage: Byte message sent= " + data);
    }

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
        Log.i(TAG, "onBinaryMessage: Binary message sent= " + binary);
    }

    @Override
    public void onSendingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onFrameSent(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onFrameUnsent(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onThreadCreated(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

    }

    @Override
    public void onThreadStarted(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

    }

    @Override
    public void onThreadStopping(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {

    }

    @Override
    public void onFrameError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onMessageError(WebSocket websocket, WebSocketException cause, List<WebSocketFrame> frames) throws Exception {

    }

    @Override
    public void onMessageDecompressionError(WebSocket websocket, WebSocketException cause, byte[] compressed) throws Exception {

    }

    @Override
    public void onTextMessageError(WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {

    }

    @Override
    public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onUnexpectedError(WebSocket websocket, WebSocketException cause) throws Exception {

    }

    @Override
    public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {

    }

    @Override
    public void onSendingHandshake(WebSocket websocket, String requestLine, List<String[]> headers) throws Exception {

    }
}
