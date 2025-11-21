package rental ;

import java.util.Scanner;

import rental.paymentStrategy.CashPayment;
import rental.paymentStrategy.CreditCardPayment;
import rental.paymentStrategy.PaymentStrategy;
import rental.vehicleFactory.Vehicle;
import rental.vehicleFactory.VehicleFactory;
import rental.vehicleFactory.VehicleType;

public class Main {

    public static void main(String[] args) {
        
    
        RentalSystem rentalSystem = RentalSystem.getInstance();

        // Create rental stores
        RentalStore store1 = new RentalStore(
            1, "Downtown Rentals", new Location("123 Main St", "New York", "NY", "10001"));
        RentalStore store2 = new RentalStore(
            2, "Airport Rentals", new Location("456 Airport Rd", "Los Angeles", "CA", "90045"));
        rentalSystem.addStore(store1);
        rentalSystem.addStore(store2);

        // Create vehicles using Factory Pattern
        Vehicle economyCar = VehicleFactory.createVehicle("EC001", "Toyota" ,
            VehicleType.SUV,   50.0);
        Vehicle luxuryCar = VehicleFactory.createVehicle(  "LX001", "Mercedes",
            VehicleType.LUXURY, 200.0);
        Vehicle suvCar = VehicleFactory.createVehicle("SV001", "Honda", 
            VehicleType.SUV,  75.0);

        // Add vehicles to stores
        store1.addVehicle(economyCar);
        store1.addVehicle(luxuryCar);
        store2.addVehicle(suvCar);

         // Register user
        User user1 = new User(123, "ABC" , "abc@gmail.com");
        User user2 = new User(345 , "BCD" , "bcd@yahoo.com");


        rentalSystem.registerUser(user1);
        rentalSystem.registerUser(user2);

        // Create reservations
        Reservation reservation1 = rentalSystem.createReservation(user1.getId(), economyCar.getRegistrationNumber(),
                        store1.getId(), store1.getId(), new Date(20, 4, 2025),
                new Date(20, 5, 2025));

        // Process payment using different strategies (Strategy Pattern)
        Scanner scanner = new Scanner(System.in);
        System.out.println("nProcessing payment for reservation #" + reservation1.getId());
        System.out.println("Total amount: $" + reservation1.getTotalAmount());
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash") ;

        int choice = scanner.nextInt();
        PaymentStrategy paymentStrategy;
        switch (choice) {
            case 1:
                paymentStrategy = new CreditCardPayment();
                break;
            case 2:
                paymentStrategy = new CashPayment();
                break;
            default:
                System.out.println("Invalid choice! Defaulting to credit card payment.");
                paymentStrategy = new CreditCardPayment();
                break;
        }


        boolean paymentSuccess = rentalSystem.processPayment(reservation1.getId(), paymentStrategy);
        if (paymentSuccess) {
            System.out.println("Payment successful!");

            // Start the rental
            rentalSystem.startRental(reservation1.getId());

            // Simulate rental period
            System.out.println("Simulating rental period...");

            // Complete the rental
            rentalSystem.completeRental(reservation1.getId());
        } else {
            System.out.println("Payment failed!");
        }
    }

}