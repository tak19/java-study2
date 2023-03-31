package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_26 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int R,C,result;
	static int[][] map,melt;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		//지도를 입력받음
		map = new int[R][C];
		for(int i = 0 ; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < C ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		//치즈치즈치즈치즈
		while(true) {
			time++;
			melt = new int[R][C]; //공기와 맞닿은 면적을 계산히기 위해 선언
			visit = new boolean[R][C];
			bfs();
			result = 0;
			CheeseMelt();
			if( !countCheese() ) {
				break;
			}
		}
		sb.append(time).append("\n").append(result);
		System.out.println(sb);
	}

	//남은 치즈가 하나라도 있다면 true 반환
	private static boolean countCheese() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if( map[i][j] == 1 ) {
					return true;
				}
			}
		}
		return false;
	}

	private static void CheeseMelt() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				//공기와 맞닾은적 있다면 제거해줌
				if( melt[i][j] > 0 ) {
					result++;
					map[i][j] = 0;
				}
			}
		}
	}

	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(0,0));
		visit[0][0] = true;

		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;

			for(int i = 0 ; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//범위 안이면서 방문한적이 없다면
				if( canGo(gox, goy) ) {
					if( !visit[gox][goy] ) {
						//치즈라면
						if( map[gox][goy] == 1 ) {
							visit[gox][goy] = true;
							melt[gox][goy]++;
						}else {
							//치즈가 아니라면
							visit[gox][goy] = true;
							q.offer(new Pos(gox,goy));
						}
					}

				}
			}

		}
	}

	//범위 안인지 확인함
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < R && goy >= 0 && goy < C ) {
			return true;
		}
		return false;
	}
	//좌표저장
	static class Pos {
		int x,y,cost;
		Pos(int x, int y){
			this.x = x;
			this.y = y;

		}
	}
}