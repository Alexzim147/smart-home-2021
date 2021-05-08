package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.event.Action;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.alarm = alarm;
        this.rooms = rooms;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    @Override
    public void execute(Action action) {
        rooms.forEach(room -> room.execute(action));
    }
}
