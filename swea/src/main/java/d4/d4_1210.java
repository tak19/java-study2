package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class d4_1210 {
	//BFS좌표
//	static int[] dx = { 0, 0, 1};
//	static int[] dy = { 1, -1, 0};
	//BFS 좌표
	static int[] dx = { 0, 0, -1};
	static int[] dy = { 1, -1, 0};
	static int[][] map = new int[100][100]; //지도
	static boolean[][] visit;
	static boolean ck;
	static Queue<Pos> q = new ArrayDeque<>();

	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		//하,좌,우 만 갈 수 있음!!

		for(int test_case = 1 ; test_case <= 10 ; test_case++) {
			br.readLine(); //테케수 버리기용
			sb.append("#" + test_case + " ");

			StringTokenizer st;
			//지도 입력
			for(int i = 0 ; i < 100 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 100 ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if( map[i][j] == 2) {
						q.add(new Pos(i,j));
					}
				}
			}
			
			bfs();
			//start(); //DFS풀이
		}
		System.out.println(sb);

	}

	private static void bfs() {
		visit = new boolean[100][100];
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			visit[x][y] = true;
			if( x == 0) {
				sb.append(y).append("\n");
				q.clear();
				break;
			}
			//좌-우-상
			for(int i = 0 ; i < 3 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				
				if( gox >= 0 && gox < 100 && goy >= 0 && goy < 100) { //범위안이라면
					if( !visit[gox][goy] && map[gox][goy] != 0) { //방문하지 않았다면
						q.add(new Pos(gox,goy));
						break;
					}
				}
				
			}
			
			
		}
		
		
		
	}

	//사다리 출발점 찾기
	private static void start() {
		// 첫번째 행 하나씩 들어가서 도착할 수 있는지 본다!!
		for(int j = 0 ; j < 100 ; j++) {
			if( map[0][j] == 1 ) { //시작 점이라면!!
				ck = false;
				visit = new boolean[100][100];

				dfs(0,j);
				if( ck ) {
					sb.append(j + "\n");
					break; 
				}
			}
		}
	}

	private static void dfs(int x, int y) {
		if(visit[x][y]) {
			return;
		}else {

			visit[x][y] = true;
			if( map[x][y] == 2) {
				ck = true;
				return;
			}


			if( dy[0] + y < 100 && map[x][ dy[0] + y] != 0 && !visit[x][dy[0] + y]  ) {

				dfs( x , dy[0] + y ); //오른쪽

			}else if( dy[1] + y >= 0  &&  map[x][  dy[1] + y ] != 0 && !visit[x][dy[1] + y]  ) {

				dfs( x , dy[1] + y ); // 왼쪽

			}else if(  x + dx[2] < 100 && map[x + dx[2]][y] != 0 && !visit[x + dx[2]][ y] ) {

				dfs( x + dx[2] , y ); //아래로
			}


		}

	}
	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}