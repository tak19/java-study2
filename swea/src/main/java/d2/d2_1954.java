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
	static int cnt,n;
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
			
			
			cnt = 1;
			dfs(0,0);

			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					sb.append(arr[i][j] + " "); 
				}
				sb.append("\n");
			}
			

		}


		System.out.println(sb);

	}

	private static void dfs(int i, int j) {
		arr[i][j] = cnt++;
		if( j+dy[0] < n && arr[i][j+dy[0]] == 0 ) { //우측
			dfs( i , j+dy[0] );
			
		}else if( i+dx[1] < n && arr[i+dx[1]][j] == 0 ) { //하 
			dfs(i+dx[1],j);
			
		}else if( j+dy[2] >= 0 && arr[i][j+dy[2]] == 0 ) { //좌
			dfs(i,j+dy[2]);
			
		}else if ( i+dx[3] >= 0 && arr[i+dx[3]][j] == 0 ) { //상
			dfs(i+dx[3],j);
		}
		
	}
}









