package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class d3_11315 {
	//8방탐색
	static int[] dx = {0, 0, 1, -1,  1, 1, -1, -1};
	static int[] dy = {1,-1, 0,  0, -1, 1,  1, -1};
	static int N;
	static boolean ck;
	static char[][] map;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");
			N = Integer.parseInt(br.readLine()); //지도 크기

			map = new char[N][N];
			//오목판 입력받기
			for(int i = 0 ; i < N ; i++) {
				String s = br.readLine();
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = s.charAt(j);
				}
			}
			
			//탐색 시작
			breakPoint:
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					ck = false;
					//돌이 있는 칸이라면 5목이 될 확률이 있음 
					if( map[i][j] == 'o' ) {
						for(int dir = 0 ; dir < 8 ; dir++) {
							dfs(i,j,dir,1);
							if( ck ) {
								break breakPoint;
							}
						}
					}
				}
			}
			if( ck ) {
				sb.append("YES\n");
			}else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb);

	}

	//탐색함 - 좌표값, 방향, 현재 돌의 수를 계산
	private static void dfs(int i, int j, int dir, int cnt) {
		if( cnt >= 5 ) {
			ck = true;
			return;
		}
		//앞으로 갈 방향
		int gox = i + dx[dir];
		int goy = j + dy[dir];
		//이동 가능하다면
		if( canGo(gox,goy) && map[gox][goy] == 'o') {
			dfs(gox,goy,dir,cnt+1);
		}
		
	}
	
	//범위 안인지 검사
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}


}