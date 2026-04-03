package demo1;

import java.util.Scanner;

public class AdminPanel {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final BikeService bikeService;
    private final RentalService rentalService;

    public AdminPanel() {
        this.userService = new UserService(scanner);
        this.bikeService = new BikeService();
        this.rentalService = new RentalService();
    }

    public void userManagementOptions() {
        while (true) {
            System.out.println("\n===== Welcome to E-Ryder Admin Panel =====");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. Exit Program");
            System.out.println("6. View System Logs");
            System.out.println("7. Manage Pending Bike Requests");
            System.out.println("8. Demo the Bike Rental System");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    userService.addNewUsers();
                    break;
                case 2:
                    userService.viewRegisteredUsers();
                    break;
                case 3:
                    userService.removeRegisteredUsers();
                    break;
                case 4:
                    userService.updateRegisteredUsers();
                    break;
                case 6:
                    managePendingRequests();
                    break;
                case 7:
                    runBikeRentalDemo();
                    break;
                case 8:
                    System.out.println("Program exited successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }



    private void managePendingRequests() {
        while (true) {
            System.out.println("\n==================== MANAGE PENDING BIKE REQUESTS ====================");
            System.out.println("1. View Queue");
            System.out.println("2. Update Queue");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }
            switch (choice) {
                case 1:
                    bikeService.viewPendingRequestQueue();
                    break;
                case 2:
                    bikeService.updateRequestQueue();
                    break;
                case 3:
                    System.out.println("Returning to Admin Panel main menu...");
                    return;
                default:
                    System.out.println("Invalid option! Please select a number between 1 and 3.");
            }
        }
    }

    private void runBikeRentalDemo() {
        System.out.println("This is the simulation of the e-bike rental process.");
        System.out.print("Are you a registered user? (true/false): ");
        boolean isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter your email: ");
        String emailAddress = scanner.nextLine();

        System.out.print("Enter rental location: ");
        String location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");
        String bikeID = bikeService.findAvailableBike(location);
        if (!isRegisteredUser) {
            System.out.println("You're not our registered user. Please consider registering.");
            new UserRegistration().registration();
        } else {
            System.out.println("Welcome back, " + emailAddress + "!");
        }

        System.out.println("Simulating e-bike reservation...");
        boolean reserved = bikeService.reserveBike(bikeID, emailAddress, location);
        if (!reserved) return;

        java.time.LocalDateTime tripStartTime = java.time.LocalDateTime.now();
        rentalService.addActiveRental(bikeID, emailAddress, tripStartTime);

        System.out.println("Displaying the active rentals...");
        rentalService.viewActiveRentals();

        System.out.println("Simulating the end of the trip...");
        rentalService.endRental(bikeID);
        bikeService.releaseBike(bikeID,emailAddress);

        System.out.println("Displaying the active rentals after trip ends...");
        rentalService.viewActiveRentals();
    }

    public static void main(String[] args) {
        new AdminPanel().userManagementOptions();
    }

}
