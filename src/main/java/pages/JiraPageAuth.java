package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraPageAuth {

    private final SelenideElement inputUserName = $x("//input[@name='os_username']");
    private final SelenideElement inputUserPass = $x("//input[@name='os_password']");
    private final SelenideElement inputLogin = $x("//input[@name='login']");

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
        return this;
    }
}
