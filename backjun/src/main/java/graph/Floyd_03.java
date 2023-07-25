package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Floyd_03 {
	static int N;
	static int[][] distance;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st = null;
		
		//도시의 개수와 버스 노선 입력 받음
		N = Integer.parseInt(br.readLine());
		
		//거리배열 초기화
		distance = new int[N+1][N+1];
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; j++) {
				distance[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//플로이드 알고리즘 수행
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				for(int k = 1 ; k <= N ; k++) {
					if(  distance[j][i] == 1 && distance[i][k] == 1 ) {
						distance[j][k] = 1;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				sb.append(distance[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}