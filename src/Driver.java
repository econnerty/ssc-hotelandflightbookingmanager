package src;

public class Driver {

    private static String currentUser = "";

    private static boolean run(){

        return false;
    }
    
    public static void main(String args[]) {

        Utilities.getInstance(); //Call init on Utilities

        System.out.println(Utilities.hashPassword("password","salt"));

        while(run() == true);

        

        
            
    }


}
