package main.java.com.epam.barkou.parking.bean;

import java.io.Serializable;

public class SlotReservation implements Serializable{

    private static final long serialVersionUID = 3946368361694489574L;

    private String vehicleRegNumber;
    private int slotNumber;

    public SlotReservation(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlotReservation that = (SlotReservation) o;

        if (slotNumber != that.slotNumber) return false;
        return vehicleRegNumber != null ? vehicleRegNumber.equals(that.vehicleRegNumber) : that.vehicleRegNumber == null;

    }

    @Override
    public int hashCode() {
        int result = vehicleRegNumber != null ? vehicleRegNumber.hashCode() : 0;
        result = 31 * result + slotNumber;
        return result;
    }

    @Override
    public String toString() {
        return "SlotReservation{" +
                "vehicleRegNumber='" + vehicleRegNumber + '\'' +
                ", slotNumber=" + slotNumber +
                '}';
    }
}
