package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/test/resources/test.property"
})

public interface Props extends Config {
    Props props = ConfigFactory.create(Props.class);

    @Key("url")
    String url();

    @Key("login")
    String userLogin();

    @Key("password")
    String userPassword();

    @Key("nameTask")
    String nameTask();

    @Key("statusTask")
    String statusTask();

    @Key("fixVersion")
    String fixVersion();

    @Key("theme")
    String theme();

    @Key("typeTask")
    String typeTask();

    @Key("project")
    String curProject();

    @Key("nameCreateTask")
    String nameCreateTask();

    @Key("atWork")
    String atWork();

    @Key("done")
    String done();

    @Key("path_chromedriver")
    String pathChromeDriver();
}
