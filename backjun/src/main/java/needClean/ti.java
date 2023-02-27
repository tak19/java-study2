package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class ti {
	//좌상우하 순
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int n,m,limit,result;
	static int[] output;
	static int[][] map,origin;
	static boolean[][] visit;
	static List<Pos> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		
		origin = new int[n][m];
		int size = 0;
		//cctc 정보 입력
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
				if (origin[i][j] != 0 && origin[i][j] != 6) {
					list.add(new Pos(i,j,origin[i][j]));
				}
			}
		}

		//CCTV 개수 저장
		output = new int[list.size()];
		result = Integer.MAX_VALUE;
		pec(0);
		System.out.println(result);
		//System.out.println(sb);

	}
	
	private static void pec(int cnt) {
		if(cnt == list.size()) {
			map = new int[n][m];
			copyArray();
			visit = new boolean[n][m];
			
			for(int i = 0 ; i < list.size() ; i++) {
				
				Pos pos = list.get(i);
				//dir-> cctv 종류
				switch (pos.dir) {
				case 1: {//한쪽
					dfs(pos.x, pos.y, output[i]);
					break;
				}case 2: { //양쪽
					dfs(pos.x, pos.y, output[i]);
					dfs(pos.x, pos.y, output[i]+2);
					break;
				}case 3: {//90도로
					dfs(pos.x, pos.y, output[i]);
					dfs(pos.x, pos.y, output[i]+1);
					break;
				}case 4: {//3방향
					dfs(pos.x, pos.y, output[i]);
					dfs(pos.x, pos.y, output[i]+1);
					dfs(pos.x, pos.y, output[i]+2);
					break;
				}case 5: {//4방
					dfs(pos.x, pos.y, output[i]);
					dfs(pos.x, pos.y, output[i]+1);
					dfs(pos.x, pos.y, output[i]+2);
					dfs(pos.x, pos.y, output[i]+3);
					break;
				}

				}
				
			}
			int sum = 0;
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < m ; j++) {
					if( map[i][j] == 0 && !visit[i][j]) {
						sum++;
					}
				}
			}
			result = Math.min(result, sum);

			return;
		}

		for(int i = 0 ; i < 4 ; i++) {
			output[cnt] = i;
			pec(cnt+1);
		}

	}
	//탐색해유~
	private static void dfs(int x, int y, int dir) {
		dir %= 4;
		//이동할 방향
		int gox = x + dx[dir];
		int goy = y + dy[dir];
		//이동 범위 안이라면
		if (canGo(gox,goy)) {
			if(map[gox][goy] == 0 && !visit[gox][goy]) {
				visit[gox][goy] = true;
				dfs(gox,goy,dir);
			}else if( map[gox][goy]  >= 1 && map[gox][goy] <= 5 || visit[gox][goy] ){
				dfs(gox,goy,dir);
			}
		}
		
		
	}

	//범위 안인지 확인
	private static boolean canGo(int x, int y) {
		if( x >= 0 && x < n && y >= 0 && y < m) {
			return true;
		}
		return false;
	}
	//배열을 복사해
	private static void copyArray() {
		//좌표정보, 카메라 종류, 방향 입력함
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}
	static class Pos{
		int x,y;
		int dir;

		public Pos(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}
}
