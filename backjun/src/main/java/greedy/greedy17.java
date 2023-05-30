package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class greedy17 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int len = Integer.parseInt(st.nextToken()); //테이블 길이
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		//구멍난 부분 확인하기
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		double min = 0;
		int result = 0;
		//테이프 생각하기
		for(int i = 0 ; i < n ; i++) {
			int em = arr[i];
			//막힌곳이 아니라면
			if( min < em ) {
				min = em + len - 0.5;
				result++;
			}
		}
		System.out.println(result);
		
	}
}