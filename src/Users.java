package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public abstract class Users {

    //protected UUID uuid; //why do we need uuid if all the usernames are unique
    protected String name;
    protected String password; //This should be the hashed password if we have time for that
    protected Date creationDate; //Date the user was created


    public Users(String name, String password, Date creationDate) {
        this.name = name;
        this.password = password;
        this.creationDate = creationDate;
    }

    public String toString() {
        return this.name + " " + this.password + " " + this.creationDate.toString();
    }
    
}
