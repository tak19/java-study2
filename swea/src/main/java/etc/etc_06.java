package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class etc_06 {
	private static StringBuilder sb = new StringBuilder();
	//사방탐색이요~
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int n,k,max;
	static int[][] map,origin;
	static int[][] dis;
	static boolean[][] visit;
	static int result;

	public static void main(String[] args) throws Exception {
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/*
		 * 2. 입력파일 객체화
		 */
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken()); //등산로 크기
			k = Integer.parseInt(st.nextToken()); //공사 가능 깊이 k

			map = new int[n][n];
			origin = new int[n][n];
			
			max = 0;
			//지도 정보 확인
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n ; j++) {
					origin[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, origin[i][j]); //최고봉 값 저장함
				}
			}
			result = 0;
			start();
			//			for(int i = 0 ; i < n ; i++) {
			//				for(int j = 0; j < n ; j++) {
			//					System.out.print(dis[i][j] + " ");
			//				}
			//				System.out.println();
			//			}
			sb.append(result+1).append("\n");

		}

		System.out.println(sb);

	}
	//최고봉 찾아서 BFS 돌릴꺼야
	private static void start() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0; j < n ; j++) {
				if(origin[i][j] == max) {
					visit = new boolean[n][n];
					visit[i][j] = true;
					update();
					dis = new int[n][n];
					dfs(i,j,0);

					System.out.println(i + " " + j + "_----------------------");
					for(int q = 0 ; q < n ; q++) {
						for(int w = 0; w < n ; w++) {
							System.out.print(dis[q][w] + " ");
						}
						System.out.println();
					}
				}
			}
		}
	}

	//깊은복사 필요
	private static void update() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}
	//좌표랑 공사 했는지 확인여부
	private static void dfs(int i, int j, int ck) {

		int x = i;
		int y = j;
		//사방탐색
		for(int go = 0 ; go < 4 ; go++) {
			int gox = x + dx[go];
			int goy = y + dy[go];
			//범위 안이면서 현재 봉우리 보다 낮다면
			if( canGo(gox,goy) ) {
				if( map[gox][goy] < map[x][y] ) {
					if( dis[gox][goy] < dis[x][y] + 1 ) { //거리도 내께 더 멀면
						dis[gox][goy] = dis[x][y] + 1;
						dfs(gox,goy,ck);
					}

				}
				//범위 안 더 큰 봉우리를 마주했을때
				else if( map[gox][goy] >= map[x][y] ) {
					int sum = map[gox][goy] - map[x][y];
					if( sum <= k && ck == 0) { //공사 가능 범위면서 공사이력이 없다면!!
						map[gox][goy] = map[x][y] - 1 ;
						dis[gox][goy] = dis[x][y] + 1;
						dfs(gox, goy, 1);
					}else {
						result = Math.max(result, dis[x][y]);
					}
				}
			}

		}
	}


	private static void bfs(int i, int j) {
		Queue<Pos> q = new ArrayDeque();
		q.add(new Pos(i,j,0,1)); //최고봉 큐에 넣기~
		//봉우리에서 BFS로 탐색로 찾아요!
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			//사방탐색
			for(int go = 0 ; go < 4 ; go++) {
				int gox = x + dx[go];
				int goy = y + dy[go];
				//범위 안이면서 해당 거리보다 긴 거리를 가지고 있다면
				if( canGo(gox,goy) && dis[gox][goy] < pos.dis + 1 ) {
					if( map[gox][goy] < map[x][y] ) {
						//내 봉우리가 더 높으면 거리만 늘리고 방문하면됨
						dis[gox][goy] = pos.dis + 1;
						q.offer(new Pos(gox,goy,pos.ck,pos.dis+1));
					}else {
						//내 봉우리가 더 낮다면  공사해야지
						int sub = map[gox][goy] - map[x][y]; //두봉우리간 차이값
						//공사 가능 깊이가 차이보다 크고, 공사이력이 없다면

						if( sub <= k && pos.ck == 0) {
							//다음 이동할 봉우리를  현재 봉우리보다 1 작게 공사함- 이게 최선임
							map[gox][goy] = ( map[x][y] - 1 );
							q.offer(new Pos(gox,goy,pos.ck+1,pos.dis+1));
							dis[gox][goy] = pos.dis + 1;
						}else {
							//더이상 갈 수 없는 경우임-- 해당 경우는 거리값 비교만
							result = Math.max(result, pos.dis);
						}
					}
				}

			}


		}

	}

	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < n && goy >= 0 && goy < n) {
			return true;
		}
		return false;
	}

	static class Pos{
		int x,y,ck,dis;
		Pos(int x, int y, int ck, int dis){
			this.x = x;
			this.y = y;
			this.ck = ck;
			this.dis = dis;
		}
	}

}
