package schedule_related;

import event_gateway.GetEvents;
import event_gateway.iGetEvents;
import event_related.Event;
import event_related.iEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * A manager that stores Events and relationship between Events and Rooms
 */
public class EventManager implements Serializable {
    private ArrayList<iEvent> events = new ArrayList<>();
    private final Hashtable<String, iEvent> nameToEvent = new Hashtable<>();

    /**
     * The constructor
     */
    public EventManager() {
        iGetEvents filedEvents = new GetEvents();
        ArrayList<String> allEventInfo = filedEvents.read();
        addEvents(allEventInfo);
    }

    /**
     * Returns an event with name, summary and starts at start time
     *
     * @param name      the event name
     * @param summary   the event summary
     * @param startTime the start time of this event
     * @return an event with name, summary and starts at start time
     */
    public iEvent createEvent(String name, String summary, LocalDateTime startTime, int duration) {
        return new Event(name, summary, startTime, duration);
    }

    /**
     * Returns a list of events if the given names are all valid and an empty list
     * if at least one of the names is invalid.
     *
     * @param eventNames the name of the event
     * @return a list of events if the given names are all valid and an empty list
     * if at least one of the names is invalid.
     */
    public ArrayList<iEvent> getEvents(String eventNames) {
        String[] names = eventNames.split(",");
        ArrayList<iEvent> events = new ArrayList<>();
        for (String eventName : names) {
            if (!eventExists(eventName)) {
                return new ArrayList<>();
            }
            events.add(getEvent(eventName));
        }
        return events;
    }

    /**
     * Adds the given event into the event list
     *
     * @param event the given event
     */
    public void addEvent(iEvent event) {
        events.add(event);
        nameToEvent.put(event.getName(), event);
    }

    /**
     * Remove an existed event of the event list and update the nameToEvent
     *
     * @param name name
     */
    public void cancelEvent(String name) {
        iEvent e = getEvent(name);
        events.remove(e);
        nameToEvent.remove(name);
    }

    // TODO
    public int getLength(ArrayList<iEvent> events) {
        return events.size();
    }

    // TODO
    public boolean isPastEvent(iEvent event) {
        boolean isPastEvent = false;
        LocalDateTime eventEndTime = event.getEndTime();
        LocalDateTime present = LocalDateTime.now();
        if (eventEndTime.isBefore(present)) {
            isPastEvent = true;
        }
        return isPastEvent;
    }

    // TODO
    public iEvent grabEvent(ArrayList<iEvent> events, String i) {
        int index = Integer.parseInt(i);
        return events.get(index - 1);
    }

    // TODO
    public iEvent getEvent(String eventName) {
        return nameToEvent.getOrDefault(eventName, null);
    }

    // TODO
    public String eventsToNames(ArrayList<iEvent> events) {
        if (!events.isEmpty()) {
            StringBuilder names = new StringBuilder();
            names.append("----------------------------------------\n");
            int length = events.size();
            for (int i = 0; i < length; i++) {
                iEvent event = events.get(i);
                String name = event.getName();
                String startTime = event.getStartTime().toString();
                String endTime = event.getEndTime().toString();
                // name of the event
                names.append(i + 1).append(". ").append(name);
                // time
                names.append(" (from ").append(startTime);
                names.append(" to ").append(endTime).append(")\n");
            }
            names.append("----------------------------------------");
            return names.toString();
        }
        return "\nNone.";
    }

    /**
     * Returns all events that happen at the same time as the given start time of an event
     *
     * @param startTime the start time of an event
     * @return all events that happen at the same time as the given start time of an event
     */
    public ArrayList<iEvent> getPotentialConflict(LocalDateTime startTime) {
        ArrayList<iEvent> potentialConflicts = new ArrayList<>();
        for (iEvent e : events) {
            if ((startTime.isEqual(e.getStartTime()) | startTime.isAfter(e.getStartTime()))
                    & (startTime.isBefore(e.getEndTime()) | startTime.isEqual(e.getEndTime()))) {
                potentialConflicts.add(e);
            }
        }
        return potentialConflicts;
    }

    /**
     * Returns true iff the event with given name exists
     *
     * @param name the name of the event
     * @return true iff the event with given name exists
     */
    public boolean eventExists(String name) {
        return nameToEvent.containsKey(name);
    }

    private iEvent convertIntoEvents(String oneEvent) {
        ScheduleManager sm = new ScheduleManager();

        String[] arrayInfo = oneEvent.split(",");
        List<String> info = Arrays.asList(arrayInfo);
        String name = info.get(0);
        String summary = info.get(1);
        LocalDateTime startTime = sm.createStartTime(Arrays.copyOfRange(arrayInfo, 2, 7));
        int duration = Integer.parseInt(info.get(7));
        return new Event(name, summary, startTime, duration);
    }

    private void addEvents(ArrayList<String> allEventsInfo) {
        for (String oneEvent : allEventsInfo) {
            iEvent event = convertIntoEvents(oneEvent);
            events.add(event);
            nameToEvent.put(event.getName(), event);
        }
    }

    // getters and setters

    /**
     * Returns the list of all created events (the entire event list).
     *
     * @return the list of all created events (the entire event list).
     */
    public ArrayList<iEvent> getEvents() {
        return events;
    }

    /**
     * Set the entire event list to a new event list.
     *
     * @param events the new event list.
     */
    public void setEvents(ArrayList<iEvent> events) {
        this.events = events;
    }
}
