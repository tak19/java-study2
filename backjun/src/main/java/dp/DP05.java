package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP05 {
	static int[] lion;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		lion = new int[n];
		
		System.out.println(dp(n-1));
	}

	private static int dp(int n) {
		if( n == 0) {
			return lion[0] = 3;
		}
		if( n == 1 ) {
			return lion[0] = 7;
		}
		if( lion[n] != 0) {
			return lion[n];
		}
		return lion[n] = (( 2 * dp(n-1) ) + dp(n-2) ) % 9901;
	}
}
