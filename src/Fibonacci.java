import java.util.ArrayList;

/**
 * Created by hari on 17/11/17.
 */
public class Fibonacci {

    static ArrayList<Integer> fib = new ArrayList<>();
    static void isadjfibo(int n1,int n2){

        int n, a = 0, b = 0, c = 1;
        System.out.print("Enter value of n:");
        n = 9;
        System.out.print("Fibonacci Series:");
        for(int i = 1; i <= n; i++) {
            a = b;
            b = c;
            c = a + b;
            fib.add(a);
            //System.out.print(a+" ");
        }
    }

    static void adj(int n1,int n2){

        int first = -1;
        for(int i = 0 ; i < fib.size() ; ){

            int diff = Math.abs(n1 - n2);
            //System.out.println("fibo1: " +fibonaccinumbers.get(i) +"fibo2: "+fibonaccinumbers.get(i+1));
            if(fib.get(i) == n1 && fib.get(i+1) == n2 || fib.get(i) == n2 && fib.get(i+1) == n1){
                first = i;
                System.out.println("true");
                break;
            }
            else {
                i++;
            }
        }
    }



    public static void main(String[] args) {

        isadjfibo(1,2);
        for(Integer n : fib){
            System.out.print(n+" ");
        }


        int ad[][] = {{1,0},{2,2},{3,1}};

        System.out.println(ad.length); // no of rows
        System.out.println(ad[0].length); // no of coolumns
        adj(2,4);
    }

}
