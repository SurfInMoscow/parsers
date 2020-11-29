package ru.voroby.plugins.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonSelenium {
    private static ChromeOptions chromeOptions;
    private WebDriver chromeDriver;
    private WebDriverWait waitChrome;

    static {
        chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "/Users/vorobyev/Tools/selenium/chromedriver/86.0.4240.22/chromedriver");
        chromeOptions.addArguments("--headless");
    }

    public CommonSelenium() {
        this.chromeDriver = new ChromeDriver(chromeOptions);
        this.waitChrome = new WebDriverWait(chromeDriver, 10L);
    }

    public WebDriver getChromeDriver() {
        return chromeDriver;
    }

    public void closeDriver() {
        chromeDriver.quit();
    }
}
