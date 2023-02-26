package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_02 {
public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			int day = Integer.parseInt(br.readLine()); //일수
			
			int result = 0;
			for(int i = 0 ; i < day ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int max =  Math.max(c, Math.max(a, b) ); //최대값이데이
				
				if( max > 0 ) {
					result += max;
				}
			}
			sb.append(result).append("\n");
			
			
		}
		System.out.println(sb);
		
	}
}
