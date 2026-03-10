package demo1;

public class test {
    public static void main(String[] args) {
                ERyder bike1 = new ERyder();
                bike1.printBikeDetails();
                bike1.printUsageDetails(24);
                ERyder bike2 = new ERyder("R0k1shi", 75, true, 120.5,"Mikasa",1995888);
                bike2.printUsageDetails(64);
                bike2.ride();
                bike2.printBikeDetails();

    }
}
