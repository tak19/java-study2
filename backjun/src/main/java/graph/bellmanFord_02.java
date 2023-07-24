package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bellmanFord_02 {
	static int N,M,start,end;
	static long[] distance,cost;
	static Node[] node;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//도시 개수와 시작-도착 도시 그리고 이동 수단 수를 입력받음
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//노선의 개수만큼의 배열을 만든다.
		node = new Node[M];
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//노드 배열 추가
			node[i] = new Node(a,b,c);
		}
		//비용 입력받기
		cost = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		//거리배열 초기화 - 시작 위치의 값을 입력받은 값을 초기화
		distance = new long[N];
		Arrays.fill(distance, Long.MIN_VALUE);
		distance[start] = cost[start];
		
		//드는 비용에서 이득을 빼서 비용의 최소값을 구함 -> 해당 값의 부호를 변환하면 정답일 것 같음
		for(int i = 0 ; i <= N + 100 ; i++) {
			//노선 수 만큼 반복
			for(int j = 0 ; j < M ; j++) {
				Node n = node[j];

				//방문하지 않은 노드라면 제외
				if( distance[n.now] == Long.MIN_VALUE ) {
					continue;
				}else if( distance[n.now] == Long.MAX_VALUE ) {
					distance[n.next] = Long.MAX_VALUE;
				}else if( distance[n.next] < (distance[n.now] + cost[n.next] - n.value) ) {
					//더 많은 돈을 벌 수 있다면 값 업데이트
					distance[n.next] = (distance[n.now] + cost[n.next] - n.value);
					//반복 후 업데이트되는 종료 노드는 양수 사이클 연결 노드로 변경
					if( i >= (N-1) ) {
						distance[n.next] = Long.MAX_VALUE;
					}
				}
			}
		}
		
		if( distance[end] == Long.MIN_VALUE ) {
			System.out.println("gg");
		}else if( distance[end] == Long.MAX_VALUE ) {
			System.out.println("Gee");
		}else {
			System.out.println(distance[end]);
		}
		
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