package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class graph_05 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static ArrayList<Integer>[] tree;
	static int[] parent;
	static int[] depth;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		//노드 수
		N = Integer.parseInt(br.readLine());
		//연결관계 초기화
		tree = new ArrayList[N+1];
		for(int i = 1 ; i <= N ; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		//연결 정보 입력
		for(int i = 0 ; i < N - 1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}

		depth = new int[N+1];
		parent = new int[N+1];
		visit = new boolean[N+1];
		bfs(1);
		
		//값 구함
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(LCA(a,b)).append("\n");
		}
		System.out.println(sb);
		
	}
	private static int LCA(int a, int b) {
		if( depth[a] < depth[b] ) {
			int tem = a;
			a = b;
			b = tem;
		}
		while( depth[a] != depth[b] ) {
			a = parent[a];
		}
		while( a != b ) {
			a = parent[a];
			b = parent[b];
		}
		return a;
	}
	//깊이 정보 저장
	private static void bfs(int i) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(i);
		visit[i] = true;
		int level = 1;
		int nowSize = 1;
		int cnt = 0;
		//단계만큼만 큐를 진행
		while( !q.isEmpty() ) {
			int nowNode = q.poll();
			for(Integer next : tree[nowNode] ) {
				if( !visit[next] ) {
					visit[next] = true;
					q.add(next);
					parent[next] = nowNode;
					depth[next] = level;
				}
			}
			cnt++;
			if( cnt == nowSize ) {
				cnt = 0;
				nowSize = q.size();
				level++;
			}
		}
		
	}
}

