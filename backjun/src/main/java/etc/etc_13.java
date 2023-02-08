package com.ssafy.daily03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class tee_01 {
	private static StringBuilder sb = new StringBuilder();
	static int n,m;
	static int[] arr;
	static int[] input;

	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //전체 수
		m = Integer.parseInt(st.nextToken()); //뽑을 수
		
		//뽑은 배열을 저장함
		arr = new int[m]; 
		input= new int[m];
		// 현재 뽑은수와 시작 수를 매개변수로 넘겨줌
		fun(0,1);
		
//		for(int i = 0 ; i < m ; i++) {
//			input[i] = i+1;
//		}
//		
//		combination(0,0);
		
		
		System.out.println(sb);

	}

	private static void fun(int cnt, int start) {
		if( cnt == m ) {
			for(int i = 0 ; i < m ; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start ; i <= n ; i++) {
			arr[cnt] = i;
			fun(cnt+1,i+1);
		}		
		
	}
	// 0,0시작
	private static void combination(int cnt, int start) {
		
		if (cnt == m) {
			for(int num : arr) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start ; i < n ; i++) {
			arr[cnt] = input[i];
			combination(cnt+1,i+1); // 뽑은 수 중복되지 않게 i+1
		}
	}

}










