package main.java.com.epam.barkou.parking.dao;

import main.java.com.epam.barkou.parking.bean.Slot;
import main.java.com.epam.barkou.parking.bean.SlotReservation;
import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.dao.exception.DAOException;

public interface ISlotDAO {
    void addSlot(Slot slot) throws DAOException;

    Slot findFreeSlot(Vehicle.Type type, boolean covered) throws DAOException;

    void occupy(Vehicle vehicle, int slotNumber) throws DAOException;

    boolean checkSlotIsFree(int slotNumber)throws DAOException;

    boolean checkSlotSuitsVehicleType(Vehicle.Type type, int slotNumber) throws DAOException;

    int freeSlot(SlotReservation slotReservation) throws DAOException;
}
