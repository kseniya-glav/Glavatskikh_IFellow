package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class JiraCardTask {

    private final SelenideElement status = $x("//span[@id='status-val']").as("Status task");
    private final SelenideElement fixVersion = $x("//span[@id='fixVersions-field']").as("FixVersion task");
    private final SelenideElement btnStatusAtWork = $x("//span[contains(text(),'В работе')]/..").as("button status task at work");
    private final SelenideElement btnBusProc = $x("//span[contains(text(),'Бизнес-процесс')]/..").as("button task buss-proc");
    private final SelenideElement btnStatusDone = $x("//span[contains(text(),'Выполнено')]/..").as("button status task at done");

    private final By statusTaskBy = By.xpath("//span[@id='status-val']");

    public String status() {
        return status.innerText();
    }

    public String fixVersion() {
        return fixVersion.innerText();
    }

    public void statusAtWork() {
        btnStatusAtWork.click();
    }

    public void statusDone() {
        btnBusProc.click();
        btnStatusDone.click();
    }

    public By statusTaskBy() {
        return statusTaskBy;
    }
}
