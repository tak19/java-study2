package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_30 {
	static int N,M;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//집합 크기와 연산 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//각 부모 자신으로 설정하기
		parent = new int[N+1];
		for(int i = 0 ; i <= N ; i++) {
			parent[i] = i;
		}
		StringBuilder sb = new StringBuilder();
		//연산 수행
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 집합이라면
			if( cal == 0 ) {
				union(a,b);
			}else {
				// 부모 찾는거라면
				if( ckeckSame(a,b) ) {
					sb.append("YES").append("\n");
				}else {
					sb.append("NO").append("\n");
				}
			}			
		}
		System.out.println(sb);
	}
	private static boolean ckeckSame(int a, int b) {
		a = find(a);
		b = find(b);
		if( a == b ) {
			return true;
		}
		return false;
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