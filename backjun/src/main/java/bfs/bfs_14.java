package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_14 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] map;
	static boolean[][] visit;
	static int n,m,max;
	static int wallCnt;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		wallCnt=0;
		//연구실 상황을 입역받음
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) { //벽 개수 파악한다.
					wallCnt++;
				}
			}
		}

		//0: 빈칸  1: 벽   2: 바이러스
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j ++) {
				if( map[i][j] == 0 ) { //빈칸이라면
					map[i][j] = 1;
					createWall(1);
					map[i][j] = 0;
				}
			}
		}
		//탐색할때 빈칸이였던 곳이 벽으로 3 칸 차기때문에 벽의 개수를 3 더하고 결과값에 빼준다.
		wallCnt += 3;
		System.out.println(max - wallCnt);
	}
	


	//DFS를 통한 벽 건설
	private static void createWall(int cnt) {
		//벽 3개를 다 지으면 BFS 탐색하요~
		if( cnt == 3 ) {
			visit = new boolean[n][m];
			infection();
			cleanAreaCnt();
			return;
		}
		//재귀 호출시작
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j ++) {
				if( map[i][j] == 0 ) { //빈칸이라면
					map[i][j] = 1;
					createWall(cnt+1);
					map[i][j] = 0;
				}
			}
		}

	}

	//감염 한다! --> BFS
	private static void infection() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j ++) {
				
				if( map[i][j] == 2 && !visit[i][j]) { //바이러스 칸이면서 방문 x라면
					// visit 배열을 통해 빈방의 감염 정도를 파악할꺼임 ---> false 라면 감염이 안된 방이다. but 벽의 개수가 포함된값임
					visit[i][j] = true;
					Queue<Pos> q = new LinkedList<>();
					q.add(new Pos(i,j));

					while(!q.isEmpty()) {
						Pos p = q.poll();
						int x = p.x;
						int y = p.y;
						//4방 탐색 시작
						for(int go =0; go < 4; go++) {
							int gox = x + dx[go];
							int goy = y + dy[go];
							//범위 안이라면
							if(gox >= 0 && gox < n && goy >= 0 && goy < m) {
								if(map[gox][goy] == 0 && !visit[gox][goy]) { //방문하지 않았고 빈방이라면
									visit[gox][goy] = true;
									q.add(new Pos(gox,goy));
								}
							}

						}
					}


				}
			}
		}
	}
	//바이러스가 없는곳의 개수를 센다 --> 즉, 벽과 빈칸의 수를 셈
	private static void cleanAreaCnt() {
		int count = 0;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j ++) {
				if( !visit[i][j] ) {
					count++;
				}
			}
		}
		max = Math.max(max, count);
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
