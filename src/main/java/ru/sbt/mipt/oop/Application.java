package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Application {
    private final SmartHomeLoader smartHomeLoader;

    Application(SmartHomeLoader smartHomeLoader) {
        this.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        Application application = new Application(new JsonSmartHomeLoader("smart-home-1.js"));
        application.act();
    }

    public void act() {
        SensorEventGenerator sensorEventGenerator = new SensorEventGenerator();

        ArrayList<EventProcessing> eventProcessings = new ArrayList<>(Arrays.asList(
                new LightEventProcessing(),
                new DoorEventProcessing(),
                new HallDoorEventProcessing()
        ));
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        EventProcessingCycle eventProcessingCycle = new EventProcessingCycle(smartHome, sensorEventGenerator, eventProcessings);
        eventProcessingCycle.processEventsCycle();
    }
}
