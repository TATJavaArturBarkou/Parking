package main.java.com.epam.barkou.parking.controller.command.impl;

import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.controller.command.Command;
import main.java.com.epam.barkou.parking.controller.util.RequestChecker;
import main.java.com.epam.barkou.parking.service.IVehicleService;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;
import main.java.com.epam.barkou.parking.service.factory.ServiceFactory;
import main.java.com.epam.barkou.parking.view.scanner.UserInput;
import org.apache.log4j.Logger;

public class AddVehicle extends Command {

    private final static Logger log = Logger.getLogger(UserInput.class);
    private final int vehicleRegNumberIndex = 1;
    private final int vehicleTypeIndex = 2;
    private String response = null;
    private final static String MESSAGE_VEHICLE_ADDED = "Vehicle has been added successfully";
    private final static String MESSAGE_VEHICLE_NOT_ADDED = "Unable to add vehicle";

    @Override
    public String execute(String request) {

        String[] requestData = request.split(SPLITTER);

        int paramsQuantity = 2;
        if (RequestChecker.checkParamsQuantity(requestData, paramsQuantity)) {

            Vehicle.Type type;

            String regNumber = requestData[vehicleRegNumberIndex];


            type = Vehicle.Type.valueOf(requestData[vehicleTypeIndex]);

            Vehicle vehicle = new Vehicle(regNumber, type);

            ServiceFactory factory = ServiceFactory.getInstance();
            IVehicleService vehicleService = factory.getVehicleService();

            try {

                vehicleService.addNewVehicle(vehicle);
                response = MESSAGE_VEHICLE_ADDED;

            } catch (ServiceException e) {
                response = MESSAGE_VEHICLE_NOT_ADDED;
                log.error(e);

            }

        } else {
            response = MESSAGE_NOT_ENOUGH_PARAMS;
        }
        return response;
    }
}
