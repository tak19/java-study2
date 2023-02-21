package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class simul_06 {
	static int n;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		//영상 입력받음
		for(int i = 0 ; i < n ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < n ; j++) {
				map[i][j] = c[j] - '0';
			}
		}
		cut(0,0,n);
		System.out.println(sb);

	}
	//압축 드가자~~
	private static void cut(int row, int col, int size) {
		
		//구성을 확인한다.
		int ck =0;
		for(int i = row ; i < row + size ; i++) {
			for(int j = col ; j < col + size ; j++) {
				ck += map[i][j];
			}
		}

		//기저 조건
		if( ck == size * size) {//모두 1이라면 
			sb.append(1);

		}else if(ck == 0) { //모두 0이라면!
			sb.append(0);

		}else {
			sb.append("(");
			//왕기춘급 유도
			int half = size / 2;

			cut(row,col,half);
			cut(row,col+half,half);
			cut(row+half,col,half);
			cut(row+half,col+half,half);
			
			sb.append(")");
		}
	}
}