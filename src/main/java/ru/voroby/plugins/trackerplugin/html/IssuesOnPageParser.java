package ru.voroby.plugins.trackerplugin.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.voroby.plugins.trackerplugin.to.IssueTo;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssuesOnPageParser {
    public List<IssueTo> parse(String html) {
        Document document = Jsoup.parse(html);
        Elements tbodyElements = document.getElementsByClass("text-left");
        List<Element> tdElements = new ArrayList<>();
        tbodyElements.forEach(tbodyElement -> tdElements.addAll(tbodyElement.getElementsByTag("td")));

        List<String> values = new ArrayList<>();

        int n = 0;
        for (Element tdElement : tdElements) {
            if (n == 4) {
                n = 0;
                continue;
            }
            values.add(tdElement.text());
            n++;
        }

        List<IssueTo> issues = new ArrayList<>();
        IssueTo issue = new IssueTo();
        for (String value: values) {
            if (n == 4) {
                n = 0;
                issues.add(issue);
                issue = new IssueTo();
            }

            switch (n) {
                case 0:
                    issue.setDateTime(value);
                    break;
                case 1:
                    issue.setName(value);
                    break;
                case 2:
                    issue.setPriority(value);
                    break;
                case 3:
                    issue.setStatus(value);
                    break;
                default:
            }
            n++;
        }
        issues.add(issue);

        return issues;
    }
}
