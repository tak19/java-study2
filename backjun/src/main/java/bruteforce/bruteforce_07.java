package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bruteforce_07 {
	private static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int result;

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];

		//색종이 입력받음
		StringTokenizer st = null;
		for(int i = 0 ; i < 10 ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 10 ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//붙이기 시작
		dfs(0,0,new int[6],new boolean[10][10]);


		System.out.println(sb);


	}
	//종이 붙이기
	private static void dfs(int row, int col, int[] paper, boolean[][] visit) {
		//열 끝 번이라면
		if( col == 9 ) {
			dfs(row+1,0,paper,visit);
			return;
		}
		//다 붙였다면..
		if( row == 10) {

			return;
		}

		//색종이를 붙여야한다면 -> 각종 색종이 붙인다!
		if( map[row][col] == 1 && !visit[row][col] ) {
			for(int i = 1 ; i <= 5 ; i++) {
				
				//true면 진행 flase 나오면 더이상 할 필요도 읎다
				if( ck(row,col,i) ) {
					//붙으면 방문처리해준다요
					for(int r = row ; r < row + i ; row++) {
						for(int c = col ; c < col + i ; col++) {
							visit[r][c] = true;	
						}
					}
					
					
					
				}
			}
		}
		//다음 열 탐색
		dfs(row,col+1,paper,visit);




	}
	//1이 연속되는지 확인함
	private static boolean ck(int row, int col, int size) {
		//해당 사이즈 만큼의 종이가 들어가는가?
		for(int r = row ; r < row + size ; row++) {
			for(int c = col ; c < col + size ; col++) {
				//0이 나온다면 조건 불충분함
				if( map[r][c] == 0 ) {
					return false;
				}
			}
		}

		return true;
	}

}







