package rental.vehicleFactory;

import rental.Date;
import java.util.*;

/**
 * Abstract Vehicle with a daily calendar map for booking status.
 * Calendar key: "dd-mm-yyyy" string
 */
public abstract class Vehicle {

    private final String registrationNumber;
    private final String model;
    private final VehicleType type;
    private VehicleStatus status;
    private double rentalPrice;

    // daily calendar: key = "dd-mm-yyyy" -> true if booked
    private final Map<String, Boolean> dailyCalendar = new HashMap<>();

    public Vehicle(String model, String registrationNumber, VehicleType type, double rentalPrice) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.status = VehicleStatus.AVAILABLE;
        this.rentalPrice = rentalPrice;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double price) {
        this.rentalPrice = price;
    }

    public String key(Date d) {
        return String.format("%02d-%02d-%04d", d.day, d.month, d.year);
    }

    // check if ALL days in [start,end] are free (not booked)
    public boolean isAvailable(Date start, Date end) {
        Date cur = start;
        while (cur.isBeforeOrEqual(end)) {
            if (dailyCalendar.getOrDefault(key(cur), false)) {
                return false;
            }
            cur = cur.nextDay();
        }
        return true;
    }

    // block all days in [start,end]
    public void blockDates(Date start, Date end) {
        Date cur = start;
        while (cur.isBeforeOrEqual(end)) {
            dailyCalendar.put(key(cur), true);
            cur = cur.nextDay();
        }
    }

    // free all days in [start,end]
    public void freeDates(Date start, Date end) {
        Date cur = start;
        while (cur.isBeforeOrEqual(end)) {
            dailyCalendar.remove(key(cur));
            cur = cur.nextDay();
        }
    }

    // calculate cost (default simple price * days), subclasses may override
    public int daysBetween(Date s, Date e) {
        int days = 0;
        Date cur = s;
        while (cur.isBeforeOrEqual(e)) {
            days++;
            cur = cur.nextDay();
        }
        return days;
    }

    public abstract double calculateRentalCost(int days);
}
