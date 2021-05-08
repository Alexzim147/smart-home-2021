package ru.sbt.mipt.oop.alarm;

public class Alarm {
    private AlarmState state = new AlarmDeactivation(this);
    private int code;

    void setState(AlarmState newState) {
        state = newState;
    }

    public boolean isActivated() {
        return !(state instanceof AlarmDeactivation);
    }

    public boolean isBeeping() {
        return state instanceof AlarmBeeping;
    }

    void setAccessCode(int code) {
        this.code = code;
    }

    int getAccessCode() {
        return code;
    }

    public void activate(int code) {
        state.activate(code);
    }

    public void deactivate(int code) {
        state.deactivate(code);
    }

    public void startBeep() {
        state.startBeep();
    }
}