package lecture;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class lecture_03_1 {
	static int NODE_MAX = 5000;
	static boolean[] select;
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
	
		//TC실행
		for(int test = 1 ; test <= 10 ; test++) {
			LinkedList list = new LinkedList();
			sb.append("#").append(test).append(" ");
			//현재 암호문 길이
			N = Integer.parseInt(br.readLine());
			int[] initArr = new int[N];
			//암호문 추가함
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				initArr[i] = Integer.parseInt(st.nextToken());
			}
			//초기 명령
			list.insert(0, initArr);
			
			//명령어 횟수
			int comand = Integer.parseInt(br.readLine());
			//명령어 입력받음
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < comand; i++) {
				String s = st.nextToken();
				if( s.equals("I") ) {
					//삽입
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int[] temp = new int[y];
					for(int j = 0 ; j < y ; j++) {
						temp[j] = Integer.parseInt(st.nextToken());
					}
					list.insert(x, temp);
				}else if( s.equals("A") ) {
					//추가
					int y = Integer.parseInt(st.nextToken());
					for(int j = 0 ; j < y ; j++) {
						list.add(Integer.parseInt(st.nextToken()));
					}
				}else {
					//삭제
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					list.delete(x, y);
				}
			}
			list.print();
			sb.append("\n");
		}
		System.out.println(sb);
	}
	//정점 클래스 선언
	static class Node{
		int data;
		Node next;
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	static class LinkedList{
		Node head;
		Node tail;
		Node[] nodeArr;
		int nodeCnt;
		//링크드 리스크 초기화
		public LinkedList() {
			head = null;
			nodeArr = new Node[NODE_MAX];
			nodeCnt = 0;
		}
		// 새로운 노드 하나 추가
		Node getNewNode(int data) {
			nodeArr[nodeCnt] = new Node(data);
			return nodeArr[nodeCnt++];
		}
		//값 삽입 - 삽입할 인덱스와 데이터를 전달받음
		void insert(int idx, int[] nums) {
			int st = 0;
			if( idx == 0 ) { // 맨 앞 인덱스를 붙이는 경우
				//제일 앞에 첫번째 데이터를 우선 삽입한다.
				if( head != null ) {
					Node newNode = getNewNode(nums[0]);
					newNode.next = head;
					head = newNode;
				}else {
					//처음에 헤더가 없는경우 첫번쨰 데이를 바로 헤더로 선언하면 된다.
					head = getNewNode(nums[0]);
				}
				//남은 수 head 뒤에 차례로 이어 붙이기
				idx = 1;
				st = 1;
			}
			//중간에 이어 붙일 경우 -- 현재 커서를 삽입할 위치 전 노드까지 이동시킴
			Node cur = head;
			//이동 횟수만큼 이동
			for(int i =1 ; i < idx ; i++) {
				cur = cur.next;
			}
			//하나씩 붙여줌
			for(int i = st ; i < nums.length; i++) {
				Node newNode = getNewNode(nums[i]);
				newNode.next = cur.next;
				cur.next = newNode;
				cur = newNode;
			}
			if( cur.next == null ) {
				tail = cur;
			}
		}
		 // idx 번 인덱스부터 cnt 개 만큼 삭제하기
		void delete(int idx, int cnt) {
			Node cur = head;
			//맨 처음 노드 삭제라면 - 그냥 개수만큼 이동해서 헤더 위치만 변경
			if( idx == 0 ) {
				//해당 인덱스만큼 이동 - 삭제 시작 위치
				for(int i = 0 ; i < cnt; i++) {
					cur = cur.next;
				}
				head = cur;
				return;
			}
			//해당 인덱스만큼 이동 - 삭제 시작 전 위치 
			for(int i = 1 ; i < idx; i++) {
				cur = cur.next;
			}
			//삭제 끝 위치까지 또 이동
			Node anchor = cur;
			for(int i = 0 ; i < cnt; i++) {
				anchor = anchor.next;
			}
			//이동 후 해당 위치 이어주기
			cur.next = anchor.next;
			if( cur.next == null ) {
				tail = cur;
			}
			
		}
		//데이터 추가 - 가장 뒤에 붙임
		void add(int data) {
			Node cur = tail;
			Node newNode = getNewNode(data);
			cur.next = newNode;
			tail = newNode;
		}
		
		void print() throws Exception {  // 출력하기
            int cnt = 10;
            Node cur = head;
            while(--cnt >=0) {
                sb.append(cur.data +" ");
                cur = cur.next;
            }
        }
		
	}

}