package rental.vehicleFactory;

public class VehicleFactory {
    public static Vehicle createVehicle(String regNumber , String model , VehicleType type, double baseRentalPrice) {
        switch (type) {
            case SUV -> {
                return new SuvVehicle (regNumber , model, type , baseRentalPrice);
            }
            case LUXURY -> {
                return new LuxuryVehicle (regNumber , model, type , baseRentalPrice);
            }
            case BIKE -> {
                return new BikeVehicle (regNumber , model, type , baseRentalPrice);
            }
            default -> throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}