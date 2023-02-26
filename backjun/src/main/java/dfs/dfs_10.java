package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//게리맨더링
public class dfs_10 {
	private static StringBuilder sb = new StringBuilder();
	static boolean isSelect;
	static int n,devideA,devideB,result;
	static int[] region;
	static Node[] list;
	static boolean[] visit;

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); //지역구 수

		//지역구 인원 수 입력받음
		region = new int[n+1];
		list = new Node[n+1];
		visit = new boolean[n];

		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= n ; i++) {
			region[i] = Integer.parseInt(st.nextToken());
		}
		int isolate = 0;
		int isolateIndex = -1;
		//연결 지역구 확인
		for(int i = 1 ; i <= n ; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			if( size == 0) {
				isolate++; //격리된 구역 확인
				isolateIndex = i;
			}
			for(int j = 0 ; j < size ; j++) {
				int near = Integer.parseInt(st.nextToken()); //연결 지역구
				list[i] = new Node(near,list[i]);
				list[near] = new Node(i,list[near]);


			}
		}
		result = Integer.MAX_VALUE;
		if(isolate == 2 && n == 2) { //분리된 지역이 1개라면
			int s = 0;
			for(int i = 1 ; i <= n ; i++) {
				s += region[i];
			}

			System.out.println(Math.abs(s - (region[isolateIndex] * 2)));

		}else if( isolate >= 2) { //분리된 지역이 2개 이상이라면 불가능해유
			System.out.println(-1); 

		}else if(isolate == 1) { //분리된 지역이 1개라면
			int s = 0;
			for(int i = 1 ; i <= n ; i++) {
				s += region[i];
			}
			System.out.println(Math.abs(s - (region[isolateIndex] * 2)));
		}
		else  {
			subSet(0);
			System.out.println(result);
		}
	}
	//부분 집합
	private static void subSet(int cnt) {

		if(cnt == n) {
			int a = -1;
			int b = -1;
			//현재 나뉜 지역구의 개수를 파악하기 위함
			int aSize = 0;
			int bSize = 0;
			//visit에 뽑은 원소의 인덱스 정보가 저장되어 있음
			for(int i = 1 ; i <= n ; i++) {
				if(visit[i-1]) { //선택된 지역하나 확인하해서 탐색함 -- 어차피 2개 구역으로만 구분됨
					a = i; //A지역구 대표 - true
					aSize++;
				}else {
					b = i; //B지역구 대표 - false
					bSize++;
				}
			}
			//연결된 지역구 확인하기 - DFS,BFS
			devideA = 0;
			devideB = 0;

			//부분집합에서 지역구 몰른 쪽은 배제한다
			if( a != -1 && b != -1) {

				dfsA(a, new boolean[n+1]); // DFS로 구할 수도 있지 
				dfsB(b, new boolean[n+1]); // 주석 해제 후 바로 사용가능

//				bfsA(a); // BFS로도 돌릴 수 있지
//				bfsB(b); // 주석 해제후 바로 사용

				//나눈지역가 연결되어 있다면
				if( devideA == aSize && devideB == bSize) {
					calMin();

				}
			}
			return;
		}
		//부분집합 진행
		visit[cnt] = true;
		subSet(cnt+1);
		visit[cnt] = false;
		subSet(cnt+1);

	}
	// 두 지역간의 차이 계산
	private static void calMin() {
		int suma = 0;
		int sumb = 0;
		//각 지역구 합 계산
		for(int i = 1 ; i <= n ; i++) {
			if(visit[i-1]) { //선택된 지역하나 확인하해서 탐색함 -- 어차피 2개 구역으로만 구분됨
				suma += region[i];
			}else {
				sumb += region[i];
			}
		}
		result = Math.min(result, Math.abs(suma-sumb));

	}
	private static void dfsA(int a, boolean[] bs) {
		devideA++;
		bs[a] = true;
		for(Node temp = list[a] ; temp != null ; temp = temp.node) {
			int current = temp.near;
			if( !bs[current] && visit[current-1] ) { //방문이력이,없으면서 A지역구인곳만 방문함
				dfsA(current,bs);
			}	
		}
	}
	private static void dfsB(int a, boolean[] bs) {
		devideB++;
		bs[a] = true;
		for(Node temp = list[a] ; temp != null ; temp = temp.node) {
			int current = temp.near;
			if( !bs[current] && !visit[current-1] ) { //방문이력이,없으면서 B지역구인곳만 방문함
				dfsB(current,bs);
			}	
		}
	}
	//현재 뽑은 구역이 연결 가능한지 확인한다!!
	private static void bfsA(int num) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visitA = new boolean[n+1];

		q.offer(num);
		visitA[num] = true;

		int currnt = 0;
		while(!q.isEmpty()) {
			devideA++;
			currnt = q.poll();

			for(Node temp = list[currnt]; temp != null ; temp = temp.node) {
				if( !visitA[temp.near] && visit[temp.near-1] ) {
					q.offer(temp.near);
					visitA[temp.near] = true;
				}
			}

		}
	}

	private static void bfsB(int num) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visitB = new boolean[n+1];

		q.offer(num);
		visitB[num] = true;

		int currnt = 0;
		while(!q.isEmpty()) {
			devideB++;
			currnt = q.poll();

			for(Node temp = list[currnt]; temp != null ; temp = temp.node) {
				if( !visitB[temp.near] && !visit[temp.near-1] ) {
					q.offer(temp.near);
					visitB[temp.near] = true;
				}
			}

		}
	}

	static class Node{
		int near;
		Node node;

		public Node(int near, Node node) {
			this.near = near;
			this.node = node;
		}	
	}

}
