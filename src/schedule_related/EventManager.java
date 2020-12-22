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
 * An event manager.
 * A manager that stores all events and relationship between events and assigned rooms.
 */
public class EventManager implements Serializable {
    private final Hashtable<String, iEvent> nameToEvent = new Hashtable<>();
    private ArrayList<iEvent> events = new ArrayList<>();

    /**
     * Constructs a new event manager.
     */
    public EventManager() {
        iGetEvents filedEvents = new GetEvents();
        ArrayList<String> allEventInfo = filedEvents.read();
        addEvents(allEventInfo);
    }

    /**
     * Returns an event with the given name, summary and starts at the given start time.
     *
     * @param name      the given event name
     * @param summary   the given event summary
     * @param startTime the given start time of this event
     * @return an event with the given name, summary and starts at the given start time
     */
    public iEvent createEvent(String name, String summary, LocalDateTime startTime, int duration) {
        return new Event(name, summary, startTime, duration);
    }

    /**
     * Returns a list of events if the given names are all valid or an empty list
     * if at least one of the names is invalid.
     *
     * @param eventNames the given event names
     * @return a list of events if the given names are all valid or an empty list
     * if at least one of the names is invalid
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
     * Adds the given event into the event list.
     *
     * @param event the given event
     */
    public void addEvent(iEvent event) {
        events.add(event);
        nameToEvent.put(event.getName(), event);
    }

    /**
     * Returns true if and only if the given event's end time is in the past.
     *
     * @param event the given event
     * @return true if and only if the given event's end time is in the past
     */
    public boolean isPastEvent(iEvent event) {
        boolean isPastEvent = false;
        LocalDateTime eventEndTime = event.getEndTime();
        LocalDateTime present = LocalDateTime.now();
        if (eventEndTime.isBefore(present)) {
            isPastEvent = true;
        }
        return isPastEvent;
    }

    /**
     * Returns (i-1)th event in the given event list.
     *
     * @param events the give event list
     * @param i      the number of index + 1
     * @return (i - 1)th event in the given event list
     */
    public iEvent grabEvent(ArrayList<iEvent> events, String i) {
        int index = Integer.parseInt(i);
        return events.get(index - 1);
    }

    /**
     * Returns the given of the given event name and null if the given event name does not match with
     * any existing events.
     *
     * @param eventName the given event name
     * @return the given of the given event name and null if the given event name does not match with
     * any existing events
     */
    public iEvent getEvent(String eventName) {
        return nameToEvent.getOrDefault(eventName, null);
    }

    /**
     * Returns the names of the events in the given event list.
     *
     * @param events the given event list
     * @return the names of the events in the given event list
     */
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
     * Returns all events that happen at the same time as the given start time of an event.
     *
     * @param startTime the given start time of an event
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
     * Returns true if and only if the event with the given name exists.
     *
     * @param name the name of the event
     * @return true if and only if the event with the given name exists
     */
    public boolean eventExists(String name) {
        return nameToEvent.containsKey(name);
    }

    // helper methods
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
