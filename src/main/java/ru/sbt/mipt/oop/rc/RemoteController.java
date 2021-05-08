package ru.sbt.mipt.oop.rc;

import java.util.Map;

public class RemoteController implements RemoteControl {
    private final String rcId;
    private final Map<String, Command> buttonsToCommands;

    public RemoteController(Map<String, Command> buttonsToCommands, String rcId) {
        this.buttonsToCommands = buttonsToCommands;
        this.rcId = rcId;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (this.rcId.equals(rcId)) {
            processButtonPress(buttonCode);
        }
    }

    public String getRcId() {
        return rcId;
    }

    private void processButtonPress(String buttonCode) {
        if (buttonsToCommands.containsKey(buttonCode)) {
            Command command = buttonsToCommands.get(buttonCode);
            command.execute();
        }
    }
}