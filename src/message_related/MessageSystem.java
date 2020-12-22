package message_related;


import _start.GetInput;
import event_related.EventSystem;
import login_related.RegisterSystem;
import user_related.iUser;


/**
 * A message system.
 * <p>
 * Responsible for all message-related user interactions
 */
public class MessageSystem {
    private final MessagePresenter presenter = new MessagePresenter();
    private final GetInput getInput = new GetInput();

    // store
    private final ReceivedMessageManager receivedMessageManager = new ReceivedMessageManager();
    private final UnreadMessageManager unreadMessageManager = new UnreadMessageManager();
    private final ArchivedMessageManager archivedMessageManager = new ArchivedMessageManager();
    private final SentMessageManager sentMessageManager = new SentMessageManager();

    private final SeeMessage seeMessage;
    private final SendMessage sendMessage;

    /**
     * Creates an instance of MessageSystem based on the given RegisterSystem and EventSystem
     *
     * @param registerSystem the given RegisterSystem
     * @param eventSystem    the given EventSystem
     */
    public MessageSystem(RegisterSystem registerSystem, EventSystem eventSystem) {
        sendMessage = new SendMessage(registerSystem, eventSystem, this);
        seeMessage = new SeeMessage(sendMessage, this);
    }

    /**
     * Allows the user to interact with message interface
     *
     * @param user the given user
     */
    public void run(iUser user) {
        String input = "";

        while (!input.equals("back")) {
            presenter.printMessage();
            input = getInput.getNumericalInput(2);

            if (input.equals("1")) {
                // see message
                seeMessage.seeMessageStrategy(user);
            } else if (input.equals("2")) {
                // send message
                sendMessage.sendMessages(user);
            }
            // go back
        }
    }

    // getters and setters
    public ArchivedMessageManager getArchivedMessageManager() {
        return archivedMessageManager;
    }

    public ReceivedMessageManager getReceivedMessageManager() {
        return receivedMessageManager;
    }

    public SentMessageManager getSentMessageManager() {
        return sentMessageManager;
    }

    public UnreadMessageManager getUnreadMessageManager() {
        return unreadMessageManager;
    }

}