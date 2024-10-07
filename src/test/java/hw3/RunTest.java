package hw3;

import config.Props;
import hook.WebHooks;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import steps.TestStep;

import java.time.Duration;


public class RunTest extends WebHooks {


    private final TestStep testStep = new TestStep();

    @Test
    @DisplayName("Аутентификация")
    @Description("Аутентификация")
    public void checkAuth() {

        Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });
    }

    @Test
    @DisplayName("Переход во вкладку 'Проекты'")
    @Description("Переход во вкладку 'Проекты'")
    public void goProject() {

        Assertions.assertTimeout(Duration.ofSeconds(20), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });

        Assertions.assertTimeout(Duration.ofSeconds(10), () -> testStep.goProject(Props.props.curProject()));

    }

    @Test
    @DisplayName("Создание задачи во вкладке 'Проекты.Задачи'")
    @Description("Создание задачи во вкладке 'Проекты.Задачи'")
    public void createTasks() {

        Assertions.assertTimeout(Duration.ofSeconds(20), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });

        Assertions.assertTimeout(Duration.ofSeconds(10), () -> testStep.goProject(Props.props.curProject()));

        int countBefore = testStep.taskCount();
        testStep.createTasks(Props.props.nameCreateTask());
        Refresh();
        int countAfter = testStep.taskCount();

        Assertions.assertEquals(countAfter - countBefore, 1);

    }

    @Test
    @DisplayName("Поиск нужной задачи и её проверка на статус и версию")
    @Description("Поиск нужной задачи и её проверка на статус и версию")
    public void goTask() {

        Assertions.assertTimeout(Duration.ofSeconds(20), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });
        Wait().until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        testStep.goTask(Props.props.nameTask());

        Assertions.assertAll("Статус задачи и версия",
                () -> Assertions.assertEquals(testStep.statusTask(), Props.props.statusTask()),
                () -> Assertions.assertEquals(testStep.fixVersionTask(), Props.props.fixVersion())
        );
    }

    @Test
    @DisplayName("Создание бага и проведение по всем статусам")
    @Description("Создание бага и проведение по всем статусам")
    public void createBug() {

        Assertions.assertTimeout(Duration.ofSeconds(20), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });

        testStep.createBug(Props.props.typeTask(), Props.props.theme());
        Refresh();
        testStep.goTask(Props.props.theme());

        testStep.statusAtWork();
        Wait().until(ExpectedConditions.textToBe(testStep.statusTaskBy(), Props.props.atWork()));
        Assertions.assertEquals(testStep.statusTask().toLowerCase(), Props.props.atWork().toLowerCase());

        testStep.statusDone();
        Wait().until(ExpectedConditions.textToBe(testStep.statusTaskBy(), Props.props.done()));
        Assertions.assertEquals(testStep.statusTask().toLowerCase(), Props.props.done().toLowerCase());


    }
}
