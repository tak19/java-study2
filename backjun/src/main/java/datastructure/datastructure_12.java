package datastructure;
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

public class datastructure_12 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		//TC실행
		for(int test = 0 ; test < T ; test++) {
			Set s = new HashSet<Integer>();
			//수접1
			int a = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < a ; j++) {
				s.add(Integer.parseInt(st.nextToken()));
			}
			
			//수첩2
			int b = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < b ; j++) {
				if(s.contains(Integer.parseInt(st.nextToken()))) {
					sb.append(1).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}
			
		}
		
		System.out.println(sb);
		
	}
}