package demo1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AdminPanel {
    List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void userManagementOptions() {
        while (true) {
            System.out.println("\n===== Welcome to E-Ryder Admin Panel =====");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. Exit Program");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNewUsers();
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers();
                    break;
                case 4:
                    updateRegisteredUsers();
                    break;
                case 5:
                    System.out.println("Program exited successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("Enter number of users to add: ");
        int userCount = scanner.nextInt();
        ;
        for (int i = 0; i < userCount; i++) {
            System.out.println("\n----- Enter details for User " + (i + 1) + " -----");
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Email Address: ");
            String email = scanner.nextLine();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();
            System.out.print("Card Number: ");
            String cardNumber = scanner.nextLine();
            System.out.print("Card Provider: ");
            String cardProvider = scanner.nextLine();
            System.out.print("Card Expiry Date (MM/YY): ");
            String expiry = scanner.nextLine();
            System.out.print("CVV: ");
            String cvv = scanner.nextLine();
            System.out.print("User Type (e.g., Regular/VIP): ");
            String userType = scanner.nextLine();
            String[] trips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\n--- Trip " + (j + 1) + " ---");
                System.out.print("Trip Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Source: ");
                String start = scanner.nextLine();
                System.out.print("Destination: ");
                String end = scanner.nextLine();
                System.out.print("Fare (€): ");
                double fare = scanner.nextDouble();
                System.out.print("User Feedback (can be empty): ");
                String feedback = scanner.nextLine();
                StringBuilder tripStr = new StringBuilder();
                tripStr.append("Date: ").append(date)
                        .append(", Source: ").append(start)
                        .append(", Destination: ").append(end)
                        .append(", Fare (€): ").append(fare)
                        .append(", Feedback: ").append(feedback.isEmpty() ? "None" : feedback);
                trips[j] = tripStr.toString();
            }
            RegisteredUsers user = new RegisteredUsers(
                    fullName, email, dob, cardNumber, expiry, cardProvider, cvv, userType, trips
            );
            registeredUsersList.add(user);
            System.out.println("✅ User " + fullName + " added successfully!");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display.");
            return;
        }
        System.out.println("\n===== Registered Users List =====");
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
            System.out.println("------------------------");
        }
    }
    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove.");
            return;
        }
        System.out.print("Enter email of user to remove: ");
        String email = scanner.nextLine();
        int removeIndex = -1;
        for (int i = 0; i < registeredUsersList.size(); i++) {
            RegisteredUsers user = registeredUsersList.get(i);
            if (user.getEmailAddress().equals(email)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            RegisteredUsers removedUser = registeredUsersList.remove(removeIndex);
            System.out.println("✅ User " + removedUser.getFullName() + " removed successfully!");
        } else {
            System.out.println("❌ No user found with this email address.");
        }
    }
    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update.");
            return;
        }

        System.out.print("Enter email of user to update: ");
        String email = scanner.nextLine();

        RegisteredUsers target = null;
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equals(email)) {
                target = user;
                break;
            }
        }

        if (target == null) {
            System.out.println("❌ No user found with this email address.");
            return;
        }
        System.out.println("\n----- Update User Info (Press ENTER to keep current value) -----");
        System.out.print("New Full Name (Current: " + target.getFullName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) target.setFullName(newName);
        System.out.print("New Email (Current: " + target.getEmailAddress() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) target.setEmailAddress(newEmail);
        System.out.print("New DOB (Current: " + target.getDateOfBirth() + "): ");
        String newDob = scanner.nextLine();
        if (!newDob.isEmpty()) target.setDateOfBirth(newDob);
        System.out.print("New Card Number (Current: " + target.getCardNumber() + ", enter 0 to keep): ");
        String newCard = scanner.nextLine();
        if (!newCard.equals("0")) target.setCardNumber(newCard);
        System.out.print("New Card Provider (Current: " + target.getCardProvider() + "): ");
        String newProvider = scanner.nextLine();
        if (!newProvider.isEmpty()) target.setCardProvider(newProvider);
        System.out.print("New Expiry Date (Current: " + target.getCardExpiryDate() + "): ");
        String newExpiry = scanner.nextLine();
        if (!newExpiry.isEmpty()) target.setCardExpiryDate(newExpiry);
        System.out.print("New CVV (Current: " + target.getCvv() + "): ");
        String newCvv = scanner.nextLine();
        if (!newCvv.isEmpty()) target.setCvv(newCvv);
        System.out.print("New User Type (Current: " + target.getUserType() + "): ");
        String newType = scanner.nextLine();
        if (!newType.isEmpty()) target.setUserType(newType);
        System.out.println("✅ User information updated successfully!");
    }
}





