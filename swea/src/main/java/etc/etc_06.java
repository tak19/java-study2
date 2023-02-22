package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class etc_06 {
	private static StringBuilder sb = new StringBuilder();
	//사방탐색이요~
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int n,k,max;
	static int[][] origin;
	static boolean[][] visit;
	static int result;

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken()); //등산로 크기
			k = Integer.parseInt(st.nextToken()); //공사 가능 깊이 k

			origin = new int[n][n];

			max = 0;
			//지도 정보 확인
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n ; j++) {
					origin[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, origin[i][j]); //최고봉 값 저장함
				}
			}
			result = 0;
			visit = new boolean[n][n];
			start();

			sb.append(result).append("\n");

		}

		System.out.println(sb);

	}
	//최고봉 찾아서 BFS 돌릴꺼야
	private static void start() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0; j < n ; j++) {
				if(origin[i][j] == max) {
					visit[i][j] = true;
					dfs(i,j,0,1,origin[i][j]);
					visit[i][j] = false;
				}
			}
		}
	}

	//좌표랑 공사 했는지 확인여부
	private static void dfs(int i, int j, int ck, int dis, int hi) {

		int x = i;
		int y = j;
		result = Math.max(result, dis);

		//사방탐색
		for(int go = 0 ; go < 4 ; go++) {
			int gox = x + dx[go];
			int goy = y + dy[go];
			//범위 안이면서 현재 봉우리 보다 낮다면
			if( canGo(gox,goy) ) {
				if( origin[gox][goy] < hi && !visit[gox][goy]) {
					visit[gox][goy] = true;
					dfs(gox,goy,ck,dis+1,origin[gox][goy]);
					visit[gox][goy] = false; //다른 탐색 시 검색 허용하기 위해
				}
				//범위 안 더 큰 봉우리를 마주했을때
				else if( origin[gox][goy] >= hi && !visit[gox][goy]) {
					int sub = origin[gox][goy] - origin[x][y];
					if( sub < k && ck == 0) { //공사 가능 범위면서 공사이력이 없다면!!
						visit[gox][goy] = true;
						dfs(gox, goy, ck+1,dis+1,hi -1);
						visit[gox][goy] = false;
					}
				}
			}

		}
	}

	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < n && goy >= 0 && goy < n) {
			return true;
		}
		return false;
	}

}
