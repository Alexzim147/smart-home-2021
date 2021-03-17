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
        if (isDoorEvent(event) && isHallDoorEvent(smartHome, event)) {
            if (event.getType() == DOOR_CLOSED) {
                processClosingDoorEvent(smartHome);
            }
        }
    }

    private void processClosingDoorEvent(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN;
    }

    private boolean isHallDoorEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (room.getName().equals("hall")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
