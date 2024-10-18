package apiForTask;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;


public class Specifications {

    @Step("Отправка запроса")
    public static RequestSpecification baseRequestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Step("Статус код 200 || 201")
    public static ResponseSpecification baseResponseSpecSuccess() {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(200), is(201)))
                .log(LogDetail.ALL)
                .build();

    }
}
