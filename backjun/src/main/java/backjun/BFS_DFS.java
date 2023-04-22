package backjun;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_DFS { 
	static boolean[] visit1;
	static boolean[] visit2;
	static Queue<Integer> queue = new LinkedList<Integer>();
	public static void main(String[] args) {
		//list에 값이 없으면 null 주는것 같음
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //정점의 수
		int m = sc.nextInt(); //간선의 수
		int v = sc.nextInt(); //시작 번호
		queue.add(v);  //시작 노드 큐에 넣음
		
		//방문한 노드 확인할 불리언값
		visit1 = new boolean[n+1];
		visit2 = new boolean[n+1];

		//정점의 수 만큼 List 생성
		ArrayList<Integer>[] list = new ArrayList[n+1];
		for(int i = 0 ; i < list.length ; i ++) {
			list[i] = new ArrayList<>();
		}
		
		
		for(int i = 0 ; i < m ; i ++) {
			int start = sc.nextInt();
			int finish = sc.nextInt();
			//양방향 연결이기 때문에 서로 넣어줌
			list[start].add(finish);
			list[finish].add(start);
			
		}
		
		StringBuilder sb = new StringBuilder();

		
		//list 크기 만큼 반복... 만약 리스안에 값이 없다면 다음 리스트로 돌아감..
		for(int i = 0 ; i < list.length ; i ++) {
			//리스트에 값이 없다면 다음 노드의 간선을 확인함
			if(list[i].size() == 0) {
				continue;
			}
			list[i].sort(null);
//			for(int j = 0 ; j < list[i].size() ; j++) {
//				//System.out.print(list[i].get(j));
//				//현재리스트와 방문불리언, 시작번호 넘김
//			}
			
		}
		//정렬된 리스트를 가지고 방문시작
		dfs(list,visit1,v,sb);
		sb.append("\n");
		bfs(list,visit2,v,sb,queue);
		
		System.out.println(sb);
	}	
	
	
	private static void dfs(ArrayList<Integer>[] list, boolean[] visit1, int v, StringBuilder sb) {
		//방문했다면 
		if(visit1[v]) {
			return;
		}else {  //방문 x 노드라면
			//방문 표시 후 해당 노드 StringBuilder에 추가
			visit1[v] = true;
			sb.append(v + " ");
			
			//해당 노드의 크기만큼 반복함
			for(int i = 0 ; i < list[v].size(); i ++) {
				dfs(list,visit1,list[v].get(i),sb);
			}

		}

	}
	
	private static void bfs(ArrayList<Integer>[] list, boolean[] visit2, int v, StringBuilder sb, Queue<Integer> queue) {
		//방문했다면 
		if(visit2[v]) {
			//방문한 경우 해당 노드를 보내고 다시 실행 -- 큐에 공간 있는지 확인
			if(queue.size() > 1 ) {
				queue.poll();
				bfs(list,visit2,queue.peek(),sb,queue);
			}
			
		}else {  //방문 x 노드라면
			//방문 표시 후 해당 노드 StringBuilder에 추가
			visit2[v] = true;
			sb.append(v + " ");
			
			//해당 노드와 연결된 노드를 큐에 저장
			for(int i = 0 ; i < list[v].size(); i ++) {
				int qadd = list[v].get(i);
				//System.out.println(qadd);
				queue.add(qadd);
			}
			
			//헤드노드는 방문처리 후 poll -- size 확인
			if(queue.size() > 1) {
				//System.out.println(queue.poll());
				queue.poll();
				bfs(list,visit2,queue.peek(),sb,queue);
			}else {
				return;
			}
			

		}

	}
	


	
	
}



















