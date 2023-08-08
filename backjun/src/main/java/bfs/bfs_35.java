package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_35 {
	//사방 탐색
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int N,M,result; // 호수의 크기
	static boolean[][] visit; // 이동 가능한지 확인
	static boolean posible;
	static char[][] map; // 호수지도
	static Pos[] swan; // 백조 2마리
	static Queue<Pos> q,waterQ;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//행과 열 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//지도와 방문 체크 
		visit = new boolean[N][M];
		map = new char[N][M];
		swan = new Pos[2];

		q = new ArrayDeque<Pos>();
		waterQ = new ArrayDeque<Pos>();

		int index = 0;
		// 호수 입력 받기
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = c[j];
				if (map[i][j] == 'L') { // 백조 위치 파악
					swan[index++] = new Pos(i,j); 
				}
				if (map[i][j] != 'X') { // 물이라면
					waterQ.offer(new Pos(i,j));
				}
			}
		}
		//출발 백조 - 큐에 넣고 방문체크
		q.offer(swan[0]);
		visit[swan[0].x][swan[0].y] = true;

		// 백조가 만나면 posibel 변경함 true로
		posible = false;
		result = 0;
		bfs();

		System.out.println(result);
	}
	//너비탐색 시작
	private static void bfs() {
		//백조가 만날때까지
		while( true ) {
			Queue<Pos> nextQ = new ArrayDeque<>();
			while( !q.isEmpty() ) {
				Pos pos = q.poll();
				//백조가 만났다면 종료
				if( pos.x == swan[1].x && pos.y == swan[1].y ) {
					posible = true;
					break;
				}
				//4방탐색
				for (int dir = 0; dir < 4; dir++) {
					int nx = pos.x + dx[dir];
					int ny = pos.y + dy[dir];
					//범위 안이면서 방문 x라면
					if ( canGo(nx,ny) && !visit[nx][ny]) {
						visit[nx][ny] = true; //방문처리하고
						//빙판이라면 다음날 큐에 넣고 다음 탐색
						if( map[nx][ny] == 'X' ) {
							nextQ.offer(new Pos(nx,ny));
							continue;
						}
						//아니면 지금바로 탐색
						q.offer(new Pos(nx,ny));
					}
				}
			}
			//백조 만났다면 종료
			if( posible ) {
				break;
			}
			//q를 다음날 탐색할 지역이 담긴 nextQ로 바꿈
			q = nextQ;
			//얼음 녹이기
			int size = waterQ.size();
			for(int i = 0; i < size ; i++) {
				Pos pos = waterQ.poll();
				for(int dir = 0 ; dir < 4 ; dir++) {
					int nx = pos.x + dx[dir];
					int ny = pos.y + dy[dir];
					if( canGo(nx,ny) ) {
						if( map[nx][ny] == 'X' ) {
							map[nx][ny] = '.';
							waterQ.offer(new Pos(nx,ny));
						}
					}
				}
			}
			result++; //하루 추가
		}

	}
	//범위 안인지 확인
	private static boolean canGo(int nx, int ny) {
		if( nx >= 0 && nx < N && ny >= 0 && ny < M ) {
			return true;
		}
		return false;
	}
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}