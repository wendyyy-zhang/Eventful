package friend_related;

import user_related.iUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A friend manager.
 * A manager that stores the relationship between users and their friends.
 */
public class FriendManager implements Serializable {

    private final Hashtable<iUser, ArrayList<iUser>> userToFriends = new Hashtable<>();

    /**
     * Returns true if and only if the friend is not yet added.
     *
     * @param user  the given user
     * @param other the potential friend
     * @return true if and only if the friend is not yet added
     */
    public boolean isNotYetAdded(iUser user, iUser other) {
        ArrayList<iUser> friendList = getFriend(user);

        for (iUser friend : friendList) {
            if (other == friend) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if and only if the friend has been successfully added.
     *
     * @param user   the given user
     * @param friend the given to-be-added friend
     * @return true if and only if the friend has been successfully added
     */
    public boolean addFriend(iUser user, iUser friend) {
        boolean haveFriendList = !getFriend(user).isEmpty();
        if (isNotYetAdded(user, friend) && haveFriendList) {
            userToFriends.get(user).add(friend);
            return true;
        } else if (isNotYetAdded(user, friend)) {
            ArrayList<iUser> friends = new ArrayList<>();
            friends.add(friend);
            userToFriends.put(user, friends);
            return true;
        }
        return false;
    }

    /**
     * Returns the friend list of the given user
     *
     * @param user the given user
     * @return the friend list of the given user
     */
    public ArrayList<iUser> getFriend(iUser user) {
        return userToFriends.getOrDefault(user, new ArrayList<>());
    }

}
