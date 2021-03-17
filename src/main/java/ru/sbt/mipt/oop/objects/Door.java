package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.event.Action;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }


}
