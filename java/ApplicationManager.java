package java;

public class ApplicationManager implements Manager{

    private static ApplicationManager appManager;

    private ApplicationManager(){

    }

    public static ApplicationManager getInstance() {

        if (appManager == null)
            appManager = new ApplicationManager();

        return appManager;
        
    }
    
}
