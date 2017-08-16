package main.java.com.epam.barkou.parking.view.action.impl;

import main.java.com.epam.barkou.parking.view.action.IConsoleIOActions;

public class EnterVehicleNumberAction extends IConsoleIOActions {

    private final static String ENTER_MESSAGE = "Type your vehicle registration number:";

    @Override
    public boolean checkInput(String userInput) {

        if (userInput.equals(EMPTY_INPUT) || userInput.equals(SPACE_INPUT)) {
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
