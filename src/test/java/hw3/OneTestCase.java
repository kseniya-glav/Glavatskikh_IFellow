package hw3;

import config.Props;
import hook.WebHooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.JiraCardTask;
import pages.JiraPageAuth;
import pages.JiraPageProject;
import steps.TestStep;

import java.time.Duration;


public class OneTestCase extends WebHooks {

    private final JiraPageAuth jiraPageAuth = new JiraPageAuth();
    private final JiraPageProject jiraPageProject = new JiraPageProject();
    private final JiraCardTask jiraCardTask = new JiraCardTask();
    private final TestStep testStep = new TestStep();

    @Test
    public void checkAuth() {
        System.out.println("1 Тест входа в Jira");

        Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });
    }

    @Test
    public void goProject() {
        System.out.println("2 Тест перехода во вкладку 'Проекты'");

        Assertions.assertTimeout(Duration.ofSeconds(20), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });

        Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
            testStep.goProject();
            testStep.refProject();
        });

    }

    @Test
    public void createTasks() {
        System.out.println("3 Тест создания задачи во вкладке 'Проекты' -> 'Задачи'");

        Assertions.assertTimeout(Duration.ofSeconds(20), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });

        Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
            testStep.goProject();
            testStep.refProject();
        });

        int countBefore = testStep.taskCount();
        testStep.createTasks();
        int countAfter = testStep.taskCount();

        Assertions.assertEquals(countAfter - countBefore, 1);

    }

    @Test
    public void goTask() {
        System.out.println("4 Тест перехода к задаче '" + Props.props.nameTask() + "'");

        Assertions.assertTimeout(Duration.ofSeconds(20), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });

        Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
            testStep.goProject();
            testStep.refProject();
        });

        int countBefore = testStep.taskCount();
        testStep.createTasks();
        int countAfter = testStep.taskCount();

        Assertions.assertEquals(countAfter - countBefore, 1);


        testStep.goTask(Props.props.nameTask());

        System.out.println("Проверка задачи на статус '" + Props.props.statusTask() + "' и версию '" + Props.props.fixVersion() + "'");
        Assertions.assertAll("Статус задачи и версия",
                () -> Assertions.assertEquals(testStep.statusTask(), Props.props.statusTask()),
                () -> Assertions.assertEquals(testStep.fixVersionTask(), Props.props.fixVersion())
        );
    }

    @Test
    public void createBug() {
        System.out.println("5 Тест создания бага и проведения по всем статусам");

        Assertions.assertTimeout(Duration.ofSeconds(20), () -> {
            testStep.checkAuth(Props.props.userLogin(), Props.props.userPassword());
        });

        Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
            testStep.goProject();
            testStep.refProject();
        });

        int countBefore = testStep.taskCount();
        testStep.createTasks();
        int countAfter = testStep.taskCount();

        Assertions.assertEquals(countAfter - countBefore, 1);


        testStep.goTask(Props.props.nameTask());

        System.out.println("Проверка задачи на статус '" + Props.props.statusTask() + "' и версию '" + Props.props.fixVersion() + "'");
        Assertions.assertAll("Статус задачи и версия",
                () -> Assertions.assertEquals(testStep.statusTask(), Props.props.statusTask()),
                () -> Assertions.assertEquals(testStep.fixVersionTask(), Props.props.fixVersion())
        );

        testStep.createBug(Props.props.typeTask(), Props.props.theme());
        WebHooks.Refresh();
        testStep.goTask(Props.props.theme());

        testStep.statusAtWork();
        Wait().until(ExpectedConditions.textToBe(testStep.statusTaskBy(), "В РАБОТЕ"));
        Assertions.assertEquals(testStep.statusTask().toLowerCase(), "в работе");

        testStep.statusDone();
        Wait().until(ExpectedConditions.textToBe(testStep.statusTaskBy(), "ГОТОВО"));
        Assertions.assertEquals(testStep.statusTask().toLowerCase(), "готово");


    }
}
