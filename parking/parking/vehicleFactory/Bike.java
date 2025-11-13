package parking.vehicleFactory;

import parking.parkingFeeStrategy.ParkingFeeStrategy;

public class Bike extends Vehicle {
    Bike ( String type , String numberPlate , ParkingFeeStrategy parkingFeeStrategy , VehicleSize size ) {
        super ( type , numberPlate , parkingFeeStrategy , size  ) ;
    }
}
