package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_03 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());


		//지도 입력받음
		int[][] map= new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			char[]c = br.readLine().toCharArray();
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = c[j] - '0';
			}
		}

		boolean[][] visit = new boolean[n][m];
		visit[0][0] = true;

		// BFS -->  (1,1) -> n,m까지 가야함!!
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0,0));

		while(!q.isEmpty()) {
			
			Pos p = q.poll();

			visit[p.x][p.y] = true;
			for(int i = 0 ; i < 4; i++) {
				int gox = p.x + dx[i];
				int goy = p.y + dy[i];
				//맵 위치 안이고 방문x에다가 갈 수 있는 길인지 확인
				if(gox >=0 && gox < n && goy >= 0 && goy < m && map[gox][goy] != 0 && !visit[gox][goy]) {
					//맞다면 해당 위치 큐에 넣엊줌 + 앞으로 갈 위치에 현재 누적값 갱신 + 방문표시
					q.add(new Pos(gox,goy));
					map[gox][goy] = map[p.x][p.y] + 1;
					visit[gox][goy] = true;
				}
			}

		}
		System.out.println(map[n-1][m-1]);
	}

	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}


