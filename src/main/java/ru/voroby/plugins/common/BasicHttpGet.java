package ru.voroby.plugins.common;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BasicHttpGet {

    private final Logger log = LoggerFactory.getLogger(BasicHttpGet.class);

    public String get(String url, CommonHttpClient commonHttpClient) throws IOException {
        CloseableHttpClient httpclient = commonHttpClient.getHttpclient();
        BasicCookieStore cookieStore = commonHttpClient.getCookieStore();
        byte[] bytes;
        String page;
        HttpGet httpget = new HttpGet(url);

        try (final CloseableHttpResponse response = httpclient.execute(httpget)) {
            final HttpEntity entity = response.getEntity();
            StringBuilder builder = new StringBuilder();
            Arrays.stream(response.getHeaders()).forEach(header -> builder.append("Header - ")
                    .append(header.getName())
                    .append(", value - ")
                    .append(header.getValue()).append("\n"));
            log.debug(builder.toString());
            bytes = entity.getContent().readAllBytes();
            page = new String(bytes, 0, bytes.length);

            log.info("GET " + url + " : " + response.getCode() + " " + response.getReasonPhrase());

            StringBuilder builder1 = new StringBuilder("Initial set of cookies:\n");
            List<Cookie> cookies = cookieStore.getCookies();
            if (!cookies.isEmpty()) {
                cookies.forEach(cookie -> builder1.append("- ").append(cookie).append("\n"));
            }
            log.info(builder1.toString());
        }

        return page;
    }
}
