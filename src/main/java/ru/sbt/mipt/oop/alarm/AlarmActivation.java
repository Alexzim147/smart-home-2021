package ru.sbt.mipt.oop.alarm;


public class AlarmActivation extends AlarmState {
    public AlarmActivation(Alarm alarm) {
        super(alarm);
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