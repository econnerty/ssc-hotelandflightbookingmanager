package src;

import java.util.ArrayList;
import java.util.Date;

import org.javatuples.*;

public abstract class Users {

    protected String name;
    protected String password; //This should be the hashed password if we have time for that
    protected Date creationDate; //Date the user was created
    protected Pair<String, int[]> bookings; //Bookings can just be a tuple in users. The string will be the uuid to the plane or hotel. The int array will hold the coordinates to the room or the plane's seat
    
}
