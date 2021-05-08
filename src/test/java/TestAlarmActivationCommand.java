import org.junit.Test;
import ru.sbt.mipt.oop.event.DoorEventProcessing;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.rc.AlarmActivationCommand;

public class TestAlarmActivationCommand {
    @Test
    public void simpleAlarmAcivationCommandTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        AlarmActivationCommand alarmActivationCommand = new AlarmActivationCommand(smartHome);
        alarmActivationCommand.execute();
        assert(smartHome.getAlarm().isActivated());
    }
}
