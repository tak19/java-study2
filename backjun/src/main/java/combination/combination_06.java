package combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class combination_06 {
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //전체 수
			int m = Integer.parseInt(st.nextToken()); //뽑을 수
			arr = new int[n+1][m+1];
			sb.append(combi(n,m)).append("\n");
			

		}
		System.out.println(sb);

	}

	private static int combi(int n, int m) {
		if( arr[n][m] != 0 ) {
			return arr[n][m];
		}
		
		if( n == m || m == 0 ) {
			return arr[n][m] = 1;
		}
		return arr[n][m] = ( ((combi(n-1,m-1) % 1000000007) + ( combi(n-1,m) % 1000000007)) % 1000000007) ;
		
		
	}
}
