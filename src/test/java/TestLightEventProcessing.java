import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.event.LightEventProcessing;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class TestLightEventProcessing {
    @Test
    public void onLightProcessingTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        LightEventProcessing lightEventProcessing = new LightEventProcessing();
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        lightEventProcessing.processEvent(sensorEvent, smartHome);
        Light changedLight = null;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()){
                if (light.getId().equals("1")) {
                    changedLight = light;
                }
            }
        }
        assert(changedLight.isOn());
    }

    @Test
    public void offLightProcessingTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        LightEventProcessing lightEventProcessing = new LightEventProcessing();
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        lightEventProcessing.processEvent(sensorEvent, smartHome);
        Light changedLight = null;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()){
                if (light.getId().equals("1")) {
                    changedLight = light;
                }
            }
        }
        assert(!changedLight.isOn());
    }

}
