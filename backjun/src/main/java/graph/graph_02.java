package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 여행경로가 이어져있는지만 확인하면 될듯 아무 정점 시작해서
 */
public class graph_02 {
	static int N,M,now;
	static Node[] node;
	static int[] list;
	static boolean[][] connect;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine()); //관계수
		M = Integer.parseInt(br.readLine()); //여행 포함 수

		node = new Node[N+1];
		connect = new boolean[N+1][N+1];
		//관계를 입력
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; j++) {
				int from = Integer.parseInt(st.nextToken());
				//연결되어 있다면
				if( from != 0 ) {
					node[i] = new Node(j,node[i]);
					node[j] = new Node(i,node[j]);
				}
				if( i == j ) {
					connect[i][j] = true;
				}
			}
		}
		
		for(int i = 1 ; i <= N ; i++) {
			now = i;
			dfs(i);
		}
		if( M != 1) {
			
		}
		st = new StringTokenizer(br.readLine());
		list = new int [M+1];
		for(int i = 1 ; i <= M; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		//print();
		
		String result = "YES";
		for(int i = 2 ; i <= M; i++) {
			if( !connect[list[1]][list[i]] ) {
				result = "NO";
			}
		}
		
		System.out.println(result);
	}
	
	private static void print() {
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				System.out.print(connect[i][j] + "\t");				
			}
			System.out.println();
		}
	}
	
	private static void dfs(int current) {
		for( Node temp = node[current]; temp != null ; temp = temp.node ) {
			if ( !connect[now][temp.from] ) {
				connect[now][temp.from] = true;
				dfs(temp.from);
			}
		}
	}
	//순서 확인함
	private static boolean ck(int from) {
		//뒷순서가 먼저 나오면 안된다!!
		for(int i = (now+1) ; i <= M ; i++) {
			if( list[i] == from ) {
				return true;
			}
		}
		return false;
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