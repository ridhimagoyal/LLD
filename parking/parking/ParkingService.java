package parking;

import parking.vehicleFactory.Vehicle;
import parking.parkingFeeStrategy.ParkingFeeStrategy;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingService {

    private final List<Floor> floors;
    private final Map<String, ParkingTicket> activeTickets = new ConcurrentHashMap<>();
    private final ParkingFeeStrategy feeStrategy ;

    public ParkingService(List<Floor> floors, ParkingFeeStrategy feeStrategy) {
        this.floors = floors;
        this.feeStrategy = feeStrategy;
    }

    public synchronized ParkingTicket parkVehicle(Vehicle v) {
        for (Floor f : floors) {
            var spot = f.findAvailableSpotForVehicle(v);
            if (spot != null) {
                spot.parkVehicle(v);
                ParkingTicket ticket = new ParkingTicket(v, f.getFloorNumber(), spot.getSpotNumber());
                activeTickets.put(ticket.getTicketId(), ticket);
                return ticket;
            }
        }
        return null; // no spot available
    }

    public synchronized double checkoutAndVacate(String ticketId) {

        ParkingTicket ticket = activeTickets.remove(ticketId);
        if (ticket == null) throw new IllegalArgumentException("Invalid ticket");
        ticket.markExit();
        // compute duration in hours (rounded up)
        long seconds = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).getSeconds();
        int hours = (int) Math.max(1, (seconds + 3599) / 3600); // ceil to hours, min 1
        double fee = ticket.getVehicle().calculateFees(hours, parking.DurationType.HOURS  );
        // find floor & spot and vacate
        for (Floor f : floors) {
            if (f.getFloorNumber() == ticket.getFloorNumber()) {
                for (var s : f.spots ) {
                    if (s.getSpotNumber() == ticket.getSpotNumber()) {
                        s.vacate();
                        return fee;
                    }
                }
            }
        }
        // should not happen
        throw new IllegalStateException("Spot not found to vacate");
    }

    public Optional<ParkingTicket> getTicket(String id) {
        return Optional.ofNullable(activeTickets.get(id));
    }

    public Collection<ParkingTicket> getActiveTickets() {
        return activeTickets.values();
    }
}