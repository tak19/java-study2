package pratice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class homework_01 {
	static int[] dx = {0, 1,  0, -1};
	static int[] dy = {1, 0, -1, 0};
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
			sb.append("#" + test_case + " ");
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];
			boolean[][] visit = new boolean[n][n];


			int cnt = n*n;
			int gox = 0;
			int goy = 0;
			
			int max = n;
			int min = 0;
			
			arr[0][0] = 1;
			int w = 0;
			
			while( y < n )
			
			for(int i = 1; i <= cnt ; i++) {
				
				
				
			}
			
			
			
			

			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
			}


		}


		System.out.println(sb);

	}
}









