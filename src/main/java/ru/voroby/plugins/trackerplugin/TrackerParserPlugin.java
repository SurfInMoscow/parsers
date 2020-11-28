package ru.voroby.plugins.trackerplugin;

import ru.voroby.plugins.common.CommonHttpClient;
import ru.voroby.plugins.trackerplugin.http.LoginForm;

import java.io.IOException;

public class TrackerParserPlugin {
    private final CommonHttpClient commonHttpClient = new CommonHttpClient();
    private final LoginForm loginForm = new LoginForm();

    public void parse(String url) throws IOException {
        System.out.println(loginForm.execute(url, commonHttpClient));
        commonHttpClient.getHttpclient().close();
    }
}