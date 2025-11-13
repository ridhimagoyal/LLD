package parking.vehicleFactory;

import parking.parkingFeeStrategy.ParkingFeeStrategy;

public class Car extends Vehicle {
    Car( String type , String numberPlate , ParkingFeeStrategy parkingFeeStrategy ,  VehicleSize size ) {
        super ( type , numberPlate , parkingFeeStrategy , size) ;
    }
}
