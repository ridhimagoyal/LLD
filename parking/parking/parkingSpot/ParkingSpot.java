package parking.parkingSpot;
import parking.vehicleFactory.Vehicle;
import parking.vehicleFactory.VehicleSize; 

public abstract class ParkingSpot {
    
    private final int spotNumber ;
    private final String spotType ;
    private boolean isOccupied ; 
    private Vehicle vehicle ;
    private final VehicleSize maxSize; 

    public ParkingSpot ( int spotNumber  , String spotType , VehicleSize maxSize) {
        this.spotNumber = spotNumber ;
        this.spotType = spotType ;
        this.isOccupied = false ;
        this.maxSize = maxSize;

    }

    public void spotInfo() {
        System.out.println( " Spot Number: " + spotNumber + " | Spot Type: " + spotType + " | Is Occupied: " + isOccupied ) ;
        if ( isOccupied ) {
            System.out.println( "   -> Occupied by Vehicle Type: " + vehicle.getVehicleType() + " | Number Plate: " + vehicle.getLicensePlate() ) ;
        }
    }

    protected boolean supportsSize(Vehicle vehicle) {
        if (vehicle == null || vehicle.getSize() == null) return false;
        return vehicle.getSize().ordinal() <= maxSize.ordinal();
    }

    public boolean isOccupied () {
        return isOccupied ;
    }

    public abstract boolean canPark( Vehicle vehicle ) ;

    public void parkVehicle ( Vehicle vehicle ){
        if ( isOccupied ) {
            throw new IllegalStateException( " Parking Spot is already occupied ! " ) ;
        }
        if ( !canPark(vehicle ) ) {
            throw new IllegalStateException( " This vehicle spot cannot be parked in this spot ! " ) ;
        }
        System.out.println( " Succucessful Parking of vehicle: " + vehicle.getVehicleType() + " in spot number " + spotNumber ) ;
        this.vehicle = vehicle ;
        this.isOccupied = true ;
    }

    public VehicleSize getMaxSize() { return maxSize; }

    public void vacate() {
        if ( !isOccupied ) {
            throw new IllegalStateException( " Parking Spot is already vacant ! " ) ;
        }
        System.out.println( " Spot number " + spotNumber + " is now vacated. " ) ;
        this.vehicle = null ;
        this.isOccupied = false ;
    }

    public int getSpotNumber() {
        return spotNumber ;
    }

    public String getSpotType() {
        return spotType ;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }



}
