package com.bilginyuksel.sdk.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.Settings.System.getString;

public class NotificationSender {

    private final static String CHANNEL_ID = "channel-test";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void send(Context context, Object notificationMessage) {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "test",NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder b = new NotificationCompat.Builder(context,CHANNEL_ID);
        try {
           JSONObject jsonObject=new JSONObject((String) notificationMessage);
            b.setContentTitle(jsonObject.getString("title"))
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText(jsonObject.getString("description"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        notificationManager.notify(3424234, b.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void send(Context context) {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "test",NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder b = new NotificationCompat.Builder(context,CHANNEL_ID);


        b.setContentTitle("My notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(123123, b.build());
    }
}