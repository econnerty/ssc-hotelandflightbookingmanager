package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

public class UserManager {

    private static UserManager userManager;
    private static Users currentUser;
    private static HashMap<String, Users> users; //We will store all the users inside this hashmap. The UUID will be the key.

    private UserManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        users = Utilities.loadUsers(); //This should load all Users from the JSON into the HashMap.

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
    public Users getCurrentUser(){

        if (this.currentUser == null) {
            System.out.println("You need to login first.");
            return null;
        }

        return this.currentUser;
    }

    public void saveUser(Users user) {
        //TODO
    }

    /**
     * Logins and returns whether the login was successful
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {

        Users user = users.get(username);

        if (user.checkPassword(password)) {
            this.currentUser = user;
            return true;
        }
        return false;

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
