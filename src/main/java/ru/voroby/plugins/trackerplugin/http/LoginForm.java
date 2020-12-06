package ru.voroby.plugins.trackerplugin.http;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voroby.plugins.common.BasicHttpGet;
import ru.voroby.plugins.common.BasicHttpPost;
import ru.voroby.plugins.common.CommonHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginForm {
    private final Logger log = LoggerFactory.getLogger(LoginForm.class);

    private BasicHttpGet basicHttpGet;
    private BasicHttpPost basicHttpPost;

    @Autowired
    public LoginForm(BasicHttpGet basicHttpGet, BasicHttpPost basicHttpPost) {
        this.basicHttpGet = basicHttpGet;
        this.basicHttpPost = basicHttpPost;
    }

    public String execute(String url, CommonHttpClient commonHttpClient) throws IOException {
        String loginPrefix = "tracker";
        String loginUrl = url.concat(loginPrefix);
        String loginPage = basicHttpGet.get(loginUrl, commonHttpClient);
        log.debug(loginPage);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "user@ya.ru"));
        params.add(new BasicNameValuePair("password", "password"));

        return basicHttpPost.post(loginUrl, params, commonHttpClient);
    }
}