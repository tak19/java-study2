package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class greedy19 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>( (o1,o2) -> {
			return o2 - o1;
		});
		for(int i = 0 ; i < n ; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		long result = 0;
		int cnt = 0;
		while( !pq.isEmpty() ) {
			if( pq.peek() - cnt <= 0 ) {
				break;
			}
			result += pq.poll() - cnt++;
		}
		System.out.println(result);
		
	}
}