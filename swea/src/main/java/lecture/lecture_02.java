package lecture;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class lecture_02 {
	static boolean[] select;
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		//TC실행
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			boolean ck = true;
			for(int i = 0 ; i < N ; i++) {
				if( (M & ( 1 << i )) == 0 ) {
					ck = false;
					sb.append("OFF").append("\n");
					break;
				}
			}
			if( ck ) {
				sb.append("ON").append("\n");
			}
		}
		System.out.println(sb);
	}

}