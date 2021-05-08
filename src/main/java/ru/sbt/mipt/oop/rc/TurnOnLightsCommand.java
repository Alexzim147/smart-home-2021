package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.event.Action;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

public class TurnOnLightsCommand implements Command {
    private final SmartHome smartHome;
    public TurnOnLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action action = object ->
        {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);
            }
        };
        smartHome.execute(action);
    }
}
