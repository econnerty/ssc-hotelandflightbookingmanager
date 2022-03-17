package src;

public class ApplicationManager {

    private static ApplicationManager appManager;
    private static UserManager userManager;
    private static BookingManager bookingManager;

    private ApplicationManager(){
        userManager = UserManager.getInstance();
        bookingManager = BookingManager.getInstance();
    }

    public static ApplicationManager getInstance() {

        if (appManager == null)
            appManager = new ApplicationManager();

        return appManager;
        
    }

    public boolean print() {
        //TODO
        return false;
    }
    
}
