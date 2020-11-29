package ru.voroby.plugins.trackerplugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.voroby.plugins.common.CommonHttpClient;
import ru.voroby.plugins.trackerplugin.html.IssuesOnPageParser;
import ru.voroby.plugins.trackerplugin.http.LoginForm;
import ru.voroby.plugins.trackerplugin.http.ProjectDetailsPage;
import ru.voroby.plugins.trackerplugin.to.IssueTo;

import java.io.IOException;
import java.util.List;

public class TrackerParserPlugin {
    private static final Logger log = LoggerFactory.getLogger(TrackerParserPlugin.class);
    private final CommonHttpClient commonHttpClient = new CommonHttpClient();
    private final LoginForm loginForm = new LoginForm();
    private final ProjectDetailsPage projectDetailsPage = new ProjectDetailsPage();
    private final IssuesOnPageParser issuesOnPageParser = new IssuesOnPageParser();

    public void parse(String url) throws IOException {
        String loginPageHtml = loginForm.execute(url, commonHttpClient);
        log.debug(loginPageHtml);
        projectDetailsPage.setHtml(loginPageHtml);
        String projectDetailsPage = this.projectDetailsPage.execute(url, commonHttpClient);
        log.debug(projectDetailsPage);
        List<IssueTo> parseResult = issuesOnPageParser.parse(projectDetailsPage);
        parseResult.forEach(System.out::println);
        commonHttpClient.getHttpclient().close();
    }
}