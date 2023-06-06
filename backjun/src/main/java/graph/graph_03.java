package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class graph_03 {
	static Node[] node;
	static boolean[] select;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 친구 수
		int m = Integer.parseInt(br.readLine()); // 관계 수
		
		node = new Node[n+1];
		select = new boolean[n+1];
		StringTokenizer st = null;
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//양방향 연결
			node[a] = new Node(b,node[a]);
			node[b] = new Node(a,node[b]);
		}
		// 상근이 학번 - 1번
		select[1] = true;
		dfs(1,0);
		System.out.println(result);
		
	}
	//탐색
	private static void dfs(int index, int cnt) {
		//친구 관계 초과시 탐색 안함
		if( cnt > 1 ) {
			return;
		}
		//상근이 탐색
		for(Node n = node[index] ; n != null ; n = n.node) {
			if( !select[n.x] ) {
				result++;
				select[n.x] = true;
			}
			//자기 자신이 아니라면
			if( n.x != 1 ) {
				dfs(n.x,cnt+1);
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