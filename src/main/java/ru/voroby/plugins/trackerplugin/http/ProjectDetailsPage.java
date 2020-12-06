package ru.voroby.plugins.trackerplugin.http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voroby.plugins.common.BasicHttpGet;
import ru.voroby.plugins.common.CommonHttpClient;

import java.io.IOException;

@Service
public class ProjectDetailsPage {
    private final BasicHttpGet basicHttpGet;
    private String html;

    @Autowired
    public ProjectDetailsPage(BasicHttpGet basicHttpGet) {
        this.basicHttpGet = basicHttpGet;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String execute(String url, CommonHttpClient commonHttpClient) throws IOException {
        Document document = Jsoup.parse(html);
        Elements trElements = document.getElementsByTag("tr");

        for (Element trElement : trElements) {
            Elements tdElements = trElement.getElementsByTag("td");
            for (Element tdElement : tdElements) {
                if (tdElement.text().equals("Google ML")) {
                    Elements aElements = trElement.getElementsByTag("a");
                    url = url.concat(aElements.get(0).attributes().get("href"));
                    break;
                }
            }
        }

        return basicHttpGet.get(url, commonHttpClient);
    }

}
