package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class AlarmDecoratorEventProcessing implements EventProcessing {
    private final List<EventProcessing> eventProcessings;

    public AlarmDecoratorEventProcessing(List<EventProcessing> eventProcessings) {
        this.eventProcessings = eventProcessings;
    }

    @Override
    public void processEvent(Event event, SmartHome smartHome) {
        if (isAlarmEvent(event)) {
            return;
        }

        if (smartHome.getAlarm().isBeeping()) {
            // bip boop bip
            return;
        }

        eventProcessings.forEach(processing -> processing.processEvent(event, smartHome));

        if (smartHome.getAlarm().isActivated()) {
            smartHome.getAlarm().startBeep();
        }
    }

    private boolean isAlarmEvent(Event event) {
        return event.getType() == EventType.ALARM_ACTIVATE || event.getType() == EventType.ALARM_DEACTIVATE;
    }
}
