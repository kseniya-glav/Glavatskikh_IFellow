package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class JiraPageProject {

    private final SelenideElement refProject = $x("//a[@id='browse_link']").as("link go project");
    private final SelenideElement tabTasks = $x(("//section[@id='sidebar']//span[@title='Задачи']/..")).as("tab tasks");
    private final SelenideElement tasks = $x("//div[@class='pager']/div[@class='showing']/span").as("tasks");
    private final SelenideElement inputSearch = $x("//input[@name='searchString']").as("input Search");
    private final SelenideElement butCreateTasks = $x("//button[contains(text(),'Создать задачу')]").as("button create task");
    private final SelenideElement butTypeTasks = $x("//button[@aria-label='Выбрать тип задачи']").as("button open tasks type");
    private final SelenideElement butTypeTap = $x("//ul[@class='aui-list-truncate']//a[contains(text(),'Задача')]").as("button task type");
    private final SelenideElement inputDesc = $x("//textarea[@name='summary']").as("input description");
    private final SelenideElement navCreate = $x("//a[@id='create_link']").as("link create task");


    private final By itemSearch = By.xpath("//li[@class='quick-search-result-item'][1]/a[@class='icon-container']/..");

    private final SelenideElement currentProject(String str) {
        return $x("//div[@id='project_current']//a[contains(text(),'" + str + "')]").as("current project");
    }


    private SelenideElement res(String str) {
        return $x("//span[@aria-tooltip='" + str + "']/..").as("search task");
    }

    public JiraPageProject refProject(String str) {
        refProject.click();
        currentProject(str).click();
        tabTasks.click();
        return this;
    }

    public int taskCount() {
        return Integer.parseInt(tasks.innerText().split(" ")[2]);
    }

    public JiraPageProject createTasks(String str) {
        butCreateTasks.click();
        butTypeTasks.click();
        butTypeTap.click();
        inputDesc.setValue(str);
        inputDesc.pressEnter();
        tabTasks.click();

        return this;
    }

    public JiraPageProject searchTask(String str) {
        inputSearch.setValue(str);
        sleep(10000);
        res(str).click();
        return this;
    }


    public JiraPageProject createBug(String status, String theme) {
        navCreate.click();
        JiraFormCreateTask formCreateTask = new JiraFormCreateTask();

        formCreateTask.createTask(status, theme);

        return this;
    }

    public By itemSearchBy() {
        return itemSearch;
    }

}
