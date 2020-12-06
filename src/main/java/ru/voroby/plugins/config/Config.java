package ru.voroby.plugins.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.voroby.plugins.common",
        "ru.voroby.plugins.trackerplugin"})
public class Config {
}
