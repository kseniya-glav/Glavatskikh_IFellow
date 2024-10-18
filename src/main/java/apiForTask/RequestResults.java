package apiForTask;

import config.Props;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResults {

    @Step("Создание пользователя")
    public Response postCreateUser(String jsonData) {

        return given()
                .baseUri(Props.props.urlReqres())
                .contentType(ContentType.JSON)
                .body(jsonData)
                .when()
                .post("/api/users")
                .then()
                .extract().response();

    }
}
