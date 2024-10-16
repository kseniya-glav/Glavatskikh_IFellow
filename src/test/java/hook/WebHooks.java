package hook;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.Props;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    private static final Logger logger = LoggerFactory.getLogger(WebHooks.class);

    @BeforeEach
    @Step("Браузер открыт")
    public void initBrowser() throws IOException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors-spki-list");
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver;

        //обработка наличия пропсы и возможности запустить драйвер
        try {
            if (Props.props.pathChromeDriver() != null && !Props.props.pathChromeDriver().isEmpty()) {
                System.setProperty("webdriver.chrome.driver", Props.props.pathChromeDriver());
            }
            driver = new ChromeDriver(options);
        } catch (WebDriverException e) {
            System.out.println("Не удалось запустить " + Props.props.pathChromeDriver());

            System.clearProperty("webdriver.chrome.driver");
            logger.error("Ошибка: {}", e.getMessage(), e);
            driver = new ChromeDriver(options);
        }

        WebDriverRunner.setWebDriver(driver);
        Selenide.open(Props.props.url());


    }

    public static void Refresh() {
        getWebDriver().navigate().refresh();
    }

    public static WebDriverWait Wait() {
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
    }

    @AfterEach
    @Step("Браузер закрыт")
    public void closeBrowser() {
        WebDriverRunner.closeWebDriver();
    }
}
