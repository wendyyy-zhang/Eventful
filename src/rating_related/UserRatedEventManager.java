package rating_related;

import event_related.iEvent;
import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A user rated event manager.
 * Stores the relationship between users and list of events they have rated for.
 */
public class UserRatedEventManager {
    private final Hashtable<iUser, ArrayList<iEvent>> userToRatedEvents = new Hashtable<>();

    /**
     * Adds the given event to the given user's rated event list
     *
     * @param user  the given user
     * @param event the given event
     */
    public void addEvent(iUser user, iEvent event) {
        if (userHasRated(user)) {
            userToRatedEvents.get(user).add(event);
        } else {
            ArrayList<iEvent> ratedEvents = new ArrayList<>();
            ratedEvents.add(event);
            userToRatedEvents.put(user, ratedEvents);
        }
    }

    private boolean userHasRated(iUser user) {
        return userToRatedEvents.containsKey(user);
    }

    private ArrayList<iEvent> getRatedEvents(iUser user) {
        return userToRatedEvents.getOrDefault(user, new ArrayList<>());
    }

    /**
     * Returns true if and only if the given user has not yet rated for the given event.
     *
     * @param user  the given user
     * @param event the given event
     * @return true if and only if the given user has not yet rated for the given event
     */
    public boolean hasNotRated(iUser user, iEvent event) {
        ArrayList<iEvent> ratedEvents = getRatedEvents(user);
        for (iEvent e : ratedEvents) {
            if (e == event) {
                return false;
            }
        }
        return true;
    }
}
