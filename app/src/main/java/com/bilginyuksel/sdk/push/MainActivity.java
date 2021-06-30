package com.bilginyuksel.sdk.push;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.bilginyuksel.sdk.push.api.MessageService;
import com.bilginyuksel.sdk.push.api.WebSocketListenerImpl;
import com.bilginyuksel.sdk.push.api.WsConnection;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, MessageService.class));
    }
}