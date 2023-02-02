package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_07 {
	static int[][] arr;
	static int[][] sum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0 ; t < test; t++) {
			int n = Integer.parseInt(br.readLine()); //열 입력 받음
			
			arr = new int[2][n]; //원본
			sum = new int[2][n]; //합을 저장할꺼야
			
			StringTokenizer st;
			for(int i = 0 ; i < 2 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < n ; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//1열 1,2행 입력함
			sum[0][0] = arr[0][0];
			sum[1][0] = arr[1][0];
			
			//n 범위 확인 필요
			if( n >= 2) {
				
				//2열 1,2핵 입력함
				sum[0][1] = arr[0][1] + arr[1][0];
				sum[1][1] = arr[1][1] + arr[0][0];
				
				//구간별로 최대합을 계산해 나간다.
				for(int i = 2; i < n ; i++) {
					sum[0][i] = Math.max(sum[1][i-1], sum[1][i-2]) + arr[0][i];
					sum[1][i] = Math.max( sum[0][i-2],sum[0][i-1]) + arr[1][i]; 
				}
			}

			if( sum[0][n-1] < sum[1][n-1]) {
				sb.append(sum[1][n-1] + "\n");
			}else {
				sb.append(sum[0][n-1] + "\n");
			}

			
		}
		System.out.println(sb);
	}

	
}
