package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class datastructure_22 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> hSet = new HashSet<>();
		for(int i = 0 ; i < N ; i++) {
			String s = br.readLine();
			hSet.add(s);
		}
		
		int result = 0;
		for(int i = 0 ; i < M ;i++) {
			String key = br.readLine();
			if( hSet.contains(key) ) {
				result++;
			}
		}
		System.out.println(result);
		
	}
}