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
import ru.sbt.mipt.oop.rc.*;

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
    public SmartHome smartHome(SmartHomeLoader smartHomeLoader) {
        return smartHomeLoader.loadSmartHome();
    }

    @Bean
    public Command alarmActivationCommand(SmartHome smartHome) {
        return new AlarmActivationCommand(smartHome);
    }

    @Bean
    public Command alarmBeepingCommand(SmartHome smartHome) {
        return new AlarmBeepingCommand(smartHome);
    }

    @Bean
    public Command hallDoorClosingCommand(SmartHome smartHome) {
        return new HallDoorClosingCommand(smartHome);
    }

    @Bean
    public Command turnOnLightsCommand(SmartHome smartHome) {
        return new TurnOnLightsCommand(smartHome);
    }

    @Bean
    public Command turnOffLightsCommand(SmartHome smartHome) {
        return new TurnOffLightsCommand(smartHome);
    }

    @Bean
    public Command turnOnHallLightCommand(SmartHome smartHome) {
        return new TurnOnHallLightCommand(smartHome);
    }
    
    @Bean
    Map<String, Command> commandMap(Command alarmActivationCommand, Command alarmBeepingCommand, Command hallDoorClosingCommand,
                                    Command turnOnLightsCommand, Command turnOffLightsCommand, Command turnOnHallLightCommand) {
        return Map.of(
            "A", alarmActivationCommand,
            "B", alarmBeepingCommand,
            "C", hallDoorClosingCommand,
            "D", turnOnLightsCommand,
            "1", turnOffLightsCommand,
            "2", turnOnHallLightCommand
        );
    }

    @Bean
    RemoteController remoteController(Map<String, Command> commandMap) {
        return new RemoteController(commandMap,"1");
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(List<RemoteController> remoteControllers) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControllers.forEach(remoteController -> {
            remoteControlRegistry.registerRemoteControl(remoteController,remoteController.getRcId());
        });
        return remoteControlRegistry;
    }
    
    @Bean
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
