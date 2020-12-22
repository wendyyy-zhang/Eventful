package message_related;

import user_related.iUser;

import java.util.ArrayList;

/**
 * An iMessageManager interface.
 */
public interface iMessageManager {
    iMessage grabMessage(iUser user, int i);

    ArrayList<iMessage> getMessages(iUser user);

    int getListLength(iUser user);

    void addMessage(iUser user, iMessage message);

    void deleteMessage(iUser user, iMessage message);
}
