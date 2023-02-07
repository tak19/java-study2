package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_1210 {
	static int[] dx = { 0, 0, 1};
	static int[] dy = { 1, -1, 0};
	static int[][] map = new int[100][100]; //지도
	static boolean[][] visit;
	static boolean ck;

	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		System.setIn(new FileInputStream("res/input.txt"));
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
				}
			}
			start();
		}
		System.out.println(sb);

	}

	//사다리 출발점 찾기
	private static void start() {
		// 첫번째 행 하나씩 들어가서 도착할 수 있는지 본다!!
		for(int j = 0 ; j < 100 ; j++) {
			if( map[0][j] == 1 ) { //시작 점이라면!!
				ck = false;
				visit = new boolean[100][100];

				bfs(0,j);
				if( ck ) {
					sb.append(j + "\n");
					break; 
				}
			}
		}
	}

	private static void bfs(int x, int y) {
		if(visit[x][y]) {
			return;
		}else {

			visit[x][y] = true;
			if( map[x][y] == 2) {
				ck = true;
				return;
			}


			if( dy[0] + y < 100 && map[x][ dy[0] + y] != 0 && !visit[x][dy[0] + y]  ) {

				bfs( x , dy[0] + y ); //오른쪽

			}else if( dy[1] + y >= 0  &&  map[x][  dy[1] + y ] != 0 && !visit[x][dy[1] + y]  ) {

				bfs( x , dy[1] + y ); // 왼쪽

			}else if(  x + dx[2] < 100 && map[x + dx[2]][y] != 0 && !visit[x + dx[2]][ y] ) {

				bfs( x + dx[2] , y ); //아래로
			}


		}

	}
}