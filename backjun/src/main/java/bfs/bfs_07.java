package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_07 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<Integer>[] list = new ArrayList[n+1];
		for(int i = 0; i < n+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		StringTokenizer st;
		for(int i = 1 ; i < n; i++) {
			 st = new StringTokenizer(br.readLine());
			 int a = Integer.parseInt(st.nextToken());
			 int b = Integer.parseInt(st.nextToken());
			 //각 노드 연결함!
			 list[a].add(b);
			 list[b].add(a);
		}
		
		boolean[] visit = new boolean[n+1];
		int[] arr = new int[n+1];
		//루트노드는 따로 방문처리함
		visit[1] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		//루트 노드 부터 방문 시작함!
		while(!q.isEmpty()) {
			int node = q.poll();
			//노드의 자식 방문함
			for(int child : list[node]) {
				if( !visit[child] ) { //자식 중 방문 x라면
					visit[child] = true;
					arr[child] = node;
					q.add(child);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 2 ; i < n+1; i ++) {
			sb.append(arr[i] + "\n");
		}
		System.out.println(sb);
		
		
		
		
	}
}
