package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_16 {
	//상하좌우
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	//말 움직임
	static int[] hx = {-2, -2, -1, -1,  1, 1,  2,  2 };
	static int[] hy = { 1, -1,  2, -2, -2, 2, -1,  1 };
	static int hMove,n,m;
	static int map[][];
	static boolean visit[][][];
	static Queue<Pos> q = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		hMove = Integer.parseInt(br.readLine()) + 1; //말의 움직임 횟수-- 0 ~ 30 // +1 말 이동 x 인 경우 위해
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 세로 길이
		n = Integer.parseInt(st.nextToken()); // 가로 길이
		
		
		
		map = new int[n][m];
		visit = new boolean[n][m][hMove];
		//지도 입력받음
		for(int i = 0 ; i < n ; i ++) {
			st = new  StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j++ ) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//시작 좌표 (0,0) 말횟수 이동거리
		q.add(new Pos(0,0,0,0));
		visit[0][0][0] = true;
		if( !bfs()) {
			System.out.println(-1);
		}
		
		
	}
	
	private static boolean bfs() {
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			
			int x = p.x;
			int y = p.y;
			int hm = p.cnt; //현재까지의 말 따라하기 횟수
			
			if( x == n-1 && y == m-1) {
				System.out.println( p.dis);
				return true;
			}
			
			//제한된 말 따라하깁보다 적게 말을 따라했다면
			if( hm+1 <= hMove -1 ) {
				for(int i = 0; i < 8 ; i++) {
					int gox = x + hx[i];
					int goy = y + hy[i];
					if(gox >= 0 && gox < n && goy >= 0 && goy < m) { //범위 내인지 확인
						if( !visit[gox][goy][hm+1] && map[gox][goy] != 1 ) { //방문하지 않았다면
							q.add(new Pos(gox,goy,hm+1,p.dis+1));
							visit[gox][goy][hm+1] = true;
						}
					}
				}
			}
			//상하좌우 이동
			for(int go =0; go < 4; go++) {
				int gox = x + dx[go];
				int goy = y + dy[go];
				//범위 안이라면
				if(gox >= 0 && gox < n && goy >= 0 && goy < m) {
					if( !visit[gox][goy][hm] && map[gox][goy] != 1 ) { 
						q.add(new Pos(gox,goy,hm,p.dis+1));
						visit[gox][goy][hm] = true;
						
					}
				}

			}
			
		}
		
		
		return false;
	}
	
	static class Pos{
		int x;
		int y;
		int cnt;
		int dis;
		Pos(int x,int y, int cnt, int dis){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dis = dis;
			
		}
	}
}