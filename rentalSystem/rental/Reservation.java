package rental;

import rental.vehicleFactory.* ;

public final class Reservation {
    
    private final int id ;
    private final User user ; 
    private final Vehicle vehicle ;
    private Date start ;
    private Date end ;

    @SuppressWarnings("unused")
    private RentalStore pickupRentalStore ;
    
    @SuppressWarnings("unused")
    private RentalStore returnRentalStore ;

    private final double totalAmount ;
    private ReservationStatus status ;

    Reservation ( int id , User user , Vehicle vehicle , Date start , Date end , RentalStore pickup , RentalStore returnStore ) {
        this.id = id ; this.user = user ; this.vehicle = vehicle ;
        this.start = start ; this.end = end ; 
        this.pickupRentalStore = pickup ; this.returnRentalStore = returnStore ;
        this.status = ReservationStatus.PENDING ;


        int days = daysInBetween( start , end ) ;
        this.totalAmount = vehicle.calculateRentalCost(days) ;
    }

    public int daysInBetween(Date start, Date end) {
        return Math.abs(convert(end) - convert(start));
    }

    int convert(Date d) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int total = d.year * 365 + d.day;
        for (int i = 0; i < d.month - 1; i++) total += days[i];
        total += (d.year / 4 - d.year / 100 + d.year / 400);  
        if (isLeap(d.year) && d.month > 2) total += 1;
        return total;
    }

    boolean isLeap(int y) { 
        return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0); 
    }

    public void confirmReservation() {
        if (status == ReservationStatus.PENDING) {
            status = ReservationStatus.CONFIRMED;
            vehicle.setStatus(VehicleStatus.RESERVED);
        }
    }

    public void startRental() {
        if (status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.IN_PROGRESS;
            vehicle.setStatus(VehicleStatus.RENTED);
        }
    }

    public void completeRental() {
        if (status == ReservationStatus.IN_PROGRESS) {
            status = ReservationStatus.COMPLETED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public void cancelReservation() {
        if (status == ReservationStatus.PENDING
            || status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.CANCELED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

   public Integer getId() {
        return id;
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    public void setPickupRentalStore(RentalStore pickupRentalStore) {
        this.pickupRentalStore = pickupRentalStore;
    }




}
