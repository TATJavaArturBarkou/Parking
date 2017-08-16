package main.java.com.epam.barkou.parking.service.impl;

import main.java.com.epam.barkou.parking.bean.Slot;
import main.java.com.epam.barkou.parking.bean.SlotReservation;
import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.dao.ISlotDAO;
import main.java.com.epam.barkou.parking.dao.exception.DAOException;
import main.java.com.epam.barkou.parking.dao.factory.DAOFactory;
import main.java.com.epam.barkou.parking.service.ISlotService;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;

public class SlotServiceImpl implements ISlotService {
    private DAOFactory daoObjectFactory = DAOFactory.getInstance();
    private ISlotDAO slotDAO = daoObjectFactory.getSlotDAO();

    private static final String ERROR_SLOT_ALREADY_OCCUPIED = "Fail. Slot has already been occupied or not suitable";
    private static final String ERROR_INCORRECT_SLOT_DATA = "Incorrect slot data";
    private static final String ERROR_INCORRECT_TYPE_DATA = "Incorrect type data";
    private final static String ERROR_INCORRECT_VEHICLE_OR_SLOT_DATA = "Incorrect vehicle or slot data";
    private final static String ERROR_INCORRECT_SLOT_RESERVATION_DATA = "Incorrect slot reservation data";

    @Override
    public void addSlot(Slot slot) throws ServiceException {
        if (slot == null) {
            throw new ServiceException(ERROR_INCORRECT_SLOT_DATA);
        }
        try {
            slotDAO.addSlot(slot);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Slot findFreeSlot(Vehicle.Type type, boolean covered) throws ServiceException {

        if (type == null) {
            throw new ServiceException(ERROR_INCORRECT_TYPE_DATA);
        }

        Slot slot = null;
        try {

            slot = slotDAO.findFreeSlot(type, covered);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return slot;
    }

    @Override
    public void occupy(Vehicle vehicle, int slotNumber) throws ServiceException {
        if (vehicle == null || slotNumber < 1) {
            throw new ServiceException(ERROR_INCORRECT_VEHICLE_OR_SLOT_DATA);
        }

        try {

            boolean slotIsFree = slotDAO.checkSlotIsFree(slotNumber);

            boolean slotSuitsToType = slotDAO.checkSlotSuitsVehicleType(vehicle.getType(), slotNumber);
            if (slotIsFree && slotSuitsToType) {

                slotDAO.occupy(vehicle, slotNumber);

            } else {
                throw new ServiceException(ERROR_SLOT_ALREADY_OCCUPIED);
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean freeSlot(SlotReservation slotReservation) throws ServiceException {
        if (slotReservation == null) {
            throw new ServiceException(ERROR_INCORRECT_SLOT_RESERVATION_DATA);
        }

        try {
            int zero = 0;
            int affectedRows = slotDAO.freeSlot(slotReservation);
            if (affectedRows > zero) {
                return true;
            } else {
                return false;
            }


        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }
}
