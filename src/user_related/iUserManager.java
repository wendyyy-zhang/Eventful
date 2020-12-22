package user_related;

import java.util.ArrayList;

/**
 * An iUserManager Interface.
 */
public interface iUserManager {
    void addUser(iUser user);

    iUser createUser(String username, String password);

    ArrayList<iUser> getUsers();
}
