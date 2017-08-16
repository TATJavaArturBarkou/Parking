package main.java.com.epam.barkou.parking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.epam.barkou.parking.bean.Slot;
import main.java.com.epam.barkou.parking.bean.SlotReservation;
import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.dao.ISlotDAO;
import main.java.com.epam.barkou.parking.dao.exception.DAOException;
import main.java.com.epam.barkou.parking.dao.util.SQLConnection;

public class SQLSlotDAO implements ISlotDAO {

    private SQLConnection sqlConnection = SQLConnection.getInstance();

    private final static String SQL_ADD_SLOT = "INSERT INTO slots (size, covered) VALUES (?,?)";
    private final static String SQL_ADD_RESERVATION = "INSERT INTO reservations (vehicle_reg_number, slot_number) VALUES (?,?)";
    private final static String SQL_GET_FREE_SLOT = "SELECT number, covered FROM slots JOIN slot_capacity ON slots.size=slot_capacity.size " +
            "WHERE slot_capacity.type=? AND covered=? AND number IN " +
            "(SELECT number FROM slots LEFT JOIN reservations ON slots.number=reservations.slot_number " +
            "WHERE reservations.slot_number IS NULL) LIMIT 1;";
    private final static String SQL_CHECK_SLOT_IS_FREE = "SELECT number FROM slots JOIN reservations ON slots.number=reservations.slot_number WHERE number=?;";
    private final static String SQL_CHECK_SLOT_SUITS_VEHICLE_TYPE = "SELECT number FROM slots JOIN slot_capacity ON slots.size=slot_capacity.size " +
            "WHERE slot_capacity.type=? AND number=?;";
    private final static String SQL_DELETE_RESERVATION = "DELETE FROM reservations WHERE vehicle_reg_number=?";

    private final static String SLOT_NUMBER = "number";
    private final static String SLOT_COVERED = "covered";

    @Override
    public void addSlot(Slot slot) throws DAOException {


        final int userSizeIndex = 1;
        final int userCoveredIndex = 2;

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_ADD_SLOT)) {

            ps.setString(userSizeIndex, slot.getSize().toString());
            ps.setBoolean(userCoveredIndex, slot.isCovered());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new DAOException(e);

        }
    }

    @Override
    public Slot findFreeSlot(Vehicle.Type type, boolean covered) throws DAOException {

        ResultSet resultSet = null;
        Slot slot = null;

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_FREE_SLOT)) {

            final int vehicleTypeIndex = 1;
            final int slotCoveredIndex = 2;


            String typeString = type.toString();
            ps.setString(vehicleTypeIndex, typeString);
            ps.setBoolean(slotCoveredIndex, covered);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                slot = new Slot(resultSet.getInt(SLOT_NUMBER), resultSet.getBoolean(SLOT_COVERED));
            }

        } catch (SQLException e) {

            throw new DAOException(e);

        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {

                throw new DAOException(e);
            }
        }

        return slot;
    }

    @Override
    public void occupy(Vehicle vehicle, int slotNumber) throws DAOException {

        final int vehicleRegNumberIndex = 1;
        final int slotNumberIndex = 2;

        try (Connection connection = sqlConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_RESERVATION);
            String regNumber = vehicle.getRegNumber();

            ps.setString(vehicleRegNumberIndex, regNumber);
            ps.setInt(slotNumberIndex, slotNumber);

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new DAOException(e);

        }
    }

    @Override
    public boolean checkSlotIsFree(int slotNumber) throws DAOException {

        ResultSet resultSet = null;

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CHECK_SLOT_IS_FREE)) {

            final int slotNumberIndex = 1;

            ps.setInt(slotNumberIndex, slotNumber);

            resultSet = ps.executeQuery();

            int result = 0;
            final int zero = 0;
            while (resultSet.next()) {

                result = resultSet.getInt(SLOT_NUMBER);
            }

            if (result > zero) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {

            throw new DAOException(e);

        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {

                throw new DAOException(e);
            }
        }
    }

    @Override
    public boolean checkSlotSuitsVehicleType(Vehicle.Type type, int slotNumber) throws DAOException {

        ResultSet resultSet = null;

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_CHECK_SLOT_SUITS_VEHICLE_TYPE)) {

            final int vehicleTypeIndex = 1;
            final int slotNumberIndex = 2;

            String typeString = type.toString();
            ps.setString(vehicleTypeIndex, typeString);
            ps.setInt(slotNumberIndex, slotNumber);

            resultSet = ps.executeQuery();
            int result = 0;
            final int zero = 0;
            while (resultSet.next()) {

                result = resultSet.getInt(SLOT_NUMBER);
            }

            if (result > zero) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {

            throw new DAOException(e);

        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {

                throw new DAOException(e);
            }
        }


    }

    @Override
    public int freeSlot(SlotReservation slotReservation) throws DAOException {
        final int vehicleRegNumberIndex = 1;

        try (Connection connection = sqlConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_RESERVATION);
            String regNumber = slotReservation.getVehicleRegNumber();

            ps.setString(vehicleRegNumberIndex, regNumber);

            int rowAffected = ps.executeUpdate();

          return rowAffected;

        } catch (SQLException e) {

            throw new DAOException(e);

        }
    }

}

