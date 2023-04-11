package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class etc_26 {
	static int N;
	static int[] input;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		for(int i = 0 ; i < N ; i++) {
			input[i] = i+1;
		}
		StringBuilder sb = new StringBuilder();
		do {
			//sb.append(Arrays.toString(input)).append("\n");
			for(int i = 0 ; i < N ; i++) {
				sb.append(input[i]).append(" ");
			}
			sb.append("\n");
		}while(np(input));
		System.out.println(sb);
	} 
	private static boolean np(int[] arr) {
		int n = arr.length-1;
		int i = n;
		while( i > 0 && arr[i] < arr[i-1] ) {
			i--;
		}
		if( i == 0 ) {
			return false;
		}
		
		int j = n;
		while( arr[i-1] > arr[j] ) {
			j--;
		}
		swap(i-1,j,arr);
		
		int k = n;
		while( i < k ) {
			swap(i++, k--, arr);
		}
		
		return true;
	}
	private static void swap(int i, int j, int[] arr) {
		int tem = arr[i];
		arr[i] = arr[j];
		arr[j] = tem;
	}
}
