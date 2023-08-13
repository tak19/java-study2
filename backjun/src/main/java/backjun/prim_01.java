package backjun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prim_01 {
	static int V,E;
	static Vertex[] vertex;
	static int[] parent;
	static PriorityQueue<Vertex> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//정점과 전선 수 입력 받음
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		//유니온 파인드 부모 배열
		parent = new int[V+1];
		for(int i = 0 ; i <+ V ; i++) {
			parent[i] = i;
		}
		vertex = new Vertex[V+1];
		pq = new PriorityQueue<>();
		//그래프 정보 입력받음
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			//무향 그래프로 연결
			pq.add(new Vertex(to, weight, from));
		}
		
		int useEdge = 0;
		int result = 0;
		//최소 비용 간선 저장
		while( useEdge < V-1 ) {
			Vertex now = pq.poll();
			if( find(now.to) != find(now.vertex) ) {
				union(now.to,now.vertex);
				result += now.weight;
				useEdge++;
			}
		}
		System.out.println(result);

	}
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if( a != b ) {
			parent[b] = a;
		}
	}
	private static int find(int a) {
		if( a == parent[a] )
			return a;
		else
			return parent[a] = find(parent[a]);
	}
	//정점 클래스
	static class Vertex implements Comparable<Vertex>{
		int to;
		long weight;
		int vertex;
		
		public Vertex(int to, long weight, int vertex) {
			this.to = to;
			this.weight = weight;
			this.vertex = vertex;
		}
		//내림차순 정렬
		@Override
		public int compareTo(Vertex o) {
			return Long.compare(weight, o.weight);
		}
		
	}
}