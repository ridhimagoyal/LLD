package rental;

import java.util.*;

public class User {

    private final int id;
    private final String name;
    private final String email;
    private final List<Reservation> reservations;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reservations = new ArrayList<>();
    }

    public void getReservationList() {
        System.out.println( "The reservations of user :  " + name + " id : " + id );
        for ( Reservation r : reservations ) {
            System.out.println( r.getId() );
        }
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void deleteReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public String getName() {
        return name ;
    }

    public String getEmail() {
        return email ;
    }

    public int getId() {
        return id;
    }
}
