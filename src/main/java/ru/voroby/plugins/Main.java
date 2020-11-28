package ru.voroby.plugins;

import ru.voroby.plugins.trackerplugin.TrackerParserPlugin;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TrackerParserPlugin trackerParserPlugin = new TrackerParserPlugin();
        trackerParserPlugin.parse(Urls.URL_TRACKER);
    }
}
