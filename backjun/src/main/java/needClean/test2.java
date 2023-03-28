package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class test2 {
	static int[] dx = {0,0,-1,1,-1,-1,1,1};
	static int[] dy = {1,-1,0,0,-1,1,1,-1};
	static boolean[][] visit;
	static int[][] map;
	static int n,m;
	static Queue<Pos> q;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if( n== 0 && m == 0 ) {
				break;
			}
			map = new int[n][m];
			visit = new boolean[n][m];
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < m ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = 0;
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < m ; j++) {
					if( map[i][j] == 1 && !visit[i][j] ) {
						result++;
						visit[i][j] = true;
						q = new ArrayDeque<>();
						q.add(new Pos(i,j));
						bfs();
					}
				}
			}
			sb.append(result).append("\n");
			
			
		}
		
		System.out.println(sb);
	}

	private static void bfs() {
		
		while( !q.isEmpty() ) {
			Pos tem= q.poll();
			for(int i = 0 ; i < 8 ;i++) {
				int gox = tem.x + dx[i];
				int goy = tem.y + dy[i];
				if( canGo(gox,goy) ) {
					if( map[gox][goy] == 1 && !visit[gox][goy] ) {
						visit[gox][goy] = true;
						q.add(new Pos(gox,goy));
					}
				}
			}
		}
		
		
	}
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < n && goy >= 0 && goy < m ) {
			return true;
		}
		return false;
	}
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}




