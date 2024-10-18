package steps;

import apiForTask.RickAndMorty;
import config.Props;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForRickAndMortiApiStep {

    private final RickAndMorty rickAndMorty = new RickAndMorty();

    @Тогда("^проверка существования \\\"([^\\\"]*)\\\"$")
    public void checkName(String name) {
        Response response = rickAndMorty.getCharacterByName(Props.props.getProperty(name));

        if (response.jsonPath().getList("results.id") == null ||
                response.jsonPath().getList("results.episode") == null)
            throw new io.cucumber.java.PendingException();

    }

    @Тогда("^последний эпизод$")
    public void lastEpisode() {
        Response responseMorty = rickAndMorty.getCharacterByName(Props.props.name());

        //последний эпизод и id персонажа
        int[] inf = numberLastEpisodeAndIdCharacter(responseMorty.
                jsonPath().
                getList("results.episode"), responseMorty.jsonPath().getList("results.id"));

        Response responseLastEpisode = rickAndMorty.getEpisode(inf[0]);

        if (responseLastEpisode.jsonPath().getList("characters") == null)
            throw new io.cucumber.java.PendingException();
    }

    @Тогда("^последний персонаж$")
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

        if (responseLastCharacters.jsonPath().get("name") == null)
            throw new io.cucumber.java.PendingException();
    }

    @Тогда("^раса и локация последнего персонажа$")
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

        if (responseLastCharacters.jsonPath().get("species") == null ||
                responseLastCharacters.jsonPath().get("location.name") == null)
            throw new io.cucumber.java.PendingException();

    }

    @Тогда("^совпадение расы и локации последнего персонажа и Морти$")
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

        if (!speciesLastCharacter.equals(speciesMorty) || locationLastCharacter.equals(locationMorty))
            throw new io.cucumber.java.PendingException();

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
