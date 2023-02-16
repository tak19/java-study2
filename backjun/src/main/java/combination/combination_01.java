package combination;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class combination_01 {
	private static StringBuilder sb = new StringBuilder();
	static Character[] c;
	static char[] tem;
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); //암호 길이
		int C = Integer.parseInt(st.nextToken()); //전체 알파벳
		
		tem = new char[L];
		//전체 문자열 저장함.
		String[] s = br.readLine().split(" ");
		c = new Character[C];
		for(int i = 0 ; i < C; i++) {
			c[i] = s[i].charAt(0);
		}

		Arrays.sort(c,Collections.reverseOrder()); //레퍼에서 가능 즉, character
		
		
		int[] p = new int[C]; //NP 돌렸을때 전체 수열 확인용
		
		int cnt = 0;
		//암호 길이 만큼 확인을 한다. 암호 길이 만큼 뽑은걸 확인 --> 정렬안하기용
		while( ++cnt <= L ) {
			p[C - cnt] = 1;
		}
		
		do {
			int now = 0;
			for(int i = C-1 ; i >= 0 ; i--) {
				if(p[i] == 1) {
					tem[now] = c[i];
					now++;
				}
			}
			//암호 조건에 만족하는지 봄
			if( search() ) {
				sb.append(tem).append("\n");
			}
		}while(npC(p));


		System.out.println(sb);


	}
	private static boolean search() {
		
		int mo = 0;
		int ja = 0;
		//조건에 만족하는 암호인지 확인
		for(int i = 0 ; i < tem.length ; i++) {
			if( tem[i] == 'a' || tem[i] == 'e' || tem[i] == 'i' || tem[i] == 'o' || tem[i] == 'u') {
				mo++;
			}else {
				ja++;
			}
			
			if( mo >= 1 && ja >= 2) { //자음,모음 규칙이 만족한다면!! true 반환
				return true;
			}
		}
		
		return false;
	}
	//사전 순 정렬 --> 자기보다 큰게 아니라 작은값을 찾아서 바꿈 --> 내림차순 정렬필요 (래퍼클래스로 rever정렬함)
	private static boolean npC(int[] input) {
		//int n = input.length;
		int n = -1;
		
		int i = n + 1;
		while( i < input.length-1 && input[i+1] <= input[i]  ) {
			i++;
		}
		if( i == input.length-1 ) {
			return false;
		}
		
		int j = n + 1;
		while( input[i+1] <= input[j] ) {
			j++;
		}
		
		swap(input,i+1,j);
		
		int k = n + 1;
		while( i > k ) {
			swap(input,i--,k++);
		}
		
		return true;
	}
	//사전의 역순 정렬 --> 자기보다 큰게 값찾아 바꿈 -- > 오름차순 정렬 필요
//	private static boolean npC(int[] input) {
//		int n = input.length;
//		
//		int i = n - 1;
//		while( i > 0 && input[i-1] >= input[i]  ) {
//			i--;
//		}
//		if( i == 0 ) {
//			return false;
//		}
//		
//		int j = n - 1;
//		while( input[i-1] >= input[j] ) {
//			j--;
//		}
//		
//		swap(input,i-1,j);
//		
//		int k = n - 1;
//		while( i < k ) {
//			swap(input,i++,k--);
//		}
//		
//		return true;
//	}
	

	private static void swap(int[] input, int i, int j) {
		
		int tem = input[i];
		input[i] = input[j];
		input[j] = tem;
		
	}

}

