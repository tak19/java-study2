package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bruteforce_11 {
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[9][9];
		StringTokenizer st = null;
		//스도쿠 입력받기
		for(int i = 0 ; i < 9 ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 9 ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		play(0,0);

	}
	//스도쿠 계산
	private static void play(int row, int col) {
		//끝까지 탐색 했다면
		if( row == 9 ) {
			for(int i = 0 ; i < 9 ; i++) {
				for(int j = 0 ; j < 9 ; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
			return;
		}
		//열 탐색을 마친경우 -> 다음행 첫열부터 다시 탐색함
		if( col == 9 ) {
			play(row+1,0);
			return;
		}
		//숫자가 있다면 다음 탐색 - 열 탐색이 남은경우
		if( map[row][col] != 0 ) {
			play(row, col+1);
			return;
		}

		//이제 검사 시작 - 행과 열에 따른 조건에 맞는 숫자 대입함
		for(int i = 1 ; i <= 9 ; i++) {
			if( ckRec(row,col,i) || ckRow(col,i) || ckCol(row,i) ) {
				continue;
			}
			//조건 충족 시 해당값 대입
			map[row][col] = i;
			play(row,col+1); //다음 열 탐색함
		}
		//탐색해서 대입한 숫자는 지워준다. - 탐색 끝나고 한번만 지워주면됨
		map[row][col] = 0; 

	}

	//열에 같은 값이 있는지 판단 -< 행값을 기준으로 0~9 열탐색
	private static boolean ckCol(int row, int num) {
		for(int i = 0 ; i < 9 ; i++) {
			if( map[row][i] == num ) {
				return true;
			}
		}
		return false;
	}
	//행에 같은 값이 있는지 판단 -> 열값을 기준으로 0~9 행탐색
	private static boolean ckRow(int col, int num) {
		for(int i = 0 ; i < 9 ; i++) {
			if( map[i][col] == num ) {
				return true;
			}
		}
		return false;
	}

	// 3x3 범위 내에 중복 수가 있는지 판단
	private static boolean ckRec(int row, int col, int num) {
		//유동적인 범위에 따른 일정 범위 탐색을 위해 탐색 영역 크기인 /3 진행하고 *3을 진행
		int rangeX = (row/3) * 3;
		int rangeY = (col/3) * 3;
		//해당 범위의 3크기만큼만확인
		for(int i = rangeX ; i < rangeX+3 ; i++) {
			for(int j = rangeY ; j < rangeY+3; j++) {
				//겹치는 숫자가 나온다면 true 반환
				if( map[i][j] == num ) {
					return true;
				}
			}
		}
		return false;
	}

}