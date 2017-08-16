package main.java.com.epam.barkou.parking.view.scanner;


import main.java.com.epam.barkou.parking.view.action.IConsoleIOActions;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class UserInput {

    private final static Logger log = Logger.getLogger(UserInput.class);
    private static UserInput userInput = null;

    private UserInput() {

    }

    public static UserInput getInstance() {

        if (userInput == null) {
            userInput = new UserInput();
        }
        return userInput;
    }

    private Scanner scanner = ScannerContainer.getScanner();
    private String userInputString = "";

    public String askForAnswer(IConsoleIOActions ioActions) {
        userInputString = "";
        while (!ioActions.checkInput(userInputString)) {

            System.out.println(ioActions.getEnterMessage());

            userInputString = scanner.next();

        }

        return userInputString;
    }
}
