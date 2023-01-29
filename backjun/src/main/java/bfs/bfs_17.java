package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class bfs_17 {
	//상하좌우 -> 위쪽이랑 왼쪽 먼저
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = { 0,-1, 1, 0};
	static int[][] map;
	static int n,nowRow,nowCol,nowSize,nowEat,time;

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); //지도 크기
		map = new int[n][n];
		boolean ck = false;



		StringTokenizer st; 
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if( 1 <= map[i][j] && map[i][j] <= 6 ) {
					ck = true;
				}else if ( map[i][j] == 9 ) {
					nowRow = i;
					nowCol = j;
					map[i][j] = 0;
				}
			}
		}

		if( !ck ) { //물고기가 없다면
			System.out.println(0);
			return;
		}
		//초기 크기와 먹은 물고기 수 초기화
		nowSize = 2;
		nowEat = 0;

		int result = 0;
		while( hunt() ) {
			result += time;
		}
		System.out.println(result);

	}

	private static boolean hunt() {
		time = 0;

		if(nowEat == nowSize) {
			nowEat = 0;
			nowSize++;
		}

		boolean[][] visit = new boolean[n][n];
		Queue<Pos> q = new LinkedList<>();
		//현재 상어 위치를 큐에 넣고 방문처리
		q.add(new Pos(nowRow,nowCol,0)); 
		visit[nowRow][nowCol] = true;

		//행과 열은 20이 최대이므로 21로 초기화 길은 400이 최대이기때문에 401로 초기화
		int minRow = 21;
		int minCol = 21;
		int minTime = 401;

		while( !q.isEmpty() ) {
			Pos p = q.poll();
			if(p.dis >= minTime) { //최소 시간 < 물고기 먹는 시간 ==> 종료
				break;
			}
			int x = p.x;
			int y=  p.y;

			for(int i = 0; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//범위 안 + 방문 이력 x + 현재 크기보다 이동 칸의 크기가 더 작거나 같아야함
				if( gox >= 0 && gox < n && goy >= 0 && goy < n && !visit[gox][goy] && map[gox][goy] <= nowSize ) { 
					if( map[gox][goy] < nowSize && map[gox][goy] > 0) { //방문 x한 나보다 크기 작은 먹이라면!!!
						//위 쪽으로, 행으로 더 작은값이 나온다면 인접 물고기 변경함
						if( gox < minRow ) {
							minRow = gox;
							minCol = goy;
							minTime = p.dis + 1;
						}else if( gox == minRow ) { //같은 행에 있다면 열을 기준으로 왼쪽 물고기 선택해서 기준잡음
							if( goy < minCol ) {
								minCol = goy;
								minTime = p.dis + 1;
							}
						}

					}
					q.add(new Pos(gox,goy,p.dis+1));
					visit[gox][goy] = true;
				}

			}
		} 
		//잡은 물고기가 없다면 시간값이 최대값 그대로 일 것임
		if(minTime == 401)
			return false;
		else { //아니면 잡은 물고기가 있음 -> 해당 위치로 상어를 바꿔주고 먹은 물고기 증가 + 물고기 0 처리 + 시간 더해주기
			nowRow = minRow;
			nowCol = minCol;
			nowEat++;
			time = minTime;
			map[nowRow][nowCol] = 0;

			return true;
		}



	}

	static class Pos{
		int x;
		int y;
		int dis; //이동 시간 == 거리

		Pos(int x,int y, int dis){
			this.x = x;
			this.y = y;
			this.dis = dis;

		}
	}
}