package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class simul_02 {
	static int n,m,repeat; //row / col / 회전 수
	static boolean[] isSelect; //회전 처리 확인용
	static String[] oper,split;
	static int[][] arr,temp;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //row
		m = Integer.parseInt(st.nextToken()); //col
		repeat = Integer.parseInt(st.nextToken()); //회전 횟수

		//배열 입력받기
		arr = new int[n+1][m+1];
		for(int i = 1 ; i <= n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= m ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		split = new String[repeat];
		oper = new String[repeat];
		isSelect = new boolean[repeat];
		temp = new int[n+1][m+1];
		//회전 수 만큼
		for(int i = 0 ; i < repeat ; i++) {
			split[i] = br.readLine();
		}
		min = Integer.MAX_VALUE;
		pec(0);
		System.out.println(min);

	}
	//연산 순서
	private static void pec(int cnt) {
		if( cnt == repeat) {
			update(); //배열 복사
			rotate(); //돌리기 실행
			cal(); //배열 최소값 계산하기
			return;
		}
		//연산 순열 생성
		for(int i = 0 ; i < repeat ; i++) {
			if( isSelect[i] ) {
				continue;
			}
			oper[cnt] = split[i]; //자리수에 할당 
			isSelect[i] = true; //방문처리
			pec(cnt+1);
			isSelect[i] = false;
		}

	}
	//배열 최소값 계산해~~
	private static void cal() {
		int rowSum = 0;
		for(int i = 1 ; i <= n ; i++) {
			rowSum = 0;
			for(int j = 1 ; j <= m ; j++) {
				rowSum += temp[i][j];
			}
			if( min > rowSum) {
				min = rowSum;
			}
		}

	}
	//배열을 복사해~
	private static void update() {
		for(int i = 1 ; i <= n ; i++) {
			for(int j = 1 ; j <= m ; j++) {
				temp[i][j] = arr[i][j];
			}
		}
	}
	private static void rotate() {
		StringTokenizer st;
		for(int k = 0 ; k < repeat ; k++) {
			//( r, c, s ) -가장 왼쪽 윗 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)인 !정사각형!
			st = new StringTokenizer(oper[k]); 
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			//정사각형이라 r번 회전함 --> ( r + s ) - ( r - s ) = 2s / 2 = s
			for(int j = 0; j < s ; j++) {

				int startX =  r - s + j;
				int startY =  c - s + j;

				int endX = r + s - j ;
				int endY = c + s - j ;

				int tem = temp[startX][startY]; // 시작값 저장하고 돌림
				//시계방향 회전 --> 좌상단 값 빼주고.. 좌변부터 올림 나중에 값 넣어주면 문제없음
				//좌변 올림 - endX 감소 startY 고정
				for(int i = startX; i < endX ; i++) {
					temp[i][startY] = temp[i+1][startY];
				}


				//아래변 왼쪽으로 - endX 고정 endY 감소
				for(int i = startY; i < endY ; i++) {
					temp[endX][i] = temp[endX][i+1];
				}


				//우변 내림 - StartX 증가 endY 고정
				for(int i = endX; i > startX ; i--) {
					temp[i][endY] = temp[i-1][endY];
				}

				//윗변 오른쪽으로 - StartX 고정 startY 증가
				for(int i = endY; i > startY ; i--) {
					temp[startX][i] = temp[startX][i-1];
				}
				//초기값 대입
				temp[startX][startY+1] = tem;

			}
		}
	}
}



