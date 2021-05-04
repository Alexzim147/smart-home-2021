import org.junit.Test;
import ru.sbt.mipt.oop.event.DoorEventProcessing;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class TestDoorEventProcessing {
    @Test
    public void closeDoorProcessingTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        DoorEventProcessing doorEventProcessing = new DoorEventProcessing();
        SensorEvent sensorEvent = new SensorEvent(EventType.DOOR_CLOSED, "1");
        doorEventProcessing.processEvent(sensorEvent, smartHome);
        Door changedDoor = TestSmartHomeUtils.getDoorById(smartHome, "1");
        assert(!changedDoor.isOpen());
    }

    @Test
    public void openDoorProcessingTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        DoorEventProcessing doorEventProcessing = new DoorEventProcessing();
        SensorEvent sensorEvent = new SensorEvent(EventType.DOOR_OPEN, "1");
        doorEventProcessing.processEvent(sensorEvent, smartHome);
        Door changedDoor = TestSmartHomeUtils.getDoorById(smartHome, "1");
        assert(changedDoor.isOpen());
    }
}
