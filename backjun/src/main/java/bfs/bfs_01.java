package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_01 {
	static boolean[] visit;
	static List<Integer>[] list;
	static int cnt;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 컴퓨터 수
		int m = Integer.parseInt(st.nextToken()); // 반복 수

		list = new ArrayList[n + 1];
		visit = new boolean[n+1];
		arr = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		// 연결 입력받음
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int fir = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[fir].add(end);
		}

		//탐색시작
		for (int i = 1; i <= n; i++) {
			bfs(i);
			for(int j=1; j<=n; j++)
			{
				visit[j]=false;
			}
		}
		int max = 0;

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n+1; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		for (int i = 1; i < n+1; i++) {
			if (max == arr[i]) {
				sb.append(i + " ");
			}
		}
		System.out.println(sb);
	}

	// 만약 방문한 노드중 하위 노드를 방문한다면 해당 노드는 방문할 필요없음.. ㅇㅈ?
	private static void bfs(int num) {

		Queue<Integer> q = new LinkedList<>();
        q.add(num);
		visit[num] = true;
		
		  while (!q.isEmpty()) {
	            int v = q.poll();
	            
	            for(int i : list[v]){
	                if(!visit[i]){
	                	visit[i] = true;
	                	arr[i]++;
	                	q.add(i);
	                    
	                }
	            }

	        }
	}
}
