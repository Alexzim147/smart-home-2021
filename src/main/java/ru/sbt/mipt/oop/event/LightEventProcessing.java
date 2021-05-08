package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.EventType.*;

public class LightEventProcessing implements EventProcessing {
    @Override
    public void processEvent(Event event, SmartHome smartHome) {
        if (!(event instanceof SensorEvent)) {
            return;
        }

        SensorEvent sensorEvent = (SensorEvent) event;
        if (isLightEvent(sensorEvent)) {
            Action action = object -> {
                if (! (object instanceof Light)) { return; }
                Light asLight = (Light) object;
                if (asLight.getId().equals(sensorEvent.getObjectId())) {
                    asLight.setOn(sensorEvent.getType() == LIGHT_ON);
                }
            };
            smartHome.execute(action);
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
