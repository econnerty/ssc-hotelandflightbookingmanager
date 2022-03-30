package src;

import java.util.Map;

public class GuestUser extends User implements src.JSON {
    
    private static final String name = "Guest";

    public GuestUser() {
        super(name);
    }
    public GuestUser(String name) {
        super(name);
    }

    public String menuString(){

        return "Hello, " +this.username+"!\n1. Sign Up\n2. Log In\n3. Search Flights\n4. Search Hotels\n5. Review a Hotel/Airlines\n6. Log Out\n\nWhat would you like to do?"; 
    
    }

    
    public Map toJsonObject() {

        return null;
        
    }
    
}