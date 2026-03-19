package demo1;
import java.time.Period;
import java.util.Scanner;
import java.time.LocalDate;
public class UserRegistration {
    double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    double VIP_DISCOUNT_UNDER_18 = 20.0;
    double VIP_BASE_FEE = 100.0;
    String fullName;
    String emailAddress;
    String dateOfBirth;
    long cardNumber;
    String cardProvider;
    String cardExpiryDate;
    double feeToCharge;
    int cvv;
    String userType;
    boolean emailValid;
    boolean minorAndBirthday;
    boolean minor;
    boolean ageValid;
    boolean cardNumberValid;
    boolean cardStillValid;
    boolean validCVV;

    private boolean analyseEmail(String email) {
        boolean hasAt = email.contains("@");
        boolean hasDot = email.contains(".");
        if (hasAt && hasDot) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address, returning to the start of the registration process.\n");
            registration();
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dob, currentDate).getYears();
        boolean isBirthday = dob.getMonthValue() == currentDate.getMonthValue() &&
                dob.getDayOfMonth() == currentDate.getDayOfMonth();
        if (age <= 12 || age > 120) {
            return false;
        }
        if (userType.equals("VIP User")) {
            if (isBirthday && age <= 18 && age > 12) {
                System.out.println("Happy Birthday! You get a 25% discount on your VIP subscription because you are under 18 and it's your birthday today!");
                this.minorAndBirthday = true;
            } else if (age <= 18 && age > 12) {
                System.out.println("You get a 20% discount on your VIP subscription because you are under 18!");
                this.minor = true;
            }
        }
        return true;
    }

    private boolean analyseCardNumber(long cardNumber) {
        String cardNumStr = String.valueOf(cardNumber);
        int length = cardNumStr.length();
        int firstTwo = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFour = Integer.parseInt(cardNumStr.substring(0, 4));
        if ((length == 13 || length == 15) && firstTwo / 10 == 4) {
            this.cardProvider = "VISA";
            return true;
        } else if (length == 16) {
            if ((firstTwo >= 51 && firstTwo <= 55) || (firstFour >= 2221 && firstFour <= 2720)) {
                this.cardProvider = "MasterCard";
                return true;
            }
        } else if (length == 15) {
            if (firstTwo == 34 || firstTwo == 37) {
                this.cardProvider = "American Express";
                return true;
            }
        }
        return false;
    }

    private boolean analyseCardExpiryDate(String expiryDate) {
        int month = Integer.parseInt(expiryDate.substring(0, 2));
        int year = Integer.parseInt(expiryDate.substring(3)) + 2000;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("Card is valid");
            return true;
        } else {
            return false;
        }
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);
        int length = cvvStr.length();
        if (this.cardProvider.equals("American Express")) {
            if (length == 4) {
                System.out.println("Card CVV is valid");
                return true;
            }
        } else if (this.cardProvider.equals("VISA") || this.cardProvider.equals("MasterCard")) {
            if (length == 3) {
                System.out.println("Card CVV is valid");
                return true;
            }
        }
        return false;

    }

    private void chargeFees() {
        if (this.minorAndBirthday) {
            this.feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        } else if (this.minor) {
            this.feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18 / 100);
        } else {
            this.feeToCharge = VIP_BASE_FEE;
        }
        String cardNumberStr = String.valueOf(this.cardNumber);
        String lastFour = cardNumberStr.substring(cardNumberStr.length() - 4);
        System.out.println("\nThank you for your payment.");
        System.out.printf("A fee of %.2f has been charged to your card ending in ****%s%n", this.feeToCharge, lastFour);
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("\nSorry, registration failed for the following reasons:");
            if (!emailValid) System.out.println("- Invalid email address");
            if (!ageValid) System.out.println("- Invalid age");
            if (!cardNumberValid) System.out.println("- Invalid card number/unsupported card type");
            if (!cardStillValid) System.out.println("- Card has expired");
            if (!validCVV) System.out.println("- Invalid CVV");
            System.out.println("Returning to the start of the registration process.\n");
            registration();
        }
    }


    public void registration() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to ERyder registration.\nYou have two options:\n1.Register as Regular User\n2.Register as VIP User\nPlease enter your choice(1 or 2)");
        int choice = sc.nextInt();
        if (choice == 1) {
            this.userType = "Regular User";
        } else if (choice == 2) {
            this.userType = "VIP User";
        }
        System.out.println("Please enter your full name:");
        this.fullName = sc.nextLine();
        System.out.println("Please enter your email address:");
        this.emailAddress = sc.nextLine();
        this.emailValid = analyseEmail(this.emailAddress);
        if (!this.emailValid) {
            System.out.println("Invalid email address, returning to the start of the registration process.\n");
            registration();
            return;
        }
        System.out.println("Please enter your date of birth (format:YYYY-MM-DD):");
        this.dateOfBirth = sc.nextLine();
        LocalDate dob = LocalDate.parse(this.dateOfBirth);
        this.ageValid = analyseAge(dob);
        if (!this.ageValid) {
            System.out.println("Invalid age, registration cannot proceed.");
            System.exit(0);
        }
        System.out.print("Please enter your card number (we only support VISA, Mastercard, American Express): ");
        this.cardNumber = sc.nextLong();
        this.cardNumberValid = analyseCardNumber(this.cardNumber);
        if (!this.cardNumberValid) {
            System.out.println("Unsupported card type or invalid card number, returning to the start of the registration process.\n");
            registration();
            return;
        }
        System.out.print("Please enter your card expiry date (format: MM/YY): ");
        this.cardExpiryDate = sc.nextLine();
        this.cardStillValid = analyseCardExpiryDate(this.cardExpiryDate);
        if (!this.cardStillValid) {
            System.out.println("Card has expired, returning to the start of the registration process.\n");
            registration();
            return;
        }
        System.out.print("Please enter your card CVV: ");
        this.cvv = sc.nextInt();
        this.validCVV = analyseCVV(this.cvv);
        if (!this.validCVV) {
            System.out.println("Invalid CVV, returning to the start of the registration process.\n");
            registration();
            return;
        }
        finalCheckpoint();
        sc.close();
    }

    @Override
    public String toString() {
        String cardNumberStr = String.valueOf(this.cardNumber);
        if (cardNumberStr.length() <= 4) {
            return "Invalid card number length";
        }
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFour = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFour;
        return """
                "Registration successful! Your information is as follows:\nUser Type: %s\nFull Name: %s\nEmail Address: %s\nDate of Birth: %s\nCard Number: %s\nCard Provider: %s\nCard Expiry Date: %s",
                this.userType, this.fullName, this.emailAddress, this.dateOfBirth, censoredNumber, this.cardProvider, this.cardExpiryDate
                """;
    }
}


