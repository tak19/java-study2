package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bfs_25 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N,result;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int cnt = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if( N == 0 ) {
				break;
			}
			//지도 입력받았다!!
			map = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[N][N];
			bfs();
			sb.append("Problem "+ cnt++ +": ").append(result).append("\n");

		}
		System.out.println(sb);
	}

	//모험이 시작된다!!
	private static void bfs() {
		//비용이 낮은 거 부터 빼낸다
		PriorityQueue<Pos> pq = new PriorityQueue<>((o1,o2) -> {
			return o1.cost - o2.cost;
		});
		visit[0][0] = true;
		pq.offer(new Pos(0,0,map[0][0]));

		while( !pq.isEmpty() ) {
			Pos p = pq.poll();
			int x = p.x;
			int y = p.y;
			int cost = p.cost;
			
			if( x == N-1 && y == N-1 ) {
				result = cost;
				pq.clear();
				break;
			}

			for(int i = 0 ; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//범위 안인지 확인함!!
				if( canGo(gox,goy) && !visit[gox][goy]) {
					visit[gox][goy] = true;
					pq.add(new Pos(gox,goy, cost + map[gox][goy]));
				}
			}
		}
	}
	//범위 안인지 확인함
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	//좌표저장
	static class Pos {
		int x,y,cost;
		Pos(int x, int y, int cost){
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}