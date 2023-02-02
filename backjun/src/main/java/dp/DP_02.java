package dp;

import java.util.Scanner;

public class DP_02 {
	//총 경우의 수를 저장할 변수
	//eclipse 스택 오류 but 
	static long[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n  = sc.nextInt();
		sc.close();
		
		arr = new long[n+1];
		
		System.out.println(dp(n) % 15746);
		
		
		
		
	}
	private static long dp(int i) {
		if(i == 1) {
			return arr[i] = 1;
		}
		if(i == 2) {
			return arr[i] = 2;
		}
		if(arr[i] != 0) {
			return arr[i] % 15746;
		}
		return arr[i] = (dp(i-1) % 15746) + (dp(i-2) % 15746) ;
		
	}
}
