package parking.vehicleFactory;

import parking.parkingFeeStrategy.ParkingFeeStrategy;

public class VehicleFactory {
    
    public static Vehicle createVehicle ( String type , String numPlate , ParkingFeeStrategy feeStrategy , VehicleSize size ) {
        switch ( type.toLowerCase() ) {
            case "car" -> { 
                return new Car ( type, numPlate, feeStrategy , size ) ;
            }
            case "bike" -> {
                return new Bike ( type , numPlate, feeStrategy , size ) ;
            }
            default -> throw new IllegalStateException( " The vehicle type " + type + " doesn't exist!!! " ) ;
        }
    }

}
