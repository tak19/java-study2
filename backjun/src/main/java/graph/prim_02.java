package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prim_02 {
	private static StringBuilder sb = new StringBuilder();
	static int V,E;
	static boolean[] visit;
	static Vertex[] vertex;

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		V = Integer.parseInt(br.readLine()); //정점 수
		E = Integer.parseInt(br.readLine()); //간선 수

		vertex = new Vertex[V+1];
		StringTokenizer st = null;
		for(int i = 0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			//양방향 연결됨
			vertex[from] = new Vertex(to, weight, vertex[from]);
			vertex[to] = new Vertex(from, weight, vertex[to]);
		}
		//방문배열과 인접 간선 값 저장
		visit = new boolean[V+1];
		int[] minEdge = new int[V+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);

		long result = 0;
		minEdge[1] = 0; // 첫번째 정점부터 방문할꺼임.
		visit[1] = true;
		int cnt = 1; //뽑은 정점 수

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		for( Vertex tem = vertex[1] ; tem != null; tem = tem.vertex ) {
			pq.offer(tem);
		}
		//방문 시작
		while( !pq.isEmpty() ) {

			Vertex minVertex = pq.poll();
			//방문했다면 제외
			if( visit[minVertex.to] ) {
				continue;
			}
			//방문처리하고 비용 누적
			visit[minVertex.to] = true;
			result += minVertex.weight;

			if( ++cnt == V ) {
				break;
			}
			//연결 요소 찾기
			for( Vertex tem = vertex[minVertex.to] ; tem != null; tem = tem.vertex ) {
				//방문 x 면서 최소 간선이 나온다면
				if( !visit[tem.to] && minEdge[tem.to] > tem.weight ) {
					minEdge[tem.to] = tem.weight;
					pq.offer(tem);
				}
			}

		}


		System.out.println(result);


	}
	//Prim
	static class Vertex implements Comparable<Vertex>{
		int to,weight;
		Vertex vertex;

		public Vertex(int to, int weight, Vertex vertex) {
			this.to = to;
			this.weight = weight;
			this.vertex = vertex;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}

	}

}

