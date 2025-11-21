package rental.vehicleFactory;

public class VehicleFactory {
    // NOTE: constructor order corrected -> model then regNumber
    public static Vehicle createVehicle(String regNumber, String model, VehicleType type, double baseRentalPrice) {
        // This method signature is kept as regNumber first (to match existing call sites).
        // Internally we pass correct order (model, regNumber) into constructors.
        switch (type) {
            case SUV -> {
                return new SuvVehicle(model, regNumber, type, baseRentalPrice);
            }
            case LUXURY -> {
                return new LuxuryVehicle(model, regNumber, type, baseRentalPrice);
            }
            case BIKE -> {
                return new BikeVehicle(model, regNumber, type, baseRentalPrice);
            }
            default -> throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
