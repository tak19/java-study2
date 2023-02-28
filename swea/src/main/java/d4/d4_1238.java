package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// SWEA - Contact(1238)
public class d4_1238 {
	static Node[] node;
	static boolean[] visit;
	static int[][] map;
	static int[] depth;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10; //테케 고정
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for(int test = 1 ; test <= 1 ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //간선 정보 수
			int start = Integer.parseInt(st.nextToken()); //시작 노드

			visit = new boolean[101];
			depth = new int[101];
			node = new Node[101];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < n / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				//연결 시켜줌
				node[from] = new Node(to,node[from]);
			}
			int result = 0;
			bfs(start);

			int maxDep = 0;
			for(int i = 1 ; i <= 100 ; i++) {
				if( maxDep <= depth[i] ) {
					maxDep = depth[i];
					if( result < i ) {
						result = i;
					}
				}
			}

			sb.append(result).append("\n");

		}
		System.out.println(sb);

	}
	//연결노드 탐색
	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		//시작 원소와 연결된 원소 큐에 넣음
		q.add(start);
		visit[start] = true;
		int current = 0;
		//다음 노드 탐색
		while( !q.isEmpty() ) {
			current = q.poll();
			//System.out.println(current);

			for(Node temp = node[current] ; temp != null ; temp = temp.node) {
				//방문한적없는 노드라면!!
				if( !visit[temp.x] ) {
					visit[temp.x] = true; //방문 표시
					q.offer(temp.x);
					depth[temp.x] = depth[current] + 1;
				}

			}

		}


	}
	static class Node{
		int x;
		Node node;

		public Node(int x, Node node) {
			this.x = x;
			this.node = node;
		}

	}
}