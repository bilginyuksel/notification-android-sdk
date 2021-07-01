package com.bilginyuksel.sdk.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.Nullable;

import com.bilginyuksel.sdk.push.utils.Util;

import java.util.concurrent.atomic.AtomicInteger;

public class NotificationSender {
    private final static String CHANNEL_ID = "push-notification-channel";
    private final static String CHANNEL_NAME = "push-notification-channel";

    private final Context context;
    private final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger();
    private NotificationChannel notificationChannel;

    public NotificationSender(Context context) {
            this.context = context;
            if (Util.isVersionBiggerThanOreo()) {
                notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            }
    }

    public void send(RemoteMessage remoteMessage, @Nullable NotificationChannel channel) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // If android version is bigger than oreo, notification channel is mandatory
        if (Util.isVersionBiggerThanOreo()) {
            if(channel != null)
                notificationManager.createNotificationChannel(channel);
            else
                notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = new NotificationBuilder(this.context).build(remoteMessage, CHANNEL_ID);
        notificationManager.notify(ATOMIC_INTEGER.incrementAndGet(), notification);
    }

}
