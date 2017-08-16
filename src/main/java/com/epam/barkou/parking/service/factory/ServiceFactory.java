package main.java.com.epam.barkou.parking.service.factory;

import main.java.com.epam.barkou.parking.service.ISlotService;
import main.java.com.epam.barkou.parking.service.IVehicleService;

import main.java.com.epam.barkou.parking.service.impl.SlotServiceImpl;
import main.java.com.epam.barkou.parking.service.impl.VehicleServiceImpl;

public class ServiceFactory {
	public static final ServiceFactory instance = new ServiceFactory();
	private final ISlotService slotService = new SlotServiceImpl();
	private final IVehicleService vehicleService = new VehicleServiceImpl();

	private ServiceFactory() {

	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public ISlotService getSlotService() {
		return slotService;
	}

	public IVehicleService getVehicleService() {
		return vehicleService;
	}

}
