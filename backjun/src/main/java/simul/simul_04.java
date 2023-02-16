package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class simul_04 {
	static int[][] origin,temp;
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int allOper = Integer.parseInt(st.nextToken());

		//기존 배열 입력받음
		origin = new int[N][M];
		temp = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < allOper ; i++) {
			int what = Integer.parseInt(st.nextToken());

			switch (what) {
			case 1:
				updown();
				break;
			case 2:
				leftright();
				break;
			case 3:
				right();
				break;
			case 4:
				left();
				break;
			case 5:
				RClock();
				break;
			case 6:
				LClock();
				break;

			}

		}
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				sb.append(origin[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}


	//위,아래 반전
	private static void updown() {
		temp = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				temp[i][j] = origin[N - i - 1][j];
			}
		}
		origin = temp;
	}
	//좌우 반전
	private static void leftright() {
		temp = new int[N][M];
		for(int j = 0 ; j < M ; j++) {
			for(int i = 0 ; i < N ; i++) {
				temp[i][j] = origin[i][M - j - 1];
			}
		}


		origin = temp;

	}
	//오른쪽 90도 회전 --> 행과 열이 달라버리면 문제가 생김.. 새로운 배열 만들자
	private static void right() {
		int[][] t = new int[M][N];

		int col = N - 1;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				t[j][col] = origin[i][j]; 
			}
			col--;
		}
		int temp = N;
		N = M;
		M = temp;

		origin = t;



	}
	//왼쪽 90도 회전
	private static void left() {

		int[][] t = new int[M][N];

		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				t[M - j - 1][i] = origin[i][j]; 
			}
		}
		int temp = N;
		N = M;
		M = temp;

		origin = t;

	}
	//4등분해서 오른쪽
	private static void RClock() {
		int[][] t = new int[N][M];

		int midN = N / 2;
		int midM = M / 2;

		// 1부터 시계방향으로 하나씩 회전시킴
		for (int i = 0; i < midN; i++) {
			for (int j = 0; j < midM; j++) {
				t[i][midM + j] = origin[i][j];
			}
		}

		for (int i = 0; i < midN; i++) {
			for (int j = midM; j < M; j++) {
				t[midN + i][j] = origin[i][j];
			}
		}

		for (int i = midN; i < N; i++) {
			int col = 0;
			for (int j = midM; j < M; j++, col++) {
				t[i][col] = origin[i][j];
			}
		}

		int row = 0;
		for (int i = midN; i < N; i++, row++) {
			for (int j = 0; j < midM; j++) {
				t[row][j] = origin[i][j];
			}
		}

		origin = t;
	}
	//4등분해서 왼쪽
	private static void LClock() {
		int[][] t = new int[N][M];

		int midN = N / 2;
		int midM = M / 2;

		// 1 > 4
		for (int i = 0; i < midN; i++) {
			for (int j = 0; j < midM; j++) {
				t[midN + i][j] = origin[i][j];
			}
		}

		// 4 > 3
		for (int i = midN; i < N; i++) {
			for (int j = 0; j < midM; j++) {
				t[i][midM + j] = origin[i][j];
			}
		}

		// 3 > 2
		int row = 0;
		for (int i = midN; i < N; i++, row++) {
			for (int j = midM; j < M; j++) {
				t[row][j] = origin[i][j];
			}
		}

		// 2 > 1
		for (int i = 0; i < midN; i++) {
			int col = 0;
			for (int j = midM; j < M; j++, col++) {
				t[i][col] = origin[i][j];
			}
		}

		origin = t;

		
		
	}

}
