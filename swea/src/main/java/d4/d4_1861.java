package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class d4_1861 {
	static int n,max,index;
	static int dis; // 임시 거리저장
	static int[][] map;
	static boolean[][] visit;
	static Queue<Pos> q = new ArrayDeque<>();
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			//지도 입력받음
			n = Integer.parseInt(br.readLine()); //n수입력
			map = new int[n][n];
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < n ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int size = n*n;
			// 최대 방문 방 수와 인덱스 초기화
			max = -1;
			index = Integer.MAX_VALUE;

			//차례로 방문
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) { 
					if( size - map[i][j] >= max) {
						dis = 0;
						q.add(new Pos(i,j));
						bfs();
						//최대 이동가능한 방을 찾는다면 해당 하는 곳이 최대값이고 최소방번호임 
						if( max < dis ) {
							max = dis;
							index = map[i][j];
						}else if ( max == dis) { //현재 지정된 최대 방이동 수와 같다면 인덱스만 비교힘
							index = Math.min(index, map[i][j]);
						}
					}


				}
			}
			sb.append(index).append(" ").append(max).append("\n");

		}
		System.out.println(sb);

	}

	private static void bfs() {
		while( !q.isEmpty() ) {
			dis += 1;

			Pos pos = q.poll();

			int x = pos.x;
			int y = pos.y;
			int num = map[x][y];

			for(int i = 0 ; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//범위 안에다가 1이 큰 곳을 찾으면 이동
				if( inArea(gox,goy) && (num + 1) == map[gox][goy] ) { 
					q.add(new Pos(gox,goy));

				}

			}



		}

	}

	private static boolean inArea(int gox, int goy) {

		if( gox >= 0 && gox < n && goy >= 0 && goy < n) {
			return true;
		}
		return false;
	}

	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
