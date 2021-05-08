package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.event.Action;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class HallDoorClosingCommand implements Command {
    private final SmartHome smartHome;
    public HallDoorClosingCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action action = object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(door -> {
                        if (door instanceof Door) {
                            ((Door) door).setOpen(false);
                        }
                    });
                }
            }
        };
        smartHome.execute(action);
    }
}