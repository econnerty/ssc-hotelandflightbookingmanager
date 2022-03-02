package src;

import java.util.ArrayList;
import java.util.Date;

public abstract class Users {

    protected String name;
    protected Date date;
    protected ArrayList<Booking> bookings;

    public class RegisteredUser extends Users {


    }

    public class BusinessUser extends Users {
        
    }
    
}
