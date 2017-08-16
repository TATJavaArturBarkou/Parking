package main.java.com.epam.barkou.parking.view;

import main.java.com.epam.barkou.parking.view.action.impl.*;
import main.java.com.epam.barkou.parking.view.scanner.UserInput;

public class ActionsManager {


    public void init() {
        UserInput userInput = UserInput.getInstance();
        SlotCommandRunner SlotCommandRunner = new SlotCommandRunner();

        EnterVehicleNumberAction enterVehicleNumberAction = new EnterVehicleNumberAction();
        String regNumber;
        regNumber = userInput.askForAnswer(enterVehicleNumberAction);

        EnterCommandAction enterCommandAction = new EnterCommandAction();
        String commandNumber;
        commandNumber = userInput.askForAnswer(enterCommandAction);

        String vehicleType = "";
        String slot = "";
        final int occupyCommand = 1;

        final int choose = Integer.valueOf(commandNumber);
        if (choose == occupyCommand) {

            EnterVehicleTypeAction enterVehicleTypeAction = new EnterVehicleTypeAction();

            vehicleType = userInput.askForAnswer(enterVehicleTypeAction);

            EnterCoveredAction enterCoveredAction = new EnterCoveredAction();
            String covered;
            covered = userInput.askForAnswer(enterCoveredAction);

            SlotCommandRunner.getFreeSlots(vehicleType, covered);

            EnterSlotAction enterSlotAction = new EnterSlotAction();

            slot = userInput.askForAnswer(enterSlotAction);

        }

        SlotCommandRunner.run(commandNumber, regNumber, vehicleType, slot);
    }
}