package ru.sbt.mipt.oop.event;

public class AlarmEvent extends Event {
    private final int code;

    public AlarmEvent(EventType type, int code) {
        super(type);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}