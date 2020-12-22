package schedule_related;

import event_related.iEvent;
import user_related.iUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A manager that stores the relationship between Events and Speakers
 */
public class EventSpeakerManager implements Serializable, iEventUserManager {

    private final Hashtable<iEvent, ArrayList<iUser>> eventToSpeaker = new Hashtable<>();

    /**
     * Add the speaker to the list of attendees of the given event
     *
     * @param speaker the speaker to be added
     * @param event   the event has is about to have one more speaker
     */
    @Override
    public void addRelevant(iEvent event, iUser speaker) {
        if (eventToSpeaker.containsKey(event)) {
            eventToSpeaker.get(event).add(speaker);
        } else {
            ArrayList<iUser> newList = new ArrayList<>();
            newList.add(speaker);
            eventToSpeaker.put(event, newList);
        }
    }

    public void addRelevant(iEvent event) {
        if (!eventToSpeaker.containsKey(event)) {
            ArrayList<iUser> newList = new ArrayList<>();
            eventToSpeaker.put(event, newList);
        }
    }

    /**
     * Returns the list of speakers of an event
     *
     * @param event the event
     * @return the list of speakers of an event
     */
    @Override
    public ArrayList<iUser> getRelevant(iEvent event) {
        return eventToSpeaker.getOrDefault(event, new ArrayList<>());
    }
}
