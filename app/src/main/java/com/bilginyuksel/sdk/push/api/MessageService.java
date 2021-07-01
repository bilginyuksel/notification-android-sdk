package com.bilginyuksel.sdk.push.api;

import android.app.NotificationChannel;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bilginyuksel.sdk.push.NotificationSender;
import com.bilginyuksel.sdk.push.RemoteMessage;

public  class MessageService extends Service {

    private final NotificationSender notificationSender;

    public MessageService() {
        Log.i("TAG", "MessageService: ");
        this.notificationSender = new NotificationSender(this);
        WsConnection wsConnection = new WsConnection(new WebSocketListenerImpl(this));
        wsConnection.start();
    }

    public void onMessageReceived(RemoteMessage notification){
        //Triggered when remoteMessage received
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public NotificationChannel updateNotificationChannel() {
        return null;
    }

    public void send(RemoteMessage remoteMessage) {
        NotificationChannel notificationChannel = null;

        this.onMessageReceived(remoteMessage);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = this.updateNotificationChannel();
        }

        notificationSender.send(remoteMessage, notificationChannel);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
