package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class JiraPageAuth {

    private final SelenideElement inputUserName = $$x("//input[@name='os_username']").get(0);
    private final SelenideElement inputUserPass = $$x("//input[@name='os_password']").get(0);
    private final SelenideElement inputLogin = $$x("//input[@name='login']").get(0);

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
