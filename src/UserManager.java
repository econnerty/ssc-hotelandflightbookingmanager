package src;

public class UserManager implements Manager {

    private static UserManager userManager;

    private UserManager(){

    }

    public static UserManager getInstance() {

        if (userManager == null)
            userManager = new UserManager();

        return userManager;
        
    }
    
}
