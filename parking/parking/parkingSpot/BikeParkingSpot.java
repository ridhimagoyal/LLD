package parking.parkingSpot;

import parking.vehicleFactory.Vehicle;
import parking.vehicleFactory.VehicleSize;

public class BikeParkingSpot extends ParkingSpot {

    public BikeParkingSpot ( int number , VehicleSize size ) {
        super ( number , "bike" , size ) ;
    }
    
    @Override
    public boolean canPark( Vehicle vehicle ) {
        return vehicle.getVehicleType() != null
            && vehicle.getVehicleType().trim().equalsIgnoreCase("bike")
            && supportsSize(vehicle) ;
    }

}
