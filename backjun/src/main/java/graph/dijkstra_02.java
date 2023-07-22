package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dijkstra_02 {
	static int N,M,K;
	static List<Node>[] list;
	static PriorityQueue<Integer>[] distance;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//도시 개수와 도로 수 K 값을 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//연결요소를 저장할 List 배열 초기화
		list = new ArrayList[N+1];
		for(int i = 0 ; i <= N ; i++) {
			list[i] = new ArrayList<Node>();
		}
		//관계를 입력받음
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//a->b로 가는데 c 비용이 들고 횟수는 1 이라고 생각
			list[a].add(new Node(b,c));
		}
		
		//거리값이 큰것부터 뽑히게끔한다.
		distance = new PriorityQueue[N+1];
		for(int i = 0 ; i <= N ; i++) {
			distance[i] = new PriorityQueue<Integer>( (o1,o2) -> {
				return o2 - o1;
			});
		}

		dijkstra();
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= N ; i++) {
			if( distance[i].size() == K ) {
				sb.append(distance[i].peek()).append("\n");
			}else {
				sb.append(-1).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	//다익스트라
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>( (o1,o2) -> {
			return o1.value - o2.value;
		});
		pq.offer(new Node(1,0));
		distance[1].add(0);

		//큐가 빌때까지
		while( !pq.isEmpty() ) {
			Node node = pq.poll();
			//현재 방문 인덱스
			int now = node.next;
			for( Node n : list[now] ) {
				//K보다 경로 도착 횟수가 작다면 그냥 값을 넣는다
				if( distance[n.next].size() < K ) {
					distance[n.next].offer( node.value + n.value );
					pq.offer(new Node( n.next,node.value + n.value ));
				}
				//K개를 초과 했지만 현재 방문한 경로의 비용이 더 작은 경우 값을 넣어줌
				else if( distance[n.next].peek() > node.value + n.value ) {
					distance[n.next].poll(); //기존 경로에 있는 가장 큰 값을 제외시킴
					distance[n.next].offer( node.value + n.value );
					pq.offer(new Node( n.next,node.value + n.value ));
				}
			}
		}
	}
	//다음 노드, 비용, 수행횟수를 저장함
	static class Node{
		int next,value;

		public Node(int next, int value) {
			this.next = next;
			this.value = value;
		}
		
	}
}