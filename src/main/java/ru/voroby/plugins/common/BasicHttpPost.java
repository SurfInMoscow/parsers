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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Component
public class BasicHttpPost {

    private final Logger log = LoggerFactory.getLogger(BasicHttpPost.class);

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
            StringBuilder builder = new StringBuilder();
            Arrays.stream(response.getHeaders()).forEach(header -> builder.append("Header - ")
                    .append(header.getName())
                    .append(", value - ")
                    .append(header.getValue()).append("\n"));
            log.debug(builder.toString());
            bytes = entity.getContent().readAllBytes();
            page = new String(bytes, 0, bytes.length);

            log.info("POST " + url + " : " + response.getCode() + " " + response.getReasonPhrase());

            StringBuilder builder1 = new StringBuilder("Initial set of cookies:\n");
            List<Cookie> cookies = cookieStore.getCookies();
            if (!cookies.isEmpty()) {
                cookies.forEach(cookie -> builder1.append("- ").append(cookie).append("\n"));
            }
            log.info(builder1.toString());

            return page;
        }
    }
}
