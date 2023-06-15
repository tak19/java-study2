package string;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class string_05 {
	static int[] arr;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		//DNA 입력받기
		String[] dna = new String[n]; 
		for(int i = 0 ; i < n ; i++) {
			dna[i] = br.readLine();
		}
		
		
		
	}
}