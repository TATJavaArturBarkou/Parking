package main.java.com.epam.barkou.parking.view.action.impl;

import main.java.com.epam.barkou.parking.view.action.IConsoleIOActions;

public class EnterCoveredAction extends IConsoleIOActions {
    private final static String ENTER_MESSAGE = "Do you want covered slot y/n?:";
    private static final String USER_YES_INPUT = "y";
    private static final String USER_NO_INPUT = "n";

    @Override
    public boolean checkInput(String userInput) {

        if (userInput.equals(USER_YES_INPUT) || userInput.equals(USER_NO_INPUT)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String getEnterMessage() {
        return ENTER_MESSAGE;
    }
}
