package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class JiraFormCreateTask {

    private final SelenideElement comboboxTypeTask = $x("//label[contains(text(),'Тип задачи')]/..//input[@role='combobox']").as("Комбобокс 'Тип задачи'");
    private final SelenideElement inputTheme = $x("//label[contains(text(), Тема)]/../input[@id='summary']").as("Поисковая строка");
    private final SelenideElement btnCreate = $x("//input[@value='Создать']").as("Кнопка 'Создать'");

    private SelenideElement liTypeTask(String status) {
        return $x("//ul[@class='aui-last']/li[contains(@id, '" + status + "')]").as("Ссылка на выбранный элемент статуса задачи");
    }

    public void createTask(String status, String theme) {
        comboboxTypeTask.click();

        if (!Objects.equals(comboboxTypeTask.getValue(), status)) {
            liTypeTask(status.toLowerCase()).click();
        }

        inputTheme.setValue(theme);
        btnCreate.click();
    }


}
