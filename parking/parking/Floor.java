package parking;

import parking.parkingSpot.ParkingSpot;
import parking.vehicleFactory.Vehicle;
import java.util.List;

public class Floor {
    final int floorNumber;
    final List<ParkingSpot> spots;

    public Floor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public void getFloorInfo() {
        System.out.println("Floor Number: " + floorNumber);
        for (ParkingSpot spot : spots) {
            spot.spotInfo();
        }
    }

    public int getFloorNumber() { return floorNumber; }

    public ParkingSpot findAvailableSpotForVehicle(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (!s.isOccupied() && s.canPark(v)) {
                return s;
            }
        }
        return null;
    }
}