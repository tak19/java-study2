package dfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dfs_09 {
	private static StringBuilder sb = new StringBuilder();
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int R,C;
	static char[][] map;
	static boolean[][] visit;
	static boolean[] visitAlp;
	static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); //n,m,d 순서
		R = Integer.parseInt(st.nextToken()); //row
		C = Integer.parseInt(st.nextToken()); //col
		
		map = new char[R][C];
		visit = new boolean[R][C];
		//맵을 입력받음
		for(int i = 0 ; i < R ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < C ; j++) {
				map[i] = c;
			}
		}
		result = 0;
		visitAlp = new boolean[26]; //알파벳 개수만큼이용
		//첫번째 문자는 무조건 방문해유
		visitAlp[map[0][0] - 'A'] = true;
		visit[0][0] = true;
		//알파벳 탐색
		dfs(0,0,1);

		System.out.println(result);
	}
	// 길찾기
	private static void dfs(int x, int y, int dis) {
		
		result = Math.max(result, dis);
		
		for(int i = 0 ; i < 4 ; i++) {
			int gox = x + dx[i];
			int goy = y + dy[i];
			//갈 수 있는 범위 인지
			if(canGo(gox,goy)) {
				// 방문한적이 없고, 겹치는 문자가 아닌경우에!
				if( !visit[gox][goy] && !visitAlp[map[gox][goy] - 'A']) {
					visit[gox][goy] = true;
					visitAlp[map[gox][goy] - 'A'] = true;
					dfs(gox,goy,dis+1);
					visitAlp[map[gox][goy] - 'A'] = false;
					visit[gox][goy] = false;
					
				}
			}
			
		}
	}
	//범위 안에 있는지 확인
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < R && goy >= 0 && goy < C) {
			return true;
		}
		return false;
	}
}

