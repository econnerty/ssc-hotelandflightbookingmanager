package src;

import java.util.ArrayList;

public class UserManager implements Manager {

    private static UserManager userManager;
    private static Users currentUser;
    private static ArrayList<Users> users;

    private UserManager(){

    }

    public static UserManager getInstance() {

        if (userManager == null)
            userManager = new UserManager();

        return userManager;
        
    }
    
}
