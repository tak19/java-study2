package backjun;

import java.util.Scanner;

public class DP {
	static long[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		arr = new long[n+1];
		sc.close();
		
		
		System.out.println(dp(n));
		
		

	}
	public static long dp(int n) {
		if( n == 1)
			return arr[1] = 1;
		if( n == 2)
			return arr[2] = 1;

		if( arr[n] != 0 )
			return arr[n];
		
		return arr[n] = dp(n-1) + dp(n-2);
	}

}
