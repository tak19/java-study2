package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bruteforce_07 {
	private static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int[] paper;
	static boolean[][] visit;
	static int result;

	public static void main(String[] args) throws Exception{
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
		result = 26; //25장 어차피 안넘음
		paper = new int[] {0,0,0,0,0,0};
		visit = new boolean[10][10];
		//붙이기 시작
		dfs(0,0,paper,0);

		System.out.println(result == 26 ? -1 : result);

	}
	//종이 붙이기
	private static void dfs(int row, int col, int[] paper, int cnt) {

		//다 붙였다면..
		if( row == 9 && col == 10) {
			result = Math.min(result, cnt);
			return;
		}

		//열 끝 번이라면
		if( col == 10 ) {
			dfs(row+1,0,paper,cnt);
			return;
		}
		//현재 cnt(사용종이)가 최소값보다 크다면 더 이상 계산할 필요가 없다.
		if(cnt >= result) return; 


		//색종이를 붙여야한다면 -> 각종 색종이 붙인다!
		if( map[row][col] == 1 && !visit[row][col] ) {
			for(int i = 5 ; i > 0 ; i--) {
				//true면 진행 flase 나오면 더이상 할 필요도 읎다
				if( ck(row,col,i) && paper[i] < 5 ) {
					
					//5장 다 안썼다면
					paper[i]++; //1장 사용
					//붙으면 방문처리해준다요
					inpaper(row,col,i);

					//다음 열 탐색
					dfs(row,col+1,paper,cnt+1);
					//방문 후 종이 개수 원상태로 바꾸고 뗌
					paper[i]--;
					outpaper(row,col,i);

				}

			}
		}
		else {
			dfs(row,col+1,paper,cnt);

		}
	}
	// 색종이 제거
	private static void outpaper(int row, int col, int size) {
		for(int r = row ; r < row + size ; r++) {
			for(int c = col ; c < col + size ; c++) {
				visit[r][c] = false;
			}
		}
	}
	//색종이 붙이기
	private static void inpaper(int row, int col, int size) {
		for(int r = row ; r < row + size ; r++) {
			for(int c = col ; c < col + size ; c++) {
				visit[r][c] = true;
			}
		}
	}
	//1이 연속되는지 확인함 -> size 만큼
	private static boolean ck(int row, int col, int size) {
		//해당 사이즈 만큼의 종이가 들어가는가?
		for(int r = row ; r < row + size ; r++) {
			for(int c = col ; c < col + size ; c++) {
				if( r >= 10 || c >= 10 ) {
					return false;
				}
				//0이 나오거나 붙이는 종이가 겹친다면 false;
				if( map[r][c] == 0 || visit[r][c] ) {
					return false;
				}
			}
		}
		return true;
	}
}
