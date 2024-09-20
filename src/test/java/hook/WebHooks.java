package hook;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.Props;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeAll
    public static void initBrowser() {

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

    @AfterAll
    public static void closeBrowser() {
        WebDriverRunner.closeWebDriver();
    }
}
