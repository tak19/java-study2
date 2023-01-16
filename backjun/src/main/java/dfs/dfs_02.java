package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class dfs_02 {
	//상하좌우 이동함..
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] cabbage;
	static int m,n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < test ; t++) {
			String[] info = br.readLine().split(" ");
			m = Integer.parseInt(info[0]);
			n = Integer.parseInt(info[1]);
			int cnt = Integer.parseInt(info[2]);
			
			cabbage = new boolean[m][n];
			
			for(int i = 0 ; i < cnt ; i++) {
				//배추 있는데 true 값 할당
				String[] s = br.readLine().split(" ");
				cabbage[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = true;
			}
			int total=0;
			for(int i = 0; i < m ; i++) {
				for(int j = 0; j < n ; j++) {
					//배추가 있다면
					if( cabbage[i][j] ) {
						dfs(i,j);
						total++;
					}
				}
			}
			System.out.println(total);
			
			
		}

	}
	private static void dfs(int i, int j) {
		if( !cabbage[i][j] ) { //배추 없으면 바로 리턴함!
			return;
		}else { // 배추 있어요!!
			cabbage[i][j] = false;
			for(int go = 0 ; go < 4 ; go++) {
				int gox = i + dx[go];
				int goy = j + dy[go];
				if( gox >= 0 && gox < m && goy >= 0 && goy < n) { //좌표 범위 내에 이동함
					dfs(gox,goy);
				}
			}
		}
		
	}

}
