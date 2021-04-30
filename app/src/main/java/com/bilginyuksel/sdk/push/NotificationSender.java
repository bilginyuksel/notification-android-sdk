package com.bilginyuksel.sdk.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


public class NotificationSender {

    private final Context context;
    private final static String CHANNEL_ID = "push-notification-channel";
    private NotificationChannel notificationChannel;

    public NotificationSender(Context context) {
        this.context= context;
        checkAndroidVersionIsBiggerThanOreo();
    }

    public void checkAndroidVersionIsBiggerThanOreo(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             notificationChannel = createChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationChannel createChannel(){
        return new NotificationChannel(CHANNEL_ID,"push-notification-channel",NotificationManager.IMPORTANCE_HIGH);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void send() {
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(123123, buildNotification());
    }

    public Notification buildNotification(){
        NotificationCompat.Builder b = new NotificationCompat.Builder(context,CHANNEL_ID);
        b.setContentTitle("My notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(createPendingIntent())
                .setAutoCancel(true);
        return b.build();
    }

    public PendingIntent createPendingIntent(){
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }
}