package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_21 {
	static List<Pos>[] list;
	static boolean[] visit;
	static int maxDis,result;
	static int[] disOne;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//트리 정보를 담을 List 선언
		list = new ArrayList[n+1];
		for(int i = 1 ; i <= n ; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for(int i = 1 ; i <= n ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());

			while(true) {
				int link = Integer.parseInt(st.nextToken());
				if(link == -1) {
					break;
				}
				int dis = Integer.parseInt(st.nextToken());
				list[start].add(new Pos(link,dis)); 
			}

		}
		//트리의 지름 중 한점을 찾는 과정
		disOne = new int[n+1];
		visit = new boolean[n+1];
		bfs(1);
		
		maxDis = 1;
		for(int i = 2 ; i <= n ; i++) {
			if( disOne[maxDis] < disOne[i]) {
				maxDis = i;
			}
		}
		//한점을 찾았으면 나머지 점을 찾음 -- > bfs 돌려서 최대값이 지름 노드임
		disOne = new int[n+1];
		visit = new boolean[n+1];
		bfs(maxDis);
		Arrays.sort(disOne);

		System.out.println(disOne[n]);



	}
	private static void bfs(int now) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(now);
		visit[now] = true;

		while( !q.isEmpty() ) {
			int now_node = q.poll();
			for( Pos pos : list[now_node] ) {
				int next_node = pos.v;
				int next_dis = pos.dis;

				if( !visit[next_node] ) {
					visit[next_node] = true;
					q.add(next_node);
					disOne[next_node] = disOne[now_node] + next_dis;
				}	
			}
		}
	}

	static class Pos{
		int v;
		int dis;
		Pos(int v, int dis){
			this.v = v;
			this.dis = dis;
		}
	}
}
