package schedule_related;

import _start.GeneralPresenter;

public class SchedulePresenter extends GeneralPresenter {
    /**
     * Print the options when an organizer schedules events
     */
    public void printScheduleOptions() {
        System.out.println("\n" +
                "1. See list of speakers\n" +
                "2. See list of rooms\n" +
                "3. Create a user\n" +
                "4. Create a room\n" +
                "5. Create an event\n" +
                "Option:");
    }

    /**
     * Ask for the name of the new event
     */
    public void printGetName() {
        System.out.println("\nEvent name:");
    }

    public void printNameConflict() {
        System.out.println("\nThis name has been picked, please try again.");
    }

    /**
     * Ask for the summary of the new event
     */
    public void printGetSummary() {
        System.out.println("\nEvent summary:");
    }

    /**
     * Ask for the start time of the new event
     */
    public void printGetTime() {
        System.out.println("\nEnter the time in this format (year,month,day,hour,min)");
    }

    /**
     * Prints out to ask for the duration in hours of the new event
     */
    public void printGetDuration(){
        System.out.println("\n" +
                "Enter the duration of event (in hours) \n" +
                "(Note: duration should be between 1 and 48 hours)");
    }

    /**
     * Ask for the username of the speaker who is related to new event
     */
    public void printGetSpeaker() {
        System.out.println("\nSpeaker username:");
    }

    /**
     * Ask for the number of room assigned to the event
     */
    public void printGetRoom() {
        System.out.println("\nRoom number:");
    }

    /**
     * Print when a new event is added
     */
    public void printEventAdded() {
        System.out.println("\nEvent has been successfully added!");
    }

    /**
     * Print when the new event cannot be added
     */
    public void printEventConflict() {
        System.out.println("\nFailed: conflict.");
    }

    /**
     * Print when the input time has the wrong format
     */
    public void printTimeWrongFormat() {
        System.out.println("\nFailed: The start time cannot be created.");
    }

    /**
     * Print the options when the provided speaker does not exist
     */
    public void printCreateSpeaker() {
        System.out.println("\n" +
                "Speaker not found\n" +
                "1. Create one\n" +
                "2. Decline request\n" +
                "Option:");
    }

    /**
     * Print the options when the provided room does not exist
     */
    public void printCreateRoom() {
        System.out.println("\n" +
                "Room not found, do you want to add one?\n" +
                "1. Create one\n" +
                "2. Decline request\n" +
                "Option:");
    }

    /**
     * Ask for the room number
     */
    public void printAddRoom() {
        System.out.println("\nEnter the room number: ");
    }

    public void printRoomConflict() {
        System.out.println("\nFailed: the room already exists");
    }

    /**
     * Print when a new room has been added
     */
    public void printRoomAdded() {
        System.out.println("\nThe room has been successfully added!");
    }
}
