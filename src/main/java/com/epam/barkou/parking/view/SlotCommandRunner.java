package main.java.com.epam.barkou.parking.view;

import main.java.com.epam.barkou.parking.controller.Controller;

import java.util.HashMap;

public class SlotCommandRunner {

    private static final String OCCUPY_KEY = "1";
    private static final String FREE_SLOT_KEY = "2";

    private static final String OCCUPY_VALUE = "occupy";
    private static final String FREE_SLOT_VALUE = "free_slot";
    private static final String SPLITTER = "&";
    private static final String COMMAND_FIND_FREE_SLOT = "find_free_slot";

    private static final String USER_YES_INPUT = "y";
    private static final String COVERED_TRUE_VALUE = "true";
    private static final String COVERED_FALSE_VALUE = "false";

    private static final String MESSAGE_NEAREST_FREE_SLOT = "Nearest Free slot is: ";
    private static final String MESSAGE_NEAREST_SLOT_NOT_FOUND = "Nearest slot isn't found. ";

    private static final String MESSAGE_RESPONSE = "Response is: ";

    private static HashMap<String, String> commandsMap = new HashMap<String, String>();

    static {
        commandsMap.put(OCCUPY_KEY, OCCUPY_VALUE);
        commandsMap.put(FREE_SLOT_KEY, FREE_SLOT_VALUE);
    }


    public void run(String commandKey, String regNumber, String type, String slot) {

        StringBuilder request = new StringBuilder();
        Controller controller = Controller.getInstance();

        String commandValue = commandsMap.get(commandKey);

        switch (commandKey) {

            case OCCUPY_KEY:
                request = request.append(commandValue).append(SPLITTER).append(regNumber).append(SPLITTER).append(type).append(SPLITTER).append(slot);
                System.out.println(MESSAGE_RESPONSE + controller.executeTask(request.toString()));
                break;
            case FREE_SLOT_KEY:
                request = request.append(commandValue).append(SPLITTER).append(regNumber);
                System.out.println(MESSAGE_RESPONSE + controller.executeTask(request.toString()));
                break;

        }

    }

    public static HashMap<String, String> getCommandsMap() {
        return commandsMap;
    }

    public void getFreeSlots(String vehicleType, String covered) {

        StringBuilder request = new StringBuilder();


        if (covered.equals(USER_YES_INPUT)) {
            covered = COVERED_TRUE_VALUE;
        } else {
            covered = COVERED_FALSE_VALUE;
        }

        request.append(COMMAND_FIND_FREE_SLOT).append(SPLITTER).append(vehicleType).append(SPLITTER).append(covered);

        Controller controller = Controller.getInstance();
        String response = controller.executeTask(request.toString());

        if (response != null) {
            System.out.println(MESSAGE_NEAREST_FREE_SLOT + response);
        } else {
            System.out.println(MESSAGE_NEAREST_SLOT_NOT_FOUND);
            System.exit(1);
        }


    }
}
