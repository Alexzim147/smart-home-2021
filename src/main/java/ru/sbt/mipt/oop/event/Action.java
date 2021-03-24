package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.Actionable;

public interface Action {
    void execute(Actionable actionable);
}
