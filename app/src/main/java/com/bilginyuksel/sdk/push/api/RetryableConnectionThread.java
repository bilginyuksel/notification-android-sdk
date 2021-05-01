package com.bilginyuksel.sdk.push.api;

public abstract class RetryableConnectionThread extends Thread {

    protected WsConnInfo wsConnInfo;
    protected int maxRetryCount = 3;
    protected int timeoutLimit = 5000;
    protected int firstRetryMilliseconds = 500;

    // If the internet connection is gone, run local alarm system to make the connection
    protected boolean alarmSystemOnline = true;

    public RetryableConnectionThread(WsConnInfo wsConnInfo) {
        this.wsConnInfo = wsConnInfo.clone();
    }

    public Object connect() {
        try {
            return makeConnection();
        } catch (Exception e) {
        }
        return null;
    }

    protected abstract Object makeConnection() throws Exception;

}
