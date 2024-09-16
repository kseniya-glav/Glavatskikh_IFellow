package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class JiraPageProject {

    private final SelenideElement refProject = $$x("//a[@id='browse_link']").get(0);
    private final SelenideElement currentProject = $$x("//div[@id='project_current']//a[contains(text(),'TEST')]").get(0);
    private final SelenideElement tabTasks = $$x(("//section[@id='sidebar']//span[@title='Задачи']/parent::a")).get(0);
    private final SelenideElement tasks = $$x("//div[@class='pager']/div[@class='showing']/span").get(0);
    private final SelenideElement inputSearch = $$x("//input[@name='searchString']").get(0);
    private final SelenideElement butCreateTasks = $$x("//button[contains(text(),'Создать задачу')]").get(0);
    private final SelenideElement butTypeTasks = $$x("//button[@aria-label='Выбрать тип задачи']").get(0);
    private final SelenideElement butTypeTap = $$x("//ul[@class='aui-list-truncate']//a[contains(text(),'Задача')]").get(0);
    private final SelenideElement inputDesc = $$x("//textarea[@name='summary']").get(0);
    private final SelenideElement navCreate = $$x("//a[@id='create_link']").get(0);

    public JiraPageProject refProject() {
        refProject.click();
        currentProject.click();
        tabTasks.click();
        return this;
    }

    public int taskCount() {

        return Integer.parseInt(tasks.innerText().split(" ")[2]);
    }

    public JiraPageProject createTasks() {
        butCreateTasks.click();
        butTypeTasks.click();
        butTypeTap.click();
        inputDesc.setValue("Тестовый кот");
        inputDesc.pressEnter();
        tabTasks.click();

        return this;
    }

    public JiraPageProject searchTask(String str) {
        inputSearch.setValue(str);
        SelenideElement res = $$x("//span[@aria-tooltip='" + str + "']/parent::a").get(0);
        res.click();
        return this;
    }


    public JiraPageProject createBug(String status, String theme) {
        navCreate.click();
        JiraFormCreateTask formCreateTask = new JiraFormCreateTask();

        formCreateTask.createTask(status, theme);

        return this;
    }


}
