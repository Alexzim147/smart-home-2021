import org.junit.Test;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.rc.HallDoorClosingCommand;
import ru.sbt.mipt.oop.rc.TurnOffLightsCommand;

public class TestTurnOffLightsCommand {
    @Test
    public void simpleTurnOffLightsCommandTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        TurnOffLightsCommand turnOffLightsCommand = new TurnOffLightsCommand(smartHome);
        turnOffLightsCommand.execute();
        Light light = TestSmartHomeUtils.getLightById(smartHome, "2");
        assert(!light.isOn());
    }
}
