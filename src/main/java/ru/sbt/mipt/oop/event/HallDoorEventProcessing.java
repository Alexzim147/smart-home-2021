package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;

public class HallDoorEventProcessing implements EventProcessing {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (isDoorEvent(event)) {
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
