package rental;

import java.util.* ;
import rental.vehicleFactory.* ;

public class ReservationManager {
    private final Map<Integer, Reservation> reservations;
    private int nextReservationId;

    public ReservationManager() {
        this.reservations = new HashMap<>();
        this.nextReservationId = 1;
    }

    public Reservation createReservation(User user, Vehicle vehicle,
        RentalStore pickupStore, RentalStore returnStore, Date startDate,
        Date endDate) {
        Reservation reservation = new Reservation(nextReservationId++, user,
            vehicle, startDate, endDate , pickupStore, returnStore);
        reservations.put(reservation.getId(), reservation);
        user.addReservation(reservation);
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
}
