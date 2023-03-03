package etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_14 {
	static int N,M,C;
	static int[][] map,sumMap;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //지도크기
			M = Integer.parseInt(st.nextToken()); //통 수 - 윈도우 사이즈
			C = Integer.parseInt(st.nextToken()); //최대 양 - 크기
			//지도 입력받음
			map = new int[N+1][M+1];
			sumMap = new int[N+1][M+1]; //누적합 생성
			for(int i = 1 ; i <= N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1 ; j <= N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					sumMap[i][j] = sumMap[i][j-1] + map[i][j]; //이전 누적합과 현재값을 더해서 값 구함   
				}
			}

			collection(0,0,0);


		}


		System.out.println(sb);

	}
	//채취 메소드 -- 행을 매개변수로!
	private static void collection(int row, int x, int y) {
		if( row == N ) {
			
			return;
		}
		//
		for(int i = row + 1 ; i <= N ; i++) {
			
		}
		
		
	}
}

