package steps;

import org.openqa.selenium.By;
import pages.JiraCardTask;
import pages.JiraPageAuth;
import pages.JiraPageProject;

public class TestStep {

    private final JiraPageAuth jiraPageAuth = new JiraPageAuth();
    private final JiraPageProject jiraPageProject = new JiraPageProject();
    private final JiraCardTask jiraCardTask = new JiraCardTask();

    public void checkAuth(String login, String password) {
        jiraPageAuth.inputUserName(login);
        jiraPageAuth.inputUserPass(password);
        jiraPageAuth.inputLogin();
    }

    public void goProject() {
        jiraPageProject.refProject();
    }

    public void createTasks() {
        jiraPageProject.createTasks();

    }

    public void goTask(String nameTask) {
        jiraPageProject.searchTask(nameTask);
    }

    public String statusTask() {
        return jiraCardTask.status().trim();
    }

    public String fixVersionTask() {
        return jiraCardTask.fixVersion().trim();
    }

    public void createBug(String typeTask, String theme) {
        jiraPageProject.createBug(typeTask, theme);
    }

    public void statusAtWork() {
        jiraCardTask.statusAtWork();
    }

    public void statusDone() {
        jiraCardTask.statusDone();
    }

    public By statusTaskBy() {
        return jiraCardTask.statusTaskBy();
    }

    public void refProject() {
        jiraPageProject.refProject();
    }

    public int taskCount() {
        return jiraPageProject.taskCount();
    }

}
