package steps;

import config.Props;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.JiraCardTask;
import pages.JiraPageAuth;
import pages.JiraPageProject;

import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.sleep;

public class TestStep {

    private final JiraPageAuth jiraPageAuth = new JiraPageAuth();
    private final JiraPageProject jiraPageProject = new JiraPageProject();
    private final JiraCardTask jiraCardTask = new JiraCardTask();


    @Given("^пользователь на странице входа$")
    public void userGoAuthPage() {
        if (!jiraPageAuth.loginForm())
            throw new io.cucumber.java.PendingException();
    }

    @Then("^пользователь вводит логин \"([^\"]*)\" и пароль \"([^\"]*)\"$")
    public void userEnterLoginAndPassword(String login, String password) {
        jiraPageAuth.inputUserName(Props.props.getProperty(login));
        jiraPageAuth.inputUserPass(Props.props.getProperty(password));
        jiraPageAuth.inputLogin();
    }


    @And("^пользователь переходит к проекту \"([^\"]*)\"$")
    public void userGoProject(String str) {
        jiraPageProject.refProject(Props.props.getProperty(str));
    }

    @And("^пользователь создаёт задачу \"([^\"]*)\"$")
    public void userCreateTask(String str) {
        int countBefore = jiraPageProject.taskCount();
        jiraPageProject.createTasks(Props.props.getProperty(str));
        sleep(5000);
        int countAfter = jiraPageProject.taskCount();

        if (!(countAfter - countBefore == 1))
            throw new io.cucumber.java.PendingException();
    }


    @And("^пользователь переходит к задаче \"([^\"]*)\"$")
    public void userGoTask(String str) {
        jiraPageProject.searchTask(Props.props.getProperty(str));
    }

    @And("^статус задачи \"([^\"]*)\"$")
    public void checkStatusTask(String str) {
        if (!jiraCardTask.status().trim().contains(Props.props.getProperty(str)))
            throw new io.cucumber.java.PendingException();
    }

    @And("^версия задачи \"([^\"]*)\"$")
    public void fixVersionTask(String str) {
        if (!jiraCardTask.fixVersion().trim().contains(Props.props.getProperty(str)))
            throw new io.cucumber.java.PendingException();
    }


    @And("^пользователь создаёт задачу с типом \"([^\"]*)\" и с темой \"([^\"]*)\"$")
    public void createTaskWithTypeAndTheme(String typeTask, String themeTask) {
        jiraPageProject.createBug(Props.props.getProperty(typeTask), Props.props.getProperty(themeTask));
    }

    @And("^пользователь переводит задачу в статус \"([^\"]*)\"$")
    public void statusTaskEdit(String str) {
        if (str.toLowerCase().contains("work"))
            jiraCardTask.statusAtWork();
        else if (str.toLowerCase().contains("done"))
            jiraCardTask.statusDone();

        Wait().until(ExpectedConditions.textToBe(jiraCardTask.statusTaskBy(), Props.props.getProperty(str)));

        if (!jiraCardTask.status().trim().toLowerCase().contains(Props.props.getProperty(str).toLowerCase()))
            throw new io.cucumber.java.PendingException();
    }

}
