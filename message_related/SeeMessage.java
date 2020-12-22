package message_related;

import _start.GetInput;
import user_related.iUser;

import java.util.ArrayList;

public class SeeMessage {
    private final SeeMessagePresenter presenter = new SeeMessagePresenter();
    private final GetInput getInput = new GetInput();

    private final MessageManager messageManager = new MessageManager();

    private final SendMessage sendMessage;

    private final ReceivedMessageManager receivedMessageManager;
    private final UnreadMessageManager unreadMessageManager;
    private final ArchivedMessageManager archivedMessageManager;
    private final SentMessageManager sentMessageManager;

    public SeeMessage(SendMessage sendMessage, MessageSystem messageSystem) {
        this.sendMessage = sendMessage;
        this.receivedMessageManager = messageSystem.getReceivedMessageManager();
        this.unreadMessageManager = messageSystem.getUnreadMessageManager();
        this.archivedMessageManager = messageSystem.getArchivedMessageManager();
        this.sentMessageManager = messageSystem.getSentMessageManager();
    }

    // TODO
    protected void seeMessageStrategy(iUser user) {
        String input;
        iMessageManager currentManager;
        int type;

        while (true) {
            presenter.printSeeMessage();
            input = getInput.getNumericalInput(4);

            switch (input) {
                case "1":
                    // general inbox
                    currentManager = receivedMessageManager;
                    type = 1;
                    break;
                case "2":
                    // unread inbox
                    currentManager = unreadMessageManager;
                    type = 1;
                    break;
                case "3":
                    // archived inbox
                    currentManager = archivedMessageManager;
                    type = 1;
                    break;
                case "4":
                    // default manager: sent message
                    currentManager = sentMessageManager;
                    type = 2;
                    break;
                default:
                    // go back
                    return;
            }
            ArrayList<iMessage> messages = currentManager.getMessages(user);
            String titles = messageManager.messagesToTitles(messages, type);
            presenter.print(titles);
            showSingleMessage(user, currentManager);
        }
    }

    private void showSingleMessage(iUser user, iMessageManager manager) {
        int ValidRange = manager.getListLength(user);
        if (ValidRange != 0) {
            String input = getInput.getNumericalInput(ValidRange);

            if (!input.equals("back")) {
                int index = Integer.parseInt(input);
                iMessage message = manager.grabMessage(user, index);

                unreadMessageManager.deleteMessage(user, message);
                presenter.print(message.toString());
                singleMessageOptions(user, manager, message);
            }
        }
    }

    private void singleMessageOptions(iUser user, iMessageManager manager, iMessage message) {
        presenter.printMessageOption();
        String input = getInput.getNumericalInput(4);
        switch (input) {
            case "1":  // mark as unread
                unreadMessageManager.addMessage(user, message);
                presenter.printMarked();
                break;
            case "2":  // archive
                archivedMessageManager.addMessage(user, message);
                presenter.printArchived();
                break;
            case "3":  // delete
                boolean confirmed = confirmDelete(manager, user, message);
                if (confirmed) {
                    presenter.printMessageDeleted();
                } else {
                    presenter.printRequestDeclined();
                }
                break;
            case "4":  // reply
                reply(user, message);
                break;
            // else, go back
        }
    }

    private boolean confirmDelete(iMessageManager manager, iUser user, iMessage message) {
        boolean confirmed = false;
        int type;

        if (manager instanceof ReceivedMessageManager) {
            // remove from all inboxes
            presenter.printConfirmDelete();
            type = 1;
        } else {
            // remove from this box
            presenter.printConfirmRemove();
            type = 2;
        }

        String input = getInput.getNumericalInput(2);
        if (input.equals("1")) {
            confirmed = true;
            delete(manager, user, message, type);
        }
        return confirmed;
    }

    private void delete(iMessageManager manager, iUser user, iMessage message, int type) {
        ArrayList<iMessageManager> allManagers = new ArrayList<>();

        if (type == 1) {
            // remove from all
            allManagers.add(receivedMessageManager);
            allManagers.add(unreadMessageManager);
            allManagers.add(archivedMessageManager);
        } else {
            // remove from one
            allManagers.add(manager);
        }

        for (iMessageManager m : allManagers) {
            m.deleteMessage(user, message);
        }
    }

    private void reply(iUser user, iMessage message) {
        sendMessage.reply(user, message);
    }
}
