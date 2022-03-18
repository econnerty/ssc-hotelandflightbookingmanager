package src;

import java.util.ArrayList;
import java.util.HashMap;

public class UserManager {

    private static UserManager userManager;
    private static Users currentUser;
    private static HashMap<String, Users> users; //We will store all the users inside this hashmap. The UUID will be the key.

    private UserManager(){

        users = Utilities.loadUsers(); //This should load all Users from the JSON into the HashMap.

    }

    public static UserManager getInstance() {

        if (userManager == null)
            userManager = new UserManager();

        return userManager;
        
    }

    public void saveUser(Users user) {
        //TODO
    }

    public void login(String username, String password) {
        //TODO
    }

    public void creatAccount() {
        //TODO
    }

    public void initialize() {
        //TODO
    }

    public void logout() {
        //TODO
    }

    public void changePassword() {
        //TODO
    }
    
}
