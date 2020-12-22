package schedule_related;

import event_related.iEvent;
import user_related.iUser;

import java.util.ArrayList;

public interface iEventUserManager {
    ArrayList<iUser> getRelevant(iEvent event);

    void addRelevant(iEvent event, iUser relevant);
}
