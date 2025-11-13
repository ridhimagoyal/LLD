package parking;

import java.util.* ;

import parking.parkingFeeStrategy.ParkingFeeStrategy;
import parking.parkingSpot.*;
import parking.parkingFeeStrategy.RegularFeeStrategy;
import parking.vehicleFactory.* ;


public class ParkingLotMain {
    public static void main(String[] args) {

        List<ParkingSpot> floor1Spots = new ArrayList<>() ;
        floor1Spots.add( new CarParkingSpot( 1, parking.vehicleFactory.VehicleSize.MEDIUM ) ) ;
        floor1Spots.add( new BikeParkingSpot( 2 , parking.vehicleFactory.VehicleSize.LARGE ) ) ;

        // create spots for floor 2
        List<ParkingSpot> floor2Spots = new ArrayList<>() ;
        floor2Spots.add( new CarParkingSpot( 3, parking.vehicleFactory.VehicleSize.LARGE ) ) ;
        floor2Spots.add( new BikeParkingSpot( 4 , parking.vehicleFactory.VehicleSize.LARGE ) ) ;

        Floor f1 = new Floor(1, floor1Spots);
        Floor f2 = new Floor(2, floor2Spots);

        ParkingFeeStrategy regulFeeStrategy = new RegularFeeStrategy() ;
        ParkingService service = new ParkingService(List.of(f1, f2), regulFeeStrategy);

        Vehicle car1 = VehicleFactory.createVehicle ( "Car" , "KA-01-12345" , regulFeeStrategy , parking.vehicleFactory.VehicleSize.MEDIUM ) ;
        Vehicle bike1 = VehicleFactory.createVehicle ( "Bike" , "KA-01-54321" , regulFeeStrategy , parking.vehicleFactory.VehicleSize.SMALL ) ;

        var ticket1 = service.parkVehicle(car1);
        System.out.println("Ticket1 id: " + (ticket1 == null ? "NO_SPACE" : ticket1.getTicketId()));

        var ticket2 = service.parkVehicle(bike1);
        System.out.println("Ticket2 id: " + (ticket2 == null ? "NO_SPACE" : ticket2.getTicketId()));
        
    }
}
