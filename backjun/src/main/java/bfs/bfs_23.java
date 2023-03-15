package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class bfs_23 {
	//사방탐색
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] map;
	static boolean[][] ckWB; //검은방인지 흰방인지
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//바둑판 size
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		ckWB = new boolean[N][N];
		
		//지도 상황 입력 받음
		for(int i = 0 ; i < N ; i++) {
			char[] tem = br.readLine().toCharArray();
			for(int j = 0 ; j < N ; j++) {
				//흰방인 경우만 체크한다.
				if( tem[j] - '0' == 1 ) {
					ckWB[i][j] = true;
				}
				//벽 부순 횟수를 저장할 배열을 최대값으로 초기화 시킴
				map[i][j] = Integer.MAX_VALUE;
			}
		}
		
		bfs();
		System.out.println(map[N-1][N-1]);
		
	}
	//탐색 시작함
	private static void bfs() {
		Queue<Pos> q = new ArrayDeque();
		q.offer(new Pos(0,0,0)); //시작 인덱스
		map[0][0] = 0; //벽부순 횟수
		//탐색 시작함
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			//4방탐색
			for(int i = 0 ; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//범위 안인가?
				if( canGo(gox,goy) ) {
					if( ckWB[gox][goy] ) {
						//벽이 없는 곳이라면 현재 부순 벽의 개수만 보고 판단
						if( map[gox][goy] > pos.wallCnt ) {
							map[gox][goy] = pos.wallCnt; //벽 부쉰 횟수 그대로
							q.offer(new Pos( gox,goy,pos.wallCnt));
						}
						
					}else {
						//벽이 있는 곳이라면
						//앞으로 갈 곳이 자기보다 벽을 부쉰 횟수가 크다면 즉, 자기가 덜 부수고 갈 수 있는곳이라면 방문함
						if( map[gox][goy] > (pos.wallCnt + 1) ) {
							map[gox][goy] = pos.wallCnt + 1; //벽 개수 초기화
							q.offer(new Pos( gox,goy,pos.wallCnt+1 ));
						}
					}
					
				}
				
			}
		}
	}
	//범위 안인지 판단
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}

	//좌표정보 저장
	static class Pos{
		int x,y,wallCnt;

		public Pos(int x, int y, int wallCnt) {
			super();
			this.x = x;
			this.y = y;
			this.wallCnt = wallCnt;
		}

		
	}
}