package prime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class prime_01 {
	static boolean[] prime;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); //시작점
		int end = Integer.parseInt(st.nextToken()); //끝
		
		prime = new boolean[end+1];
		
		StringBuilder sb = new StringBuilder();
		//에라토스테네스의 체
		prime[1] = true;
		//방법 1 -> 바로 값을 집어넣음
		for(int i = 2 ; i <= end ; i++) {
			if( !prime[i] ) {
				if( i >= start ) {
					sb.append(i).append("\n");
				}
				for( int j = i+i ; j <= end ; j+=i ) {
					prime[j] = true;
				}
			}
		}
		
		//방법 2 - 에라토스테네스의 범위를 줄여 탐색한 후 추후에 값을 넣음
//		for(int i = 2 ; i <= Math.sqrt(end) ; i++) {
//			if( !prime[i] ) {
//				for( int j = i+i ; j <= end ; j+=i ) {
//					prime[j] = true;
//				}
//			}
//		}
//		for(int i = start ; i <= end ; i++) {
//			if( !prime[i] ) {
//				sb.append(i).append("\n");
//			}
//			
//		}
		
		System.out.println(sb);
		
		
	}
}