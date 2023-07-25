package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Floyd_02 {
	static int N,M;
	static int[][] distance;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());

		//도시의 개수와 버스 노선 입력 받음
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		//거리배열 초기화
		distance = new int[N+1][N+1];
		//자기 자신의 비용은 0 아니면 충분히 큰값으로
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				if( i == j ) {
					distance[i][j] = 0;
				}else {
					distance[i][j] = 20_000_000;
				}
			}
		}
		StringTokenizer st = null;
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//중복 경로를 방지 - 해당 경우 최소값으로 초기화 시켜줘야함
			if( distance[a][b] > c ) {
				distance[a][b] = c;
			}
		}
		//플로이드 알고리즘 수행
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				for(int k = 1 ; k <= N ; k++) {
					if( distance[j][k] > distance[j][i] + distance[i][k] ) {
						distance[j][k] = distance[j][i] + distance[i][k];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				if( distance[i][j] == 20_000_000 ) {
					sb.append(0).append(" ");
				}else {
					sb.append(distance[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}