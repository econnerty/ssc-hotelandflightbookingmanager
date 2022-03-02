package src;

public class Driver {
    
    public static void main(String args[]) {

        ApplicationManager app = ApplicationManager.getInstance();

        int[] num = new int[2];

        num[0] =1;
        num[1] = 2;

        int[] num2 = new int[3];

        num2[0] =1;
        num2[1] = 2;
        num2[2] = 3;

        num = num2;

        for(int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }
        
    
    }


}
