package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;

public class DoorEventProcessing implements EventProcessing {
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (isDoorEvent(event)) {
            Action action = object -> {
                if (! (object instanceof Door)) { return; }
                Door asDoor = (Door) object;
                if (asDoor.getId().equals(event.getObjectId())) {
                    asDoor.setOpen(event.getType() == DOOR_OPEN);
                }
            };
            smartHome.execute(action);
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

}
