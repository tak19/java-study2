package d2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class d2_1954 {
	//우,하,좌,상 순서
		static int[] dx = {0, 1,  0, -1};
		static int[] dy = {1, 0, -1, 0};
		static int[][] arr;
		static int n;
		private static StringBuilder sb = new StringBuilder();

		public static void main(String[] args) throws Exception {
			/*
			 * 1. 입력파일 읽어 들이기
			 */
			//System.setIn(new FileInputStream("res/17478_input.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			/*
			 * 2. 입력파일 객체화
			 */
			int T = Integer.parseInt(br.readLine());
			for(int test_case = 1 ; test_case <= T ; test_case++) {
				sb.append("#" + test_case + " ").append("\n");
				n = Integer.parseInt(br.readLine());
				
				arr = new int[n][n];
				int x = 0;
				int y = 0;
				int change = 0;
				
				for (int i = 1 ; i <= n*n; i++) {

					//정수 입력 
					arr[x][y] = i;

					//다음 칸
					int nextx = x + dx[change];
					int nexty = y + dy[change];
					//다음칸 갈수있는지 확인하기
					//방향바꾸기 조건 : 1.범위를 넘어갈때  , 2. 다음칸이 채워져있을때  
					if(nextx >=n || nexty >=n || nextx < 0 || nexty < 0 || arr[nextx][nexty] != 0 ) {
						change = (change+1)%4;			//방향전환 - 우,하,좌,상 순ㄴ
					}

					//다음칸 지정
					x = x + dx[change];
					y = y + dy[change];

				}


				for(int i = 0 ; i < n ; i++) {
					for(int j = 0 ; j < n ; j++) {
						sb.append(arr[i][j] + " "); 
					}
					sb.append("\n");
				}


			}


			System.out.println(sb);

		}

	}


