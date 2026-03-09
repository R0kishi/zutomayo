package demo1;

public class test {
    public static void main(String[] args) {
                ERyder bike1 = new ERyder();
                bike1.printBikeDetails();
                ERyder bike2 = new ERyder("R0k1shi", 75, true, 120.5);
                bike2.ride();
                bike2.printBikeDetails();

    }
}

