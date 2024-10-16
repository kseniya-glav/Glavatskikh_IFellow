package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraPageProject {

    private final SelenideElement refProject = $x("//a[@id='browse_link']").as("Ссылка просмотра проектов");
    private final SelenideElement tabTasks = $x(("//section[@id='sidebar']//span[@title='Задачи']/..")).as("Вкладка 'Задачи' на проекте");
    private final SelenideElement tasks = $x("//div[@class='pager']/div[@class='showing']/span").as("Счётчик задач");
    private final SelenideElement inputSearch = $x("//input[@name='searchString']").as("Поисковая строка");
    private final SelenideElement butCreateTasks = $x("//button[contains(text(),'Создать задачу')]").as("Кнопка 'Создать задачу'");
    private final SelenideElement butTypeTasks = $x("//button[@aria-label='Выбрать тип задачи']").as("Кнопка 'Выбрать тип задачи'");
    private final SelenideElement butTypeTap = $x("//ul[@class='aui-list-truncate']//a[contains(text(),'Задача')]").as("Выбор типа задачи 'Задача'");
    private final SelenideElement inputDesc = $x("//textarea[@name='summary']").as("Поле ввода описания задачи");
    private final SelenideElement navCreate = $x("//a[@id='create_link']").as("Кнопка подтверждения создания задачи");


    private SelenideElement res(String str) {
        return $x("//span[@aria-tooltip='" + str + "']/..").as("Ссылка на результат поиска");
    }

    private SelenideElement curProject(String str) {
        return $x("//div[@id='project_current']//a[contains(text(),'" + str + "')]").as("Переход к нужному проекту");
    }


    public void refProject(String name) {
        refProject.click();
        curProject(name).click();
        tabTasks.click();
    }

    public int taskCount() {
        return Integer.parseInt(tasks.innerText().split(" ")[2]);
    }

    public void createTasks(String name) {
        butCreateTasks.click();
        butTypeTasks.click();
        butTypeTap.click();
        inputDesc.setValue(name);
        inputDesc.pressEnter();
        tabTasks.click();
    }

    public void searchTask(String str) {
        inputSearch.setValue(str);
        res(str).click();
    }

    public void createBug(String status, String theme) {
        navCreate.click();
        JiraFormCreateTask formCreateTask = new JiraFormCreateTask();
        formCreateTask.createTask(status, theme);
    }

}
