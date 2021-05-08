import org.junit.Test;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.rc.TurnOffLightsCommand;
import ru.sbt.mipt.oop.rc.TurnOnLightsCommand;

public class TestTurnOnLightsCommand {
    @Test
    public void simpleTurnOnLightsCommandTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        TurnOnLightsCommand turnOnLightsCommand = new TurnOnLightsCommand(smartHome);
        turnOnLightsCommand.execute();
        Light light = TestSmartHomeUtils.getLightById(smartHome, "2");
        assert(light.isOn());
    }
}
