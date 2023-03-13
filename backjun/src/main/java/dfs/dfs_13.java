package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_13 {
	static int N,result;
	static int[] nodeWeight;
	static Node[] node;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //노드 수

		node = new Node[N+1]; //노드 생성 - 인덱스번호 그대로 활용

		StringTokenizer st = null;
		//n-1 개 간선
		for(int i = 1 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			//노드를 연결 시킨다 -- 무방향
			node[from] = new Node(to,weight,node[from]);
			node[to] = new Node(from,weight,node[to]);
		}
		//전체 가중치 합을 저장할 노드 가중치 배열
		nodeWeight = new int[N+1];
		visit = new boolean[N+1];
		bfs(1); //루트노드 1 먼저 방문

		int max = 0;
		int maxIndex=0;
		//최대 노드 찾기 --> 해당 노드의 인덱스값을 저장함
		for(int i = 1 ; i <= N ; i++) {
			//System.out.print(nodeWeight[i] + " ");
			if( nodeWeight[i] > max ) {
				maxIndex = i;
				max = nodeWeight[i];
			}
		}
		//가중치 값과 방문배열 초기화
		nodeWeight = new int[N+1];
		visit = new boolean[N+1];
		
		//끝 정점부터 다시 방문함 --> 해당 노드에서 가장 먼쪽이 트리으 지름이 됨
		bfs(maxIndex);
		
		
		System.out.println(result);
	}
	//노드 방문함
	private static void bfs(int index) {
		//방문한 노드라면 제외
		if( visit[index] ) {
			return;
		}else {
			//방문처리하고
			visit[index] = true;
			//인접 노드를 방문함
			for( Node tem = node[index] ; tem != null ; tem = tem.node ) {
				//방문하지 않았다면
				if( !visit[tem.to] ) {
					//가중치 합 -> 현재 노드의 값과 해당 노드로 갈때의 가중치를 더함
					nodeWeight[tem.to] = nodeWeight[index] + tem.weight;
					result = Math.max(result, nodeWeight[tem.to]);
					bfs(tem.to); //해당 노드 방문함
				}
			}
		}

	}

	//간선 정보를 저장
	static class Node{
		int to,weight;
		Node node;

		public Node(int to, int weight, Node node) {
			this.to = to;
			this.weight = weight;
			this.node = node;
		}

	}
}