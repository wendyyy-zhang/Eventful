package schedule_related;

import event_related.iEvent;
import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

public class OrganizerEventManager implements iUserEventManager {
    private final Hashtable<iUser, ArrayList<iEvent>> organizerToEvent;

    public OrganizerEventManager() {
        organizerToEvent = new Hashtable<>();
    }

    /**
     * get new array list.
     *
     * @param organizer the iUser organizer
     * @return organizer, Arraylist<>
     */
    @Override
    public ArrayList<iEvent> getEvents(iUser organizer) {
        return organizerToEvent.getOrDefault(organizer, new ArrayList<>());
    }

    /**
     * add a event
     *
     * @param event     the event being added
     * @param organizer the organizer
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
