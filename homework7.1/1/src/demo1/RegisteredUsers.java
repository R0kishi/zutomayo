package demo1;

public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private String cvv;
    private String userType;
    private String[] lastThreeTrips;
    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth,
                           String cardNumber, String cardExpiryDate, String cardProvider,
                           String cvv, String userType, String[] lastThreeTrips) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;
    }
    public RegisteredUsers() {}
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCardExpiryDate() { return cardExpiryDate; }
    public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }
    public String getCardProvider() { return cardProvider; }
    public void setCardProvider(String cardProvider) { this.cardProvider = cardProvider; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public String[] getLastThreeTrips() { return lastThreeTrips; }
    public void setLastThreeTrips(String[] lastThreeTrips) { this.lastThreeTrips = lastThreeTrips; }
    @Override
    public String toString() {
        StringBuilder tripsInfo = new StringBuilder();
        for (int i = 0; i < lastThreeTrips.length; i++) {
                tripsInfo.append("  Trip ").append(i+1).append(": ").append(lastThreeTrips[i]).append("\n");
        }
        return "Full Name: " + fullName +
                "\nEmail Address: " + emailAddress +
                "\nDate of Birth: " + dateOfBirth +
                "\nCard Number: " + cardNumber +
                "\nCard Provider: " + cardProvider +
                "\nCard Expiry Date: " + cardExpiryDate +
                "\nCVV: " + cvv +
                "\nUser Type: " + userType +
                "\nLast Three Trips:\n" + tripsInfo;
    }
}
