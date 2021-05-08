package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.EventType.DOOR_OPEN;

public class DoorEventProcessing implements EventProcessing {
    public void processEvent(Event event, SmartHome smartHome) {
        if (!(event instanceof SensorEvent)) {
            return;
        }

        SensorEvent sensorEvent = (SensorEvent) event;
        if (isDoorEvent(sensorEvent)) {
            Action action = object -> {
                if (! (object instanceof Door)) { return; }
                Door asDoor = (Door) object;
                if (asDoor.getId().equals(sensorEvent.getObjectId())) {
                    asDoor.setOpen(sensorEvent.getType() == DOOR_OPEN);
                }
            };
            smartHome.execute(action);
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

}
