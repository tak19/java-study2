package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_11 {
	//우 - 대각 - 아래 순
	static int[] dx = {0,1,1};
	static int[] dy = {1,1,0};
	static int N,result;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		//지도 입력받기
		StringTokenizer st = null;
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 1,2에서 가로방향으로 시작
		dfs(1,2,0);

		System.out.println(result);

	}
	//좌표정보와 방향 정보를 매개변수로 받음
	private static void dfs(int r, int c, int dir) {
		//범위 안인지
		if( canGo(r,c) ) {
			//끝 지점 도착하면 1증가 후 종료
			if( r == N && c == N ) {
				result++;
				return;
			}

			//방향 별로 경로 탐색
			switch (dir) {
			case 0: {
				//가로 -> 진행방향 그대로 or 대각선
				if( canGo(r, c+1) && map[r][c+1] != 1 ) {
					dfs(r,c+1,dir);
				}
				if( canGo(r+1, c+1) && chMap(r,c+1) && chMap(r+1,c+1) && chMap(r+1,c)) {
					dfs(r+1,c+1,dir+1);
				}
				break;
			}case 1: {
				//대각

				if( canGo(r, c+1) && chMap(r,c+1) ) {
					dfs(r,c+1,dir-1); //가로
				}
				if( canGo(r+1, c+1) && chMap(r,c+1) && chMap(r+1,c+1) && chMap(r+1,c)) {
					dfs(r+1,c+1,dir); //대각
				}
				if( canGo(r+1, c) && chMap(r+1,c) ) {
					dfs(r+1,c,dir+1); //아래
				}

				break;
			}case 2: {
				//아래
				if( canGo(r+1, c+1) && chMap(r,c+1) && chMap(r+1,c+1) && chMap(r+1,c)) {
					dfs(r+1,c+1,dir-1); //대각
				}
				if( canGo(r+1, c) && chMap(r+1,c) ) {
					dfs(r+1,c,dir); //아래
				}
				break;
			}
			}

		}else {
			//범위 벗어나면 종료
			return;
		}

	}
	//벽이 없는지 확인
	private static boolean chMap(int r, int i) {
		if( map [r][i] == 0 ) {
			return true;
		}
		return false;
	}
	//범위 안인지
	private static boolean canGo(int r, int c) {

		if( r <= N && c <= N ) {
			return true;
		}

		return false;
	}
}
