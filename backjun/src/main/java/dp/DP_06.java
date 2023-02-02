package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_06 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //짐 개수
		int limit = Integer.parseInt(st.nextToken()); //무게제한

		int[][] list = new int[n+1][2];

		for(int i = 1 ; i <= n ; i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken()); //무게
			list[i][1] = Integer.parseInt(st.nextToken()); //가치
		}

		int[][] dp = new int[n+1][limit+1];
		for(int i = 1 ; i <= n ; i++) { //짐 개수 만큼 반복
			int w = list[i][0]; //현재 짐 무게
			int v = list[i][1]; //현재 짐의 가치
			for(int j = 1 ; j <= limit ; j++) { //들고갈 수 있는 무게 
				if( j >= w ) {
					//최대값 찾는 과정... 자기 포함x로 위의 원소에서 or 자기 포함하고 남은 무게에 해당하는 가치 더해줌 
					dp[i][j] = Math.max(dp[i-1][j], list[i][1] + dp[i-1][ j - list[i][0] ] );
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}

		}

		System.out.println(dp[n][limit]);
	}


}
