package demo1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
public class BikeService {
    private final Stack<ERyderLog> systemLogStack = new Stack<>();
    private int logIdSequence = 1;
    private final String logIdPrefix = "BR";
    private final Queue<BikeRequest> bikeRequestQueue = new ArrayDeque<>();
    // 无可用车时，请求加入队列
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
    public boolean reserveBike(String bikeID, String emailAddress) {
        if (bikeID == null) {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
            return false;
        }
        for (Bike bike : BikeDataBase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(false);
                bike.setLastUsedTime(java.time.LocalDateTime.now());
                LocalDateTime eventTime = LocalDateTime.now();
                String logId = logIdPrefix + logIdSequence;
                String rentEventDesc = String.format("Bike with ID %s was rented by user %s from location %s",
                        bikeID, emailAddress, bike.getLocation());
                systemLogStack.push(new ERyderLog(logId, rentEventDesc, eventTime));
                String tripStartEventDesc = String.format("Trip started for bike ID %s, user %s",
                        bikeID, emailAddress);
                systemLogStack.push(new ERyderLog(logId, tripStartEventDesc, eventTime));
                logIdSequence++;
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
                LocalDateTime eventTime = LocalDateTime.now();
                String logId = logIdPrefix + (logIdSequence - 1);
                String tripEndEventDesc = String.format("Trip ended for bike ID %s at location %s",
                        bikeID, bike.getLocation());
                systemLogStack.push(new ERyderLog(logId, tripEndEventDesc, eventTime));
                logIdSequence++;
                if (!bikeRequestQueue.isEmpty()) {
                    System.out.println("Processing pending bike request from the queue...");
                    BikeRequest pendingRequest = bikeRequestQueue.poll();
                    String availableBikeId = findAvailableBike(pendingRequest.getLocation());
                    if (availableBikeId != null) {
                        reserveBike(availableBikeId, pendingRequest.getUserEmail());
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


