package src;

import java.util.ArrayList;
import java.util.HashMap;

public class UserManager implements Manager {

    private static UserManager userManager;
    private static Users currentUser;
    private static HashMap<String, Users> users; //We will store all the users inside this hashmap. The UUID will be the key.

    private UserManager(){

    }

    public static UserManager getInstance() {

        if (userManager == null)
            userManager = new UserManager();

        return userManager;
        
    }
    
}
