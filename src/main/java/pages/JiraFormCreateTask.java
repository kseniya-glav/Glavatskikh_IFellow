package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$x;

public class JiraFormCreateTask {

    private final SelenideElement comboboxTypeTask = $$x("//label[contains(text(),'Тип задачи')]/parent::div//input[@role='combobox']").get(0);
    private final SelenideElement inputTheme = $$x("//label[contains(text(), Тема)]/parent::div/input[@id='summary']").get(0);
    private final SelenideElement btnCreate = $$x("//input[@value='Создать']").get(0);

    public void createTask(String status, String theme) {
        comboboxTypeTask.click();

        if (!Objects.equals(comboboxTypeTask.getValue(), status)) {
            SelenideElement liTypeTask = $$x("//ul[@class='aui-last']/li[contains(@id, '" + status.toLowerCase() + "')]").get(0);
            liTypeTask.click();
        }

        inputTheme.setValue(theme);
        btnCreate.click();
    }


}
