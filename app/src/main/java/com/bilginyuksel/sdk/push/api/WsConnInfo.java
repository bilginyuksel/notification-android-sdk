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

    public static WsConnInfoDTOBuilder create(String hostname, int port) {
        return new WsConnInfoDTOBuilder(hostname, port);
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

    public static class WsConnInfoDTOBuilder {
        private final WsConnInfo dto;

        public WsConnInfoDTOBuilder(String hostname, int port) {
            dto = new WsConnInfo();
            dto.hostname = hostname;
            dto.port = port;
        }

        public WsConnInfoDTOBuilder path(String path) {
            dto.path = path;
            return this;
        }

        public WsConnInfoDTOBuilder scheme(String scheme) {
            dto.scheme = scheme;
            return this;
        }

        public WsConnInfoDTOBuilder setRequestBody(Map<String, Object> requestBody) {
            dto.requestBody.putAll(requestBody);
            return this;
        }

        public WsConnInfoDTOBuilder addRequestParameter(String key, String value) {
            dto.requestParameters.put(key, value);
            return this;
        }

        public WsConnInfoDTOBuilder setRequestParameters(Map<String, String> requestParameters) {
            dto.requestParameters.putAll(requestParameters);
            return this;
        }

        public WsConnInfo build() {
            return dto;
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
