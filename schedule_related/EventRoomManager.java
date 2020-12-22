package schedule_related;

import event_related.iEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class EventRoomManager implements Serializable {
    private final Hashtable<iEvent, ArrayList<iRoom>> eventToRoom;

    /**
     * Constructs a new event-room manager.
     */
    public EventRoomManager() {
        eventToRoom = new Hashtable<>();
    }

    /**
     * Remove an existed event of the event list and update the eventToRoom
     *
     * @param event the event
     */
    public void cancelEvent(iEvent event) {
        eventToRoom.remove(event);
    }

    /**
     * Returns the assigned room of an event
     *
     * @param event the event
     * @return the assigned room of an event
     */
    public ArrayList<iRoom> getRelevant(iEvent event) {
        return eventToRoom.getOrDefault(event, new ArrayList<>());
    }

    /**
     * Return true if the room is not used by the list of given events
     *
     * @param room   the room
     * @param events the list of events
     * @return true if the room is available at the start time of an event.
     */
    public boolean roomIsChecked(iRoom room, ArrayList<iEvent> events) {
        // the occupied rooms
        ArrayList<iRoom> occupiedRooms = new ArrayList<>();
        for (iEvent e : events) {
            ArrayList<iRoom> rooms = eventToRoom.get(e);
            occupiedRooms.addAll(rooms);
        }
        for (iRoom rm : occupiedRooms) {
            if (rm == room) {
                return false;
            }
        }
        return true;
    }

    /**
     * Assign a room to an event
     *
     * @param event the event
     * @param room  the room
     */
    public void addRoom(iEvent event, iRoom room) {
        if (eventToRoom.containsKey(event)) {
            eventToRoom.get(event).add(room);
        } else {
            ArrayList<iRoom> newList = new ArrayList<>();
            newList.add(room);
            eventToRoom.put(event, newList);
        }
    }

    // TODO
    public String seeRelevant(iEvent event) {
        if (getRelevant(event).isEmpty()) {
            return "None.";
        }
        return getRelevant(event).toString();
    }
}
