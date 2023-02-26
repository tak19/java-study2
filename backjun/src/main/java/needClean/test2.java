package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class test2 {
	static int cnt,n;
	static List<Integer>[] list;
	static boolean[] visit;
	static boolean ck;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int con = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n];
		for(int i = 0 ; i < n ; i++) {
			list[i] = new ArrayList<>();
		}
		
		
		for(int i = 0 ; i < con ; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			list[v1].add(v2);
			list[v2].add(v1);
		}		
		for(int i = 0 ; i < n ; i++) {
			visit = new boolean[n];
			cnt = 1;
			dfs(i,cnt);
			if( ck ) {
				break;
			}
		}
		if( ck ) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
		
	}

	private static void dfs(int node, int depth) {
		if( depth == 5 ) {
			ck = true;
			return;
		}
		//방문처리하고 연결요소 방문
		visit[node] = true;
		for( int v : list[node] ) {
			//연결노드중 방문하지 않았다면
			if( !visit[v] ) {
				dfs( v, (depth+1));
				visit[v] = false; // 다른 경로가 있을때를 위함
			}
		}
		
	}

	
}
