package message_related;

import _start.GetInput;
import event_related.EventAttendManager;
import event_related.EventSystem;
import event_related.iEvent;
import login_related.RegisterSystem;
import schedule_related.EventManager;
import schedule_related.SpeakerEventManager;
import schedule_related.ScheduleSystem;
import user_related.*;

import java.util.ArrayList;

public class SendMessage {
    private final SendMessagePresenter presenter = new SendMessagePresenter();
    private final GetInput getInput = new GetInput();

    // managers
    private final MessageManager messageManager = new MessageManager();

    private final UserManager userManager;
    private final SpeakerManager speakerManager;
    private final AttendeeManager attendeeManager;
    private final EventManager eventManager;
    private final SpeakerEventManager relatedEventManager;
    private final EventAttendManager eventAttendManager;

    private final ReceivedMessageManager receivedMessageManager;
    private final UnreadMessageManager unreadMessageManager;
    private final SentMessageManager sentMessageManager;

    public SendMessage(RegisterSystem registerSystem, EventSystem eventSystem, MessageSystem messageSystem){
        ScheduleSystem scheduleSystem = eventSystem.getScheduleSystem();
        userManager = registerSystem.getUserManager();
        speakerManager = registerSystem.getSpeakerManager();
        attendeeManager = registerSystem.getAttendeeManager();
        eventManager = scheduleSystem.getEventManager();
        relatedEventManager = scheduleSystem.getRelatedEventManager();
        eventAttendManager = eventSystem.getEventAttendManager();
        this.receivedMessageManager = messageSystem.getReceivedMessageManager();
        this.unreadMessageManager = messageSystem.getUnreadMessageManager();
        this.sentMessageManager = messageSystem.getSentMessageManager();
    }

    // TODO
    protected void sendMessages(iUser sender) {
        boolean isOrganizer = sender.isOrganizer();
        boolean isSpeaker = sender.isSpeaker();

        if (isOrganizer) {
            organizerSendMessage(sender);
        } else if (isSpeaker) {
            speakerSendMessage(sender);
        } else {
            // attendee send message
            attendeeSendMessage(sender);
        }
    }

    private void organizerSendMessage(iUser organizer) {
        presenter.printOrganizerSendMessage();
        String input = getInput.getNumericalInput(3);

        if (input.equals("3")) {  // to a user
            attendeeSendMessage(organizer);
        } else if (!input.equals("back")) {  // to all
            ArrayList<iUser> users = getUsers(input);
            sendToAll(organizer, users);
            presenter.printMessageSent();
        }
    }

    private void speakerSendMessage(iUser speaker) {
        presenter.printSpeakerSendMessage();
        String input = getInput.getNumericalInput(2);

        ArrayList<iEvent> events = getEvents(input, speaker);

        if (!events.isEmpty() && !input.equals("back")) {
            ArrayList<iUser> attendees = getAttendees(events);
            sendToAll(speaker, attendees);
            presenter.printMessageSent();
        } else if (!input.equals("back")) {
            presenter.printInvalidEvents();
        }
    }

    private void attendeeSendMessage(iUser attendee) {
        iUser receiver = getReceiver(attendee);
        if (receiver != null) {
            ArrayList<iUser> user = new ArrayList<>();
            user.add(receiver);
            sendToAll(attendee, user);
            presenter.printMessageSent();
        } else {
            presenter.printNoMatch();
        }
    }

    private ArrayList<iUser> getUsers(String input) {
        iUserManager manager;
        if (input.equals("1")) {
            manager = attendeeManager;
        } else {
            manager = speakerManager;
        }
        return manager.getUsers();
    }

    private ArrayList<iEvent> getEvents(String input, iUser speaker) {
        ArrayList<iEvent> relatedEvents = relatedEventManager.getEvents(speaker);
        ArrayList<iEvent> events = new ArrayList<>();
        if (input.equals("1")) {  // to attendees of one or more events
            events = getRelatedEvents(relatedEvents);
        } else if (input.equals("2")) {  // to all attendees
            events = relatedEvents;
        }
        return events;
    }

    private iUser getReceiver(iUser sender) {
        presenter.printSendTo();
        String receiverName = getInput.getUserInput();
        iUser receiver = userManager.getUser(receiverName);

        if (receiver != null) {
            boolean notOrganizerSender = !sender.isOrganizer();
            boolean organizerReceiver = receiver.isOrganizer();

            if (notOrganizerSender && organizerReceiver) {
                receiver = null; // organizers cannot receive message from non-organizers
            }
        }
        return receiver;
    }

    private String getTitle() {
        presenter.printGetTitle();
        return getInput.getUserInput();
    }

    private String getContent() {
        presenter.printGetContent();
        return getInput.getUserInput();
    }

    private void sendToAll(iUser sender, ArrayList<iUser> users) {
        String title = getTitle();
        String content = getContent();
        for (iUser user : users) {
            sendMessage(sender, user, title, content);
        }
    }

    private void sendMessage(iUser sender, iUser receiver, String title, String content) {
        // create a single message
        iMessage message = messageManager.createMessage(sender, receiver, title, content);
        // add to sender's sent message list
        sentMessageManager.addMessage(sender, message);
        // add to receiver's received + unread list
        receivedMessageManager.addMessage(receiver, message);
        unreadMessageManager.addMessage(receiver, message);
    }

    private ArrayList<iEvent> getRelatedEvents(ArrayList<iEvent> relatedEvents) {
        presenter.printEventNames();
        String eventNames = getInput.getUserInput();
        ArrayList<iEvent> events = eventManager.getEvents(eventNames);
        if (events.isEmpty() || !relatedEvents.containsAll(events)) {
            // if any of the names are invalid or not all events are related
            return new ArrayList<>();
        }
        return events;
    }

    private ArrayList<iUser> getAttendees(ArrayList<iEvent> relatedEvents) {
        ArrayList<iUser> relatedAttendees = new ArrayList<>();
        for (iEvent event : relatedEvents) {
            relatedAttendees.addAll(eventAttendManager.getRelevant(event));
        }
        return relatedAttendees;
    }

    // TODO
    protected void reply(iUser user, iMessage message) {
        String receiverUsername = message.getSenderUsername();
        iUser receiver = userManager.getUser(receiverUsername);
        String title = getTitle();
        String content = getContent();
        sendMessage(user, receiver, title, content);
        presenter.printMessageSent();
    }
}
