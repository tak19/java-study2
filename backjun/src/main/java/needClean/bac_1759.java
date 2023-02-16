package needClean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class bac_1759 {
	private static StringBuilder sb = new StringBuilder();
	static char[] c;
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); //암호 길이
		int C = Integer.parseInt(st.nextToken()); //전체 알파벳
		
		//전체 문자열 저장함.
		String[] s = br.readLine().split(" ");
		c = new char[C];
		for(int i = 0 ; i < C; i++) {
			c[i] = s[i].charAt(0);
		}

		Arrays.sort(c);
		int[] p = new int[C]; //NP 돌렸을때 전체 수열 확인용
		
		int cnt = 0;
		//암호 길이 만큼 확인을 한다. 암호 길이 만큼 뽑은걸 확인 --> 정렬안하기용
		while( ++cnt <= L ) {
			p[C - cnt] = 1;
		}
//		for(int i = 0 ; i < C; i++) {
//			System.out.print(p[i] + " ");
//		}
		
		
		do {
			for(int i = 0 ; i < C ; i++) {
				if(p[i] == 1) {
					System.out.print(c[i] + " ");
				}
			}
			System.out.println();
		}while(npC(p));


		System.out.println(sb);


	}

	private static boolean npC(int[] input) {
		int n = input.length;
		
		int i = n - 1;
		while( i > 0 && input[i-1] >= input[i]  ) {
			i--;
		}
		if( i == 0 ) {
			return false;
		}
		
		int j = n - 1;
		while( input[i-1] >= input[j] ) {
			j--;
		}
		
		swap(input,i-1,j);
		
		int k = n - 1;
		while( i < k ) {
			swap(input,i++,k--);
		}
		
		return true;
	}
	

	private static void swap(int[] input, int i, int j) {
		
		int tem = input[i];
		input[i] = input[j];
		input[j] = tem;
		
	}

}

