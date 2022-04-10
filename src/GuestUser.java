package src;
import java.util.Map;
/**
 * GuestUser class extends User, which implements JSON
 */
public class GuestUser extends User implements src.JSON {
    
    private static final String DEFAULT_NAME = "Guest";

    /**
     * class constructor with default name
     */
    public GuestUser() {
        super(DEFAULT_NAME);
    }
    /**
     * class constructor with paramatered name
     * @param name
     */
    public GuestUser(String name) {
        super(name);
    }
    /**
     * main menu for Guest Users
     * @return String
     */
    public String menuString() {
        return "Hello, " +this.username+"!\n1. Sign Up\n2. Log In\n3. Search Flights\n4. Search Hotels\n5. Review a Hotel/Airlines\n6. Exit\n\nWhat would you like to do?"; 
    }

    /**
     * checks if the user using is truly the user
     * @return boolean 
     */
    @Override
    public boolean equals(Object obj) {
        GuestUser guest = (GuestUser) obj;
        if (guest.getUsername().equalsIgnoreCase(this.username))
            return true;
        return false;
    }

    /**
     * for writing to JSON (none because it is guest)
     */
    public Map toJsonObject() {
        return null;
    }
}