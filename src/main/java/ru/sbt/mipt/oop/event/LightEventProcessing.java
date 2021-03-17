package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.SensorEventType.*;

public class LightEventProcessing implements EventProcessing {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (isLightEvent(event)) {
            Action action = object -> {
                if (! (object instanceof Light)) { return; }
                Light asLight = (Light) object;
                if (asLight.getId().equals(event.getObjectId())) {
                    asLight.setOn(event.getType() == LIGHT_ON);
                }
            };
            smartHome.execute(action);
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
