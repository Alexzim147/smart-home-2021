package ru.sbt.mipt.oop.adapter;


import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.event.EventProcessing;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Map;

public class CCEventProcessingAdapter implements EventHandler {
    private final EventProcessing eventProcessing;
    private final SmartHome smartHome;
    private final Map<String, EventType> ccEventTypeToEventTypeMap;

    public CCEventProcessingAdapter(EventProcessing eventProcessing, SmartHome smartHome, Map<String, EventType> ccEventTypeToEventTypeMap) {
        this.eventProcessing = eventProcessing;
        this.smartHome = smartHome;
        this.ccEventTypeToEventTypeMap = ccEventTypeToEventTypeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = new SensorEvent(
                ccEventTypeToEventTypeMap.get(event.getEventType()),
                event.getObjectId()
        );

        eventProcessing.processEvent(sensorEvent, smartHome);
    }
}