package com.bilginyuksel.sdk.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.concurrent.atomic.AtomicInteger;


public class NotificationSender {
    private final static String CHANNEL_ID = "push-notification-channel";
    private final static String CHANNEL_NAME = "push-notification-channel";

    private final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    private final Context context;

    private NotificationChannel notificationChannel;

    private int smallIcon;
    private int priority;
    private boolean autoCancel;


    public NotificationSender(Context context) {
        this.context = context;

        this.priority = NotificationCompat.PRIORITY_DEFAULT;
        this.smallIcon = R.drawable.ic_launcher_background;
        this.autoCancel = true;

        if (isVersionBiggerThanOreo()) {
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        }
    }

    public void send(RemoteMessage remoteMessage) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(remoteMessage.getTitle())
                .setContentText(remoteMessage.getContent())
                .setSmallIcon(smallIcon)
                .setPriority(priority)
                .setAutoCancel(autoCancel)
                .setContentIntent(createPendingIntent())
                .build();

        // If android version is bigger than oreo, notification channel is mandatory
        if (isVersionBiggerThanOreo()) {
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(ATOMIC_INTEGER.incrementAndGet(), notification);
    }

    public void setNotificationChannel(NotificationChannel channel) {
        this.notificationChannel = channel;
    }

    public void setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
    }

    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    private boolean isVersionBiggerThanOreo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

}