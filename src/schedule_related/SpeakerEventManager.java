package schedule_related;

import event_related.iEvent;
import user_related.iUser;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A speaker manager.
 * A manager that stores the relationship between iUser and received Messages
 */
public class SpeakerEventManager implements Serializable, iUserEventManager {

    private final Hashtable<iUser, ArrayList<iEvent>> speakerToEvent = new Hashtable<>();

    /**
     * Returns the list of events the given speaker is related to.
     *
     * @param speaker the given speaker
     * @return the list of events the given speaker is related to
     */
    @Override
    public ArrayList<iEvent> getEvents(iUser speaker) {
        return speakerToEvent.getOrDefault(speaker, new ArrayList<>());
    }

    /**
     * Adds the given event into the given speaker's related event list
     *
     * @param speaker the given speaker
     * @param event   the given event
     */
    public void addEvent(iEvent event, iUser speaker) {
        if (!getEvents(speaker).isEmpty()) {
            speakerToEvent.get(speaker).add(event);
        } else {
            ArrayList<iEvent> newList = new ArrayList<>();
            newList.add(event);
            speakerToEvent.put(speaker, newList);
        }
    }

    /**
     * Returns true if and only if the speaker is not joining other events at the given start time.
     *
     * @param startTime the start time of an event
     * @param speaker   the speaker
     * @return true if and only if the speaker is not joining other events at the given start time
     */
    public boolean speakerIsChecked(LocalDateTime startTime, iUser speaker) {
        // if the speaker does not have any related events
        if (!getEvents(speaker).isEmpty()) {
            // get related events and compare the time
            ArrayList<iEvent> relatedEvents = speakerToEvent.get(speaker);
            for (iEvent e : relatedEvents) {
                if ((startTime.isEqual(e.getStartTime()) | startTime.isAfter(e.getStartTime()))
                        && (startTime.isBefore(e.getEndTime()) | startTime.isEqual(e.getEndTime()))) {
                    return false;
                }
            }
        }
        return true;
    }

}
