package parking.vehicleFactory;

import parking.DurationType;
import parking.parkingFeeStrategy.ParkingFeeStrategy;

public abstract class Vehicle {

    String type ; String numberPlate ; ParkingFeeStrategy parkingFeeStrategy ;
    VehicleSize size ;

    Vehicle ( String type , String numberPlate , ParkingFeeStrategy parkingFeeStrategy , VehicleSize size ) {
        this.type = type ; 
        this.numberPlate = numberPlate ;
        this.parkingFeeStrategy = parkingFeeStrategy ;
        this.size = size ;
    }

    public String getVehicleType() { return type ; } 

    public String getLicensePlate() { return numberPlate ; } 

    public VehicleSize getSize() { return size; }

    public double calculateFees( int duration , DurationType durationType ) {
        return parkingFeeStrategy.calculateFees(type, duration, durationType) ;
    }


}
