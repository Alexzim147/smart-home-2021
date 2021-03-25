package ru.sbt.mipt.oop.alarm;

public class AlarmDeactivation extends AlarmState {
    public AlarmDeactivation(Alarm alarm) {
        super(alarm);
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
