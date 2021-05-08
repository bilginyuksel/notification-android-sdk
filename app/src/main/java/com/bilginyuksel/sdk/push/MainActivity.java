package com.bilginyuksel.sdk.push;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationSender notificationSender = new NotificationSender(getApplicationContext());
        RemoteMessage remoteMessage = new RemoteMessage();

        Map<String ,String> extras = new HashMap<>();
        extras.put("mesut","gedik");
        extras.put("kaan","yüksel");
        remoteMessage.setTitle("Deneme");
        remoteMessage.setContent("Bu push sdk testi içindir");
        remoteMessage.setExtras(extras);
        setContentView(R.layout.activity_main);

        //WsConnection wsConnection = new WsConnection(notificationSender);
        //wsConnection.start();
        Button button = findViewById(R.id.sendNotifbutton);
        button.setOnClickListener(v -> notificationSender.send(remoteMessage));
    }



}