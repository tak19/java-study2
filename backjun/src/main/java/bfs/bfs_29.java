package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_29 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int R,C,H,W,result;
	static boolean[][] visit;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//지도 입력받기
		map = new int[R][C];
		for(int i = 0 ; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < C ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int lecX = Integer.parseInt(st.nextToken())-1;
		int lecY = Integer.parseInt(st.nextToken())-1;
		int goalX = Integer.parseInt(st.nextToken())-1;
		int goalY = Integer.parseInt(st.nextToken())-1;
		map[goalX][goalY] = 2;
		
		result = -1;
		visit = new boolean[R][C];
		bfs(lecX,lecY);
		
		System.out.println(result);
	}
	//탐색시작
	private static void bfs(int lecX, int lecY) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(lecX,lecY,0));
		visit[lecX][lecY] = true;
		
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			
			int x = pos.x;
			int y = pos.y;
			if( map[x][y] == 2 ) {
				result = pos.dis;
				return;
			}
			
			//4방
			for(int i = 0 ; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//범위안? + 벽이 아님
				if( canGo(gox,goy) && !visit[gox][goy] && map[gox][goy] != 1 ) {
					if( ckWall(gox,goy) ) {
						visit[gox][goy] = true;
						q.offer(new Pos(gox,goy,pos.dis+1));
					}
				}
				
			}
			
		}
		
	}
	//이동 범위내에 벽이있는지 확인
	private static boolean ckWall(int gox, int goy) {

		//좌상, 우하 좌표구함
		int minX = gox;
		int minY = goy;
		int maxX = gox + H;
		int maxY = goy + W;
		//사각형이 범위를 벗어난다면
		if( !canGo(maxX-1,maxY-1) ) {
			return false;
		}
		for(int i = minX ; i < maxX ; i++) {
			for(int j = minY ; j < maxY ; j++) {
				if( map[i][j] == 1 ) {
					return false;
				}
			}
		}
		return true;
	}
	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < R && goy >= 0 && goy < C ) {
			return true;
		}
		return false;
	}
	//좌표정보
	static class Pos{
		int x,y,dis;

		public Pos(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		
	}
}