package demo1;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BikeService {
    private final Queue<BikeRequest> bikeRequestQueue = new ArrayDeque<>();
    private final Stack<ERyderLog> systemLogStack = new Stack<>();
    private int logIdSequence = 1;
    private final String logIdPrefix = "BR";
    private final Queue<BikeRequest> bikeRequest = new ArrayDeque<>();
    public void addBikeRequestToQueue(String userEmail, String location) {
        LocalDateTime requestTime = LocalDateTime.now();
        BikeRequest newRequest = new BikeRequest(userEmail, location, requestTime);
        bikeRequestQueue.offer(newRequest);
        System.out.println("Your request has been added to the pending queue. We will assign a bike to you as soon as possible.");
    }
    public void viewSystemLogs() {
        System.out.println("\n==================== SYSTEM LOGS ====================");
        if (systemLogStack.isEmpty()) {
            System.out.println("No system logs available at this time.");
            System.out.println("======================================================");
            return;
        }
        for (ERyderLog log : systemLogStack) {
            System.out.println(log);
        }
        System.out.println("======================================================");
    }
    public void viewPendingRequestQueue() {
        System.out.println("\n==================== PENDING BIKE REQUESTS ====================");
        if (bikeRequestQueue.isEmpty()) {
            System.out.println("No pending bike requests in the queue.");
            System.out.println("=================================================================");
            return;
        }
        for (BikeRequest request : bikeRequestQueue) {
            System.out.println(request);
        }
        System.out.println("=================================================================");
    }
    public void updateRequestQueue() {
        if (bikeRequestQueue.isEmpty()) {
            System.out.println("Queue is empty, no elements to remove.");
            return;
        }
        BikeRequest removedRequest = bikeRequestQueue.poll();
        System.out.println("Successfully removed the first request from the queue:");
        System.out.println(removedRequest);
    }
    public Queue<BikeRequest> getBikeRequestQueue() {
        return bikeRequestQueue;
    }

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
    public boolean reserveBike(String bikeID, String emailAddress, String location) {
        if (bikeID == null) {
            BikeRequest newRequest = new BikeRequest(emailAddress, location, LocalDateTime.now());
            bikeRequest.offer(newRequest);
            System.out.println("Sorry, no available bikes. Your request has been added to the waiting queue.");
            return false;
        }
        for (Bike bike : BikeDataBase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(false);
                bike.setLastUsedTime(java.time.LocalDateTime.now());
                LocalDateTime eventTime = LocalDateTime.now();
                String logId = logIdPrefix + logIdSequence;
                String rentEvent = String.format("Bike with ID %s was rented by user %s from location %s",
                        bikeID, emailAddress, location);
                systemLogStack.push(new ERyderLog(logId, rentEvent, eventTime));
                String tripStartEvent = String.format("Trip started for bike ID %s, user %s",
                        bikeID, emailAddress);
                systemLogStack.push(new ERyderLog(logId, tripStartEvent, eventTime));
                logIdSequence++;
                System.out.println("Reserving the bike with " + bikeID + ". Please follow the on-screen instructions to locate the bike.");
                return true;
            }
        }
        return false;
    }
    public void releaseBike(String bikeID, String userEmail) {
        for (Bike bike : BikeDataBase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(java.time.LocalDateTime.now());
                LocalDateTime eventTime = LocalDateTime.now();
                String logId = logIdPrefix + (logIdSequence - 1);
                String tripEndEvent = String.format("Trip ended for bike ID %s, user %s at location %s",
                        bikeID, userEmail, bike.getLocation());
                systemLogStack.push(new ERyderLog(logId, tripEndEvent, eventTime));
                logIdSequence++;
                if (!bikeRequest.isEmpty()) {
                    System.out.println("Processing pending bike request from the queue...");
                    BikeRequest pendingRequest = bikeRequest.poll();
                    String availableBikeId = findAvailableBike(pendingRequest.getLocation());
                    if (availableBikeId != null) {
                        reserveBike(availableBikeId, pendingRequest.getUserEmail(), pendingRequest.getLocation());
                        System.out.println("Bike assigned to pending request user: " + pendingRequest.getUserEmail());
                    }
                }
                if (!bikeRequestQueue.isEmpty()) {
                    System.out.println("Processing pending bike request from the queue...");
                    BikeRequest pendingRequest = bikeRequestQueue.poll();
                    String availableBikeId = findAvailableBike(pendingRequest.getLocation());
                    if (availableBikeId != null) {
                        reserveBike(availableBikeId, pendingRequest.getUserEmail(),pendingRequest.getLocation() );
                        System.out.println("Bike assigned to pending request user: " + pendingRequest.getUserEmail());
                    } else {
                        System.out.println("No available bike for pending request, request discarded.");
                    }
                }
                break;
            }
        }
    }
}


