package rating_related;

import event_related.iEvent;
import user_related.iUser;

import java.util.ArrayList;
import java.util.Hashtable;

public class UserRatedEventManager {
    private final Hashtable<iUser, ArrayList<iEvent>> userToRatedEvents = new Hashtable<>();

    public void addEvent(iUser user, iEvent event){
        if (userHasRated(user)){
            userToRatedEvents.get(user).add(event);
        } else{
            ArrayList<iEvent> ratedEvents = new ArrayList<>();
            ratedEvents.add(event);
            userToRatedEvents.put(user, ratedEvents);
        }
    }

    private boolean userHasRated(iUser user){
        return userToRatedEvents.containsKey(user);
    }

    private ArrayList<iEvent> getRatedEvents(iUser user){
        return userToRatedEvents.getOrDefault(user, new ArrayList<>());
    }

    public boolean hasNotRated(iUser user, iEvent event){
        ArrayList<iEvent> ratedEvents = getRatedEvents(user);
        for (iEvent e : ratedEvents){
            if (e==event){
                return false;
            }
        }
        return true;
    }
}
