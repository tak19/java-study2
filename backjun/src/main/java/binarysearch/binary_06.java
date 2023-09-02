package binarysearch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class binary_06 {
	static StringBuilder sb = new StringBuilder();
	static int K,N;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		//기존 랜선 개수 K 와 필요 랜선 N
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		long max = 0;
		//기존 랜선 입력 받기
		arr = new int[K];
		for(int i = 0 ; i < K ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		//0으로 나누는 경우 방지
		max++;
		long min = 0;
		long mid = 0;
		while( min < max ) {
			mid = (max + min) / 2;
			//길이를 충족하는지 확인하고 카운팅 증가
			long cnt = 0;
			for(int n : arr) {
				cnt += (n/mid);
			}
			if( cnt < N ) {
				max = mid;
			}else {
				min = mid + 1;
			}
		}
		
		System.out.println(min - 1);
		
	}
}