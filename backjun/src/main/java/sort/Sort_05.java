package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sort_05 {
	static int[] a;
	static long result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		a = new int[n];
		for(int i = 0 ; i < n ; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		// 10000보다 작음
		sort(a,5);
		for(int i = 0; i <n ; i++) {
			System.out.println(a[i]);
		}
		
	}

	private static void sort(int[] a, int max_size) {
		int[] out = new int[a.length];
		int jari = 1;
		int count = 0;
		
		while( count != max_size ) {
			//자리 수 담을 버킷
			int[] bucket = new int[10];
			//자릿 수를 계산해서 버킷에 개수 더함 -- 일의 자리부터 시작함!
			for(int i = 0 ; i < a.length ; i++ ) {
				bucket[ ( a[i] / jari ) % 10 ]++;
			}
			//합 배열 이용해 index 계산히기 -->?? 보충필요
			for(int i = 1 ; i< 10; i++) {
				bucket[i] += bucket[i-1];
			}
			//현재 자리 수 기준으로 정렬
			for(int i = a.length -1 ; i >= 0 ; i--) {
				out[bucket[ a[i] / jari % 10 ] -1 ] = a[i]; //자릿수에 해당하는 인덱스에 값 저장
				bucket[ a[i] / jari % 10 ]--; // 버킷에 값 빼줌
			}
			for(int i = 0 ; i < a.length ; i++ ) {
				a[i] = out[i];
			}
			//다음 자리 수 + 깊이값 ++
			jari = jari * 10;
			count++;
			
		}
		
	}
}
