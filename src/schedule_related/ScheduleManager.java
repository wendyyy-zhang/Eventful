package schedule_related;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A schedule manager.
 */
public class ScheduleManager {
    /**
     * Returns the start time of an event to be created
     *
     * @param stringInts the array of strings representing a time point
     * @return the start time of an event to be created
     */
    public LocalDateTime createStartTime(String[] stringInts) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (String s : stringInts) {
            try {
                int i = Integer.parseInt(s);
                ints.add(i);

            } catch (NumberFormatException e) {
                return null;
            }
        }
        return validateTime(ints);
    }

    // helper method
    private LocalDateTime validateTime(ArrayList<Integer> ints) {
        // year, month, day, hour, minute
        try {
            LocalDateTime givenTime = LocalDateTime.of(ints.get(0), ints.get(1), ints.get(2), ints.get(3), ints.get(4));

            // if is after present
            LocalDateTime present = LocalDateTime.now();

            if ((givenTime.isAfter(present))) {
                return givenTime;
            }

        } catch (DateTimeException | IndexOutOfBoundsException e) {
            return null;
        }
        return null;
    }
}
