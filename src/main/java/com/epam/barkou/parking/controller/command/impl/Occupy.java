package main.java.com.epam.barkou.parking.controller.command.impl;

import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.controller.command.Command;
import main.java.com.epam.barkou.parking.controller.exception.ControllerException;
import main.java.com.epam.barkou.parking.controller.util.RequestChecker;
import main.java.com.epam.barkou.parking.service.ISlotService;
import main.java.com.epam.barkou.parking.service.IVehicleService;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;
import main.java.com.epam.barkou.parking.service.factory.ServiceFactory;
import main.java.com.epam.barkou.parking.view.scanner.UserInput;
import org.apache.log4j.Logger;

public class Occupy extends Command {
    private final static Logger log = Logger.getLogger(UserInput.class);
    private final static String MESSAGE_UNABLE = "Unable to occupy slot";
    private final static String MESSAGE_SUCCESSFULL = "Slot has been occupied successfully";
    private final static String ERROR_INCORRECT_TYPE = "Incorrect vehicle type";
    private final int vehicleRegNumberIndex = 1;
    private final int slotNumberIndex = 3;
    private final int vehicleTypeIndex = 2;
    private String response = null;

    @Override
    public String execute(String request) throws ControllerException {

        String[] requestData = request.split(SPLITTER);

        int paramsQuantity = 2;
        if (RequestChecker.checkParamsQuantity(requestData, paramsQuantity)) {

            Vehicle.Type type;

            String regNumber = requestData[vehicleRegNumberIndex];
            int slotNumber = Integer.parseInt(requestData[slotNumberIndex]);

            try {
                String typeInUpperCase = requestData[vehicleTypeIndex].toUpperCase();
                type = Vehicle.Type.valueOf(typeInUpperCase);
            } catch (IllegalArgumentException e) {

                throw new ControllerException(ERROR_INCORRECT_TYPE, e);
            }

            Vehicle vehicle = new Vehicle(regNumber, type);

            ServiceFactory factory = ServiceFactory.getInstance();
            ISlotService slotService = factory.getSlotService();

            try {

                slotService.occupy(vehicle, slotNumber);
                response = MESSAGE_SUCCESSFULL;

            } catch (ServiceException e) {
                response = MESSAGE_UNABLE;
                log.error(e);

            }

        } else {
            response = MESSAGE_NOT_ENOUGH_PARAMS;
        }
        return response;
    }
}
