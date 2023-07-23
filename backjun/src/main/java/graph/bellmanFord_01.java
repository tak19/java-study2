package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bellmanFord_01 {
	static int N,M,K;
	static long[] distance;
	static Node[] node;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//도시 개수와 노선 수를 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		node = new Node[M];
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//연결정보 저장
			node[i] = new Node(a,b,c);
		}
		//거리값 최대로 초기화 후 자신의 거리값은 0으로 초기화
		distance = new long[N+1];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[1] = 0;
		
		//밸만포드 수행 - (N-1)번 반복수행
		for(int i = 1 ; i < N ; i++) {
			//모든 노드 리스트 방문
			for(int j = 0 ; j < M ; j++) {
				Node n = node[j];
				//현재 방문 노드의 값이 방문 가능하고 기존의 방문 경로보다 최단거리라면..
				if( distance[n.now] != Long.MAX_VALUE && distance[n.next] > distance[n.now] + n.value ) {
					distance[n.next] = distance[n.now] + n.value;
				}
			}
		}
		//음수 사이클 확인 작업 - 다시 한번 방문했을때 갱싱되는 값이 있다면 음수 싸이클이 존재하는것
		boolean ckCycle = false;
		for(int i = 0 ; i < M ; i++) {
			Node n = node[i];
			if( distance[n.now] != Long.MAX_VALUE && distance[n.next] > distance[n.now] + n.value ) {
				ckCycle = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		//음수 싸이클이 존재하지 않는다면
		if( !ckCycle ) {
			for(int i = 2 ; i <= N ; i++) {
				if( distance[i] != Long.MAX_VALUE ) {
					sb.append(distance[i]).append("\n");
				}else {
					sb.append(-1).append("\n");
				}
			}
		}else {
			sb.append(-1).append("\n");
		}
		System.out.println(sb);
	}
	//현재 노드번호와 다음노드 그리고 비용을 저장
	static class Node{
		int now,next,value;

		public Node(int now, int next, int value) {
			this.now = now;
			this.next = next;
			this.value = value;
		}

	}
}