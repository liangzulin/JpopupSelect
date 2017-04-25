//package javasource;
import java.util.*;

public class Main {
    public static void fun(){
        fun();
    }
    public static void main(String[] args) {

//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        int[] array = new int[n];
//        for(int i=0; i<n; ++i)
//            array[i] = scan.nextInt();
        
        int[] intlist = {1,2,4,2,3,5};
        
        for(int i=0; i<intlist.length; ++i)
            System.out.print(intlist[i] + " ");
        System.out.println();
        
        //array[n+1] = 0;
        //fun();
    }
 
}