package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class d4_4193 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N,result;
	static int[][] map;
	static boolean[][] visit;
	static int startX,startY,endX,endY;
	static Queue<Pos> q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			//배열 크기
			N = Integer.parseInt(br.readLine());
			//지도 입력받음
			map = new int[N][N];
			for(int i = 0 ; i < N ;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			q = new ArrayDeque<>();
			
			//시작위치와 도착위치가 주어짐
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			q.offer(new Pos( startX, startY ,0 ));
			
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());

			visit = new boolean[N][N];
			result = 0;
			bfs();
			sb.append(result == 0 ? -1 : result).append("\n");
		}
		System.out.println(sb);

	}
	//탐색 시작함
	private static void bfs() {
		visit[startX][startY] = true;
		//탐색 시작함
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			int time = pos.time + 1;
			
			for(int i = 0 ; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				
				//범위 안이면서 벽이 아니라면
				if( canGo(gox,goy) && map[gox][goy] != 1 ) {
					
					//끝지점에 도착했다면
					if( gox == endX && goy == endY ) {
						result = pos.time+1;
						return;
						//break;
					}
					
					//방문하지 않은 경우라면
					if( !visit[gox][goy] ) {
						if( map[gox][goy] == 0 ) {
							visit[gox][goy] = true;
							q.offer(new Pos(gox,goy,time));
						}else {
							//소용돌이가 없다면 방문가능
							if( (pos.time % 3) == 2 ){
								visit[gox][goy] = true;
								q.add(new Pos(gox,goy,time));
							}else {
								//소용돌이에 막히면 해당 자리에 그대로 있음
								q.offer(new Pos(x,y,time));
							}
						}
					}
				}

			}
		}

	}
	//범위 안인가
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	// 좌표정보 저장
	static class Pos{
		int x,y,time;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}
}