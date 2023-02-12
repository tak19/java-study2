package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class binary_01 {
	static int[] arr;
	static int mid,find;
	static StringBuilder sb = new StringBuilder();
	static boolean ck;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];

		//n개수 입력받음
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		//m개 수 입력받음
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < m ; i++) {
			ck = false;
			find = Integer.parseInt(st.nextToken());
			BinarySearch(0,n-1);
			if(ck) {
				sb.append(1).append("\n");
			}else {
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}
	//이분 탐색 시작 --> 중간값과 찾는 값 비교
	private static void BinarySearch(int start, int end) {
		if( start <= end ) {
			mid = ( start + end ) / 2; //중간값
			if( arr[mid] < find ) {
				BinarySearch(mid+1, end);

			}else if( arr[mid] > find ) {
				BinarySearch(start, mid-1);

			}else if( arr[mid] == find ) {
				ck = true;
				return;
			}
		}else {
			return;
		}


	}
}
