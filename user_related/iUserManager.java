package user_related;

import java.util.ArrayList;

public interface iUserManager {

    void addUser(iUser user);

    iUser createUser(String username, String password);

    ArrayList<iUser> getUsers();
}
