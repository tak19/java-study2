package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class etc_06 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int total=0;
		
		//음수도 들어오는 구나...
		for(int i = 0 ; i < n ; i++) {
			int goal = arr[i];
			int start = 0;
			int end = n-1;
			//탐색 시작
			while(start < end) {
				//합 결과가 목저수보다 크면
				if(arr[start] + arr[end] == goal) {
					//같다면!!
					if( start != i && end != i) {
						total++;
						break;
					}else if( start == i){
						start++;
					}else if (end == i) {
						end--;
					}
				}else if(arr[start] + arr[end] < goal) {
					start++;
				}else {
					end--;
				}
			}
			
		}
		System.out.println(total);
	}

}
