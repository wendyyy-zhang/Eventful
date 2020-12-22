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
     * Returns the assigned room of the given event.
     *
     * @param event the given event
     * @return the assigned room of the given event
     */
    public ArrayList<iRoom> getRelevant(iEvent event) {
        return eventToRoom.getOrDefault(event, new ArrayList<>());
    }

    /**
     * Returns true if the given room is not assigned to the given list of events.
     *
     * @param room   the given room
     * @param events the given list of events
     * @return true if the room is not assigned to the given list of events
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
     * Assigns the given room to the given event
     *
     * @param event the given event
     * @param room  the given room
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

    /**
     * Returns the list of rooms assigned to the given event and null if the event has no room.
     *
     * @param event the given event
     * @return the list of rooms assigned to the given event and null if the event has no room
     */
    public String seeRelevant(iEvent event) {
        if (getRelevant(event).isEmpty()) {
            return "None.";
        }
        return getRelevant(event).toString();
    }
}
