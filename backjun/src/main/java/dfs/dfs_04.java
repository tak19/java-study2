package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class dfs_04 {
	static char[][] c;
	static boolean[][] visit1; // 정상
	static boolean[][] visit2; // 적록
	static int[] dx = { 0, 0, 1, -1 }; // 상하좌우 이동
	static int[] dy = { 1, -1, 0, 0 };
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		c = new char[n][n];

		visit1 = new boolean[n][n];
		visit2 = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				c[i][j] = line[j];
			}
		}
		int cnt1 = 0; //정상인 보이는 수
		int cnt2 = 0; //적록 보이는 수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 방문하지 않았다면
				if (!visit1[i][j]) {
					dfs(c[i][j], i, j); // 색상 정보와 위치정보 전달함
					cnt1++;
				}
				if (!visit2[i][j]) {
					dfs2(c[i][j], i, j); // 색상 정보와 위치정보 전달함
					cnt2++;
				}
			}
		}
		System.out.println(cnt1 + " " + cnt2);
	}

	private static void dfs2(char color, int i, int j) {
		if (visit2[i][j]) { // 방문했다면 그냥 리턴
			return;
		} else { //방문하기 시작!
			visit2[i][j] = true;// 방문처리 후 이동
			// 상하좌우 이동
			for (int go = 0; go < 4; go++) {
				int goX = i + dx[go];
				int goY = j + dy[go];
				// 이동 가능해야함
				if (goX >= 0 && goX < n && goY >= 0 && goY < n) {
					if( color == 'B') { //파란색이라면!!!
						if( c[goX][goY] == color && !visit2[goX][goY]) {
							dfs2(color,goX,goY);
						}
					}else {
						if( c[goX][goY] != 'B' && !visit2[goX][goY]) {
							dfs2(color,goX,goY);
						}
					}
					
				}
			}
		}
	}

	private static void dfs(char color, int i, int j) {
		if (visit1[i][j]) { // 방문했다면 그냥 리턴
			return;
		} else { //방문하기 시작!
			visit1[i][j] = true;// 방문처리 후 이동
			// 상하좌우 이동
			for (int go = 0; go < 4; go++) {
				int goX = i + dx[go];
				int goY = j + dy[go];
				// 이동 가능해야함
				if (goX >= 0 && goX < n && goY >= 0 && goY < n) {
					if( c[goX][goY] == color && !visit1[goX][goY]) {
						dfs(color,goX,goY);
					}
				}
			}
		}

	}

}