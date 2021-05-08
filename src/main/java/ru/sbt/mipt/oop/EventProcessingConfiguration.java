package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.event.*;

@Configuration
public class EventProcessingConfiguration {
    @Bean
    EventProcessing alarmActivatingEventProcessing() {
        return new AlarmActivatingProcessing();
    }

    @Bean
    EventProcessing alarmDeactivatingEventProcessing() {
        return new AlarmDeactivatingProcessing();
    }

    @Bean
    EventProcessing doorEventProcessing() {
        return new DoorEventProcessing();
    }

    @Bean
    EventProcessing hallDoorEventProcessing() {
        return new HallDoorEventProcessing();
    }

    @Bean
    EventProcessing lightEventProcessing() {
        return new LightEventProcessing();
    }
}
