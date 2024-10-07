package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.JiraCardTask;
import pages.JiraPageAuth;
import pages.JiraPageProject;

public class TestStep {

    private final JiraPageAuth jiraPageAuth = new JiraPageAuth();
    private final JiraPageProject jiraPageProject = new JiraPageProject();
    private final JiraCardTask jiraCardTask = new JiraCardTask();

    @Step("Вход в аккаунт")
    public void checkAuth(String login, String password) {
        jiraPageAuth.inputUserName(login);
        jiraPageAuth.inputUserPass(password);
        jiraPageAuth.inputLogin();
    }

    @Step("Переход к проекту")
    public void goProject(String name) {
        jiraPageProject.refProject(name);
    }

    @Step("Создание задачи")
    public void createTasks(String name) {
        jiraPageProject.createTasks(name);

    }

    @Step("Переход к задаче")
    public void goTask(String nameTask) {
        jiraPageProject.searchTask(nameTask);
    }

    @Step("Статус задачи")
    public String statusTask() {
        return jiraCardTask.status().trim();
    }

    @Step("Версия задачи")
    public String fixVersionTask() {
        return jiraCardTask.fixVersion().trim();
    }

    @Step("Создание бага")
    public void createBug(String typeTask, String theme) {
        jiraPageProject.createBug(typeTask, theme);
    }

    @Step("Перевод задачи в статус 'В работе'")
    public void statusAtWork() {
        jiraCardTask.statusAtWork();
    }

    @Step("Перевод задачи в статус 'Готово'")
    public void statusDone() {
        jiraCardTask.statusDone();
    }

    @Step("Ожидание изменения статуса")
    public By statusTaskBy() {
        return jiraCardTask.statusTaskBy();
    }

    @Step("Количество задач")
    public int taskCount() {
        return jiraPageProject.taskCount();
    }

}
