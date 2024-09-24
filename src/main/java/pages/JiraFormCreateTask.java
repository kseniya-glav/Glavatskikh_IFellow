package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class JiraFormCreateTask {

    private final SelenideElement comboboxTypeTask = $x("//label[contains(text(),'Тип задачи')]/..//input[@role='combobox']").as("open combobox task type");
    private final SelenideElement inputTheme = $x("//label[contains(text(), Тема)]/../input[@id='summary']").as("input task theme");
    private final SelenideElement btnCreate = $x("//input[@value='Создать']").as("button Create");

    private SelenideElement liTypeTask(String status) {
        return $x("//ul[@class='aui-last']/li[contains(@id, '" + status + "')]");
    }

    public void createTask(String status, String theme) {
        comboboxTypeTask.click();

        if (!Objects.equals(comboboxTypeTask.getValue(), status)) {
            liTypeTask(status.toLowerCase()).click();
        }

        inputTheme.setValue(theme);
        btnCreate.click();
        sleep(3000);
    }


}
