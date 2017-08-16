package main.java.com.epam.barkou.parking.controller;

import main.java.com.epam.barkou.parking.controller.command.Command;
import main.java.com.epam.barkou.parking.controller.command.CommandProvider;
import main.java.com.epam.barkou.parking.controller.exception.ControllerException;
import org.apache.log4j.Logger;

public class Controller {

    private final char paramDelimiter = '&';
    private final CommandProvider provider = CommandProvider.getInstance();
    private final static Logger log = Logger.getLogger(Controller.class);
    private final static String ERROR_CONTROLLER = "Controller Error while command executing";
    private static Controller controller;

    private Controller() {

    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public String executeTask(String request) {

        String command;
        int beginIndex = 0;
        command = request.substring(beginIndex, request.indexOf(paramDelimiter));

        String response = null;

        Command executionCommand = provider.getCommand(command);


        try {
            response = executionCommand.execute(request);

        } catch (ControllerException e) {
            log.error(ERROR_CONTROLLER, e);
        }


        return response;

    }
}