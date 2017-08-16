package main.java.com.epam.barkou.parking.bean;

import java.io.Serializable;

public class Slot implements Serializable {

    private static final long serialVersionUID = -7592097432714554856L;
    private int number;
    private Size size;
    private boolean covered;

    public enum Size{
        SMALL, REGULAR;

    }

    public Slot(Size size, boolean covered) {
        this.size = size;
        this.covered = covered;
    }
    public Slot(int number, boolean covered) {
        this.number = number;
        this.covered = covered;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slot slot = (Slot) o;

        if (number != slot.number) return false;
        if (covered != slot.covered) return false;
        return size == slot.size;

    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + size.hashCode();
        result = 31 * result + (covered ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "number=" + number +
                ", size=" + size +
                ", covered=" + covered +
                '}';
    }
}
