package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.EventType.DOOR_OPEN;

public class HallDoorEventProcessing implements EventProcessing {
    @Override
    public void processEvent(Event event, SmartHome smartHome) {
        if (!(event instanceof SensorEvent)) {
            return;
        }

        SensorEvent sensorEvent = (SensorEvent) event;
        if (isDoorEvent(sensorEvent)) {
            if (event.getType() == DOOR_CLOSED) {
                Action action = object -> {
                    if (! (object instanceof Light)) { return; }
                    Light asLight = (Light) object;
                    asLight.setOn(false);
                };
                smartHome.execute(action);
            }
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN;
    }
}
