package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_06 {
	static int[] dx = {0,0,-1,1}; //상하좌우 이동 좌표
	static int[] dy = {1,-1,0,0};
	static boolean[][] visit;
	static int[][] map;
	static int n,m;
	static int detach,cnt;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n =Integer.parseInt(st.nextToken());
		m =Integer.parseInt(st.nextToken());

		//빙산 지도를 입력받아서 빙산이 있는 곳은 큐에 집어넣음
		map = new int[n][m]; // 빙산 지도
		visit = new boolean[n][m];
		Queue<Pos> q = new LinkedList<>();
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//처음에 바로 분리된경우
		bfs();
		if(detach >= 2) {
			System.out.println(0);
			return;
		}


		int result = 0;
		cnt = 0;
		//빙산을 녹이는 BFS 진행
		while( detach < 2 ) {
			melt();
			bfs();
			if(cnt == 0) {
				result=0;
				break;
			}
			
			result++;
		}

		System.out.println(result );



	}

	private static void melt() {
		Queue<Pos> q = new LinkedList<>();

		boolean[][] visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					q.offer(new Pos(i, j));
					visited[i][j] = true; //빙산인 부분만 true 처리를 하여 이중으로 빼는 현상을 방지
				}
			}
		}
		int nx, ny;
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			int area = 0; // 빙하 상하좌우에 존재하는 바다 영역의 수.

			for (int i = 0; i < 4; i++) {
				nx = pos.x + dx[i];
				ny = pos.y + dy[i];

				if( nx >= 0 && nx < n && ny >= 0 && ny < m ) {
					if( !visited[nx][ny] && map[nx][ny] == 0 ) { //바다와 맞닿아 있다면!
						area++;
					}
				}
			}

			if (map[pos.x][pos.y] - area < 0) {
				map[pos.x][pos.y] = 0;
			} else {
				map[pos.x][pos.y] -= area;
			}
		}
	}

	//빙산과 연결된 개수를 센다면?? 해당 수가 큐에 남은 수와 다르다면 분리된것 아니냐?
	//맵의 숫자가 있는곳은 (새로운 방문값에)방문처리한다. 처음 방문처리가 끝났는데 또 구현된다면,, 즉 또 방문값이 있다면 분리된것임
	private static void bfs() {
		detach = 0;
		cnt = 0;
		boolean[][] ck = new boolean[n][m];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0; j < m ; j++) {
				//빙산이 안 녹은 곳이 있다면!! 하나씩 들리면서 빙산은 전부 방문처리 한다. 다음은 어떻게..
				//숫자로 판단 만약 0 보다 큰 곳이 있다면 안녹!! 해당 경우 + 방문안한곳이라면!! 
				if( map[i][j] != 0 && !ck[i][j] ) {
					cnt++;
					if( detach++ >= 2) { //분리된 빙산 지역이 2개 이상이라면
						return;
					}
					Queue<Pos> q = new LinkedList<>();
					q.add(new Pos(i,j));
					while( !q.isEmpty() ) {
						Pos pos = q.poll(); //빙산 하나 가져와!
						int x = pos.x;
						int y = pos.y;
						ck[i][j] = true; //해당 빙산 방문처리함.
						for(int go = 0; go < 4; go ++) { //4방 탐색
							int gox = x + dx[go];
							int goy = y + dy[go];
							//갈수 있는 범위 내라면!!
							if( gox >= 0 && gox < n && goy >= 0 && goy < m ) {
								if( map[gox][goy] > 0 && !ck[gox][goy]) { //옆 인덱스가 빙산이라면! + 방문 x라면!
									ck[gox][goy] = true;
									q.add(new Pos(gox,goy));

								}
							}
						}
					}
				}
			}
		}
	}

	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
}

