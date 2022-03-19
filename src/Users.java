package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public abstract class Users {

    //protected UUID uuid; //why do we need uuid if all the usernames are unique
    protected String name;
    protected String password; //This should be the hashed password if we have time for that
    protected Date dob;
    protected Date creationDate; //Date the user was created


    public Users(String name, String password, Date dob, Date creationDate) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.creationDate = creationDate;
    }


    public boolean checkPassword(String password) {
        return (password.equals(this.password)) ? true : false;
    }

    public String toString() {
        return this.name + " " + this.password + " " + this.creationDate.toString();
    }
    
}
