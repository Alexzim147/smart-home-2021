package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.EventType.DOOR_OPEN;

public class HallDoorEventProcessing implements EventProcessing {
    @Override
    public void processEvent(Event event, SmartHome smartHome) {
        if (isDoorEvent(event)) {
            Action action = roomCandidate -> {
                if (!(event instanceof SensorEvent)) {
                    return;
                }
                SensorEvent sensorEvent = (SensorEvent) event;

                if (!(roomCandidate instanceof Room)) {
                    return;
                }

                Room room = (Room) roomCandidate;

                if (!room.getName().equals("hall")) {
                    return;
                }

                Action roomAction = doorCandidate -> {
                    if (!(doorCandidate instanceof Door)) {
                        return;
                    }
                    Door door = (Door) doorCandidate;

                    if (!door.getId().equals(sensorEvent.getObjectId())) {
                        return;
                    }

                    if (sensorEvent.getType() == DOOR_CLOSED) {
                        processDoorClosingEvent(smartHome);
                    }

                };
                room.execute(roomAction);
            };
            smartHome.execute(action);
        }
    }

    private void processDoorClosingEvent(SmartHome smartHome) {
        smartHome.execute(lightCandidate -> {
            if (!(lightCandidate instanceof Light)) {
                return;
            }

            Light light = (Light) lightCandidate;

            light.setOn(false);
        });
    }


    private boolean isDoorEvent(Event event) {
        return event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN;
    }
}
