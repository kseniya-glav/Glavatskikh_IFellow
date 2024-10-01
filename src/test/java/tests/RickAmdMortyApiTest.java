package tests;

import apiForTask.RickAndMorty;
import apiForTask.Specifications;
import config.Props;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RickAmdMortyApiTest {

    private final RickAndMorty rickAndMorty = new RickAndMorty();

    @BeforeAll
    public static void SetUp() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(Props.props.urlRickAndMorty());
        RestAssured.responseSpecification = Specifications.baseResponseSpecSuccess();
    }

    @Test
    public void checkName() {
        Response response = rickAndMorty.getCharacterByName(Props.props.name());
        Assertions.assertNotNull(response.jsonPath().getList("results.id"));
        Assertions.assertNotNull(response.jsonPath().getList("results.episode"));
    }

    @Test
    public void lastEpisode() {
        Response responseMorty = rickAndMorty.getCharacterByName(Props.props.name());

        //последний эпизод и id персонажа
        int[] inf = numberLastEpisodeAndIdCharacter(responseMorty.
                jsonPath().
                getList("results.episode"), responseMorty.jsonPath().getList("results.id"));

        Response responseLastEpisode = rickAndMorty.getEpisode(inf[0]);

        Assertions.assertNotNull(responseLastEpisode.jsonPath().getList("characters"));
    }

    @Test
    public void lastCharacter() {
        Response responseMorty = rickAndMorty.getCharacterByName(Props.props.name());

        //последний эпизод и id персонажа
        int[] inf = numberLastEpisodeAndIdCharacter(responseMorty.
                jsonPath().
                getList("results.episode"), responseMorty.jsonPath().getList("results.id"));

        Response responseLastEpisode = rickAndMorty.getEpisode(inf[0]);

        List<String> ListResponseCharacter = responseLastEpisode.jsonPath().getList("characters");

        int lastNumberCharacter = lastNumber(ListResponseCharacter);
        Response responseLastCharacters = rickAndMorty.getCharacter(lastNumberCharacter);

        Assertions.assertNotNull(responseLastCharacters.jsonPath().get("name"));
    }


    @Test
    public void lastCharacterSpeciesAndLocation() {
        Response responseMorty = rickAndMorty.getCharacterByName(Props.props.name());

        //последний эпизод и id персонажа
        int[] inf = numberLastEpisodeAndIdCharacter(responseMorty.
                jsonPath().
                getList("results.episode"), responseMorty.jsonPath().getList("results.id"));

        Response responseLastEpisode = rickAndMorty.getEpisode(inf[0]);

        List<String> ListResponseCharacter = responseLastEpisode.jsonPath().getList("characters");

        int lastNumberCharacter = lastNumber(ListResponseCharacter);
        Response responseLastCharacters = rickAndMorty.getCharacter(lastNumberCharacter);

        String speciesLastCharacter = responseLastCharacters.jsonPath().get("species");
        String locationLastCharacter = responseLastCharacters.jsonPath().get("location.name");

        Assertions.assertAll("поля раса и местонахождение не пустые",
                () -> Assertions.assertNotNull(speciesLastCharacter),
                () -> Assertions.assertNotNull(locationLastCharacter)
        );

    }

    @Test
    public void lastAndMortyCheckSpeciesAndLocation() {
        Response responseMorty = rickAndMorty.getCharacterByName(Props.props.name());

        //последний эпизод и id персонажа
        int[] inf = numberLastEpisodeAndIdCharacter(responseMorty.
                jsonPath().
                getList("results.episode"), responseMorty.jsonPath().getList("results.id"));

        Response responseLastEpisode = rickAndMorty.getEpisode(inf[0]);

        List<String> ListResponseCharacter = responseLastEpisode.jsonPath().getList("characters");

        int lastNumberCharacter = lastNumber(ListResponseCharacter);
        Response responseLastCharacters = rickAndMorty.getCharacter(lastNumberCharacter);

        String speciesLastCharacter = responseLastCharacters.jsonPath().get("species");
        String locationLastCharacter = responseLastCharacters.jsonPath().get("location.name");

        String speciesMorty = rickAndMorty.getCharacter(inf[1]).jsonPath().get("species");
        String locationMorty = rickAndMorty.getCharacter(inf[1]).jsonPath().get("location.name");

        Assertions.assertAll("Морти и последний персонаж одной расы и в разных локациях",
                () -> Assertions.assertEquals(speciesLastCharacter, speciesMorty),
                () -> Assertions.assertNotEquals(locationLastCharacter, locationMorty)
        );


    }

    private int lastNumber(List<String> response) {
        int last = 0;
        for (String url : response) {
            int number = Integer.parseInt(url.replaceAll("\\D", ""));
            if (last < number)
                last = number;
        }

        return last;
    }

    //пара значений [номер последнего эпизода, id персонажа]
    private int[] numberLastEpisodeAndIdCharacter(List<List<String>> str, List<Integer> id) {

        Map<Integer, List<String>> keyvalue = new HashMap<>();

        int i = 0;
        for (List<String> list : str) {
            keyvalue.put(id.get(i), list);
            i++;
        }

        int lastNumber = 0, idCharacter = 0;
        for (Map.Entry<Integer, List<String>> entry : keyvalue.entrySet()) {
            int item = lastNumber(entry.getValue());
            if (lastNumber < item) {
                lastNumber = item;
                idCharacter = entry.getKey();
            }

        }

        return new int[]{lastNumber, idCharacter};
    }
}
