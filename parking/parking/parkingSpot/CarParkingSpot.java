package parking.parkingSpot;

import parking.vehicleFactory.*;

public class CarParkingSpot extends ParkingSpot {

    @Override
    public boolean canPark(Vehicle vehicle) {
        return vehicle.getVehicleType() != null
            && vehicle.getVehicleType().trim().equalsIgnoreCase("car")
            && supportsSize(vehicle);
    }

    public CarParkingSpot ( int number , VehicleSize maxSize ) {
        super ( number , "car" , maxSize ) ;
    }
    
}
