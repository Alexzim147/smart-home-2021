package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.objects.SmartHome;

public class AlarmActivationCommand implements Command {
    private final SmartHome smartHome;
    public AlarmActivationCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(1);
    }
}