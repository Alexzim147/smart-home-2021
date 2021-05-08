package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.event.Action;

public interface Actionable {
    void execute(Action action);
}
