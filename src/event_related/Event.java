package event_related;

import java.time.LocalDateTime;

/**
 * An event.
 */
public class Event implements iEvent {

    private String name;
    private String summary;

    private LocalDateTime startTime;
    private int duration;  // is in hour
    private LocalDateTime endTime;

    /**
     * Constructs an event based on the given name, summary, start time, and duration.
     *
     * @param eventName    the name of this event
     * @param eventSummary the summary of this event
     * @param startTime    the starting time of this event
     * @param duration     the duration of this event (in hours)
     */
    public Event(String eventName, String eventSummary, LocalDateTime startTime, int duration) {
        name = eventName;
        summary = eventSummary;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = startTime.plusHours(duration);
    }

    /**
     * Returns the string version of an event.
     *
     * @return the string version of an event
     */
    @Override
    public String toString() {
        return "" +
                "Name: " + name + '\n' +
                "Summary: " + summary + '\n' +
                "Start time: " + startTime + '\n' +
                "Duration: " + duration + " hour(s)\n";
    }

    // getters and setters

    /**
     * Returns the name of this event.
     *
     * @return the name of this event.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this event to the given event name.
     *
     * @param eventName the new event name
     */
    public void setName(String eventName) {
        name = eventName;
    }

    /**
     * Returns the summary of this event.
     *
     * @return the summary of this event
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary of this event to the given event summary.
     *
     * @param eventSummary the new event summary
     */
    public void setSummary(String eventSummary) {
        summary = eventSummary;
    }

    /**
     * Returns the duration of this event.
     *
     * @return duration of this event
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of this event and updates the end time.
     *
     * @param duration the new event duration
     */
    public void setDuration(int duration) {
        // difference between the original duration and the new duration
        int difference = this.duration - duration;

        // the duration has been shortened
        if (difference > 0) {
            endTime = endTime.minusHours(difference);
        } else if (difference < 0) {
            endTime = endTime.plusHours(difference);
        }

        this.duration = duration;
    }

    /**
     * Returns the start time of this event
     *
     * @return the start time of this event
     */
    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time to a new start time and update the end time.
     *
     * @param startTime the time for this event to start
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        endTime = startTime.plusHours(duration);
    }

    /**
     * Returns the end time of this event
     *
     * @return the end time of this event
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
}