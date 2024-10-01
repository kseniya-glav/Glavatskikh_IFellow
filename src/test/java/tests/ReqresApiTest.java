package tests;

import apiForTask.RequestResults;
import apiForTask.Specifications;
import config.Props;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReqresApiTest {

    private final RequestResults requestResults = new RequestResults();

    @BeforeAll
    public static void SetUp() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(Props.props.urlReqres());
        RestAssured.responseSpecification = Specifications.baseResponseSpecSuccess();


    }


    @Test
    public void createUser() throws IOException {

        //изменение данных в json
        String content = new String(Files.readAllBytes(Paths.get(Props.props.filePathJson())));
        JSONObject jsonObject = new JSONObject(content);
        jsonObject.put("name", Props.props.newName());
        jsonObject.put("job", Props.props.newJob());
        Files.write(Paths.get(Props.props.filePathJson()), jsonObject.toString(4).getBytes());


        Response response = requestResults.postCreateUser(new String(Files.readAllBytes(Paths.get(Props.props.filePathJson()))));

        Assertions.assertEquals(response.getStatusCode(), 201);
        Assertions.assertAll("",
                () -> Assertions.assertEquals(response.jsonPath().getString("name"), Props.props.newName()),
                () -> Assertions.assertEquals(response.jsonPath().getString("job"), Props.props.newJob())
        );


    }


}
