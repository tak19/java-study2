package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prim_01 {
	static int V,E;
	static Vertex[] vertex;
	static long result;
	static long[] minWeight;
	static boolean[] visit;
	static PriorityQueue<Vertex> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//정점과 전선 수 입력 받음
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		vertex = new Vertex[V+1];
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			//무향 그래프로 연결
			vertex[from] = new Vertex(to, weight, vertex[from]);
			vertex[to] = new Vertex(from, weight, vertex[to]);
			
		}
		//최소 비용 간선 저장
		minWeight = new long[V+1];
		Arrays.fill(minWeight, Long.MAX_VALUE);
		
		pq = new PriorityQueue<>();
		//1번정점에서 임의로 시작함
		visit = new boolean[V+1];
		visit[1] = true;
		minWeight[1] = 0;
		//1번과 연결된 간선 PQ에 넣음
		for(Vertex v = vertex[1] ; v != null ; v = v.vertex) {
			pq.offer(v);
		}
		
		int cnt = 1; //뽑은 정점 수
		//하나씩 탐핵함
		while( !pq.isEmpty() ) {
			Vertex v = pq.poll(); //가중치가 가장 작은놈 우선 뺌
			//이미 방문한 정점이라면 
			if( visit[v.to] ) { 
				continue;
			}
			//방문 x 정점이라면
			//해당 정점 방문처리하고 가중치 더함
			visit[v.to] = true;
			result += v.weight; 
			
			//(선택사항) 가지치기
			if( ++cnt == V ) {
				break;
			}
			
			//pq에 넣어야 할것 같은데 -> 방문안한 놈 넣어줌
			for(Vertex tem = vertex[v.to] ; tem != null ; tem = tem.vertex) {
				if( !visit[tem.to] && minWeight[tem.to] > tem.weight ) {
					minWeight[tem.to] = tem.weight;
					pq.offer(tem);
				}
			}
			
			
		}
		
		System.out.println(result);

	}
	//정점 클래스
	static class Vertex implements Comparable<Vertex>{
		int to;
		long weight;
		Vertex vertex;
		
		public Vertex(int to, long weight, Vertex vertex) {
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