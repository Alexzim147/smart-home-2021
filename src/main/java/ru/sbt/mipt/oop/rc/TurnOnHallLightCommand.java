package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.event.Action;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class TurnOnHallLightCommand implements Command {
    private final SmartHome smartHome;
    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action action = object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(light -> {
                        if (light instanceof Light) {
                            ((Light) light).setOn(true);
                        }
                    });
                }
            }
        };
        smartHome.execute(action);
    }
}