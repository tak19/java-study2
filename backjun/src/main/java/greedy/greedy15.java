package greedy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class greedy15 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int result = arr[n-1]; //들 수 있는 무게
		int cnt = 1;
		int sum = arr[n-1];
		for(int i = n-2 ; i >= 0 ; i--) {
			cnt++;
			sum += arr[i];
			if( result < arr[i] * cnt ) {
				result = arr[i] * cnt;
			}
			
		}
		System.out.println(result);
		
	}
}