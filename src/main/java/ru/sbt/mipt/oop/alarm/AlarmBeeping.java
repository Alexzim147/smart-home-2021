package ru.sbt.mipt.oop.alarm;

public class AlarmBeeping extends AlarmState {
    public AlarmBeeping(Alarm alarm) {
        super(alarm);
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
