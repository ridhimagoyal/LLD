package parking.parkingFeeStrategy;
import parking.*; 

public interface ParkingFeeStrategy {

    public double calculateFees( String vehicleType , int duration , DurationType durationType ) ;

}
