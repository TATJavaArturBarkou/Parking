package main.java.com.epam.barkou.parking.service;

import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;

public interface IVehicleService {

	void addNewVehicle(Vehicle vehicle) throws ServiceException;

    Vehicle getVehicle(String regNumber) throws ServiceException;

}
