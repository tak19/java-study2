package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class dfs_99 {
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[][] map; // 이동 가능한지 확인
	static char[][] arr; // 호수지도
	static int m, n; // 호수의 크기
	static int[] point; // 백조의 위치
	static int x1, y1, x2, y2; // 백조의 위치 따로!
	static boolean posibel;
	static boolean[][] visit;
	static int[][] melt; // 빙산 녹는시간
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int max, xdis, ydis;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		m = Integer.parseInt(info[0]);
		n = Integer.parseInt(info[1]);
		// 호수를 만들어 - 하나는 얼음인지 확인 하나는 회차별 변경 호수 상태임!!
		map = new boolean[m][n];
		arr = new char[m][n];
		melt = new int[m][n];

		point = new int[4];
		int index = 0;
		// 호수 입력 받기
		for (int i = 0; i < m; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				if (c[j] == 'L') { // 백조 위치 파악해둠 - 한쪽만 파악하면됨!
					point[index] = i;
					point[index + 1] = j;
					index += 2;
				} else if (c[j] == 'X') { // 물이 얼어 있다면!!
					map[i][j] = true;
				}
			}
		}
		// 2마리 백조가 위치하는 좌표
		x1 = point[0];
		y1 = point[1];
		x2 = point[2];
		y2 = point[3];

		// 백조가 만나면 posibel 변경함 true로
		posibel = false;
		int day = 0;

		bfs();
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(melt[i][j] + " ");
				//System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
//		while (!posibel) {
//			dfs(); // 하루치 녹임 -> 호수 갱신이 필요하다!!
//			update();
//			visit = new boolean[m][n];
//			dfs(x1, y1);
//			day++;
//		}

		System.out.println(day);
	}

	private static void bfs() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j]) { // 얼음 발견하면 bfs 돈다!
					System.out.println("bfs 실행 " + " i: " + i + " j: "+ j);
					xdis = i;
					ydis = j;
					visit = new boolean[m][n];
					int min = Integer.MAX_VALUE;
					Queue<pos> q = new LinkedList<pos>();
					q.add(new pos(i, j));

					int dis = 0; // 물까지 거리임!
					while (!q.isEmpty()) {
						pos cur = q.poll();

						if (visit[cur.x][cur.y]) {
							return;
						} else {
							visit[cur.x][cur.y] = true;
							
							dis += 1;
							for (int dir = 0; dir < 4; dir++) {
								int nx = cur.x + dx[dir];
								int ny = cur.y + dy[dir];
								if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
									if (!map[nx][ny] ) { // 물을 발견하면 거리를 갱신함.!!
										min = Math.min(min, dis);
										melt[xdis][ydis] = min;
										
									} else {
										q.add(new pos(nx, ny));
									}
								}

							}
						}

					}

				}
			}
		}
	}

	// 호수를 녹일꺼임!
	// 연쇄적으로 녹는 경우를 생각안함!! 멥과 지도를 구분항 필요가 있음!!!
	private static void dfs() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == '.') { // 얼음 이라면!!!
					// 상하좌우 비교함
					for (int go = 0; go < 4; go++) {
						int gox = i + dx[go];
						int goy = j + dy[go];
						// 호수 범위 내에 이동을 확인한다!! 그리고 이동 범위 내의 물이 얼어있다면!! 물을 녹임!
						if (gox >= 0 && gox < m && goy >= 0 && goy < n) {
							if (map[gox][goy]) {
								map[gox][goy] = false;
							}

						}

					}
				} else { // 얼었다면 보내버림!!
					continue;
				}
			}

		}
	}

	// 백조를 탐색한다!! visi 필요할듰!!
	private static void dfs(int nowX, int nowY) {
		if (posibel) {
			posibel = true;
			return;
		}

		if (visit[nowX][nowY]) { // 얼어 있다면 그냥 나옴 || 방문했다면 안돈다!
			return;
		} else {
			visit[nowX][nowY] = true;

			// 백조를 기준으로 이동 시작 -- L을 찾을때까지
			for (int go = 0; go < 4; go++) {
				int gox = nowX + dx[go];
				int goy = nowY + dy[go];
				if (gox >= 0 && gox < m && goy >= 0 && goy < n && !map[gox][goy]) { // 호수 범위 내에 이동함!! + 얼음이 아니여야함!!
					if (arr[gox][goy] == 'L' && gox == x2 && goy == y2) { // 다음이 백조라면 답 찾음!! (첫번째 백조는 아니여야지!)
						posibel = true;
						return;
					} else { // 첫번째 백조를 기준으로 탐색하는데 물이 안얼어있어야 이동 가능!! false -> 물인것!
						dfs(gox, goy);
					}

				}
			}
		}

	}

	static class pos {
		int x, y;

		public pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}