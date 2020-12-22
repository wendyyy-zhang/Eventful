package event_related;

import java.time.LocalDateTime;

/**
 * An event.
 */
public class Event implements iEvent {

    private String name;
    private String summary;

    private LocalDateTime startTime;
    // duration is in hour
    private int duration;
    private LocalDateTime endTime;

    /**
     * Construct an event based on the information
     *
     * @param eventName    the name of this event
     * @param eventSummary the summary of this event
     * @param startTime    the starting time of this event
     */
    public Event(String eventName, String eventSummary, LocalDateTime startTime, int duration) {
        name = eventName;
        summary = eventSummary;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = startTime.plusHours(duration);
    }

    /**
     * Returns the string version of an event
     *
     * @return the string version of an event
     */
    @Override
    public String toString() {
        return "" +
                "Name: " + name + '\n' +
                "Summary: " + summary + '\n' +
                "StartTime: " + startTime + '\n' +
                "Duration: " + duration + " hour(s)\n";
    }

    // getters and setters

    /**
     * Set the name of this event.
     *
     * @param name_event the new name to set
     */
    public void setName(String name_event) {
        name = name_event;
    }

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
     * Set the summary of this event.
     *
     * @param summary_event the new summary to set
     */
    public void setSummary(String summary_event) {
        summary = summary_event;
    }

    /**
     * Returns the summary of this event.
     *
     * @return the summary of this event.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Set the duration of this event. Update the endTime of this event.
     *
     * @param duration the new duration to set
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
     * Returns the duration of this event
     *
     * @return duration of this event
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the start time to a new start time; update the end time.
     *
     * @param startTime the time for this event to start
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        endTime = startTime.plusHours(duration);
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
     * Returns the end time of this event
     *
     * @return the end time of this event
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
}