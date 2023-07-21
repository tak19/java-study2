package bfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_34 {
	//사방 탐색
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N,M;
	static int sheep,wolf,rSheep,rWolf;
	static char[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//행과 열 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//지도와 방문 체크
		map = new char[N][M];
		visit = new boolean[N][M];
		//지도 입력받음
		for(int i = 0 ; i < N ; i++) {
			map[i] = br.readLine().toCharArray();
		}

		//탐색 시작
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				//울타리가 아니라면 탐색함
				if( map[i][j] != '#' && !visit[i][j] ) {
					sheep = 0;
					wolf = 0;
					bfs(i,j);
					if( sheep > wolf ) {
						rSheep += sheep;
					}else {
						rWolf += wolf;
					}
				}
			}
		}
		System.out.println(rSheep + " " + rWolf);


	}
	//BFS
	private static void bfs(int r, int c) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(r,c));
		visit[r][c] = true;
		if( map[r][c] == 'v' ) {
			wolf++;
		}else if( map[r][c] == 'o' ) {
			sheep++;
		}

		//큐가 빌때까지
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			for(int i = 0 ; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//범위 안이면서 울타리가 아니라면
				if( canGo(gox,goy) && map[gox][goy] != '#' ) {
					if( !visit[gox][goy] ) {
						//양과 늑대중 한마리 라면 늘려주고
						if( map[gox][goy] == 'v' ) {
							wolf++;
						}else if( map[gox][goy] == 'o' ) {
							sheep++;
						}
						//큐에 삽입
						q.offer(new Pos(gox,goy));
						visit[gox][goy] = true;
					}

				}
			}
		}

	}
	//범위 안인지 확인
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N  && goy >= 0 && goy < M ) {
			return true;
		}
		return false;
	}
	//좌표정보 저장
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}