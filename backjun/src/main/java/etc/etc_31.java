package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class etc_31 {
	static int N,M,T,result;
	static ArrayList<Integer>[] list;
	static int[] parent;
	static int[] trueP;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//사람 수, 파티수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		//진실을 알고있는 사람 저장
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		result = 0;
		trueP = new int[T];
		for(int i = 0 ; i < T ; i++) {
			trueP[i] = Integer.parseInt(st.nextToken());
		}
		
		//파티 상황 입력받기
		list = new ArrayList[M];
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = new ArrayList<>();
			int partySize = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < partySize; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		//각 부모 자신으로 설정하기
		parent = new int[N+1];
		for(int i = 0 ; i <= N ; i++) {
			parent[i] = i;
		}
		
		// 각 파티에 대해 유니온 연산
		for(int i = 0 ; i < M ; i++) {
			int firstPeople = list[i].get(0);
			for(int j = 1; j < list[i].size() ; j++) {
				union(firstPeople, list[i].get(j));
			}
		}
		
		for(int i = 0 ; i < M ; i++) {
			boolean isPossible = true;
			int cur = list[i].get(0);
			for(int j = 0 ; j < trueP.length ; j++) {
				if( find(cur) == find(trueP[j]) ) {
					isPossible = false;
					break;
				}
			}
			if( isPossible ) {
				result++;
			}
		}
		
		System.out.println(result);
	}
	//그룹 합치기
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		//부모가 같지 않다면 합쳐줌
		if( a != b ) {
			parent[b] = a;
		}
		
	}
	// 부모 찾기
	private static int find(int a) {
		if( a == parent[a]) {
			return a;
		}else {
			//좌표압축
			return parent[a] = find(parent[a]);
		}
	}
}