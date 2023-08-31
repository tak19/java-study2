package binarysearch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class binary_05 {
	static StringBuilder sb = new StringBuilder();
	static long N,M,sum,result;
	static long[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		//나무 수와 필요미터
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		arr = new long[(int) N];
		//나무 길이 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			arr [i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);
		//양 끝값
		long left = 0;
		long right = arr[(int) (N-1)];
		//이분탐색
		while( left <= right ) {
			long mid = ( left + right ) / 2;
			sum = 0;
			for(long len : arr) {
				if( len > mid ) {
					sum += (len - mid);
				}
			}
			if( sum < M ) {
				right = mid -1;
			}else {
				left = mid + 1;
			}
		}
		System.out.println(right);
	}
}