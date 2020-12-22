package rating_related;

import event_related.EventPresenter;

public class RatingPresenter extends EventPresenter {
    public void printPastEventOptions() {
        System.out.println("1. Give feedback");
    }

    public void printScore() {
        System.out.println("Please score this event from 1 to 5:");
    }

    public void printThankYou() {
        System.out.println("\nThank you. Your feedback has been recorded.");
    }
}
