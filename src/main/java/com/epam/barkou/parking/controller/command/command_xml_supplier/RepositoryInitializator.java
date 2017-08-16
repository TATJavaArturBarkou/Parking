package main.java.com.epam.barkou.parking.controller.command.command_xml_supplier;

import main.java.com.epam.barkou.parking.controller.command.Command;
import main.java.com.epam.barkou.parking.controller.command.CommandName;
import main.java.com.epam.barkou.parking.controller.command.command_xml_supplier.util.StringUtil;
import main.java.com.epam.barkou.parking.controller.command.command_xml_supplier.xml_parser.XMLCommand;
import main.java.com.epam.barkou.parking.controller.command.command_xml_supplier.xml_parser.XMLRunner;
import main.java.com.epam.barkou.parking.controller.exception.ControllerException;

import java.util.ArrayList;
import java.util.HashMap;


public class RepositoryInitializator {

    private final static String PATH_TO_COMMAND_IMPL = "main.java.com.epam.barkou.parking.controller.command.impl.";
    private final static String ERROR_REPOSITIRY_INIT = "Repository initialization error";

    public HashMap<CommandName, Command> init() throws ControllerException {
        HashMap<CommandName, Command> repository = null;
        try {

            repository = new HashMap<CommandName, Command>();

            ArrayList<XMLCommand> xmlCommands = XMLRunner.parseXML();

            for (int index = 0; index < xmlCommands.size(); index++) {
                String commandName = xmlCommands.get(index).getName();

                Class commandNameClazz = Class.forName(PATH_TO_COMMAND_IMPL + commandName);

                if (Command.class.isAssignableFrom(commandNameClazz)) {

                    Command command = (Command) commandNameClazz.newInstance();

                    commandName = StringUtil.convertCommandToEnumForm(commandName);

                    CommandName commandNameENUM = CommandName.valueOf(commandName);
                    repository.put(commandNameENUM, command);

                }

            }

        } catch (InstantiationException | SecurityException | IllegalAccessException | ClassNotFoundException e) {
            throw new ControllerException(ERROR_REPOSITIRY_INIT, e);

        }
        return repository;
    }

}
