package demo1;

public class ERyder {
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    String COMPANY_NAME="ERyder";
    double BASE_FARE=1.0;
    double PER_MINUTE_FARE=0.5;
    final String LINKED_ACCOUNT ;
    final  int LINKED_PHONE_NUMBER;
    private int totalUsageInMinutes;
    private int totalFare;
    public  ERyder(){
          this.bikeID="unknown";
          this.batteryLevel=0;
          this.isAvailable=false;
          this.kmDriven=0.0;
          this.LINKED_ACCOUNT = "unknown";
          this.LINKED_PHONE_NUMBER=0;
          this.totalFare=0;
          this.totalUsageInMinutes=0;
    };
    public  ERyder(String bikeID, int batterryLevel, boolean isAvailable, double kmDriven, String LINKED_ACCOUNT, int linkedPhoneNumber){
        this.bikeID=bikeID;
        this.batteryLevel=batterryLevel;
        this.isAvailable=isAvailable;
        this.kmDriven=kmDriven;
        this.LINKED_ACCOUNT=LINKED_ACCOUNT;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
    };
    public void setBikeID(String bikeID){
        this.bikeID=bikeID;
    };
    public String getBikeID(){
        return this.bikeID;
    };
    public void setAvailable(boolean available) {
        isAvailable = available;
    };
    public boolean getAvailable() {
        return isAvailable;
    };
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Invalid battery level! Must be between 0 and 100.");
        }
    };
    public int getBatteryLevel() {
        return batteryLevel;
    };
    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    };
    public double getKmDriven() {
        return kmDriven;
    }
    public void ride() {
        if (isAvailable && batteryLevel > 0) {
            System.out.println("Bike is available for ride.");
        } else {
            System.out.println("Bike is not available.");
        }
    };
    public void setTotalUsageInMinutes(int totalUsageInMinutes) {
        this.totalUsageInMinutes = totalUsageInMinutes;
    };
    public int getTotalUsageInMinutes() {
        return totalUsageInMinutes;
    };
    public void setTotalFare(int totalFare) {
        this.totalFare = totalFare;
    };
    public int getTotalFare() {
        return totalFare;
    };
    private double calculateTotalFare(int usageInMinutes) {
                this.totalFare = (int) (BASE_FARE + (usageInMinutes * PER_MINUTE_FARE));
                return this.totalFare;
    }
    public void printUsageDetails(int UsageInMinutes) {
        double total=calculateTotalFare(UsageInMinutes);
        System.out.println("Total Usage in Minutes: " + UsageInMinutes);
        System.out.println("Total Fare: " + total);
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Linked Phone Number: " + LINKED_PHONE_NUMBER);
        System.out.println("Company Name: " + COMPANY_NAME);
    }
     public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println("Kilometers Driven: " + kmDriven + " km");
    };
}


