package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_12 {
	static int N,M,allCash;
	static boolean[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 크기
			M = Integer.parseInt(st.nextToken()); // 비용

			int homeCnt = 0;
			//지도 입력 받기
			map = new boolean[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					//집이 있으면 true로 처리함
					if( Integer.parseInt(st.nextToken()) == 1 ) {
						homeCnt++;
						map[i][j] = true;
					}
				}
			}
			allCash = homeCnt * M; //전체 집개수 * 비용 => 전체 비용을 계산함!!

			//전체 비용보다 운용 비용이 커지면 탐색 x
			//마름모: (n*2)-1 만큼 반복되고, 중앙 n 에서 (n*2)-1 만큼 색칠됨.. 2개씩 늘어나다가 2개씩 감소하는 구조
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					homeService(i,j);
				}
			}

		}
		System.out.println(sb);

	}
	//홈 서비스 갑니다~!  
	private static void homeService(int x, int y) {
		int K = 1;
		while( K * K + (K - 1) * (K - 1) > allCash ) {
			
			for(int i = 0 ; i < (K * 2) -1 ; i++ ) {
				
			}
			
			K++; // 홈 서비스 증가
		}
	}
}




