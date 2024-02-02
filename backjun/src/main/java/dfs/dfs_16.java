package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//촌수 계산(2644)
public class dfs_16 {
	static StringBuilder sb = new StringBuilder();
	static int T, N, goal,result;
	static Node[] node;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 전체 인원 수 N명
		N = Integer.parseInt(br.readLine());

		// 내가 궁금한 사람
		st = new StringTokenizer(br.readLine());
		int p1 = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());
		
		node = new Node[N+1];
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 관계를 이어줌
			node[x] = new Node(node[x], y);
			node[y] = new Node(node[y], x);
		}
		result = Integer.MAX_VALUE;
		visit = new boolean[N+1];
		
		//방문 시작
		visit[p1] = true;
		dfs(p1, 0);
		
		result = (result == Integer.MAX_VALUE ? -1 : result);
		System.out.println(result);

	}
	// 깊이 우선탐색
	private static void dfs(int index, int cnt) {
		if( index == goal ) {
			result = Math.min(result, cnt);
			return;
		}
		
		// 노드를 탐색함
		for (Node n = node[index]; n != null; n = n.node) {
			//탐색하지 않았던 관계라면
			if(!visit[n.to]) {
				visit[n.to] = true;				
				dfs(n.to,cnt+1);
				visit[n.to] = false;
			}
		}
	}
	
	// 관계를 나타낼 클래스
	static class Node {
		Node node;
		int to;

		public Node(Node node, int to) {
			this.node = node;
			this.to = to;
		}
	}
}