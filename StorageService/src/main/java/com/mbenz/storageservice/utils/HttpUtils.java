package com.mbenz.storageservice.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils {

    public static HttpResponse<String> makeGetCall(String url) throws Exception {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(url));
            HttpRequest request = requestBuilder.build();
            return HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        }catch (Exception e){
            throw new Exception(e.getMessage(), e);
        }
    }
}
