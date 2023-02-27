package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_3289 {
	private static StringBuilder sb = new StringBuilder();
	static int[] parents;

	public static void main(String[] args) throws Exception {
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/*
		 * 2. 입력파일 객체화
		 */
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			parents = new int[n+1];
			makeSet(n);
			
			for(int i = 0 ; i < m ; i++) {
				st = new StringTokenizer(br.readLine());
				int o = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if( o == 0 ) {
					union(a, b);
				}else if( o == 1) {
					if( findSet(a) == findSet(b) ) {
						sb.append(1);
					}else {
						sb.append(0);
					}
				}
				
				
				
			}
			sb.append("\n");
			
		}

		System.out.println(sb);

	}
	static void makeSet(int n) {
		for(int i = 1 ; i <= n ; i++) {
			parents[i] = i;
		}
	}
	static int findSet(int a) {
		if( parents[a] == a ) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if( aRoot == bRoot ) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
		
	}
}

