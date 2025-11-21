package rental;

import java.util.Scanner;

import rental.paymentStrategy.CashPayment;
import rental.paymentStrategy.CreditCardPayment;
import rental.paymentStrategy.PaymentProcessor;
import rental.paymentStrategy.PaymentStrategy;
import rental.vehicleFactory.Vehicle;
import rental.vehicleFactory.VehicleFactory;
import rental.vehicleFactory.VehicleType;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ----------------------------
        // SYSTEM INITIALIZATION
        // ----------------------------
        RentalSystem rentalSystem = RentalSystem.getInstance();

        RentalStore store1 = new RentalStore(1, "Delhi Store",
                new Location("New Delhi", "Delhi", "India", "001"));
        RentalStore store2 = new RentalStore(2, "Mumbai Store",
                new Location("Mumbai", "Maharashtra", "India", "002"));

        rentalSystem.addStore(store1);
        rentalSystem.addStore(store2);

        // ----------------------------
        // ADD VEHICLES USING FACTORY
        // ----------------------------
        Vehicle v1 = VehicleFactory.createVehicle("DL01AB1234", "Tata Nexon", VehicleType.SUV, 2500);
        Vehicle v2 = VehicleFactory.createVehicle("DL02CD5678", "Honda City", VehicleType.LUXURY, 3000);
        Vehicle v3 = VehicleFactory.createVehicle("DL05MN1111", "Royal Enfield", VehicleType.BIKE, 800);

        store1.addVehicle(v1);
        store1.addVehicle(v2);
        store1.addVehicle(v3);

        // ----------------------------
        // CREATE USER
        // ----------------------------
        int userId = rentalSystem.createUser("Ridhima", "ridhima@example.com");
        User user = rentalSystem.getUser(userId);

        // ----------------------------
        // CHECK VEHICLE AVAILABILITY
        // ----------------------------
        Date start = new Date(21, 11, 2025);
        Date end = new Date(25, 11, 2025);

        System.out.println("\nAvailable vehicles between dates:");
        for (Vehicle av : store1.getAvailableVehicles(start, end)) {
            System.out.println(" : " + av.getRegistrationNumber() + " : " + av.getModel());
        }

        // ----------------------------
        // CREATE RESERVATION
        // ----------------------------
        Reservation reservation = rentalSystem.createReservation(
                userId,
                "DL01AB1234",
                1,
                2,
                start,
                end
        );

        if (reservation != null) {
            System.out.println("\nReservation Created ID: " + reservation.getId());
            System.out.println("Total Amount: Rs. " + reservation.getTotalAmount());
        }

        // ----------------------------
        // CONFIRM RESERVATION
        // ----------------------------
        rentalSystem.getReservationManager().confirmReservation(reservation.getId());
        System.out.println("Reservation Confirmed.");

        // ----------------------------
        // PAYMENT STRATEGY
        // ----------------------------
        PaymentStrategy paymentStrategy = null;

        while (paymentStrategy == null) {
            System.out.println("\nChoose payment method:");
            System.out.println("1. Cash");
            System.out.println("2. Credit Card");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    paymentStrategy = new CashPayment();
                    break;

                case 2:
                    paymentStrategy = new CreditCardPayment();
                    break;

                default:
                    System.out.println("Invalid option! Try again.\n");
            }
        }

        // call process with amount
        double amount = reservation.getTotalAmount();
        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment(amount, paymentStrategy);

        // ----------------------------
        // START RENTAL
        // ----------------------------
        rentalSystem.getReservationManager().startRental(reservation.getId());
        System.out.println("\nRental Started. Vehicle is now out of the store.");

        // ----------------------------
        // COMPLETE RENTAL
        // ----------------------------
        rentalSystem.getReservationManager().completeRental(reservation.getId());
        System.out.println("Rental Completed. Vehicle returned to return store.");

        // ----------------------------
        // FINAL CHECK: STORE INVENTORY
        // ----------------------------
        System.out.println("\nVehicles now at Delhi Store: ");
        for (Vehicle v : store1.getAvailableVehicles(start, end)) {
            System.out.println(" Delhi Store :  " + v.getRegistrationNumber());
        }

        System.out.println("\nVehicles now at Mumbai Store: ");
        for (Vehicle v : store2.getAvailableVehicles(start, end)) {
            System.out.println("\n Mumbai Store :" + v.getRegistrationNumber());
        }

        sc.close();
    }
}
