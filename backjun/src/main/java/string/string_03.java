package string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
//9,876,543,210
public class string_03 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<String> pq = new PriorityQueue<String>( (o1,o2) ->{
			return o1.length() != o2.length() ? o1.length() - o2.length() : o1.compareTo(o2); 
		});
		Set<String> set = new HashSet<String>();
		for(int i = 0 ; i < n ; i++) {
			set.add(br.readLine());
		}
		for(String s : set) {
			pq.offer(s);
		}
		
		StringBuilder sb = new StringBuilder();
		while( !pq.isEmpty() ) {
			sb.append(pq.poll()).append("\n");
		}
		System.out.println(sb);
	}
}