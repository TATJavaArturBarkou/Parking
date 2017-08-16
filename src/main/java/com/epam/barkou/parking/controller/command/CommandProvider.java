package main.java.com.epam.barkou.parking.controller.command;

import main.java.com.epam.barkou.parking.controller.command.command_xml_supplier.RepositoryInitializator;
import main.java.com.epam.barkou.parking.controller.exception.ControllerException;
import main.java.com.epam.barkou.parking.view.scanner.UserInput;
import org.apache.log4j.Logger;

import java.util.HashMap;


public class CommandProvider {

    private final static Logger log = Logger.getLogger(UserInput.class);
    private static HashMap<CommandName, Command> repository;
    private static CommandProvider commandProvider = null;

    private CommandProvider() {

    }

    public static CommandProvider getInstance() {
        if (commandProvider != null) {
            return commandProvider;
        } else {
            RepositoryInitializator initializator = new RepositoryInitializator();
            try {
                repository = initializator.init();
            } catch (ControllerException e) {
                log.error(e);
            }
            commandProvider = new CommandProvider();

            return commandProvider;
        }

    }

    public Command getCommand(String name) {

        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }

}
