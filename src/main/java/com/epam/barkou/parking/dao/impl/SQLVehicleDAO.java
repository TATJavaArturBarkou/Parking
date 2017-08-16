package main.java.com.epam.barkou.parking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.epam.barkou.parking.bean.Vehicle;
import main.java.com.epam.barkou.parking.dao.IVehicleDAO;
import main.java.com.epam.barkou.parking.dao.exception.DAOException;
import main.java.com.epam.barkou.parking.dao.util.SQLConnection;

public class SQLVehicleDAO implements IVehicleDAO {


    private final static String VEHICLE_ID = "id";
    private final static String VEHICLE_REG_NUMBER = "reg_number";
    private final static String VEHICLE_TYPE = "type";

    private final static String SQL_ADD_VEHICLE = "INSERT INTO vehicles (reg_number, type) VALUES (?,?)";
    private static final String GET_AVAILABLE_VEHICLE = "SELECT id, reg_number, type FROM vehicles WHERE reg_number=?;";
    private SQLConnection sqlConnection = SQLConnection.getInstance();



    public void addVehicle(Vehicle vehicle) throws DAOException {

        int vehicleReqNumberIndex = 1;
        int vehicleTypeIndex = 2;
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_ADD_VEHICLE)) {

            ps.setString(vehicleReqNumberIndex, vehicle.getRegNumber());
            ps.setString(vehicleTypeIndex, vehicle.getType().toString());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new DAOException(e);

        }

    }

    public Vehicle getVehicle(String regNumber) throws DAOException {


        Vehicle vehicle = null;

        ResultSet resultSet = null;

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_AVAILABLE_VEHICLE);
        ) {

            int vehicleIdIndex = 1;

            ps.setString(vehicleIdIndex, regNumber);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                vehicle = new Vehicle(resultSet.getInt(VEHICLE_ID), resultSet.getString(VEHICLE_REG_NUMBER), Vehicle.Type.valueOf(resultSet.getString(VEHICLE_TYPE)));

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

        return vehicle;
    }


}
