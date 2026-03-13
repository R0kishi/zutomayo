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
                String sent1 = "I was very satisfied with the service.";
                String sent2 = "The e-Bike is quite comfortable to ride.";
                String sent3 = "The battery life of the e-Bike is impressive.";
                String sent4 = "The customer support was helpful and responsive.";
                String sent5 = "I would recommend this e-Bike to my friends and family.";
                Feedback userFeedback = new Feedback("Liu", "liyang", "Liuliyang@R0k1shi.com");
                userFeedback.analyseFeedback(true, sent1, sent2, sent3, sent4, sent5);
                System.out.println(userFeedback);

    }
}
