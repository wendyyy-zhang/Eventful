package schedule_related;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A room manager.
 * Stores rooms.
 */
public class RoomManager implements Serializable {

    private final Hashtable<String, iRoom> nameToRoom = new Hashtable<>();
    ArrayList<iRoom> rooms = new ArrayList<>();

    /**
     * Adds the given room to the collections.
     *
     * @param room the given room.
     */
    public void addRoom(iRoom room) {
        nameToRoom.put(room.getNumber(), room);
        rooms.add(room);
    }

    /**
     * Returns a room created by the given number.
     *
     * @param number the given room number
     * @return a room created by the given number
     */
    public iRoom createRoom(String number) {
        return new Room(number);
    }

    /**
     * Returns the room with the given room number and null if there is no room with such number.
     *
     * @param number the given room number
     * @return the room with the given room number and null if there is no room with such number
     */
    public iRoom getRoom(String number) {
        return nameToRoom.getOrDefault(number, null);

    }

    /**
     * Returns the string of all rooms.
     *
     * @return the string of all rooms
     */
    public String seeAllRooms() {
        return rooms.toString();
    }

}
