package main.java.com.epam.barkou.parking.dao;

import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.dao.exception.DAOException;

public interface IVehicleDAO {
	void addVehicle(Vehicle vehicle) throws DAOException;

	Vehicle getVehicle(String regNumber) throws DAOException;
}
