package schedule_related;

import _start.GetInput;
import event_related.iEvent;
import login_related.RegisterSystem;
import user_related.SpeakerManager;
import user_related.UserManager;
import user_related.iUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A schedule system.
 * The System responsible for organizers to create new Speakers, Rooms, Events.
 */
public class ScheduleSystem {
    private final SchedulePresenter presenter = new SchedulePresenter();
    private final GetInput getInput = new GetInput();

    // systems
    private final RegisterSystem registerSystem;

    // managers
    private final SpeakerManager sm;
    private final UserManager um;
    private final ScheduleManager sch = new ScheduleManager();

    // update
    private final EventManager eventManager = new EventManager();
    private final EventSpeakerManager eventSpeakerManager = new EventSpeakerManager();
    private final SpeakerEventManager relatedEventManager = new SpeakerEventManager();
    private final RoomManager roomManager = new RoomManager();
    private final EventRoomManager eventRoomManager = new EventRoomManager();
    private final OrganizerEventManager organizedEventManager = new OrganizerEventManager();
    private final EventOrganizerManager eventOrganizerManager = new EventOrganizerManager();

    /**
     * Creates an instance of ScheduleSystem based on the given RegisterSystem
     *
     * @param registerSystem the given RegisterSystem
     */
    public ScheduleSystem(RegisterSystem registerSystem) {
        this.registerSystem = registerSystem;
        sm = registerSystem.getSpeakerManager();
        um = registerSystem.getUserManager();
    }

    /**
     * Allows the given organizer to schedule a new event.
     *
     * @param organizer the given organizer
     */
    public void run(iUser organizer) {
        String option = "";

        while (!option.equals("back")) {
            presenter.printScheduleOptions();
            option = getInput.getNumericalInput(5);

            switch (option) {
                case "1":
                    // see speaker list
                    String speakers = sm.seeAllSpeakers();
                    System.out.println(speakers);
                    break;
                case "2":
                    // see room list
                    String rooms = roomManager.seeAllRooms();
                    System.out.println(rooms);
                    break;
                case "3":
                    // create user
                    registerSystem.run();
                    break;
                case "4":
                    // create room
                    addRoom();
                    break;
                case "5":
                    // create event
                    createNewEvent(organizer);
                    break;
            }
        }
    }

    private void createNewEvent(iUser organizer) {
        iEvent event = createEvent();
        iRoom room = getRoom();
        iUser speaker = getSpeaker();
        if (speaker == null || room == null) {
            presenter.printRequestDeclined();
        } else if (noConflict(event, room, speaker)) {
            addEvent(organizer, event, speaker, room);
            presenter.printEventAdded();
        } else {
            presenter.printEventConflict();
        }
    }

    private iEvent createEvent() {
        // gather event info
        String name = getEventName();
        String summary = getEventSummary();
        LocalDateTime startTime = getEventStartTime();
        int duration = getDuration();
        return eventManager.createEvent(name, summary, startTime, duration);
    }

    private boolean noConflict(iEvent event, iRoom room, iUser speaker) {
        LocalDateTime startTime = event.getStartTime();
        ArrayList<iEvent> potentialConflicts = eventManager.getPotentialConflict(startTime);
        boolean roomIsChecked = eventRoomManager.roomIsChecked(room, potentialConflicts);
        boolean speakerIsChecked = relatedEventManager.speakerIsChecked(startTime, speaker);
        return roomIsChecked && speakerIsChecked;
    }

    private String getEventName() {
        presenter.printGetName();
        String name = getInput.getUserInput();

        while (eventManager.eventExists(name)) {
            presenter.printNameConflict();
            presenter.printGetName();
            name = getInput.getUserInput();
        }
        return name;
    }

    private String getEventSummary() {
        presenter.printGetSummary();
        return getInput.getUserInput();
    }

    private LocalDateTime getEventStartTime() {
        presenter.printGetTime();
        String timeString = getInput.getUserInput();
        String[] timeBreakDown = timeString.split(",");
        LocalDateTime startTime = sch.createStartTime(timeBreakDown);
        while (startTime == null) {
            presenter.printTimeWrongFormat();
            startTime = getEventStartTime();
        }
        return startTime;
    }

    private int getDuration() {
        presenter.printGetDuration();
        return getInput.getNumber(48);
    }

    private iUser getSpeaker() {
        presenter.printGetSpeaker();
        String username = getInput.getUserInput();
        iUser speaker = um.getUser(username);

        if (speaker == null) {
            speaker = createSpeaker();
        }
        return speaker;
    }

    private iRoom getRoom() {
        presenter.printGetRoom();
        String roomNumber = getInput.getUserInput();
        iRoom room = roomManager.getRoom(roomNumber);

        if (room == null) {
            room = createRoom();
        }
        return room;
    }

    private iRoom createRoom() {
        presenter.printCreateRoom();
        String choice = getInput.getNumericalInput(2);
        iRoom room = null;
        while (choice.equals("back")) {
            choice = getInput.getNumericalInput(2);
        }
        if (choice.equals("1")) {
            room = addRoom();
        } else {
            presenter.printRequestDeclined();
        }
        return room;
    }

    private iRoom addRoom() {
        presenter.printAddRoom();
        String roomNum = getInput.getUserInput();
        iRoom room = roomManager.getRoom(roomNum);

        if (room == null) {  // room DNE
            room = roomManager.createRoom(roomNum);
            roomManager.addRoom(room);
            presenter.printRoomAdded();
        } else {
            presenter.printRoomConflict();
        }
        return room;
    }

    private iUser createSpeaker() {
        presenter.printCreateSpeaker();
        String answer = getInput.getNumericalInput(2);
        iUser user = null;
        while (answer.equals("back")) {
            answer = getInput.getNumericalInput(2);
        }
        if (answer.equals("1")) {
            user = registerSystem.createUser(3);
        } else {
            presenter.printRequestDeclined();
        }
        return user;
    }

    private void addEvent(iUser organizer, iEvent event, iUser speaker, iRoom room) {
        eventManager.addEvent(event);
        addRelevant(1, event, speaker);
        addRelevant(3, event, organizer);
        addEvent(1, event, speaker);
        addEvent(2, event, organizer);
        // update room
        eventRoomManager.addRoom(event, room);
    }

    private void addRelevant(int type, iEvent event, iUser relevant) {
        iEventUserManager manager;

        if (type == 1) {
            manager = eventSpeakerManager;
        } else {
            manager = eventOrganizerManager;
        }
        manager.addRelevant(event, relevant);
    }

    private void addEvent(int type, iEvent event, iUser user) {
        iUserEventManager manager;
        if (type == 1) {
            manager = relatedEventManager;
        } else {
            manager = organizedEventManager;
        }
        manager.addEvent(event, user);
    }

    /**
     * Returns the eventManager.
     *
     * @return the eventManager
     */
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Returns the relatedEventManager.
     *
     * @return the relatedEventManager
     */
    public SpeakerEventManager getRelatedEventManager() {
        return relatedEventManager;
    }

    /**
     * Returns the eventSpeakerManager.
     *
     * @return the eventSpeakerManager
     */
    public EventSpeakerManager getEventSpeakerManager() {
        return eventSpeakerManager;
    }

    /**
     * Returns the eventRoomManager.
     *
     * @return the eventRoomManager
     */
    public EventRoomManager getEventRoomManager() {
        return eventRoomManager;
    }

    /**
     * Returns the organizedEventManager.
     *
     * @return the organizedEventManager
     */
    public OrganizerEventManager getOrganizedEventManager() {
        return organizedEventManager;
    }

    /**
     * Returns the eventOrganizerManager.
     *
     * @return the eventOrganizerManager
     */
    public EventOrganizerManager getEventOrganizerManager() {
        return eventOrganizerManager;
    }
}
