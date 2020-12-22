package event_related;

import schedule_related.iEventUserManager;
import user_related.iUser;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A manager that store the relationship between iEvent and iUser
 */
public class EventAttendManager implements Serializable, iEventUserManager {

    private final Hashtable<iEvent, ArrayList<iUser>> eventToAttendee = new Hashtable<>();

    /**
     * Returns the list of attendees of the given event.
     *
     * @param event the given event
     * @return the list of attendees of the given event
     */
    public ArrayList<iUser> getRelevant(iEvent event) {
        return eventToAttendee.getOrDefault(event, new ArrayList<>());
    }

    /**
     * Adds the given attendee to the event's list of attendees
     *
     * @param attendee the given attendee to be added
     * @param event    the given event
     */
    public void addRelevant(iEvent event, iUser attendee) {
        if (eventToAttendee.containsKey(event)) {
            eventToAttendee.get(event).add(attendee);
        } else {
            ArrayList<iUser> newList = new ArrayList<>();
            newList.add(attendee);
            eventToAttendee.put(event, newList);
        }
    }

    /**
     * Removes the attendee from the list of attendees of the given event
     *
     * @param attendee the given attendee to be removed
     * @param event    the given event
     */
    public void removeAttendee(iUser attendee, iEvent event) {
        eventToAttendee.get(event).remove(attendee);
    }

    /**
     * Returns the number of attendees in the given event.
     *
     * @param event the given event
     * @return the number of attendees in the given event
     */
    public int getNumOfAttendee(iEvent event) {
        return getRelevant(event).size();
    }
}
