package com.bilginyuksel.sdk.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.bilginyuksel.sdk.push.utils.Util;

import java.util.concurrent.atomic.AtomicInteger;


public class NotificationBuilder {

    private final Context context;


    private int smallIcon;
    private int priority;
    private boolean autoCancel;

    public NotificationBuilder(Context context) {
        this.context = context;
        this.priority = NotificationCompat.PRIORITY_DEFAULT;
        this.smallIcon = R.drawable.ic_launcher_background;
        this.autoCancel = true;
    }

    public NotificationBuilder setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
        return this;
    }

    public NotificationBuilder setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public NotificationBuilder setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
        return this;
    }

    public Notification build(RemoteMessage remoteMessage, String channelId) {
        return new NotificationCompat.Builder(context, channelId)
                .setContentTitle(remoteMessage.getTitle())
                .setContentText(remoteMessage.getContent())
                .setSmallIcon(smallIcon)
                .setPriority(priority)
                .setAutoCancel(autoCancel)
                .setContentIntent(Util.createPendingIntent(context))
                .build();
    }



}