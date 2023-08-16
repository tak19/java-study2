package dfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dfs_15 {
	static int N,delete,result;
	static ArrayList<Integer>[] list;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//노드 개수 입력받기
		N = Integer.parseInt(br.readLine());
		//노드 연결정보를 확인할 리스트 초기화
		list = new ArrayList[N];
		for(int i = 0 ; i < N ; i++) {
			list[i] = new ArrayList<Integer>();
		}
		int root = 0;
		//부모정보 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			int n = Integer.parseInt(st.nextToken());
			//부모노드가 있는지 확인
			if( n != -1 ){
				list[i].add(n);
				list[n].add(i);
			}else {
				root = i; //부모가 없으면 해당 노드는 루트 노드인것
			}
		}
		visit = new boolean[N];
		//삭제할 노드 입력받기
		delete = Integer.parseInt(br.readLine());
		//러트 삭제하는 경우를 예외처리
		if( delete == root ) {
			System.out.println(0);;
		}else {
			dfs(root);
			System.out.println(result);
		}
		
	}
	private static void dfs(int index) {
		visit[index] = true;
		int Node = 0;
		for(int i : list[index]) {
			if( !visit[i] && i != delete ) {
				Node++;
				dfs(i);
			}
		}
		//자식 노드가 아니면 리프노드
		if( Node == 0 ) {
			result++;
		}
		
	}
}