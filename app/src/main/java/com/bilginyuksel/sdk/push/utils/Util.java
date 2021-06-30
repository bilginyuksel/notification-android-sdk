package com.bilginyuksel.sdk.push.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.bilginyuksel.sdk.push.MainActivity;

public class Util {

    public static PendingIntent createPendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    public static boolean isVersionBiggerThanOreo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }
}
