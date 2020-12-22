package schedule_related;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class RoomManager implements Serializable {

    ArrayList<iRoom> rooms = new ArrayList<>();
    private final Hashtable<String, iRoom> nameToRoom = new Hashtable<>();

    // TODO
    public void addRoom(iRoom room) {
        nameToRoom.put(room.getNumber(), room);
        rooms.add(room);
    }

    // TODO
    public iRoom createRoom(String number) {
        return new Room(number);
    }

    // TODO
    public iRoom getRoom(String number) {
        return nameToRoom.getOrDefault(number, null);

    }

    // TODO
    public String seeAllRooms(){
        return rooms.toString();
    }

}
