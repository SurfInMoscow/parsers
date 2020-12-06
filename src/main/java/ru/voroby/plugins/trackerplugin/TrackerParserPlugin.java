package ru.voroby.plugins.trackerplugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voroby.plugins.common.CommonHttpClient;
import ru.voroby.plugins.trackerplugin.html.IssuesOnPageParser;
import ru.voroby.plugins.trackerplugin.http.LoginForm;
import ru.voroby.plugins.trackerplugin.http.ProjectDetailsPage;
import ru.voroby.plugins.trackerplugin.to.IssueTo;

import java.io.IOException;
import java.util.List;

@Service
public class TrackerParserPlugin {
    private static final Logger log = LoggerFactory.getLogger(TrackerParserPlugin.class);

    private CommonHttpClient commonHttpClient;
    private LoginForm loginForm;
    private ProjectDetailsPage projectDetailsPage;
    private IssuesOnPageParser issuesOnPageParser;

    @Autowired
    public TrackerParserPlugin(CommonHttpClient commonHttpClient, LoginForm loginForm, ProjectDetailsPage projectDetailsPage, IssuesOnPageParser issuesOnPageParser) {
        this.commonHttpClient = commonHttpClient;
        this.loginForm = loginForm;
        this.projectDetailsPage = projectDetailsPage;
        this.issuesOnPageParser = issuesOnPageParser;
    }

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