package main.java.com.epam.barkou.parking.controller.command.impl;


import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.controller.command.Command;
import main.java.com.epam.barkou.parking.service.IVehicleService;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;
import main.java.com.epam.barkou.parking.service.factory.ServiceFactory;
import main.java.com.epam.barkou.parking.view.scanner.UserInput;
import org.apache.log4j.Logger;

public class GetVehicle extends Command {
	private final static Logger log = Logger.getLogger(UserInput.class);
	private final static String MESSAGE_UNABLE = "Unable to get vehicle";

	private String response = null;
	
	private final int vehicleRegNumberIndex = 1;

	@Override
	public String execute(String request) {

		String[] requestData = request.split(SPLITTER);

		ServiceFactory factory = ServiceFactory.getInstance();
		IVehicleService vehicleService = factory.getVehicleService();

		try {

            Vehicle vehicle = vehicleService.getVehicle(requestData[vehicleRegNumberIndex]);

            response = vehicle.toString();

        } catch (ServiceException e) {
			response = MESSAGE_UNABLE;
			log.error(e);
		}

		return response;
	}



}
