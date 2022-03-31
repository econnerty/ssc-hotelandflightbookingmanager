package src;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * this abstract class in the parent of the types of users and is the container for the user information.
 */
public abstract class User {

    //protected UUID uuid; //why do we need uuid if all the usernames are unique
    protected String username;
    protected String password; //This should be the hashed password if we have time for that
    protected Date dob;
    protected Date creationDate; //Date the user was created
/**
 * This method if the constructor for the user.
 * @param username The user's username.
 */
    public User(String username) {
        this.username = username;
    }
/**
 * This method if the constructor for the user.
 * @param username the username for the user.
 * @param password the password for the user.
 * @param dob The date of Berth for the user.
 * @param creationDate The date the users account was created.
 */
    public User(String username, String password, Date dob, Date creationDate) {
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.creationDate = creationDate;
    }

    public abstract String menuString();
    public abstract Map toJsonObject();
/**
 * returns the username of the user.
 * @return returns the username of the user.
 */
    public String getUsername() {
        return this.username;
    }
/**
 * returns the password of the user.
 * @return returns the password of the user.
 */
    public String getPassword() {
        return this.username;
    }
/**
 * returns the creation date of the user.
 * @return returns the creation date of the user.
 */
    public Date getCreationDate() {
        return this.creationDate;
    }
/**
 * returns the date of berth.
 * @return returns the date of berth.
 */
    public Date getDob() {
        return this.dob;
    }
/**
 * This compares the two passwords to make sure theyre correct.
 * @param password password of the user.
 * @return returns whether the password matches.
 */
    public boolean checkPassword(String password) {
        return (password.equals(this.password)) ? true : false;
    }
/**
 * returns the contents of the user within a string.
 */
    public String toString() {
        return this.username + " " + this.password + " " + this.creationDate.toString();
    }
    
}