package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sort_06 {
private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int fru = Integer.parseInt(st.nextToken());
		int len = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[fru];
		for(int i = 0 ; i < fru; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0 ; i < fru; i++) {
			if( arr[i] <= len ) {
				len++;
			}else {
				break;
			}
		}
		
		System.out.println(len);
		
		
	}
	
}

