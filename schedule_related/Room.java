package schedule_related;

/**
 * A room.
 */
public class Room implements iRoom{

    private final String number;
    private int capacity;

    /**
     * Construct a new empty room
     */
    public Room(String number) {
        this.number = number;
        capacity = 2;
    }

    /**
     * return a comma separated list of id and capacity
     *
     * @return a comma separated list of id and capacity
     */
    public String toString() {
        return getNumber() + "(" + getCapacity() + ")";
    }

    // getters and setters

    /**
     * return the number of the room
     *
     * @return the number of the room
     */
    @Override
    public String getNumber() {
        return number;
    }

    /**
     * set new capacity for the room
     *
     * @param new_capacity the new capacity to set
     */
    public void setCapacity(Integer new_capacity) {
        capacity = new_capacity;
    }

    /**
     * return the capacity of the room
     *
     * @return the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }

}
