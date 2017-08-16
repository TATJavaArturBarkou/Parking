package main.java.com.epam.barkou.parking.bean;

import java.io.Serializable;


public class Vehicle implements Serializable {

    private static final long serialVersionUID = 7465158856979865990L;

    private int id;
    private Type type;
    private String regNumber;

    public enum Type {
        CAR, MOTORCYCLE;
    }

    public Vehicle(String regNumber, Type type) {
        this.type = type;
        this.regNumber = regNumber;
    }

    public Vehicle(int id, String regNumber, Type type) {
        this.id = id;
        this.type = type;
        this.regNumber = regNumber;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type=" + type +
                ", regNumber='" + regNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (id != vehicle.id) return false;
        if (type != vehicle.type) return false;
        return regNumber.equals(vehicle.regNumber);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type.hashCode();
        result = 31 * result + regNumber.hashCode();
        return result;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
}
