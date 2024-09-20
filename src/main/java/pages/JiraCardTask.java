package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class JiraCardTask {

    private final SelenideElement status = $x("//span[@id='status-val']");
    private final SelenideElement fixVersion = $x("//span[@id='fixVersions-field']");
    private final SelenideElement btnStatusAtWork = $x("//span[contains(text(),'В работе')]/..");
    private final SelenideElement btnBusProc = $x("//span[contains(text(),'Бизнес-процесс')]/..");
    private final SelenideElement btnStatusDone = $x("//span[contains(text(),'Выполнено')]/..");

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