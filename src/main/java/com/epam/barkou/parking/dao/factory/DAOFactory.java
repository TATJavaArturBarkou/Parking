package main.java.com.epam.barkou.parking.dao.factory;

import main.java.com.epam.barkou.parking.dao.IVehicleDAO;

import main.java.com.epam.barkou.parking.dao.ISlotDAO;
import main.java.com.epam.barkou.parking.dao.impl.SQLVehicleDAO;

import main.java.com.epam.barkou.parking.dao.impl.SQLSlotDAO;


public final class DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactory();

	private final IVehicleDAO sqlVehicleImpl = new SQLVehicleDAO();
	private final ISlotDAO sqlUserImpl = new SQLSlotDAO();

	
	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public IVehicleDAO getVehicleDAO() {
		return sqlVehicleImpl;
	}
	public ISlotDAO getSlotDAO() {
		return sqlUserImpl;
	}

}
