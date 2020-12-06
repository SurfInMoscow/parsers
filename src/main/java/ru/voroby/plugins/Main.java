package ru.voroby.plugins;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.voroby.plugins.config.Config;
import ru.voroby.plugins.trackerplugin.TrackerParserPlugin;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        TrackerParserPlugin trackerParserPlugin = ctx.getBean(TrackerParserPlugin.class);
        trackerParserPlugin.parse(Urls.URL_TRACKER);
    }
}
