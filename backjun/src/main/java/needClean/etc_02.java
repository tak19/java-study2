package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//게리맨더링
public class etc_02 {
	private static StringBuilder sb = new StringBuilder();
	static boolean isSelect;
	static int n;
	static int[] region;
	static Node[] list;
	static boolean[] visit;
	static ArrayList[] listArr;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); //지역구 수
		
		//지역구 인원 수 입력받음
		region = new int[n+1];
		list = new Node[n+1];
		visit = new boolean[n];
		listArr = new ArrayList[n];
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= n ; i++) {
			region[i] = Integer.parseInt(st.nextToken());
			listArr[i-1] = new ArrayList<Integer>(); //리스트로 해보자
		}
		//연결 지역구 확인
		for(int i = 1 ; i <= n ; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < size ; j++) {
				int near = Integer.parseInt(st.nextToken()); //연결 지역구
				list[i] = new Node(near,list[i]);
				list[near] = new Node(i,list[near]);
				
				
				listArr[i-1].add(near-1);
				listArr[near-1].add(i-1);
				
			}
		}
		
		//print(); //잘 연결됐나 확인했지
		subSet(0);
		
		System.out.println(sb);


	}
	//부분 집합
	private static void subSet(int cnt) {
		
		if(cnt == n) {
			int a = -1;
			int b = -1;
			//visit에 뽑은 원소의 인덱스 정보가 저장되어 있음
			for(int i = 1 ; i <= n ; i++) {
				if(visit[i-1]) { //선택된 지역하나 확인하해서 탐색함 -- 어차피 2개 구역으로만 구분됨
					a = i; //A지역구 대표 - true
				}else {
					b = i; //B지역구 대표 - false
				}
			}
			
			
			
			
			System.out.println();
			return;
		}
		//부분집합 진행
		visit[cnt] = true;
		subSet(cnt+1);
		visit[cnt] = false;
		subSet(cnt+1);
		
	}
	//현재 뽑은 구역이 연결 가능한지 확인한다!!
	private static void bfs(int num) {
		Queue<Integer> q = new ArrayDeque<>();
		for(Node temp = list[num]; temp != null ; temp = temp.node) {
			if( !visit[temp.near] ) {
				q.offer(temp.near);
				visit[temp.near] = true;
				System.out.print(temp.near+ " ");
			}
		}
		System.out.println();
		for(int i = 0 ; i < n ; i++) {
			if( visit[i] ) {
				System.out.print(i+ " ");
				
			}
		}
		System.out.println();
		
	}
	static void print() {
		for(Node node : list) { //각 정점 리스트의 헤드
			System.out.println(node);
		}
	}
	
	static class Node{
		int near;
		Node node;
		
		public Node(int near) {
			this.near = near;
		}
		
		public Node(int near, Node node) {
			this.near = near;
			this.node = node;
		}

		@Override
		public String toString() {
			return "Node [near=" + near + ", node=" + node + "]";
		}
		
		
	}
	

}
