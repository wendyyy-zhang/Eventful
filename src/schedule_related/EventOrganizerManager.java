package schedule_related;

import event_related.iEvent;
import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * An event organizer manager.
 * Stores the relationship between events and their organizers.
 */
public class EventOrganizerManager implements iEventUserManager {
    private final Hashtable<iEvent, ArrayList<iUser>> eventToOrganizer;

    public EventOrganizerManager() {
        eventToOrganizer = new Hashtable<>();
    }

    /**
     * Returns a list of organizers who organized the given events.
     *
     * @param event the given events
     * @return a list of organizers who organized the given events
     */
    @Override
    public ArrayList<iUser> getRelevant(iEvent event) {
        return eventToOrganizer.getOrDefault(event, new ArrayList<>());
    }

    /**
     * Adds the given organizer to the given event's list of organizers
     *
     * @param event     the given event
     * @param organizer the given organizer
     */
    @Override
    public void addRelevant(iEvent event, iUser organizer) {
        if (eventToOrganizer.containsKey(event)) {
            eventToOrganizer.get(event).add(organizer);
        } else {
            ArrayList<iUser> newList = new ArrayList<>();
            newList.add(organizer);
            eventToOrganizer.put(event, newList);
        }
    }
}
