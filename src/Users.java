package src;

import java.util.ArrayList;
import java.util.Date;

import org.javatuples.*;

public abstract class Users {

    protected String name;
    protected Date date;
    protected Pair<String, int[]> bookings;

    public class RegisteredUser extends Users {


    }

    public class BusinessUser extends Users {
        
    }
    
}
