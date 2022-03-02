package java;

public class ApplicationManager {

    private static ApplicationManager appManager;

    private ApplicationManager(){

    }

    public ApplicationManager getInstance() {

        if (appManager == null)
            appManager = new ApplicationManager();

        return appManager;
        

    }
    
}
