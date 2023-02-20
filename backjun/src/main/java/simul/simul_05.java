package simul;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class simul_05 {
	//백준 z (1074)
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static int r,c;
	static int[][] arr;
	static int cnt;

	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); 
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int area = (int) Math.pow(2, n); //n==3 -> 8??
		cutZ(area);

	}

	private static void cutZ(int size) {
		if( size == 1 ) {
			if( r == 0 && c == 0) {
				System.out.println(cnt);
				return;
			}
		}else {
			
			int half = size / 2; // 4
			//다 넘어에 있다면
			if( r >= half && c >= half ) { //우하
				cnt += (size * size / 4) * 3;
				r -= half;
				c -= half;
				cutZ(half);
				
			}else if(r <  half && c >= half ) { //우상
				cnt += (size * size / 4);
				c -= half;
				cutZ( half);
			}else if(r >= half && c <  half ) { //좌하
				
				cnt += (size * size / 4)* 2;
				r -= half;
				cutZ( half);
				
			}else if (r < half && c < half) { //좌상
				cutZ(half);
			}	
		}
	}
}

