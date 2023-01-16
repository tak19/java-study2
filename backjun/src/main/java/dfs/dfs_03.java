package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class dfs_03 {
	// 상하좌우 이동함..
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][] arr;
	static int n;
	static int cnt;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		//집이 있으면 boolean 2차원 배열에 해당 인덱스에 true로 변경
		arr = new boolean[n][n];
		for(int i = 0 ; i < n ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < n ; j++) {
				if( c[j] - '0' == 1 ) {
					arr[i][j] = true;
				}
			}
		}

		
		int total=0; //DFS 호출 횟수
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < n ; j++) {
				//집이 있다면
				
				if( arr[i][j] ) {
					cnt = 0;
					dfs(i,j);
					total++;
					list.add(cnt);
				}
			}
		}
		Collections.sort(list);
		
		sb.append(total + "\n");
		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + "\n");
		}
		System.out.println(sb);
		
	}

	private static void dfs(int a, int b) {
		if( !arr[a][b] ) { //집 없으면 바로 리턴함!
			return;
		}else { // 집 있어요 있어요!!
			arr[a][b] = false;
			cnt++;
			for(int go = 0 ; go < 4 ; go++) {
				int gox = a + dx[go];
				int goy = b + dy[go];
				if( gox >= 0 && gox < n && goy >= 0 && goy < n) { //좌표 범위 내에 이동함
					dfs(gox,goy);
				}
			}
		}
		
	}

}
