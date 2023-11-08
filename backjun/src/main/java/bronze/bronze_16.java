package bronze;

import java.util.Scanner;

public class bronze_16 {
    public static void main(String[] args) throws Exception {
    	Scanner sc = new Scanner(System.in);
    	int A = sc.nextInt(), B = sc.nextInt();
    	int C = sc.nextInt(), D = sc.nextInt();
    	System.out.println(Math.abs((A+D) - (B+C)));
    }
}
    