package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bfs_19 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static char[][] map;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];
		//puyo 입력받음 -> 12행 6열
		for(int i = 0; i < 12; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0; j < 6; j++) {
				map[i][j] = c[j];
			}
		}
		int boom = 0;
		boolean ck = false;

		while( true ) {

			ck = true; //1회 방문 시 ck true로 변경 -- > 한번이라도 터트리면 계속진행 아니면 나옴
			Queue<Pos> q = new LinkedList<>();
			boolean[][] visit = new boolean[12][6]; //방문배열 초기화
			//푸요가 밑에 있기때문에 밑에서 탐색 시작
			for(int i = 11 ; i >= 0 ; i--) {
				for(int j = 5 ; j >= 0 ; j--) {
					if( map[i][j] != '.' && !visit[i][j] ) { //탐색안한 푸요라면 BFS 탐색함
						char puyo = map[i][j]; //기준 푸요 저장
						Queue<Pos> list = new LinkedList<>();

						visit[i][j] = true;
						q.add(new Pos(i,j));
						list.add(new Pos(i,j));
						while( !q.isEmpty() ) {
							Pos pos = q.poll();
							int x = pos.x;
							int y = pos.y;

							for(int go = 0 ; go < 4 ; go++) {
								int gox = x + dx[go];
								int goy = y + dy[go];

								if( gox >= 0 && gox < 12 && goy >=0 && goy < 6 && map[gox][goy] == puyo) {// 범위 안이면서 같은 푸요라면
									if( !visit[gox][goy]) { //방문한적이 없다면
										list.add(new Pos(gox,goy)); //4개 이상 연결 됐는지 확인용
										visit[gox][goy] = true;
										q.add(new Pos(gox,goy));
									}
								}
							}
						}
						//4개 이상 연결된 푸요라면
						if( list.size() >= 4) {
							while( !list.isEmpty() ) {
								Pos pos = list.poll();
								map[pos.x][pos.y] = '.'; //푸요 터트림
								ck = false;
							}
						}

					}
				}
			}
			if(!ck) {
				updateMap();
				boom += 1;
			}else {
				break;
			}

			//map 확인

		}
		System.out.println(boom);

	}
	//행마다 탐색하면됨
	private static void updateMap() {

		for (int i = 0; i < 6; i++) {
			for (int j = 12 - 1; j > 0; j--) {
				if (map[j][i] == '.') {
					for (int k = j - 1; k >= 0; k--) {
						if (map[k][i] != '.') {
							map[j][i] = map[k][i];
							map[k][i] = '.';
							break;
						}
					}
				}
			}
		}
	}
	static class Pos{
		int x;
		int y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

