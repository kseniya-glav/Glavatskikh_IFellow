package apiForTask;

import config.Props;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RickAndMorty {

    public Response getCharacterByName(String name) {
        return given()
                .baseUri(Props.props.urlRickAndMorty())
                .param("name", name)
                .when()
                .get("/api/character/")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response getEpisode(int number) {
        return given()
                .baseUri(Props.props.urlRickAndMorty())
                .when()
                .get("/api/episode/" + number)
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response getCharacter(int number) {
        return given()
                .baseUri(Props.props.urlRickAndMorty())
                .when()
                .get("/api/character/" + number)
                .then()
                .statusCode(200)
                .extract().response();
    }

}
