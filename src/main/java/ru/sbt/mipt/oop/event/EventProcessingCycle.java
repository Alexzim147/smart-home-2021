package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.ArrayList;

public class EventProcessingCycle {
    private final SmartHome smartHome;
    private final SensorEventGenerator sensorEventGenerator;
    private final ArrayList<EventProcessing> eventProcessings;

    public EventProcessingCycle(SmartHome smartHome, SensorEventGenerator sensorEventGenerator, ArrayList<EventProcessing> eventProcessings) {
        this.smartHome = smartHome;
        this.sensorEventGenerator = sensorEventGenerator;
        this.eventProcessings = eventProcessings;
    }

    public void processEventsCycle() {
        SensorEvent event = sensorEventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessing eventProcessing : eventProcessings) {
                eventProcessing.processEvent(event, smartHome);
            }
            event = sensorEventGenerator.getNextSensorEvent();
        }
    }
}
