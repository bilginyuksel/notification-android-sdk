package com.bilginyuksel.sdk.push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RunnerThread().start();
    }
}