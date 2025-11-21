package rental.vehicleFactory;

public abstract class Vehicle {

    private final String registrationNumber ;
    private final String model ;
    private final VehicleType type ;
    private VehicleStatus status ;
    private double rentalPrice ;

    public void setRentalPrice( double price ) {
        this.rentalPrice = price ;
    }

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

    public void setStatus( VehicleStatus status ) {
        this.status = status ;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public abstract double calculateRentalCost( int days ) ;
} 