import org.junit.Test;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.rc.AlarmBeepingCommand;
import ru.sbt.mipt.oop.rc.HallDoorClosingCommand;

public class TestHallDoorClosingCommand {
    @Test
    public void simpleHallDoorClosingCommandTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        HallDoorClosingCommand hallDoorClosingCommand = new HallDoorClosingCommand(smartHome);
        hallDoorClosingCommand.execute();
        Door hallDoor = TestSmartHomeUtils.getDoorById(smartHome, "4");
        assert(!hallDoor.isOpen());
    }
}
