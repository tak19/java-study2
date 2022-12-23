package backjun;

import java.util.ArrayList;
import java.util.Scanner;

public class DFSTest {
	static boolean[] visit;
	public static void main(String[] args) {
		//list에 값이 없으면 null 주는것 같음
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //정점의 수
		int m = sc.nextInt(); //간선의 수
		int v = sc.nextInt(); //시작 번호
		
		//방문한 노드 확인할 불리언값
		visit = new boolean[n+1];
		
		//정점의 수 만큼 List 생성
		ArrayList<Integer>[] list = new ArrayList[n];
		for(int i = 0 ; i < list.length ; i ++) {
			list[i] = new ArrayList<>();
		}
		
		
		for(int i = 0 ; i < m ; i ++) {
			int start = sc.nextInt();
			int finish = sc.nextInt();
			list[start].add(finish);
		}
		
		
		for(int i = 0 ; i < list.length ; i ++) {
			//리스트에 값이 없다면 다음 노드의 간선을 확인함
			if(list[i].size() == 0) {
				continue;
			}
			list[i].sort(null);
			for(int j = 0 ; j < list[i].size() ; j++) {
				//System.out.print(list[i].get(j));
				//현재리스트와 방문불리언, 시작번호 넘김
				dfs(list[i],visit,v);
			}
		}
		//System.out.println();
	}
	
	private static void dfs(ArrayList<Integer> list, boolean[] visit,int v) {
		//방문했다면 
		if(visit[v]) {
			return;
		}else {  //방문 x 노드라면
			visit[v] = true;
//			while( list[v] ) {
//				
//			}
		}
		

	}

	
	
}





















