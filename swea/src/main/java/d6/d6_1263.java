package d6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d6_1263 {
	private static final int INF = 9999; // Integer.MAX_VALUE 사용하면 오버플로 발생함
	static int R;
	static int[][] D;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = null;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			D = new int[R][R];
			//지도 입력받음
			for(int i = 0 ; i < R ; i++) {
				for(int j = 0 ; j < R ; j++) {
					D[i][j] = Integer.parseInt(st.nextToken());
					//자기 자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 채우기
					if(  i != j && D[i][j] == 0 ) {
						D[i][j] = INF;
					}
				}
			}
			
			for(int k = 0 ; k < R ; k++) {
				for(int i = 0 ; i < R ; i++) {
					if( i == k ) {
						continue;
					}
					for(int j = 0 ; j < R ; j++) {
						if( k == j || i == j ) {
							continue;
						}
						if( D[i][j] > D[i][k] + D[k][j] ) {
							D[i][j] = D[i][k] + D[k][j];
						}
					}
				}
			}
			int result = INF;
			//최적화된 거리 값을 출력
			for(int i = 0 ; i < R ; i++) {
				int rowSum = 0;
				for(int j = 0 ; j < R ; j++) {
					rowSum += D[i][j];
				}
				result = Math.min(result, rowSum);
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
