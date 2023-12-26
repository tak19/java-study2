package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class samsung_02 {
	// 8방 탐색
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int R, N, result;
	static Queue<Pos> cloud = new ArrayDeque<Pos>();
	static int[][] arr;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 행과 열 입력 후 배열 선언
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[R + 1][R + 1];
		// 바구니 지도 입력받기
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= R; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 초기 구름위치 저장
		cloud.add(new Pos(R, 1));
		cloud.add(new Pos(R, 2));
		cloud.add(new Pos(R - 1, 1));
		cloud.add(new Pos(R - 1, 2));

		// 이동 시작
		for (int T = 0; T < N; T++) {
			// 방향과 거리 입력받음
			st = new StringTokenizer(br.readLine());
			int di = Integer.parseInt(st.nextToken()) - 1;
			int si = Integer.parseInt(st.nextToken());

			// 구름이 해당 방향으로 이동함
			moveCloud(di, si);
			// 각 구름에서 비가 내려 해당 칸의 물의 양이 1 증가함 + 구름이 모두 사라짐
			rainCloud();
			// 물복사 버그 시전!!
			waterCopy();
			// 구름 생성
			makeCloud();
		}
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= R; j++) {
				result += arr[i][j];
			}
		}
		System.out.println(result);
	}
	
	// 조건에 따른 구름 만들기
	private static void makeCloud() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= R; j++) {
				// 이전에 구름이 만들어진 칸이 아니라면 + 구름을 만들 조건인 물의 양이 2 이상이면
				if (!visit[i][j] && arr[i][j] >= 2) {
					arr[i][j] -= 2;
					cloud.offer(new Pos(i, j));
				}
			}
		}
	}

	// 물 복사 버그 시전
	private static void waterCopy() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= R; j++) {
				// 물 양이 증가한 곳이라면
				if (visit[i][j]) {
					// 대각성 방향 체크
					for (int dir = 1; dir <= 7; dir += 2) {
						// 대각 방향
						int goX = i + dx[dir];
						int goY = j + dy[dir];
						// 대각 방향이 범위 안이면서 물이 있다면 해당 칸의 물양을 1 증가시킴
						if (ckArea(goX, goY)) {
							if (arr[goX][goY] >= 1) {
								arr[i][j]++;
							}
						}
					}
				}
			}
		}
	}

	// 범위 안인지
	private static boolean ckArea(int i, int j) {
		if (i >= 1 && i <= R && j >= 1 && j <= R) {
			return true;
		}
		return false;
	}

	// 비 내려 양동이에 물이 더해지고 구름은 없어짐, 이떄 구름이였던곳 체크함
	private static void rainCloud() {
		visit = new boolean[R + 1][R + 1];
		// 구름이 있다면 - 비 양 1 증가 시키고, 방문체크함
		while (!cloud.isEmpty()) {
			Pos p = cloud.poll();
			arr[p.x][p.y]++;
			visit[p.x][p.y] = true;
		}

	}

	// 구름 이동시킴
	private static void moveCloud(int di, int si) {
		int cloudSize = cloud.size();
		for (int i = 0; i < cloudSize; i++) {
			// 현재 구름의 위치
			Pos pos = cloud.poll();
			int x = pos.x;
			int y = pos.y;
			// 구름 이동크기
			x += (dx[di] * si);
			y += (dy[di] * si);
			// 이동 후 좌표
			if (x >= 0) {
				x = calPlus(x);
			} else {
				x = calMinus(x);
			}
			if (y >= 0) {
				y = calPlus(y);
			} else {
				y = calMinus(y);
			}
			// 새로 이동한 위치의 구름을 큐에 다시 넣는다.
			cloud.offer(new Pos(x, y));
		}
	}

	// 이동한 x 좌표 구하기(음수 일떄)
	private static int calMinus(int x) {
		while (x <= 0) {
			x += R;
		}
		if (x == 0) {
			return R;
		}
		return x;
	}

	// 이동한 x 좌표 구하기(양수 일떄)
	private static int calPlus(int x) {
		if (x % R == 0) {
			return R;
		}
		return x % R;
	}

	// 좌표를 저장할 클레스
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}