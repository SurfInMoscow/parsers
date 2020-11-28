package ru.voroby.plugins.common;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BasicHttpGet {

    public String get(String url, CommonHttpClient commonHttpClient) throws IOException {
        CloseableHttpClient httpclient = commonHttpClient.getHttpclient();
        BasicCookieStore cookieStore = commonHttpClient.getCookieStore();
        byte[] bytes;
        String page;
        HttpGet httpget = new HttpGet(url);

        try (final CloseableHttpResponse response = httpclient.execute(httpget)) {
            final HttpEntity entity = response.getEntity();
            Arrays.stream(response.getHeaders()).forEach(header -> System.out.println("Header - " + header.getName() + ", value - " + header.getValue()));
            bytes = entity.getContent().readAllBytes();
            page = new String(bytes, 0, bytes.length);

            System.out.println("GET " + url + " :" + response.getCode() + " " + response.getReasonPhrase());

            System.out.println("Initial set of cookies:");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                cookies.forEach(cookie -> System.out.println("- " + cookie));
            }
        }

        return page;
    }
}
