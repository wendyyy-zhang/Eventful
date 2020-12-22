package schedule_related;

import event_related.iEvent;
import user_related.iUser;

import java.util.ArrayList;

public interface iUserEventManager {
    void addEvent(iEvent event, iUser user);

    ArrayList<iEvent> getEvents(iUser user);
}
