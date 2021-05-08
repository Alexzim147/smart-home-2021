package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.SmartHome;

public interface EventProcessing {
    void processEvent(Event event, SmartHome smartHome);
}
