package bfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_32 {
	static int N,M,L,X;
	static int[] visit;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//도시 개수, 도로 개수, 거리정보, 출발 도시
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		//도시 리스트 생성
		list = new ArrayList[N+1];
		for(int i = 1 ; i <= N ; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		//연결정보 입력
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			//방향 그래프로 한 방향에만 연결
			list[from].add(to);
		}
		// 방문 배열
		visit = new int[N+1];
		bfs(X);
		
		//정답 저장할 List
		List<Integer> aws = new ArrayList<Integer>();
		for(int i = 1; i <= N ; i++) {
			//처음 방문 노드를 1부터 시작했기 때문에 (L+1)이랑 맞는지 확인함
			if( visit[i] == (L+1) ) {
				aws.add(i);
			}
		}
		
		//정답 출력함
		StringBuilder sb = new StringBuilder();
		if( aws.isEmpty() ) {
			sb.append(-1);
		}else {
			Collections.sort(aws);
			for(Integer i : aws) {
				sb.append(i).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	//탐색함
	private static void bfs(int index) {
		visit[index]++;
		Queue<Integer> q = new LinkedList<>();
		q.offer(index);
		while( !q.isEmpty() ) {
			int nowIndex = q.poll();
			for( Integer nextNode : list[nowIndex]) {
				//방문하지 않았던 곳이라면
				if( visit[nextNode] == 0 ) {
					visit[nextNode] = visit[nowIndex] + 1;
					q.offer(nextNode);
				}
			}
		}
	}
}