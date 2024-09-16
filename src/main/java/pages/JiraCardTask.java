package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class JiraCardTask {

    private final SelenideElement status = $$x("//span[@id='status-val']").get(0);
    private final SelenideElement fixVersion = $$x("//span[@id='fixVersions-field']").get(0);
    private final SelenideElement btnStatusDo = $$x("//span[contains(text(),'сделать')]/parent::a").get(0);
    ;
    private final SelenideElement btnStatusAtWork = $$x("//span[contains(text(),'В работе')]/parent::a").get(0);
    private final SelenideElement btnBusProc = $$x("//span[contains(text(),'Бизнес-процесс')]/parent::a").get(0);
    private final SelenideElement btnStatusDone = $$x("//span[contains(text(),'Выполнено')]/parent::a").get(0);

    public String status() {
        return status.innerText();
    }

    public String fixVersion() {
        return fixVersion.innerText();
    }

    public JiraCardTask statusAtWork() {
        btnStatusAtWork.click();

        return this;
    }

    public JiraCardTask statusDone() {
        btnBusProc.click();
        btnStatusDone.click();

        return this;
    }
}
