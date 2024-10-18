package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/test/resources/test.property"
})

public interface Props extends Config {
    Props props = ConfigFactory.create(Props.class);

    @Key("url_rickAndMorty")
    String urlRickAndMorty();

    @Key("name")
    String name();

    @Key("url_reqres")
    String urlReqres();

    @Key("filePathJson")
    String filePathJson();

    @Key("newName")
    String newName();

    @Key("newJob")
    String newJob();

    default String getProperty(String key) {
        return props.getProperty(key);
    }
}