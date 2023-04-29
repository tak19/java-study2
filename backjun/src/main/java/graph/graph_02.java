package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class graph_02 {
	static int N;
	static Node[] node;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for(int test = 0 ; test < T ; test++) {
			N = Integer.parseInt(br.readLine()); //노드수
			//그래프 정보를 입력받는다
			node = new Node[N+1];
			for(int i = 1 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken()); //부모
				int child = Integer.parseInt(st.nextToken()); //자식
				//부모쪽으로 올라가는 간선을 만든다.
				node[child] = new Node(parent, child, node[child]);
			}
			
			//탐색할 노드 입력받기
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			//방문 체크 배열
			boolean[] visit = new boolean[N+1];
			int to = 0;
			visit[v1] = true;
			for(Node tem = node[v1] ; tem != null ; tem = node[to]) {
				visit[tem.to] = true;
				to = tem.to;
			}
			//v1 경유 중 v2를 방문한 이력이 있다면 바로 종료!
			if( visit[v2] ) {
				sb.append(v2).append("\n");
				continue;
			}
			//아니라면 v2 를 탐색하면서 방문 이력이 있는 노드를 찾음
			to = 0;
			for(Node tem = node[v2] ; tem != null; tem = node[to]) {
				to = tem.to;
				if(visit[to]) {
					sb.append(to).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);
		

	}
	
	//정점을 나타냄
	static class Node{
		int to,me;
		Node node;
		
		public Node(int to, int me, Node node) {
			this.to = to;
			this.me = me;
			this.node = node;
		}

		
	}
}