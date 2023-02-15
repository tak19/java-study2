package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class simul_03 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //종이 수
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		boolean[][] map = new boolean[100][100];
		//색종이 좌표 입력받음
		for(int test = 0 ; test < T ; test++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int row = n + 10;
			int col = m + 10;
			for(int i = n ; i < row ; i++) {
				for(int j = m ; j < col ; j++) {
					if( !map[i][j] ) {
						map[i][j] = true;
					}
				}
			}
			
		}
		int result = 0;
		for(int i = 0 ; i < 100 ; i++) {
			for(int j = 0 ; j < 100 ; j++) {
				if(map[i][j]) {
					result++;
				}
			}
		}
		System.out.println(result);

	}
}
