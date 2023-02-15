package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class d4_1868 {
	static int n,result;
	static char[][] map;
	static boolean[][] visit;
	//8방 탐색
	static int[] dx = {0 , 0, -1 , 1, -1, -1,  1,  1 };
	static int[] dy = {1 ,-1,  0 , 0, -1,  1, -1, 1 };
	static Queue<Pos> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			n = Integer.parseInt(br.readLine()); //지도 크기
			//지도 입력받기
			map = new char[n][n];
			visit = new boolean[n][n];
			for(int i = 0 ; i < n ; i++) {
				map[i] = br.readLine().toCharArray();
			}
			result = 0;
			//주변에 폭탄이 없는 애들만 BFS 돌려서 연쇄반응 일으킬꺼야 --> 최소의 클릭을 위해서
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {

					if( map[i][j] != '*' && !visit[i][j] ) { //지뢰가 아니고 방문하지 않았던곳

						int bom = 0;
						//자기 주변에 폭탄이 있는지 확인하고 없으면 bfs 동작함
						for(int k = 0 ; k < 8 ; k++) {
							int gox = i + dx[k];
							int goy = j + dy[k];
							//방문범위 안이거나 방문한적이 없는 곳이라면
							if( inAreat(gox,goy) && !visit[gox][goy] ) {
								if( map[gox][goy] == '*') { //다음칸이 지뢰라면
									bom++;
								}
							}
						}
						//8방탐색 후 주변에 폭탄이 아무것도 없으면 연쇄 일으킴
						if( bom == 0 ) {
							visit[i][j] = true;
							//나를 먼저 넣고 주변에 폭탄있는 확인하고 확장
							q.add(new Pos(i,j));
							bfs();
						}

					}
				}
			}
			//방문하지 않았던 땅이 있다면 카운팅해줌
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					if( map[i][j] == '.' && !visit[i][j] ) {
						result++;
					}
				}
			}


			sb.append(result).append("\n");

		}
		System.out.println(sb);

	}
	private static void bfs() {
		result++; //클릭횟수 증가
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;


			int bom = 0;
			for(int i = 0 ; i < 8 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//방문범위 안이거나 방문한적이 없는 곳이라면
				if( inAreat(gox,goy) && !visit[gox][goy] ) {
					if( map[gox][goy] == '*') { //다음칸이 지뢰라면
						bom++;
					}
				}
			}

			//주변에 지뢰가 없다면 주변도 bfs로 터트림 --> 자기가 0이면 8 방향 다 탐색
			if( bom == 0 ) {
				visit[x][y] = true;
				for(int i = 0 ; i < 8 ; i++) {
					int gox = x + dx[i];
					int goy = y + dy[i];
					//방문범위 안이거나 방문한적이 없는 곳이라면
					if( inAreat(gox,goy) && !visit[gox][goy] ) {
						if( map[gox][goy] == '.') { 
							visit[gox][goy] = true;
							q.add(new Pos(gox,goy));
						}
					}
				}
			}else {
				//연쇄 반응 시에 폭탄과 인접한 곳들의 방문확인을 위해
				visit[x][y] = true;
			}


		}

	}
	//범위 안이라면 true 아니면 false
	private static boolean inAreat(int gox, int goy) {

		if( gox >= 0 && gox < n && goy >= 0 && goy < n ) {
			return true;
		}
		return false;
	}

	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
