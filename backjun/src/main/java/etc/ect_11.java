package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ect_11 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList();
		for(int i = 1; i < n+1 ; i++) {
			q.add(i);
		}
		while ( q.size() != 1 ) {
			q.poll();
			q.add(q.poll());
		}
		
		System.out.println(q.peek());
	}
}
