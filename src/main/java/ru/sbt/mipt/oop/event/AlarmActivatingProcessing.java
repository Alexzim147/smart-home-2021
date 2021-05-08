package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.objects.SmartHome;

public class AlarmActivatingProcessing implements EventProcessing {
    @Override
    public void processEvent(Event event, SmartHome smartHome) {
        if (event.getType() != EventType.ALARM_ACTIVATE || !(event instanceof AlarmEvent)) {
            return;
        }

        AlarmEvent alarmEvent = (AlarmEvent) event;

        smartHome.getAlarm().activate(alarmEvent.getCode());
    }
}