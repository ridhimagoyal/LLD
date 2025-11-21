package rental.vehicleFactory ;

public class LuxuryVehicle extends Vehicle {

    private static final double RATE = 2.5 ;
    private double Premium_fee = 50.0 ;

    public double getPremium_fee() {
        return Premium_fee;
    }

    public void setPrimium_fee( double fee ) {
        this.Premium_fee = fee ;
    }

    public LuxuryVehicle (String registrationNumber, String model , VehicleType type , double rentalPrice ) {
        super( registrationNumber, model, type, rentalPrice ) ;
    } 

    @Override
    public double calculateRentalCost( int days ) {
        return days * getRentalPrice() * RATE + Premium_fee ;
    }
}