package parking;

import parking.vehicleFactory.Vehicle;
import java.time.Instant;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId = UUID.randomUUID().toString();
    private final Vehicle vehicle;
    private final int floorNumber;
    private final int spotNumber;
    private final Instant entryTime;
    private Instant exitTime;

    public ParkingTicket(Vehicle vehicle, int floorNumber, int spotNumber) {
        this.vehicle = vehicle;
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.entryTime = Instant.now();
    }

    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public int getFloorNumber() { return floorNumber; }
    public int getSpotNumber() { return spotNumber; }
    public Instant getEntryTime() { return entryTime; }

    public void markExit() { this.exitTime = Instant.now(); }
    public Instant getExitTime() { return exitTime; }
}