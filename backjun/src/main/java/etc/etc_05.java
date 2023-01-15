package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class etc_05 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int start = 0;
		int end = n-1;
		int cnt = 0;
		
		while( start < end ) {
			int sum = arr[start] + arr[end];
			//같다면 양쪽 포인터 이동 후 개수 증가
			if( sum == m ) {
				cnt++;
				start++;
				end--;
			}else if (sum > m) { //합 값이 크다면 끝쪽 포인터를 한칸 내림
				end--;
			}else { //합이 자가면 시작 위치의 인덱스를 한카 올림
				start++;
			}
			
		}
		System.out.println(cnt);
		
		
	}

}
