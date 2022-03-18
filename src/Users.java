package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public abstract class Users {

    protected UUID uuid;
    protected String name;
    protected String password; //This should be the hashed password if we have time for that
    protected Date creationDate; //Date the user was created


    public Users(UUID uuid, String name, String password, Date creationDate) {

    }
    
}
