package rental.vehicleFactory; 

public class SuvVehicle extends Vehicle {
    private static final double RATE = 1.8 ;

    public SuvVehicle (String registrationNumber, String model , VehicleType type , double rentalPrice ) {
        super( registrationNumber, model, type, rentalPrice ) ;
    } 

    @Override
    public double calculateRentalCost( int days ) {
        return days * getRentalPrice() * RATE ;
    }
}