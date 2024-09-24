package hook;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.Props;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class WebHooks {

    @Given("^пользователь открывает браузер \"([^\"]*)\"$")
    public void initBrowser(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors-spki-list");
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open(Props.props.getProperty(url));
    }


    public void refresh() {
        getWebDriver().navigate().refresh();
    }

    public WebDriverWait Wait() {
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
    }

    public String pageUrl() {
        return getWebDriver().getCurrentUrl();
    }

    @Then("браузер закрывается")
    public void closeBrowser() {
        WebDriverRunner.closeWebDriver();
    }

}
