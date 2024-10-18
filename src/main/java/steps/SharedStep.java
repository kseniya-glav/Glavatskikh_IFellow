package steps;

import apiForTask.Specifications;
import config.Props;
import io.cucumber.java.ru.Дано;
import io.restassured.RestAssured;

public class SharedStep {

    @Дано("^проверка статус кода \\\"([^\\\"]*)\\\"$")
    public void SetUp(String url) {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(Props.props.getProperty(url));
        RestAssured.responseSpecification = Specifications.baseResponseSpecSuccess();
    }
}
