package rental.vehicleFactory; 

public class BikeVehicle extends Vehicle {
    private static final double RATE = 1.8 ;
    private double Offroad_fee = 30.0 ;

    public double getOffroad_fee() {
        return Offroad_fee ;
    }

    public void setOffroad_fee( double fee ) {
        this.Offroad_fee = fee ;
    }
    public BikeVehicle (String registrationNumber, String model , VehicleType type , double rentalPrice ) {
        super( registrationNumber, model, type, rentalPrice ) ;
    } 

    @Override
    public double calculateRentalCost( int days ) {
        return days * getRentalPrice() * RATE + Offroad_fee ;
    }
}