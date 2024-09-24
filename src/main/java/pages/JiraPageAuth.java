package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class JiraPageAuth {

    private final SelenideElement inputUserName = $x("//input[@name='os_username']").as("input username");
    private final SelenideElement inputUserPass = $x("//input[@name='os_password']").as("input userpass");
    private final SelenideElement inputLogin = $x("//input[@name='login']").as("input login");

    private final SelenideElement loginForm = $x("//form[@id='loginform']").as("loginform");
    public JiraPageAuth inputUserName(String userName) {
        inputUserName.setValue(userName);
        return this;
    }

    public JiraPageAuth inputUserPass(String userPass) {
        inputUserPass.setValue(userPass);
        return this;
    }

    public JiraPageAuth inputLogin() {
        inputLogin.click();
        sleep(5000);
        return this;
    }

    public Boolean loginForm() {
        return inputLogin.is(Condition.visible);
    }
}
