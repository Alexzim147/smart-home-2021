package ru.sbt.mipt.oop.alarm;

public class AlarmBeeping implements AlarmState {
    private final Alarm alarm;
    public AlarmBeeping(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {

    }

    @Override
    public void deactivate(int code) {
        if (alarm.getAccessCode() == code) {
            alarm.setState(new AlarmDeactivation(alarm));
        }
    }

    @Override
    public void startBeep() {

    }
}
