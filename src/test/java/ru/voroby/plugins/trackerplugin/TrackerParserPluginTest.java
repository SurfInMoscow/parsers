package ru.voroby.plugins.trackerplugin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.voroby.plugins.config.Config;

import java.io.IOException;

import static ru.voroby.plugins.Urls.URL_TRACKER;

@SpringJUnitConfig(classes = {Config.class})
@ContextConfiguration(classes = {Config.class})
class TrackerParserPluginTest {

    @Autowired
    private TrackerParserPlugin plugin;

    @Test
    void parse() throws IOException {
        plugin.parse(URL_TRACKER);
    }
}