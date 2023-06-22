package prime;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class prime_07 {
	static int N;
	static ArrayList<Node>[] list;
	static boolean[] visit;
	static long lcm;
	static long D[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//재료 수
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N];
		visit = new boolean[N];
		D = new long[N];
		lcm = 1; //최소 공배수 저장
		
		//리스트 초기화 하기
		for(int i = 0 ; i < N ; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		//레시피 입력받기
		StringTokenizer st = null;
		for(int i = 1 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,p,q));
			list[b].add(new Node(a,q,p));
			// 최소 공배수 구하기 - 두 수의 곱 / 최대 공약수
			lcm *= ( p*q / gcd(p,q) ); 
		}
		
		D[0] = lcm;
		dfs(0);
		
		//최대 공약수 구하기
		long mgcd = D[0];
		for(int i = 1 ; i < N ; i++) {
			mgcd = gcd(mgcd,D[i]);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i++) {
			sb.append(D[i] / mgcd).append(" ");
		}
		System.out.println(sb);
		
	}
	//탐색 시작
	private static void dfs(int index) {
		//해당 인덱스 방문처리하고 연결된 노드 탐색
		visit[index] = true;
		for(Node i : list[index]) {
			int next = i.b;
			if( !visit[next] ) {
				D[next] = D[index] * i.q  / i.p;
				dfs(next);
			}
		}
	}
	//최대 공약수 구하기 - 유클리드 호제법
	private static long gcd(long p, long q) {
		if( q == 0 ) {
			return p;
		}
		else {
			return gcd(q, p % q );
		}
	}
	//노드 클래스 선언
	static class Node{
		int b,p,q;
		//이어진 정점과 비율 초기화
		public Node(int b, int p, int q) {
			this.b = b;
			this.p = p;
			this.q = q;
		}
		
	}
}