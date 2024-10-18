package steps;

import apiForTask.RequestResults;
import config.Props;
import io.cucumber.java.PendingException;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReqresApiStep {

    private final RequestResults requestResults = new RequestResults();

    @Тогда("^изменение полей name значением \\\"([^\\\"]*)\\\" и job значением \\\"([^\\\"]*)\\\" файла \\\"([^\\\"]*)\\\"$")
    public void editUser(String newName, String newJob, String fileName) throws IOException, PendingException {

        //изменение данных в json
        String content = new String(Files.readAllBytes(Paths.get(Props.props.getProperty(fileName))));
        JSONObject jsonObject = new JSONObject(content);
        jsonObject.put("name", Props.props.getProperty(newName));
        jsonObject.put("job", Props.props.getProperty(newJob));
        Files.write(Paths.get(Props.props.filePathJson()), jsonObject.toString(4).getBytes());

    }

    @И("^запрос на создание пользователя с данными из файла \\\"([^\\\"]*)\\\"$")
    public void createUser(String fileName) throws IOException {

        Response response = requestResults.postCreateUser(new String(Files.readAllBytes(Paths.get(Props.props.filePathJson()))));

        if (!response.jsonPath().getString("name").equals(Props.props.newName()) ||
                !response.jsonPath().getString("job").equals(Props.props.newJob()))
            throw new PendingException();
    }

    @Дано("^файл json \\\"([^\\\"]*)\\\" с полем name$")
    public void writeJsonToFile(String fileName) throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Potato");

        // Записываем данные в файл, если файл существует, он будет перезаписан
        Files.write(Paths.get(Props.props.getProperty(fileName)), jsonObject.toString(4).getBytes());

    }
}
