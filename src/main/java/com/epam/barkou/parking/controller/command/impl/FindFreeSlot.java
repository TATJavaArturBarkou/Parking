package main.java.com.epam.barkou.parking.controller.command.impl;

import main.java.com.epam.barkou.parking.bean.Slot;
import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.controller.command.Command;
import main.java.com.epam.barkou.parking.controller.exception.ControllerException;
import main.java.com.epam.barkou.parking.controller.util.RequestChecker;
import main.java.com.epam.barkou.parking.service.ISlotService;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;
import main.java.com.epam.barkou.parking.service.factory.ServiceFactory;
import main.java.com.epam.barkou.parking.view.scanner.UserInput;
import org.apache.log4j.Logger;

public class FindFreeSlot extends Command {

    private final static Logger log = Logger.getLogger(UserInput.class);

    private final int vehicleTypeIndex = 1;
    private final int slotCoveredIndex = 2;
    private String response = null;
    private final static String MESSAGE_SLOT_NOT_FOUND = "Free Slot not found";
    private final static String MESSAGE_UNABLE_TO_FIND = "Unable to find Slot";
    private final static String ERROR_INCORRECT_TYPE = "Incorrect vehicle type";

    @Override
    public String execute(String request) throws ControllerException {

        String[] requestData = request.split(SPLITTER);

        int paramsQuantity = 2;
        if (RequestChecker.checkParamsQuantity(requestData, paramsQuantity)) {
            Vehicle.Type type;

            try {
                String typeInUpperCase = requestData[vehicleTypeIndex].toUpperCase();
                type = Vehicle.Type.valueOf(typeInUpperCase);
            } catch (IllegalArgumentException e) {

                throw new ControllerException(ERROR_INCORRECT_TYPE, e);
            }

            boolean covered = Boolean.parseBoolean(requestData[slotCoveredIndex]);

            ServiceFactory factory = ServiceFactory.getInstance();
            ISlotService slotService = factory.getSlotService();

            try {

                Slot slot = slotService.findFreeSlot(type, covered);

                if (slot != null) {
                    response = String.valueOf(slot.getNumber());
                } else {
                    response = null;
                }

            } catch (ServiceException e) {
                response = MESSAGE_UNABLE_TO_FIND;
                log.error(e);

            }

        } else {
            response = MESSAGE_NOT_ENOUGH_PARAMS;
        }
        return response;
    }

}
