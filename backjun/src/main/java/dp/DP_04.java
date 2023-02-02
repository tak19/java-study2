package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP04 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine())+1;
		
		//0~9까지 있는 n행의 2차원 배열 선언
		int[][] arr = new int[n][10];
		
		//각 자리 수 별로 0으로 끝나는 경우는 1가지 경우로 고정 되어있음
		for(int i = 0 ; i < n ; i++) {
			arr[i][0] = 1;
		}
		// 1자리의 경우 자기 자신 하나의 경우로 고정 되어있음
		for(int i = 0 ; i < 10 ; i++) {
			arr[0][i] = 1;
		}
		
		for(int i = 1 ; i < n ; i++ ) {
			for(int j = 1; j < 10 ; j++) {
				arr[i][j] = arr[i-1][j] + arr[i][j-1];
			}
		}
		
		System.out.println(arr[n-1][9] % 10007);
		
	}
}
