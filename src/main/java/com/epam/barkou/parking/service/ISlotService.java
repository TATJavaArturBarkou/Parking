package main.java.com.epam.barkou.parking.service;

import main.java.com.epam.barkou.parking.bean.Slot;
import main.java.com.epam.barkou.parking.bean.SlotReservation;
import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;

public interface ISlotService {

	void addSlot(Slot slot) throws ServiceException;

	Slot findFreeSlot(Vehicle.Type type, boolean covered) throws ServiceException;

	void occupy(Vehicle vehicle, int slotNumber) throws ServiceException;

	boolean freeSlot(SlotReservation slotReservation) throws ServiceException;
}
