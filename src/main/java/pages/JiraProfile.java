package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraProfile {
    //    private final SelenideElement iconProfile = $x("//a[@id='header-details-user-fullname']/..").as("icon profile");
//    private final SelenideElement viewProfile = $x("//a[@id='view_profile']").as("view profile");
    private final SelenideElement userName = $x("//img[starts-with(@alt,'Пользовательский профиль для')]").as("username");


    public String userName() {
        String alt = userName.getAttribute("alt");
        assert alt != null;
        return alt.substring(alt.indexOf("для") + 4);
    }


}
