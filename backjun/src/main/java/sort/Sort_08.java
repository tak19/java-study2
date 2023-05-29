package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sort_08 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>( (o1,o2) -> {
			return o1.age == o2.age ? o1.num - o2.num : o1.age - o2.age;
		});
		int index = 0;
		StringTokenizer st = null;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Pos(Integer.parseInt(st.nextToken()),index++,st.nextToken()));
		}
		StringBuilder sb = new StringBuilder();
		while( !pq.isEmpty() ) {
			Pos p = pq.poll();
			sb.append(p.age).append(" ").append(p.name).append("\n");
		}
		System.out.println(sb);
		
	}
	static class Pos{
		int age,num;
		String name;
		public Pos(int age,int num, String name) {
			this.age = age;
			this.num = num;
			this.name = name;
		}
		
	}
}