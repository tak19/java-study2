package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class MST_01 {
	static int N,sum; //컴퓨터 수
	static int[] parent;
	static PriorityQueue<Node> pq;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		//내림차순 정렬해서 빼냄
		pq = new PriorityQueue<Node>( (o1,o2) -> {
			return o1.cost - o2.cost; 
		});
		//비용 입력받기
		for(int i = 0 ; i < N ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < N ; j++) {
				int tem = 0;
				//대 소문자에 따른 정수 처리함
				if(c[j] >= 'a' && c[j] <= 'z') {
					tem = c[j] - 'a' + 1;
				}
				else if(c[j] >= 'A' && c[j] <= 'Z'){
					tem = c[j] - 'A' + 27;
				}
				sum += tem;
				//자기 자신을 가리키는 경우와 값이 0이 아니라면 (즉 연결되어있지 않음) 그래프 노드에 추가
				if( i != j && tem != 0 ) {
					pq.add(new Node(i,j,tem));
				}
			}
		}
		//부모 배열 선언
		parent = new int[N];
		for(int i = 0 ; i < N ; i++) {
			parent[i] = i;
		}
		int useEdge = 0;
		int result = 0;
		while( !pq.isEmpty() ) {
			Node now = pq.poll();
			//같은 부모가 아니라면
			if( find(now.to) != find(now.from) ) {
				union(now.to,now.from);
				result += now.cost;
				useEdge++;
			}
		}
		if( useEdge == N - 1 ) {
			System.out.println(sum - result);
		}else {
			System.out.println(-1);
		}


	}
	//노드 합치기
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if( a != b ) {
			parent[b] = a;
		}
	}
	//find 연산 + 경로 압축
	private static int find(int a) {
		if( a == parent[a] ) {
			return a;
		}else {
			return parent[a] = find(parent[a]);
		}

	}
	//노드 저장
	static class Node{
		int to,from,cost;

		public Node(int to, int from, int cost) {
			this.to = to;
			this.from = from;
			this.cost = cost;
		}

	}
}