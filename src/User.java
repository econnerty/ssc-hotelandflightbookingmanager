package src;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


public abstract class User {

    //protected UUID uuid; //why do we need uuid if all the usernames are unique
    protected String username;
    protected String password; //This should be the hashed password if we have time for that
    protected Date dob;
    protected Date creationDate; //Date the user was created

    public User(String username) {
        this.username = "Guest";
    }

    public User(String username, String password, Date dob, Date creationDate) {
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.creationDate = creationDate;
    }

    public abstract String menuString();
    public abstract Map toJsonObject();

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.username;
    }


    public Date getCreationDate() {
        return this.creationDate;
    }

    public Date getDob() {
        return this.dob;
    }

    public boolean checkPassword(String password) {
        return (password.equals(this.password)) ? true : false;
    }

    public String toString() {
        return this.username + " " + this.password + " " + this.creationDate.toString();
    }
    
}
