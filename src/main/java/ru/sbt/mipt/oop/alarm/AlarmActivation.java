package ru.sbt.mipt.oop.alarm;


public class AlarmActivation implements AlarmState {
    private final Alarm alarm;
    public AlarmActivation(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {

    }

    @Override
    public void deactivate(int code) {
        if (alarm.getAccessCode() == code) {
            alarm.setState(new AlarmDeactivation(alarm));
        } else {
            alarm.setState(new AlarmBeeping(alarm));
        }
    }

    @Override
    public void startBeep() {
        alarm.setState(new AlarmBeeping(alarm));
    }
}