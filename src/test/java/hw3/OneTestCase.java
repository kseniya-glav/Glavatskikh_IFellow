package hw3;

import config.WebHooks;
import org.junit.jupiter.api.*;
import pages.JiraCardTask;
import pages.JiraPageAuth;
import pages.JiraPageProject;

import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OneTestCase extends WebHooks {

    @AfterEach
    public void ResTest() {
        System.out.println("Выполнено");
    }

    private final JiraPageAuth jiraPageAuth = new JiraPageAuth();
    private final JiraPageProject jiraPageProject = new JiraPageProject();
    private final JiraCardTask jiraCardTask = new JiraCardTask();

    private final String userName = "AT9";
    private final String userPass = "Qwerty123";

    private final String nameTask = "TestSeleniumATHomework";
    private final String statusTask = "Сделать";
    private final String fixVersion = "Version 2.0";
    private final String theme = "Провести по всем статусам";
    private final String typeTask = "Ошибка";


    @Test
    @Order(1)
    public void checkAuth() {
        System.out.println("1 Тест входа в Jira");
        jiraPageAuth.inputUserName(userName);
        jiraPageAuth.inputUserPass(userPass);
        Assertions.assertNotNull(jiraPageAuth.inputLogin());
    }

    @Test
    @Order(2)
    public void goProject() {
        System.out.println("2 Тест перехода во вкладку 'Проекты'");
        Assertions.assertNotNull(jiraPageProject.refProject());
    }

    @Test
    @Order(3)
    public void createTasks() throws InterruptedException {
        System.out.println("3 Тест создания задачи во вкладке 'Проекты' -> 'Задачи'");
        int countBefore = jiraPageProject.taskCount();
        jiraPageProject.createTasks();
        TimeUnit.SECONDS.sleep(3);
        int countAfter = jiraPageProject.taskCount();

        Assertions.assertEquals(countAfter - countBefore, 1);

    }

    @Test
    @Order(4)
    public void goTask() throws InterruptedException {
        System.out.println("4 Тест перехода к задаче '" + nameTask + "'");
        jiraPageProject.searchTask(nameTask);
        System.out.println("Проверка задачи на статус '" + statusTask + "' и версию '" + fixVersion + "'");
        Assertions.assertAll("Статус задачи и версия",
                () -> Assertions.assertEquals(jiraCardTask.status().trim(), statusTask),
                () -> Assertions.assertEquals(jiraCardTask.fixVersion().trim(), fixVersion)
        );
    }

    @Test
    @Order(5)
    public void createBug() throws InterruptedException {
        System.out.println("5 Тест создания бага и проведения по всем статусам");
        jiraPageProject.createBug(typeTask, theme);

        TimeUnit.SECONDS.sleep(3);
        jiraPageProject.searchTask(theme);

        jiraCardTask.statusAtWork();
        TimeUnit.SECONDS.sleep(3);
        Assertions.assertEquals(jiraCardTask.status().trim().toLowerCase(), "в работе");

        TimeUnit.SECONDS.sleep(3);
        jiraCardTask.statusDone();
        TimeUnit.SECONDS.sleep(3);
        Assertions.assertEquals(jiraCardTask.status().trim().toLowerCase(), "готово");

    }
}
