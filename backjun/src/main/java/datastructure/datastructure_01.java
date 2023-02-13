package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class datastructure_01 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> yo = new ArrayDeque<>();
		
		for(int i = 1 ; i <= n ; i++) {
			q.add(i);
		}
		
		while(q.size() > 0 ) {
			for(int i = 1 ; i < k ; i++) {
				int tem = q.poll();
				q.add(tem);
			}
			yo.add(q.poll());
		}
		
			
		StringBuilder sb = new StringBuilder();
		int size = yo.size();
		sb.append("<");
		
		for(int i = 0 ; i < size -1  ; i++) {
			sb.append(yo.poll()).append(", ");
		}
		sb.append(yo.poll());
		System.out.println(sb.append(">"));

	}
	
	
}
