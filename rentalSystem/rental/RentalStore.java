package rental;

import rental.vehicleFactory.* ;
import java.util.* ;

public class RentalStore {
    
    private final int id ;
    private final String name ;
    @SuppressWarnings("unused")
    private final Location location ;
    private final Map<String,Vehicle> map; 

    public Vehicle getVehicle(String regNum ) {
        return map.get(regNum) ;
    }

    public RentalStore(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.map = new HashMap<>();
    }

    public int getId() {
        return id ;
    }

    public String getName() {
        return name ;
    }

    public void addVehicle( Vehicle vehicle ) {
        map.put( vehicle.getRegistrationNumber() , vehicle ) ;
        System.out.println( "The vehicle is successfully added with regNumber : " + vehicle.getRegistrationNumber() );
    }

    public void removeVehicle( Vehicle vehicle ) {
        if ( map.containsKey( vehicle.getRegistrationNumber() ) ) {
            map.remove( vehicle.getRegistrationNumber() ) ;
            System.out.println( "The vehicle is successfully removed!! " ) ;
            return ;
        }
        System.out.println( "The vehicle does not exist " ) ;
    }

    public List<Vehicle> getAvailableVehicles( Date start , Date end ) {
        List<Vehicle> availableVehicles = new ArrayList<>() ;
        for ( Vehicle vehicle : map.values() ) {
            if ( vehicle.getStatus() == VehicleStatus.AVAILABLE ) {
                availableVehicles.add( vehicle ) ;
            }
        }

        return availableVehicles ;
    }

    public boolean isVahicleAvailable(
        String regNumber , Date startDate , Date endDate ){
            
        Vehicle vehicle = map.get(regNumber) ;
        return vehicle!=null && vehicle.getStatus() == VehicleStatus.AVAILABLE ;
    }

}

