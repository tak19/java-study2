package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_9229 {
	static int[] arr,snack;
	static int n,m;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken()); //과자 개수
			m = Integer.parseInt(st.nextToken()); //무게
			
			st = new StringTokenizer(br.readLine());
			arr = new int[n]; //과자 무게를 담음
			for(int i = 0 ; i < n ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			snack = new int[2];
			combi(0,0);
			if( max != Integer.MIN_VALUE ) {
				sb.append(max).append("\n");
			}else {
				sb.append(-1).append("\n");
			}
			


		}
		System.out.println(sb);

	}
	private static void combi(int cnt, int start) {
		if( cnt == 2 ) {
			if( snack[0] + snack[1] <= m ) { //제한 무게보다 작으면
				max = Math.max(max, snack[0] + snack[1]);
			}
			return;
		}
		//과자뽑자
		for(int i = start ; i < n ; i++) {
			snack[cnt] = arr[i]; //과자 저장
			combi(cnt+1, i+1);
			
		}
		
		
	}
}
