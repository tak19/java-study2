package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class etc_12 {
	static int N,M,allCash,result;
	static boolean[][] map;
	static boolean[][] visit;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

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

			result = 0;
			allCash = homeCnt * M; //전체 집개수 * 비용 => 전체 비용을 계산함!!

			//전체 비용보다 운용 비용이 커지면 탐색 x
			//마름모: (n*2)-1 만큼 반복되고, 중앙 n 에서 (n*2)-1 만큼 색칠됨.. 2개씩 늘어나다가 2개씩 감소하는 구조
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					visit = new boolean[N][N];
					homeService(i,j);
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);

	}
	//홈 서비스 갑니다~!  마름모를 4방 탐색으로 구하자!!
	private static void homeService(int x, int y) {

		int tHomeCnt = 0; // 범위 안 가구 수
		int K = 1; // 서비스 영역

		Queue<Pos> q = new ArrayDeque<>();
		//방문한다요
		while( ( K * K + (K - 1) * (K - 1) ) <= allCash ) {
			//초기 실행
			if( K == 1) {
				if(map[x][y]) {
					//시작점에 집이 있다면 가구 1 증가시킴
					tHomeCnt++;
				}
				visit[x][y] = true;
				q.add(new Pos(x,y));

			}else {
				int size = q.size();
				//사이즈만큼 --> 한 턴만큼 진행함
				for(int k = 0 ; k < size ; k++) {
					Pos p = q.poll();
					int nx = p.x;
					int ny = p.y;

					for(int i = 0 ; i < 4 ; i++) {
						int gox = nx + dx[i];
						int goy = ny + dy[i];
						//범위 안이면서 갈 수 있다면
						if( canGo(gox,goy) && !visit[gox][goy] ) {
							//집이 존재한다면 집 count 증가띠
							if( map[gox][goy]) {
								tHomeCnt++;
							}
							visit[gox][goy] = true; //방문처리함
							q.offer(new Pos(gox,goy)); //다음 방문을 위해 큐에 넣음
						}

					}

				}
			}
			
			int cost = (tHomeCnt * M) - ( K * K + (K - 1) * (K - 1) );
			//손해보지 않으면 서비스 가능 가구 수 갱신 
			if( cost >= 0 ) {
				result = Math.max(result, tHomeCnt);
			}

			K++; // 홈 서비스 증가
		}

	}
	//범위 안인지 판단
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	//좌표 정보 저장
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}




