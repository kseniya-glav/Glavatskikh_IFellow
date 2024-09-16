package config;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeAll
    public static void initBrowser() {
        System.out.println("Тест-кейс начат");
        Configuration.browser = Browsers.CHROME;
        Selenide.open("https://edujira.ifellow.ru/");
        getWebDriver().manage().window().maximize();

        Configuration.holdBrowserOpen = true;
    }

    @AfterAll
    public static void closeBrowser() {
        System.out.println("Тест-кейс выполнен");
    }
}
