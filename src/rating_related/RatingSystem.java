package rating_related;

import _start.GetInput;
import event_related.AttendEventManager;
import event_related.EventSystem;
import event_related.iEvent;
import schedule_related.EventOrganizerManager;
import schedule_related.ScheduleSystem;
import user_related.Organizer;
import user_related.iUser;

import java.util.ArrayList;

/**
 * A rating system.
 * Allows users to rate for an event after attended.
 */
public class RatingSystem {

    private final RatingPresenter presenter = new RatingPresenter();
    private final GetInput getInput = new GetInput();

    private final AttendEventManager attendEventManager;
    private final EventOrganizerManager eventOrganizerManager;

    private final RatingManager ratingManager = new RatingManager();
    private final UserRatedEventManager userRatedEventManager = new UserRatedEventManager();

    /**
     * Constructs a new RatingSystem based on the given EventSystem.
     *
     * @param eventSystem the given EventSystem
     */
    public RatingSystem(EventSystem eventSystem) {
        ScheduleSystem scheduleSystem = eventSystem.getScheduleSystem();
        attendEventManager = eventSystem.getSignedUpEventManager();
        eventOrganizerManager = scheduleSystem.getEventOrganizerManager();
    }

    /**
     * Allows the given user to rate for the given past event
     *
     * @param user  the given user
     * @param event the given past event
     */
    public void run(iUser user, iEvent event) {
        boolean hasOrganizer = !eventOrganizerManager.getRelevant(event).isEmpty();
        boolean hasNotRated = userRatedEventManager.hasNotRated(user, event);
        if (hasOrganizer && hasNotRated) {
            presenter.printPastEventOptions();
            String input = getInput.getNumericalInput(1);
            boolean wantToScore = input.equals("1");
            boolean hasSignedUp = attendEventManager.hasSignedUp(user, event);
            if (wantToScore && hasSignedUp) {
                getScore(event);
                userRatedEventManager.addEvent(user, event);
            } else if (wantToScore) {
                presenter.printDidNotSignedUp();
            }
        }
    }

    // helper methods
    private void getScore(iEvent event) {
        presenter.printScore();
        String input = getInput.getNumericalInput(5);
        if (!input.equals("back")) {
            ArrayList<iUser> organizers = eventOrganizerManager.getRelevant(event);
            for (iUser organizer : organizers) {
                recordScore(organizer, input);
            }
            presenter.printThankYou();
        }
    }

    private void recordScore(iUser organizer, String input) {
        int score = Integer.parseInt(input);
        ratingManager.updateRating((Organizer) organizer, score);
    }
}
