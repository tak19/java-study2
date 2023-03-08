package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class binary_03 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 행렬 크기
		int k = Integer.parseInt(br.readLine()); // 찾을 순서

		int result = 0;

		//이분 탐색을 위한 양끝탐색 범위
		int start = 1;
		int end = k; 

		while( start <= end ) {
			int mid = (start+end) / 2;

			int cnt = 0;
			//중앙값보다 작은 수 카운팅
			for(int i = 1; i <= n ; i++) {
				cnt += Math.min(mid/i,n);
			}


			//탐색범위가 더 크다면
			//k의 개수가 더 많다면
			if ( cnt < k ) {
				start = mid + 1;
			}else {
				result = mid;
				end = mid-1;
			}

		}
		System.out.println(result);



	}
}