package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Sort_15 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		//st = new StringTokenizer(br.readLine());
		
		String s = br.readLine();
		Integer[] arr = new Integer[s.length()];
		for(int i = 0 ; i < s.length() ; i++) {
			arr[i] = s.charAt(i) - '0';
		}
		Arrays.sort(arr,Collections.reverseOrder());
		for(Integer i : arr) {
			sb.append(i);
		}
		System.out.println(sb);
	}
}