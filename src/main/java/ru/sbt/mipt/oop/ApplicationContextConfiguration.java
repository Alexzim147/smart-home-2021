package ru.sbt.mipt.oop;

import java.util.List;
import java.util.Map;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.adapter.CCEventProcessingAdapter;
import ru.sbt.mipt.oop.event.AlarmDecoratorEventProcessing;
import ru.sbt.mipt.oop.event.EventProcessing;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;
import ru.sbt.mipt.oop.objects.SmartHome;

@Configuration
@Import(EventProcessingConfiguration.class)
public class ApplicationContextConfiguration {
    @Bean
    public Map<String, EventType> ccEventToEventMap() {
        return Map.of(
                "LightIsOn", EventType.LIGHT_ON,
                "LightIsOff", EventType.LIGHT_OFF,
                "DoorIsOpen", EventType.DOOR_OPEN,
                "DoorIsClosed", EventType.DOOR_CLOSED
        );
    }

    @Bean
    public SmartHomeLoader smartHomeLoader() {
        return new JsonSmartHomeLoader("smart-home-1.json");
    }

    @Bean
    @Autowired
    public SmartHome smartHome(SmartHomeLoader smartHomeLoader) {
        return smartHomeLoader.loadSmartHome();
    }

    @Bean
    @Autowired
    public SensorEventsManager sensorEventsManager(List<EventProcessing> eventProcessings, SmartHome smartHome, Map<String, EventType> ccEventToEventMap) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        sensorEventsManager.registerEventHandler(
                new CCEventProcessingAdapter(
                        new AlarmDecoratorEventProcessing(eventProcessings),
                        smartHome,
                        ccEventToEventMap
                )
        );

        return sensorEventsManager;
    }
}
