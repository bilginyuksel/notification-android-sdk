package com.bilginyuksel.sdk.push.api;

import androidx.annotation.NonNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class WsConnInfo {
    private String scheme = "";
    private String hostname;
    private String path;
    private int port;
    private final Map<String, String> requestParameters = new HashMap<>();
    private final Map<String, Object> requestBody = new HashMap<>();

    private WsConnInfo() {
    }

    public static WsConnInfoBuilder create(String hostname, int port) {
        return new WsConnInfoBuilder(hostname, port);
    }

    public URI getURI() {
        String uriString = scheme + "://" + hostname + ":" + port + "/" + path;

        if (requestParameters.size() > 0) {
            uriString += "?" + getRequestParametersAsStringUrl();
        }

        try {
            return new URI(uriString);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @NonNull
    @Override
    public WsConnInfo clone() {
        return WsConnInfo.create(hostname, port)
                .scheme(scheme)
                .path(path)
                .setRequestBody(requestBody)
                .setRequestParameters(requestParameters)
                .build();
    }

    public static class WsConnInfoBuilder {
        private final WsConnInfo wsConInfo;

        public WsConnInfoBuilder(String hostname, int port) {
            wsConInfo = new WsConnInfo();
            wsConInfo.hostname = hostname;
            wsConInfo.port = port;
        }

        public WsConnInfoBuilder path(String path) {
            wsConInfo.path = path;
            return this;
        }

        public WsConnInfoBuilder scheme(String scheme) {
            wsConInfo.scheme = scheme;
            return this;
        }

        public WsConnInfoBuilder setRequestBody(Map<String, Object> requestBody) {
            wsConInfo.requestBody.putAll(requestBody);
            return this;
        }

        public WsConnInfoBuilder addRequestParameter(String key, String value) {
            wsConInfo.requestParameters.put(key, value);
            return this;
        }

        public WsConnInfoBuilder setRequestParameters(Map<String, String> requestParameters) {
            wsConInfo.requestParameters.putAll(requestParameters);
            return this;
        }

        public WsConnInfo build() {
            return wsConInfo;
        }

    }

    private String getRequestParametersAsStringUrl() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> requestParam : requestParameters.entrySet()) {
            builder.append(requestParam.getKey())
                    .append("=")
                    .append(requestParam.getValue())
                    .append("&");
        }
        // Remove last & character
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

}
