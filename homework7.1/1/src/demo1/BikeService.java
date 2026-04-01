package demo1;

import java.util.List;

public class BikeService {
    public String findAvailableBike(String location) {
        for (Bike bike : BikeDataBase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }
    public boolean reserveBike(String bikeID, String emailAddress) {
        if (bikeID == null) {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
            return false;
        }
        for (Bike bike : BikeDataBase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(false);
                bike.setLastUsedTime(java.time.LocalDateTime.now());
                System.out.println("Reserving the bike with " + bikeID + ". Please follow the on-screen instructions to locate the bike.");
                return true;
            }
        }
        return false;
    }
    public void releaseBike(String bikeID) {
        for (Bike bike : BikeDataBase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(java.time.LocalDateTime.now());
                break;
            }
        }
    }
}


