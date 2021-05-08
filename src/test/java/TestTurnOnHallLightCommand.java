import org.junit.Test;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.rc.TurnOffLightsCommand;
import ru.sbt.mipt.oop.rc.TurnOnHallLightCommand;
import ru.sbt.mipt.oop.rc.TurnOnLightsCommand;

public class TestTurnOnHallLightCommand {
    @Test
    public void simpleTurnOnHallLightCommandTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        TurnOnHallLightCommand turnOnHallLightCommand = new TurnOnHallLightCommand(smartHome);
        turnOnHallLightCommand.execute();
        Light light = TestSmartHomeUtils.getLightById(smartHome, "7");
        assert(!light.isOn());
    }
}
