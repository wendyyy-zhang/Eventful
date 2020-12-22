package event_related;

import schedule_related.iUserEventManager;
import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * An attendee event manager.
 * <p>
 * A manager that stores the relationship between users and events they signed up for.
 */
public class AttendEventManager implements iUserEventManager {

    private final Hashtable<iUser, ArrayList<iEvent>> userToEvent = new Hashtable<>();

    /**
     * Returns true if and only if the given user has signed up in the event, otherwise false.
     *
     * @param user  the given user
     * @param event the given event
     * @return true if and only if the given user has signed up in the event, otherwise false
     */
    public boolean hasSignedUp(iUser user, iEvent event) {
        for (iEvent e : getEvents(user)) {
            if (event == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the list of events the given user has signed up for.
     *
     * @param user the given user
     * @return the list of events the given user has signed up for
     */
    @Override
    public ArrayList<iEvent> getEvents(iUser user) {
        return userToEvent.getOrDefault(user, new ArrayList<>());
    }

    /**
     * Adds the given event to the given user's list of signed up events.
     *
     * @param user  the given user
     * @param event the given event
     */
    @Override
    public void addEvent(iEvent event, iUser user) {
        // if the user has not signed up for any event
        if (userExists(user)) {
            // add this event to the user's signed up event list
            userToEvent.get(user).add(event);
        } else {
            ArrayList<iEvent> signedUpEvents = new ArrayList<>();
            signedUpEvents.add(event);
            userToEvent.put(user, signedUpEvents);
        }
    }

    /**
     * Removes the given event from the signed up event list of the given user
     *
     * @param user  the given user
     * @param event the given event
     */
    public void removeEvent(iUser user, iEvent event) {
        userToEvent.get(user).remove(event);
    }


    /**
     * Returns true if and only if the given user exists, otherwise false.
     *
     * @param user the given user
     * @return true if and only if the given user exists, otherwise false
     */
    private boolean userExists(iUser user) {
        return userToEvent.containsKey(user);
    }

}
