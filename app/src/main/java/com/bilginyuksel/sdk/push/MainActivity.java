package com.bilginyuksel.sdk.push;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.bilginyuksel.sdk.push.api.ClientServerConnection;
import com.neovisionaries.ws.client.WebSocketException;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    public class RunnerThread extends Thread {

        @Override
        public void run() {
            ClientServerConnection connection = new ClientServerConnection(getApplicationContext());
            try {
                connection.connect();
            } catch (IOException | URISyntaxException | WebSocketException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationSender notificationSender = new NotificationSender(getApplicationContext());
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.sendNotifbutton);
        button.setOnClickListener(v -> notificationSender.send(new RemoteMessage()));
    }
}