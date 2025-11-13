package parking.parkingFeeStrategy;

import parking.*; 

public class RegularFeeStrategy implements ParkingFeeStrategy {

    @Override
    public double calculateFees ( String vehicleType , int duration , DurationType durationType ) {

        return switch (vehicleType.toLowerCase()) {
            case "car" -> durationType == DurationType.HOURS 
                ? duration*10.0
                : duration*24.0*8.0;
            case "bike" -> durationType == DurationType.HOURS 
                ? duration*8.0
                : duration*24.0*6.0;
            default -> durationType == DurationType.HOURS 
                ? duration * 15.0
                : duration * 15.0 * 24;
        };
    }

}
