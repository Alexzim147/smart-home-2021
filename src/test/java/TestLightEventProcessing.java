import org.junit.Test;
import ru.sbt.mipt.oop.event.LightEventProcessing;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

public class TestLightEventProcessing {
    @Test
    public void onLightProcessingTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        LightEventProcessing lightEventProcessing = new LightEventProcessing();
        SensorEvent sensorEvent = new SensorEvent(EventType.LIGHT_ON, "1");
        lightEventProcessing.processEvent(sensorEvent, smartHome);
        Light changedLight = TestSmartHomeUtils.getLightById(smartHome,"1");
        assert(changedLight.isOn());
    }

    @Test
    public void offLightProcessingTest() {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.json").loadSmartHome();
        LightEventProcessing lightEventProcessing = new LightEventProcessing();
        SensorEvent sensorEvent = new SensorEvent(EventType.LIGHT_OFF, "1");
        lightEventProcessing.processEvent(sensorEvent, smartHome);
        Light changedLight = TestSmartHomeUtils.getLightById(smartHome,"1");
        assert(!changedLight.isOn());
    }

}
