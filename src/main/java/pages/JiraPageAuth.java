package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraPageAuth {

    private final SelenideElement inputUserName = $x("//input[@name='os_username']").as("Элемент ввода логина пользователя");
    private final SelenideElement inputUserPass = $x("//input[@name='os_password']").as("Элемент ввода пароля пользователя");
    private final SelenideElement inputLogin = $x("//input[@name='login']").as("Кнопка входа в личный кабинет");

    public void inputUserName(String userName) {
        inputUserName.setValue(userName);
    }

    public void inputUserPass(String userPass) {
        inputUserPass.setValue(userPass);
    }

    public void inputLogin() {
        inputLogin.click();
    }
}
