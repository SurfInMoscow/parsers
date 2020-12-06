package ru.voroby.plugins.common;

import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Класс для использования в рамках плагина-парсера.
 * Возможность использовать cookies на всех этапах работы плагина парсера.
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class CommonHttpClient {
    private final BasicCookieStore cookieStore = new BasicCookieStore();
    private CloseableHttpClient httpclient;

    public CommonHttpClient() {
        this.httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
    }

    public CloseableHttpClient getHttpclient() {
        return httpclient;
    }

    public BasicCookieStore getCookieStore() {
        return cookieStore;
    }
}
