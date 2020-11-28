package ru.voroby.plugins.common;

import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class BasicHttpPost {

    public String post(String url, List<NameValuePair> pairs, CommonHttpClient commonHttpClient) throws IOException {
        CloseableHttpClient httpclient = commonHttpClient.getHttpclient();
        BasicCookieStore cookieStore = commonHttpClient.getCookieStore();
        byte[] bytes;
        String page;

        URI uri = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (pairs != null) {
                uriBuilder.addParameters(pairs);
            }
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ClassicHttpRequest post = ClassicRequestBuilder.
                post(uri)
                .build();

        try (final CloseableHttpResponse response = httpclient.execute(post)) {
            final HttpEntity entity = response.getEntity();
            Arrays.stream(response.getHeaders()).forEach(header -> System.out.println("Header - " + header.getName() + ", value - " + header.getValue()));
            bytes = entity.getContent().readAllBytes();
            page = new String(bytes, 0, bytes.length);

            System.out.println("POST " + url + " :" + response.getCode() + " " + response.getReasonPhrase());

            System.out.println("Initial set of cookies:");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                cookies.forEach(cookie -> System.out.println("- " + cookie));
            }

            return page;
        }
    }
}
