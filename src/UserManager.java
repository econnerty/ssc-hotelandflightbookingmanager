package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

public class UserManager {

    private static UserManager userManager;
    private static User currentUser;
    private static HashMap<String, User> users; //We will store all the users inside this hashmap. The UUID will be the key.

    private UserManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        users = Utilities.loadUsers(); //This should load all Users from the JSON into the HashMap.
        Utilities.saveUsers(users);

    }

    public static UserManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (userManager == null)
            userManager = new UserManager();

        return userManager;
        
    }

    /**
     * Returns the current user
     * @return The
     */
    public User getCurrentUser(){

        if (UserManager.currentUser == null) {
            System.out.println("You need to login first.");
            return null;
        }

        return UserManager.currentUser;
    }

    public void saveUser(User user) {
        //TODO
    }

    /**
     * Logins and returns whether the login was successful
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {

        User user = users.get(username);

        if (user == null) {
            return false;
        }

        if (user.checkPassword(password)) {
            UserManager.currentUser = user;
            return true;
        }
        return false;

    }

    public boolean addUser() {
        return true;
    }

    public void logout() {
        //TODO
        this.currentUser = null;
    }

    public void changePassword(String password) {
        //TODO
    }
    
}
