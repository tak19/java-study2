package etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class etc_08 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int n,row,col;
	static int breakNow,result;
	static int[][] map,origin;
	static int[] outIndex;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());

			map = new int[row][col];
			origin = new int[row][col];
			outIndex = new int[n];
			//지도 입력받음
			for(int i = 0 ; i < row ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < col ; j++) {
					origin[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			//col에서 n만큼의 중복 순열을 뽑는다
			pec(0);
			sb.append(result).append("\n");

		}
		System.out.println(sb);

	}
	//깰 블록의 열을 지정함
	private static void pec(int cnt) {
		//n개 만큼 다 뽑았다면
		if( cnt == n ) {
			breakNow = 0;
			setMap();
			for(int i = 0 ; i < n ; i++) {
				searchWall(outIndex[i]);
				updateWall();

			}
			cntBlock();
			result = Math.min(result, breakNow);
			return;
		}
		//열만큼
		for(int i = 0 ; i < col ; i++) {
			outIndex[cnt] = i;
			pec(cnt+1);
		}
	}
	private static void cntBlock() {
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0; j < col ; j++) {
				if(map[i][j] != 0) {
					breakNow++;
				}
			}
		}
	}
	//부숴진 벽 내리기
	private static void updateWall() {
		for(int j = 0; j < col ; j++) {
			for(int i = row-1 ; i > 0 ; i--) {
				//부숴진 공간을 만났다면
				if( map[i][j] == 0 ) {
					//해당 열에 벽을 찾는다!
					for(int k = i-1 ; k >= 0 ; k--) {
						if( map[k][j] != 0 ) {
							map[i][j] = map[k][j];
							map[k][j] = 0;
							break;
						}
					}
				}
			}
		}
	}

	//원본 맵 복시
	private static void setMap() {
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0; j < col ; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}
	//부술 벽돌 찾아
	private static void searchWall(int c) {
		for(int i = 0 ; i < row ; i++) {
			//정해진 열을 따라 내려가면서 벽돌이 있는지 확인
			if( map[i][c] != 0 ) {
				breakWall(i,c);
				break;
			}
		}
	}

	//벽돌 깨기
	private static void breakWall(int r, int c) {
		int range = map[r][c]; //부술 범위
		if( map[r][c] != 0 ) {
			map[r][c] = 0; // 폭발 시키깅
		}
		//범위만큼 부수면서 재귀호출
		for(int i = 1; i < range; i++) {
			//아래방향으로 나가면서 제거함
			if( canBreakRow(r+i) ) {
				breakWall(r+i, c);
			}
			//위쪽으로 나가면서 제거함
			if( canBreakRow(r-i) ) {
				breakWall(r-i, c);
			}
			//왼쪽으로 나가면서 제거함
			if( canBreakCol(c-i) ) {
				breakWall(r, c-i);
			}
			//오른쪽으로 나가면서 제거함
			if( canBreakCol(c+i) ) {
				breakWall(r, c+i);
			}
		}


	}
	//범위 안인지 본다.
	private static boolean canBreakCol(int i) {
		if( i >= 0 && i < col) {
			return true;
		}
		return false;
	}
	//범위 안인지 본다.
	private static boolean canBreakRow(int i) {
		if( i >= 0 && i < row ) {
			return true;
		}
		return false;
	}
}

