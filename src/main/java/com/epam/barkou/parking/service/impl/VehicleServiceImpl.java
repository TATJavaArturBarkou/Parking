package main.java.com.epam.barkou.parking.service.impl;

import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.dao.IVehicleDAO;
import main.java.com.epam.barkou.parking.dao.exception.DAOException;
import main.java.com.epam.barkou.parking.dao.factory.DAOFactory;
import main.java.com.epam.barkou.parking.service.IVehicleService;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;

public class VehicleServiceImpl implements IVehicleService {
    private DAOFactory daoObjectFactory = DAOFactory.getInstance();
    private IVehicleDAO vehicleDAO = daoObjectFactory.getVehicleDAO();

	
	private final static String ERROR_INCORRECT_VEHICLE_DATA = "Incorrect vehicle data";
	private final static String ERROR_INCORRECT_REG_NUMBER_DATA = "Incorrect regNumber data";
	@Override
	public void addNewVehicle(Vehicle vehicle) throws ServiceException {

		if (vehicle == null) {
			throw new ServiceException(ERROR_INCORRECT_VEHICLE_DATA);
		}
		try {
			vehicleDAO.addVehicle(vehicle);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Vehicle getVehicle(String regNumber) throws ServiceException {

		if (regNumber == null||regNumber.isEmpty()) {
			throw new ServiceException(ERROR_INCORRECT_REG_NUMBER_DATA);
		}

		try {
			return vehicleDAO.getVehicle(regNumber);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}



}
