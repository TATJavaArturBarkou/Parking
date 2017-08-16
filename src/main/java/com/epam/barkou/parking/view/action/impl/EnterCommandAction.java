package main.java.com.epam.barkou.parking.view.action.impl;

import main.java.com.epam.barkou.parking.view.action.IConsoleIOActions;
import main.java.com.epam.barkou.parking.view.SlotCommandRunner;

import java.util.HashMap;

public class EnterCommandAction extends IConsoleIOActions {

    private final static String ENTER_MESSAGE = "Type 1 to reserve slot, type 2 to free slot:";


    @Override
    public boolean checkInput(String userInput) {

        HashMap<String, String> commandsMap = SlotCommandRunner.getCommandsMap();

        String command = commandsMap.get(userInput);

        if (userInput.equals(EMPTY_INPUT) || userInput.equals(SPACE_INPUT) || command == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public String getEnterMessage() {
        return ENTER_MESSAGE;
    }
}
