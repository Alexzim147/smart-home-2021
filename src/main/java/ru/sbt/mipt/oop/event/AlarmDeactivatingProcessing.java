package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.objects.SmartHome;

public class AlarmDeactivatingProcessing implements EventProcessing {
    private final Alarm alarm;

    public AlarmDeactivatingProcessing(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void processEvent(Event event, SmartHome smartHome) {
        if (event.getType() != EventType.ALARM_DEACTIVATE || !(event instanceof AlarmEvent)) {
            return;
        }

        AlarmEvent alarmEvent = (AlarmEvent) event;

        alarm.deactivate(alarmEvent.getCode());
    }
}