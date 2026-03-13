package demo1;

public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private boolean longFeedback;
    private String reviewID;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5) {
        String concatenatedFeedback = sent1 + sent2 + sent3 + sent4 + sent5;
        return concatenatedFeedback;
    }

    private StringBuilder feedbackUsingStringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5) {
        StringBuilder sb = new StringBuilder(sent1 + sent2 + sent3 + sent4 + sent5);
        return sb;
    }

    private boolean checkFeedbackLength(String completeFeedback) {
        if (completeFeedback.length() > 500) {
            return true;
        } else {
            return false;
        }
    }

    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        String fullName = firstName + lastName;
        int nameStart = 2;
        int nameEnd = 7;
        String nameSub = fullName.substring(nameStart, nameEnd).toUpperCase();
        int feedStart = 10;
        int feedEnd = 16;
        String feedbackSub = completeFeedback.substring(feedStart, feedEnd).toLowerCase();
        int feedbackLen = completeFeedback.length();
        long timeStamp = System.currentTimeMillis();
        String tempID = nameSub + feedbackSub + feedbackLen + "_" + timeStamp;
        this.reviewID = tempID.replace(" ", "");
    }
    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
            this.longFeedback = checkFeedbackLength(completeFeedback);
            createReviewID(firstName, lastName, completeFeedback);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5).toString();
            this.longFeedback = checkFeedbackLength(completeFeedback);
            createReviewID(firstName, lastName, completeFeedback);
        }
    }
    public String toString() {
        return "ERyder User Feedback Details:\n" +
                "User Name: " + firstName + " " + lastName + "\n" +
                "User Email: " + email + "\n" +
                "Complete Feedback: " + completeFeedback + "\n" +
                "Is Long Feedback : " + longFeedback + "\n" +
                "Review ID: " + reviewID;
    }

}
