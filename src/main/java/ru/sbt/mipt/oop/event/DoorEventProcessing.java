package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;

public class DoorEventProcessing implements EventProcessing {
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (isDoorEvent(event)) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            processOpeningDoorEvent(door, room);
                        } else {
                            processClosingDoorEvent(door, room);
                        }
                    }
                }
            }
        }
    }

    private void processOpeningDoorEvent(Door door, Room room) {
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    private void processClosingDoorEvent(Door door, Room room) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

}
