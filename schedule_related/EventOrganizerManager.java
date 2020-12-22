package schedule_related;

import event_related.iEvent;
import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

// TODO: all docstrings
public class EventOrganizerManager implements iEventUserManager {
    private final Hashtable<iEvent, ArrayList<iUser>> eventToOrganizer;
    public EventOrganizerManager(){
        eventToOrganizer = new Hashtable<>();
    }

    @Override
    public ArrayList<iUser> getRelevant(iEvent event) {
        return eventToOrganizer.getOrDefault(event, new ArrayList<>());
    }

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
