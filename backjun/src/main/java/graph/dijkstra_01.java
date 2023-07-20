package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dijkstra_01 {
	static int N,M;
	static List<Node>[] list;
	static int[] distance;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//전체 도시 수와 버스의 개수
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		//각 요소를 연결할 리스트
		list = new ArrayList[N+1];
		for(int i = 0 ; i <= N ; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		StringTokenizer st = null;
		//노선 입력받음
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			//요소들 연결
			list[to].add(new Node(from,value));
		}
		
		//시작과 끝점 입력받음
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		//거리값을 저장할 배열과 방문 배열 체크
		distance = new int[N+1];
		visit = new boolean[N+1];
		
		//거리값 최대로 초기화
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		System.out.println(dijkstra(start,end));


	}
	private static int dijkstra(int start, int end) {
		//비용이 낮은거 먼저 출력함
		PriorityQueue<Node> pq = new PriorityQueue<Node>( (o1,o2) -> {
			return o1.cost - o2.cost;
		});
		//시작점을 넣어주고 거리비용 0으로 만든다
		pq.add(new Node(start,0));
		distance[start] = 0;
		//큐가 빌때까지
		while( !pq.isEmpty() ) {
			//가장 낮은 비용 노드 하나 뽑아온다
			Node node = pq.poll();
			int now = node.next;
			//해당 노드 방문하지 않았다면
			if( !visit[now] ) {
				visit[now] = true;
				for( Node n : list[now] ) {
					//연결된 노드중에 방문하지 않았고, 기존거리보다 짧다면
					if( !visit[n.next]  && distance[n.next] > distance[now] + n.cost ) {
						distance[n.next] = distance[now] + n.cost;
						pq.offer(new Node(n.next,distance[n.next]));
					}
				}
			}
		}
		return distance[end];
	}
	//연결 관계 표시
	static class Node{
		int next,cost;

		public Node(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
		
	}
}