package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class d3_2805 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= t ; test++) {
			sb.append("#").append(test).append(" ");
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n+1][n+1];
			//입력받음
			for(int i = 1 ; i <= n ; i++) {
				char[] c = br.readLine().toCharArray();
				for(int j = 1 ; j <= n ; j++) {
					arr[i][j] = c[j-1] - '0';
				}
			}
			int mid = (n+1) / 2;
			int result = 0;
			//중간값 더하기
			for(int i = 1 ; i <= n ; i++) {
				result += arr[mid][i];
			}
			//중간부터 위아래 더함
			for(int i = 1 ; i < mid ; i++) {
				for(int j = 1 + i ; j <= n -i ; j++) {
					result += arr[mid+i][j];
					result += arr[mid-i][j];
				}
			}
			sb.append(result + "\n");
		}

		System.out.println(sb);
	}
}