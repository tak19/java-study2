package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sort_11 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n  = Integer.parseInt(br.readLine());
		
		//좌표 우선순위 x 내림차순 같다면 y 내림차순
		PriorityQueue<Pos> pq = new PriorityQueue<>( (o1,o2) -> {
			return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y; 
		});
		
		StringTokenizer st = null;
		int x = 0;
		int y = 0;
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			pq.offer(new Pos(x,y));
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i++) {
			Pos pos = pq.poll();
			x = pos.x;
			y = pos.y;
			sb.append(x).append(" ").append(y).append("\n");
		}
		System.out.println(sb);
	}
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}