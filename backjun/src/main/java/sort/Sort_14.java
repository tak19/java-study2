package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sort_14 {
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//전체 도시 수와 도로의 개수
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		//각 연결 요소를 저장할 ArrayList를 만든다.
		List<ArrayList<Node>> list = new ArrayList<>();
		List<ArrayList<Node>> rList = new ArrayList<>();
		for(int i = 0 ; i <= N ; i++) {
			list.add(new ArrayList<>());
			rList.add(new ArrayList<>()); // 관계의 역순을 저장
		}

		//건물 수 만큼의 배열 선언
		int[] connect = new int[N+1];
		StringTokenizer st = null;

		int to = 0;
		int from = 0;
		int value = 0;
		// 각 건물의 관계를 입려받는다.
		for(int i = 1 ; i <= M ; i++) {
			// 도로의 선,후 관계와 시간을 입력받음
			st = new StringTokenizer(br.readLine());
			to = Integer.parseInt(st.nextToken());
			from = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			//관계 리스트에 추가
			list.get(to).add(new Node(from,value));
			rList.get(from).add(new Node(to,value));
			connect[from]++;

		}
		//시작과 끝노드 입력
		st = new StringTokenizer(br.readLine());
		int startN = Integer.parseInt(st.nextToken());
		int endN = Integer.parseInt(st.nextToken());
		
		//큐 생성하고 시작 노드 삽입
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(startN);

		//결과를 저장할 배열 선언
		int[] result = new int[N+1];

		//모든 요소 방문할때까지
		while( !q.isEmpty() ) {
			int visit = q.poll();
			//해당 인덱스와 연결되어있는 노드 방문
			for( Node node : list.get(visit) ) {
				connect[node.next]--;
				//결과값에   현재 결과  vs  (이전 대기시간 + 현재 걸린 시간) 중 최대값을 삽입
				result[node.next] = Math.max(result[node.next], result[visit] + node.value);
				//진입 차수가 0 이라면
				if( connect[node.next] == 0 ) {
					q.add(node.next);
				}
			}
		}
		
		//끝점부터 탐색시작
		int resultCnt = 0;
		boolean[] visited = new boolean[N+1];
		q = new ArrayDeque<>();
		q.offer(endN);
		visited[endN] = true;
		//역순 큐 빌때까지
		while( !q.isEmpty() ) {
			int visit = q.poll();
			for( Node node : rList.get(visit) ) {
				if( result[node.next] + node.value == result[visit] ) {
					resultCnt++;
					if( visited[node.next] == false ) {
						visited[node.next] = true;
						q.offer(node.next);
					}
				}
			}
		}

		System.out.println(result[endN]);
		System.out.println(resultCnt);

	}
	//각 노드를 저장할 클래스
	static class Node{
		int next,value;

		public Node(int next, int value) {
			this.next = next;
			this.value = value;
		}
	}
}