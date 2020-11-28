package ru.voroby.plugins.trackerplugin.http;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import ru.voroby.plugins.common.BasicHttpGet;
import ru.voroby.plugins.common.BasicHttpPost;
import ru.voroby.plugins.common.CommonHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginForm {
    private final BasicHttpGet basicHttpGet = new BasicHttpGet();
    private final BasicHttpPost basicHttpPost = new BasicHttpPost();

    public String execute(String url, CommonHttpClient commonHttpClient) throws IOException {
        String loginPrefix = "tracker";
        String loginUrl = url.concat(loginPrefix);
        String loginPage = basicHttpGet.get(loginUrl, commonHttpClient);
        System.out.println(loginPage);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "user@ya.ru"));
        params.add(new BasicNameValuePair("password", "password"));

        return basicHttpPost.post(loginUrl, params, commonHttpClient);
    }
}