package demo1;

import java.util.LinkedList;
import java.util.List;

public class RentalService {
    private final List<ActiveRental> activeRentalsList = new LinkedList<>();
    public void addActiveRental(String bikeID, String emailAddress, java.time.LocalDateTime tripStartTime) {
        ActiveRental activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
        activeRentalsList.add(activeRental);
    }
    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }
        for (ActiveRental ar : activeRentalsList) {
            System.out.println(ar);
        }
    }
    public void endRental(String bikeID) {
        ActiveRental targetRental = null;
        for (ActiveRental ar : activeRentalsList) {
            if (ar.getBikeID().equals(bikeID)) {
                targetRental = ar;
                break;
            }
        }
        if (targetRental != null) {
            activeRentalsList.remove(targetRental);
            System.out.println("Your trip has ended. Thank you for riding with us.");
        }
    }
}
