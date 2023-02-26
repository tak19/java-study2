package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_7465 {
	static int n,m;
	static Node[] node;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 사람 수
			m = Integer.parseInt(st.nextToken()); // 관계 수

			node = new Node[n+1]; //인덱스 번호를 그대로 사용하기 위함
			//관계 입력받는다!
			for(int i = 0 ; i < m ; i++) {
				st = new StringTokenizer(br.readLine());
				// 서로 알고 있는 사람 입력 받았쥬~
				int peo1 = Integer.parseInt(st.nextToken());
				int peo2 = Integer.parseInt(st.nextToken());
				// 서로 알고 있는 사이이기 때문에 양방향으로다가 빡
				node[peo1] = new Node(peo2,node[peo1]);
				node[peo2] = new Node(peo1,node[peo2]);
			}

			int result = 0; //관계수 저장해유
			//관계수 파악하자
			visit = new boolean[n+1];
			for(int i = 1 ; i <= n ; i++) {
				if( !visit[i] ) { //방문 x 라면
					result++;
					dfs(i);
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);

	}
	//관계 파악해
	private static void dfs(int i) {
		visit[i] = true; //방문처리해유
		for( Node tem = node[i] ; tem != null ; tem = tem.node ) {
			int cur = tem.v;
			if( !visit[cur] ) {
				dfs(cur);
			}
		}
		
		
	}
	
	//열결관계
	static class Node{
		int v;
		Node node;
		
		public Node(int v, Node node) {
			this.v = v;
			this.node = node;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", node=" + node + "]";
		}
		
	}
}
