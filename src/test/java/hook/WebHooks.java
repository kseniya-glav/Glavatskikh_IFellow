package hook;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.Props;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeEach
    @Step("Браузер открыт")
    public void initBrowser() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors-spki-list");
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver(options);
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
