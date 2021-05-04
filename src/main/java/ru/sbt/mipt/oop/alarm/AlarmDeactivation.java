package ru.sbt.mipt.oop.alarm;

public class AlarmDeactivation implements AlarmState {
    private final Alarm alarm;
    public AlarmDeactivation(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
        alarm.setAccessCode(code);
        alarm.setState(new AlarmActivation(alarm));
    }

    @Override
    public void deactivate(int code) {

    }

    @Override
    public void startBeep() {

    }
}
