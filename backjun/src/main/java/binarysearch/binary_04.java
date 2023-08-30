package binarysearch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class binary_04 {
	static StringBuilder sb = new StringBuilder();
	static int N,M,sum,result;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		//지방 에산요청 수
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		//지방 요청 금액 입력받기
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		//내림차순 정렬
		Arrays.sort(arr);
		int nowIndex = N-1;
		int left = 0;
		int right = arr[nowIndex];
		
		//이분 탐색 시작
		while( left <= right ) {
			//중간 인덱스
			int mid = (left + right) / 2;
			sum  = 0;
			//차이값을 뺐을때 합계보다 작아거나 같아진다면
			for(int m : arr) {
				if( m >= mid ) {
					sum += mid;
				}else {
					sum += m;
				}
			}
			if( sum > M ) {
				right = mid -1;
			}else {
				left = mid +1;
				result = Math.max(result, mid);
			}
			
		}
		System.out.println(result);
	}
}