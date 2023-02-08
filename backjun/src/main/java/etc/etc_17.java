package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_17 {
	static int[][] arr;
	static int white;
	static int blue;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		for(int i = 0 ; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		div(0,0,n);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void div(int row, int col, int size) {
		//같은 색으로 구성되어있는지 체크함
		if( colorCk(row,col,size) ) {
			if(arr[row][col] == 0) {
				white++;
			}else {
				blue++;
			}
			return;
		}
		//색이 일치하는 경우가 없을땐 크기를 반반 쪼개기 위해 나눔
		int update  = size / 2;
		//4등분함
		div( row , col ,update ); //우 좌단
		div( row , col + update , update ); // 좌하단
		div( row + update , col ,update ); // 우 상단
		div( row + update , col + update ,update ); // 우하단

	}
	private static boolean colorCk(int row, int col, int size) {
		int color = arr[row][col]; //기준색

		for(int i = row ; i < row+size ; i++) {
			for(int j = col ; j < col+size ; j++) {
				if( arr[i][j] != color) { //다른색깔이 나온다면 false
					return false;
				}
			}
		}
		//끝까지 수행되면 색 일치하는거임
		return true;
	}
}




