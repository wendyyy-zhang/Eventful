package event_related;

import _start.GetInput;
import login_related.RegisterSystem;
import rating_related.RatingSystem;
import rating_related.iRate;
import schedule_related.*;
import user_related.UserManager;
import user_related.iUser;

import java.util.ArrayList;

/**
 * The System responsible for all event-related user interactions
 */
public class EventSystem {
    private final EventPresenter presenter = new EventPresenter();
    private final GetInput getInput = new GetInput();

    // stores scheduleSystem
    // pass registerSystem to it
    private final ScheduleSystem scheduleSystem;
    private final RatingSystem ratingSystem;

    // managers
    private final EventManager em;

    private final SpeakerEventManager rem;
    private final EventSpeakerManager esm;
    private final EventRoomManager erm;
    private final OrganizerEventManager oem;
    private final EventOrganizerManager eventOrganizerManager;
    private final UserManager um;

    // update
    private final AttendEventManager signedUpEventManager = new AttendEventManager();
    private final EventAttendManager eventAttendManager = new EventAttendManager();

    /**
     * Constructs a new EventSystem based on the given RegisterSystem
     *
     * @param registerSystem the given RegisterSystem
     */
    public EventSystem(RegisterSystem registerSystem) {
        scheduleSystem = new ScheduleSystem(registerSystem);
        ratingSystem = new RatingSystem(this);
        rem = scheduleSystem.getRelatedEventManager();
        esm = scheduleSystem.getEventSpeakerManager();
        em = scheduleSystem.getEventManager();
        erm = scheduleSystem.getEventRoomManager();
        oem = scheduleSystem.getOrganizedEventManager();
        eventOrganizerManager = scheduleSystem.getEventOrganizerManager();
        um = registerSystem.getUserManager();
    }

    /**
     * Allows the given user to interact with event menu
     *
     * @param user the given user
     */
    public void run(iUser user) {
        boolean isOrganizer = user.isOrganizer();
        boolean isSpeaker = user.isSpeaker();

        String input = "";
        while (!input.equals("back")) {

            if (isOrganizer) {
                presenter.printOrganizerEvent();
                input = getInput.getNumericalInput(4);
            } else if (isSpeaker) {
                presenter.printSpeakerEvent();
                input = getInput.getNumericalInput(1);  // see related events
            } else {  // is Attendee
                presenter.printAttendeeEvent();
                input = getInput.getNumericalInput(2);
            }

            if (input.equals("1") && isSpeaker) {
                seeEventOptions(user, "5");
            } else {
                seeEventOptions(user, input);
            }

        }
    }

    private void seeEventOptions(iUser user, String input) {
        switch (input) {
            case "1":  // see entire event list
                seeEvents(user, 0);
                break;
            case "2":  // see signed up event list
                seeEvents(user, 1);
                break;
            case "3":  // schedule an event
                // TODO: does this make it 2 actors?
                scheduleSystem.run(user);
                break;
            case "4": // see organized event list
                seeEvents(user, 3);
                break;
            case "5":  // see related event list
                seeEvents(user, 2);
                break;
        }
    }

    private ArrayList<iEvent> getEventsStrategy(iUser user, int number) {
        iUserEventManager manager;

        if (number == 1) {
            // see signed up events
            manager = signedUpEventManager;
        } else if (number == 2) {
            // see related events
            manager = rem;
        } else {
            // see organized events
            manager = oem;
        }
        return manager.getEvents(user);
    }

    private void seeEvents(iUser user, int number) {
        ArrayList<iEvent> events;
        if (number == 0) {
            events = em.getEvents();
        } else if (number == 1) {  // signed up events
            events = getEventsStrategy(user, 1);
        } else if (number == 2) {  // related events
            events = getEventsStrategy(user, 2);
        } else { // organized events
            events = getEventsStrategy(user, 3);
        }
        System.out.println(em.eventsToNames(events));
        grabSingleEvent(user, events);
    }

    private void grabSingleEvent(iUser user, ArrayList<iEvent> events) {
        int ValidRange = events.size();
        if (ValidRange != 0) {
            String input = getInput.getNumericalInput(ValidRange);
            if (!input.equals("back")) {
                iEvent event = em.grabEvent(events, input);
                singleEventOptions(user, event);
            }
        }
    }

    private void singleEventOptions(iUser user, iEvent event) {
        System.out.println(eventToString(event));
        boolean isNotSpeaker = !user.isSpeaker();
        boolean isPastEvent = em.isPastEvent(event);
        if (isNotSpeaker & !isPastEvent) {
            presenter.printSingleEventOptions();
            String input = getInput.getNumericalInput(2);
            if (input.equals("1")) {
                // sign up
                signUp(user, event);
            } else if (input.equals("2")) {
                // cancel spot
                cancelSpot(user, event);
            }
        } else if (isPastEvent) {
            ratingSystem.run(user, event);
        }
    }

    private void signUp(iUser user, iEvent event) {
        boolean hasSignedUp = signedUpEventManager.hasSignedUp(user, event);
        boolean noRoom = noRoom(event);

        if (hasSignedUp) {
            presenter.printHasSignedUp();
        } else if (noRoom) {
            presenter.printNotEnoughSpace();
        } else {
            signedUpEventManager.addEvent(event, user);
            eventAttendManager.addRelevant(event, user);
            presenter.printSignUpSuccess();
        }
    }

    private boolean noRoom(iEvent event) {
        ArrayList<iRoom> rooms = erm.getRelevant(event);
        int roomCap = 0;
        if (!rooms.isEmpty()) {
            iRoom room = rooms.get(0);
            roomCap = room.getCapacity();
        }
        int numOfAttendee = eventAttendManager.getNumOfAttendee(event);
        return roomCap <= numOfAttendee;
    }

    private void cancelSpot(iUser user, iEvent event) {
        // is has not yet signed up
        boolean hasNotSignedUp = !signedUpEventManager.hasSignedUp(user, event);

        if (hasNotSignedUp) {
            presenter.printDidNotSignedUp();
        } else {
            signedUpEventManager.removeEvent(user, event);
            eventAttendManager.removeAttendee(user, event);
            presenter.printCancelSuccess();
        }
    }

    private String eventToString(iEvent event) {
        ArrayList<iUser> os = getRelevant(1, event);
        String organizers = organizersToString(os);
        String speakers = getRelevant(2, event).toString();
        String room = erm.seeRelevant(event);
        String attendees = getRelevant(3, event).toString();
        return "" +
                "\n--------------------------------------------------\n"
                + event.toString()
                + "Organizers: " + organizers + "\n"
                + "Speakers: " + speakers + "\n"
                + "Room (number-capacity): " + room + "\n"
                + "Attendees: " + attendees + "\n" +
                "--------------------------------------------------";
    }

    private ArrayList<iUser> getRelevant(int type, iEvent event) {
        iEventUserManager manager;
        if (type == 1) {
            manager = eventOrganizerManager;
        } else if (type == 2) {
            manager = esm;
        } else {
            manager = eventAttendManager;
        }
        return manager.getRelevant(event);
    }

    private String organizersToString(ArrayList<iUser> organizers) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (iUser o : organizers) {
            iRate organizer = (iRate) o;
            result.append(organizer.toString()).append(organizer.getRating());
        }
        result.append("]");
        return result.toString();
    }

    /**
     * Returns the signedUpEventManager.
     *
     * @return the signedUpEventManager
     */
    public AttendEventManager getSignedUpEventManager() {
        return signedUpEventManager;
    }

    /**
     * Returns the eventAttendManager.
     *
     * @return the eventAttendManager
     */
    public EventAttendManager getEventAttendManager() {
        return eventAttendManager;
    }

    /**
     * Returns the scheduleSystem.
     *
     * @return the scheduleSystem
     */
    public ScheduleSystem getScheduleSystem() {
        return scheduleSystem;
    }
}
