import org.junit.Test;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.rc.AlarmActivationCommand;
import ru.sbt.mipt.oop.rc.AlarmBeepingCommand;

public class TestAlarmBeepingCommand {
    @Test
    public void simpleAlarmBeepingCommandTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        AlarmBeepingCommand alarmBeepingCommand = new AlarmBeepingCommand(smartHome);
        smartHome.getAlarm().activate(1);
        alarmBeepingCommand.execute();
        assert(smartHome.getAlarm().isBeeping());
    }
}
