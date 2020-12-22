package user_related;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A manager that stores iUsers
 */
public class UserManager implements Serializable {

    private final Hashtable<String, iUser> usernameToUser = new Hashtable<>();

    /**
     * Add a new user to the both the usernameToUser and the idToUser hashtable.
     *
     * @param newUser the new user to be added.
     */
    public void addUser(iUser newUser) {
        String username = newUser.getUsername();
        usernameToUser.put(username, newUser);
    }

    /**
     * Returns true if and only if the user exists and the username matches with the password
     *
     * @param username the username
     * @param password the password
     * @return true if and only if the user exists and the username matches with the password
     */
    public boolean logIn(String username, String password) {
        boolean userExists = usernameToUser.containsKey(username);
        if (!userExists) {
            return false;
        }
        String correctPassword = usernameToUser.get(username).getPassword();
        return password.equals(correctPassword);
    }

    // TODO
    public boolean userExists(String username) {
        return usernameToUser.containsKey(username);
    }

    // TODO
    public iUser getUser(String username) {
        return usernameToUser.getOrDefault(username, null);
    }

    // TODO
    public String usersToString(ArrayList<iUser> users){
        if (users.isEmpty()){
            return "\nNone.";
        }
        return users.toString();
    }
}