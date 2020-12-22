package schedule_related;

import event_related.iEvent;
import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * An organizer event manager.
 * Stores the relationship between organizers and their organized events.
 */
public class OrganizerEventManager implements iUserEventManager {
    private final Hashtable<iUser, ArrayList<iEvent>> organizerToEvent;

    public OrganizerEventManager() {
        organizerToEvent = new Hashtable<>();
    }

    /**
     * Returns the list of events organized by the given organizer.
     *
     * @param organizer the given organizer
     * @return the list of events organized by the given organizer
     */
    @Override
    public ArrayList<iEvent> getEvents(iUser organizer) {
        return organizerToEvent.getOrDefault(organizer, new ArrayList<>());
    }

    /**
     * Adds the given event to the given organizer's list of organized events.
     *
     * @param event     the given event
     * @param organizer the given organizer
     */
    @Override
    public void addEvent(iEvent event, iUser organizer) {
        if (!getEvents(organizer).isEmpty()) {
            organizerToEvent.get(organizer).add(event);
        } else {
            ArrayList<iEvent> newList = new ArrayList<>();
            newList.add(event);
            organizerToEvent.put(organizer, newList);
        }
    }
}
