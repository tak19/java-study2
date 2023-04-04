package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_5643 {
	static int N,M,now,result;
	static Node[] node;
	static boolean[][] connect;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			//사람 수와 관계수 
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());


			//관계를 입력
			node = new Node[N+1];
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				node[from] = new Node(to,node[from]);		
			}

			connect = new boolean[N+1][N+1];
			for(int i = 1 ; i <= N ; i++) {
				now = i;
				dfs(i);
			}
			result = 0;
			//print();
			for(int i = 1 ; i <= N ; i++) {
				int cnt = 0;
				for(int j = 1 ; j <= N ; j++) {
					if( i != j ) {
						if( connect[i][j] || connect[j][i] ) {
							cnt++;
						}
					}
				}
				if( cnt == (N-1) ) {
					result++;
				}
			}
			
			sb.append(result).append("\n");

		}
		System.out.println(sb);
	}
	private static void dfs(int current) {
		
		for( Node temp = node[current]; temp != null ; temp = temp.node ) {
			if ( !connect[now][temp.from] ) {
				connect[now][temp.from] = true;
				dfs(temp.from);
			}
		}
	}
	private static void print() {
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				System.out.print(connect[i][j] + "\t");				
			}
			System.out.println();
		}
	}
	// 관계를 저장
	static class Node{
		int from;
		Node node;

		public Node(int from, Node node) {
			this.from = from;
			this.node = node;
		}

	}
}