package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class d4_1486 {
	static int N,B,result;
	static int[] key;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int test = 1; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 점원 수
			B = Integer.parseInt(st.nextToken()); // 선반 높이

			//직원 키 배열
			key = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; i++) {
				key[i] = Integer.parseInt(st.nextToken());
			}
			result = Integer.MAX_VALUE;
			//합고 현재 뽑을놈
			dfs(0,1);

			sb.append(result-B).append("\n");

		}
		System.out.println(sb);
	}
	private static void dfs(int sum, int index) {
		//현재 구한 값보다 커버리면 해당경우 탐색 종료
		if( result < sum ) {
			return;
		}
		
		//선반보다 높다면!!
		if( sum >= B ) {
			result = Math.min(result, sum);
		}

		//다 뽑음
		if( index > N ) {
			return;
		}

		//현재 놈을 쓰는 경우
		dfs( sum + key[index], index + 1 );
		//현재 놈을 안 쓰는 경우
		dfs( sum , index + 1 );

	}
}