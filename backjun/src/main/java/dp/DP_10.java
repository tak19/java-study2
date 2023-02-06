package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_10 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] arr= new int[n+1][2];
		for(int i = 0; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); //시간
			arr[i][1] = Integer.parseInt(st.nextToken()); //가치
		}
		int max = 0;
		int[] sum = new int[n+1]; //합을 저장할꺼임
		
		for(int i = 0; i <= n ; i++) {
			//시간이 범위 안이라면 -> 자기가 들고있던값 vs 이전행 + 지금행
			sum[i] = Math.max(sum[i], max); // -> max는 현재 정해진 가장 큰값으로 sum이랑 비교해 저행해둠
			if( i+arr[i][0] <= n) {
				//현재시간 + 상담시간의 인덱스 계산 ---> 계산되어 있는 값 이랑 현재값 + 이전값 더함 둘중 큰값을 저장
				sum[i+arr[i][0]] = Math.max( sum[i+arr[i][0]], arr[i][1] + sum[i] );
			}
			//큰값 갱신
			max = Math.max(max, sum[i]);
		}
		System.out.println(max);

	}
}
