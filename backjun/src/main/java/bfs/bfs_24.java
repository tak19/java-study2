package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_24 {
	//상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int h,w,cnt,result;
	static boolean[][] visit;
	static int[][] map,area; 

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		h = Integer.parseInt(st.nextToken()); // 높이
		w = Integer.parseInt(st.nextToken()); // 넓이

		//지도 입력 받기 
		map = new int[h][w];
		for(int i = 0 ; i < h ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < w ; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if( map[i][j] == 1) {
					cnt++;
				}
			}
		}
		//남은 치즈가 없을때까지
		while( cnt > 0 ) {
			visit = new boolean[h][w];
			area = new int[h][w];
			melt();
			removeCheese();
			result++;
		}
		System.out.println(result);
	}

	//치즈 녹이깅
	private static void removeCheese() {
		for(int i = 0 ; i < h ; i++){
			for(int j = 0 ; j < w ; j++){
				//2개의 면 이상이 노출된 치즈가 있다면 녹이고, 남은 치즈개수 감소시킴
				if( area[i][j] >= 2 ) {
					map[i][j] = 0;
					cnt--;
				}
			}
		}

	}
	//치즈가 맞닿으면 계산
	private static void melt() {
		visit[0][0] = true;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(0,0));
		//닿는 면적 계산함
		while(!q.isEmpty()) {
			Pos pos = q.poll();

			for(int i = 0 ; i < 4 ; i ++) {
				int gox = pos.x + dx[i];
				int goy = pos.y + dy[i];
				//범위 안이라면
				if( canGO(gox, goy) ) {
					//치즈랑 맞닿아있다면
					if( map[gox][goy] == 1 ) {
						area[gox][goy] += 1;
					}
					else if( map[gox][goy] == 0 &&  !visit[gox][goy]) {
						visit[gox][goy] = true;
						q.add(new Pos(gox,goy));
					}
				}

			}

		}

	}

	//범위 안인지
	private static boolean canGO(int gox, int goy) {
		if( gox >= 0 && gox < h && goy >= 0 && goy < w) {
			return true;
		}
		return false;
	}
	//좌표 저장
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}