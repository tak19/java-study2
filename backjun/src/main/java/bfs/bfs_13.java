package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_13 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		//지도 입력받음
		char[][] map = new char[n][m];
		boolean[][] visit;
		for(int i = 0 ; i < n ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0; j < m ; j++) {
				map[i][j] = c[j];
			}
		}
		//섬 거리 중 가장 긴 섬음 담을꺼야
		int max = -1;
		Queue<Pos> q = new LinkedList<>();
		//BFS -- 각각 돌면서 가장 먼곳을 갱신 시키면됨
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0; j < m ; j++) {
				//땅이라면
				if(map[i][j] == 'L' ) {
					visit = new boolean[n][m];
					q.add(new Pos(i,j,0)); //현재 좌표와 이동거리 초기화
					visit[i][j] = true;

					while( !q.isEmpty() ) {
						Pos pos = q.poll();
						int x = pos.x;
						int y = pos.y;
						
						for(int go = 0; go < 4; go++) {
							int gox = x + dx[go];
							int goy = y + dy[go];
							//범위 안이라면
							if(gox >= 0 && gox < n && goy >=0 && goy < m) {
								if( !visit[gox][goy] && map[gox][goy] != 'W') { //이동 장소가 방문 x + 물이 아니라면
									visit[gox][goy] = true;
									max = Math.max(max, pos.dis+1);
									q.add(new Pos(gox,goy,pos.dis+1));
								}
							}
						}
						
						
					}
				}
			}
		}
		System.out.println(max);

	}
	static class Pos{
		int x;
		int y;
		int dis;
		Pos(int x, int y, int dis){
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}
}
