package rental;

import rental.vehicleFactory.* ;
import java.util.*;

/**
 * ReservationManager manages reservations and a vehicle calendar.
 * - Prevents overlap
 * - Blocks vehicle dates on creation (so no subsequent reservation passes)
 * - Provides utility to get vehicle status for a date range
 */
public class ReservationManager {
    private final Map<Integer, Reservation> reservations;
    private int nextReservationId;

    // (Optional) vehicle -> list of reservations for reporting / checks
    private final Map<String, List<Reservation>> vehicleCalendar;

    public ReservationManager() {
        this.reservations = new HashMap<>();
        this.nextReservationId = 1;
        this.vehicleCalendar = new HashMap<>();
    }

    // helper: two ranges overlap if start1 <= end2 AND start2 <= end1
    private boolean isOverlap(Date s1, Date e1, Date s2, Date e2) {
        return s1.isBeforeOrEqual(e2) && s2.isBeforeOrEqual(e1);
    }

    public Reservation createReservation(User user, Vehicle vehicle,
                                         RentalStore pickupStore, RentalStore returnStore,
                                         Date startDate, Date endDate) {
        if (startDate.compare(endDate) > 0) {
            System.out.println("Start date is after end date.");
            return null;
        }

        String reg = vehicle.getRegistrationNumber();
        List<Reservation> list = vehicleCalendar.getOrDefault(reg, new ArrayList<>());

        // check overlaps against active reservations
        for (Reservation r : list) {
            if (r.getStatus() != ReservationStatus.CANCELED &&
                r.getStatus() != ReservationStatus.COMPLETED &&
                isOverlap(r.getStartDate(), r.getEndDate(), startDate, endDate)) {
                System.out.println("❌ Vehicle already booked in this date range: " + reg);
                return null;
            }
        }

        // if available -> create reservation and block dates on vehicle
        Reservation reservation = new Reservation(nextReservationId++, user, vehicle,
                startDate, endDate, pickupStore, returnStore);

        // Block dates on vehicle calendar to prevent double booking
        vehicle.blockDates(startDate, endDate);

        reservations.put(reservation.getId(), reservation);
        user.addReservation(reservation);
        list.add(reservation);
        vehicleCalendar.put(reg, list);

        return reservation;
    }

    public void confirmReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.confirmReservation();
        }
    }

    public void startRental(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.startRental();
        }
    }

    public void completeRental(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.completeRental();
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.cancelReservation();
        }
    }

    public Reservation getReservation(int reservationId) {
        return reservations.get(reservationId);
    }

    // Determine vehicle status for a given date range:
    // if overlap with CONFIRMED reservation => RESERVED, else AVAILABLE
    public VehicleStatus getVehicleStatusOnDate(Vehicle vehicle, Date start, Date end) {
        String reg = vehicle.getRegistrationNumber();
        List<Reservation> list = vehicleCalendar.getOrDefault(reg, new ArrayList<>());
        for (Reservation r : list) {
            if (r.getStatus() == ReservationStatus.CONFIRMED &&
                isOverlap(r.getStartDate(), r.getEndDate(), start, end)) {
                return VehicleStatus.RESERVED;
            }
        }
        return VehicleStatus.AVAILABLE;
    }
}
