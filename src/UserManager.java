package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
/**
 * This class manages all the users 
 */
public class UserManager {

    private static UserManager userManager;
    private static User currentUser;
    private static HashMap<String, User> users; //We will store all the users inside this hashmap. The UUID will be the key.

    private UserManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        users = Utilities.loadUsers(); //This should load all Users from the JSON into the HashMap.
        Utilities.saveUsers(users);

    }
    /**
     * This gets the instance of the user manager
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     * @return this return the user manager
     */
    public static UserManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (userManager == null)
            userManager = new UserManager();

        return userManager;
        
    }

    /**
     * This gets the current user
     * @return This returns the current user
     */
    public User getCurrentUser(){

        if (UserManager.currentUser == null)
            this.currentUser = new GuestUser();

        return UserManager.currentUser;
    }
    /**
     * Allows the Users to be saved
     * @param user this is the user being saved
     * @throws IOException
     */
    public void saveUsers(User user) throws IOException {
        Utilities.saveUsers(users);
    }

    /**
     * It determines if the user is logged in
     * @param username This is the user name of the user
     * @param password This is the password of the user
     * @return returns whether the login was successful
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
    /**
     * This determines if a user can be added
     * @param username This is the user name of the user
     * @param user This is the user being added
     * @throws IOException
     */
    public boolean addUser(String username, User user) throws IOException {
        if (users.get(username) != null) 
            return false;

        users.put(username, user);
        this.currentUser = users.get(username);
        Utilities.saveUsers(users);
        return true;
    }
    /**
     * This determines if a user can be updated
     * @param user This is the user being updated
     * @throws IOException
     */
    public boolean updateUser(User user) throws IOException {
        users.put(user.username, user);
        Utilities.saveUsers(users);
        return true;
    }
    /**
     * This gets all the user in the HashMap
     * @return this returns those users in the hashmap
     */
    public HashMap<String, User> getUsers(){
        return this.users;
    }
    /** 
     * This allows the user to logout 
     * @throws IOException
     */
    public void logout() throws IOException {
        if (currentUser.getClass() != GuestUser.class)
            updateUser(UserManager.currentUser);
        this.currentUser = new GuestUser();
    }
    /**
     * This allows the user to change his or her password
     * @param password this is the current password of the user 
     * @param newPassword this is the new password being created
     */
    public void changePassword(String password, String newPassword) {
        //TODO
        boolean check=UserManager.currentUser.checkPassword(password);
    	if(check) {
    		UserManager.currentUser.password=newPassword;
    	}
    	else {
    		System.out.println("Please enter your current password.");
    	}
    }
    
}
