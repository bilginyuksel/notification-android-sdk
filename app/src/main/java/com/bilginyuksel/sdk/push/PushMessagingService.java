package com.bilginyuksel.sdk.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bilginyuksel.sdk.push.api.WsConnection;

public class PushMessagingService extends Service {
    private WsConnection wsConnection;
    protected NotificationSender notificationSender;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationSender = new NotificationSender(this);
        wsConnection = new WsConnection(notificationSender);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding so return null
        return null;
    }

}
