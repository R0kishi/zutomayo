package demo1;

public class ERyder {
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    public  ERyder(){
          this.bikeID="unknown";
          this.batteryLevel=0;
          this.isAvailable=false;
          this.kmDriven=0.0;

    };
    public  ERyder(String bikeID,int batterryLevel,boolean isAvailable,double kmDriven){
        this.bikeID=bikeID;
        this.batteryLevel=batterryLevel;
        this.isAvailable=isAvailable;
        this.kmDriven=kmDriven;
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
    public int getBatteryLevel() {
        return batteryLevel;
    };
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Invalid battery level! Must be between 0 and 100.");
        }
    };
    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    };
    public void ride() {
        if (isAvailable && batteryLevel > 0) {
            System.out.println("Bike is available for ride.");
        } else {
            System.out.println("Bike is not available.");
        }
    };
    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println("Kilometers Driven: " + kmDriven + " km");
    };
}


