package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.objects.SmartHome;

public class AlarmBeepingCommand implements Command {
    private final SmartHome smartHome;
    public AlarmBeepingCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().startBeep();
    }
}
