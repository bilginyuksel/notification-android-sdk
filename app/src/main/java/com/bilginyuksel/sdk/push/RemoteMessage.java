package com.bilginyuksel.sdk.push;

import java.util.HashMap;
import java.util.Map;

public class RemoteMessage {
    private  String title;
    private  String content;
    private  Map<String,String> extras;
    private  String icon;
    private  boolean autoCancel;
    private  String sound;
    private  String ticker;
    private  String tag;
    private Long[] vibrate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setVibrate(Long[] vibrate) {
        this.vibrate = vibrate;
    }

    public String getTitle(){
        return title;
    }

    public String getContent() {
        return content;
    }

    public Map<String, String> getExtras() {
         return new HashMap<>(extras);
    }

    public String getIcon() {
        return icon;
    }

    public boolean isAutoCancel(){
        return autoCancel;
    }

    public String getSound() {
        return sound;
    }

    public String getTicker() {
        return ticker;
    }

    public String getTag() {
        return tag;
    }

    public Long[] getVibrate() {
        return vibrate;
    }
}

