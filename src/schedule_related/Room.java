package schedule_related;

/**
 * A room.
 */
public class Room implements iRoom {

    private final String number;
    private int capacity;

    /**
     * Constructs a new room with default capacity of 2.
     */
    public Room(String number) {
        this.number = number;
        capacity = 2;
    }

    /**
     * Returns the string version of the room.
     *
     * @return the string version of the room
     */
    public String toString() {
        return getNumber() + "(" + getCapacity() + ")";
    }

    // getters and setters

    /**
     * Returns the number of the room.
     *
     * @return the number of the room
     */
    @Override
    public String getNumber() {
        return number;
    }

    /**
     * Returns the capacity of the room.
     *
     * @return the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the new capacity for the room.
     *
     * @param capacity the given new capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

}
