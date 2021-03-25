package ru.sbt.mipt.oop.alarm;

public abstract class AlarmState {
    public Alarm alarm;

    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract void activate(int code);
    public abstract void deactivate(int code);
    public abstract void startBeep();
}