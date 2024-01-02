package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class samsung_03 {
	// 8방 탐색
	static int[] dx = { -1, -1, 0, 1, 1,  1,  0, -1 };
	static int[] dy = {  0,  1, 1, 1, 0, -1, -1, -1 };
	static int N, M, K;
	static Queue<Pos> fire = new ArrayDeque<Pos>();
	static Queue<Pos>[][] arr, temArr;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 행/열, 파이어볼 개수, 명령횟수 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 배열 생성
		arr = initQueue();

		// 초기 파이어볼 입력받음
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 파이어볼 위치 - R행 C열
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 질량
			int m = Integer.parseInt(st.nextToken());
			// 속도s와 방향 d
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			// System.out.println("r: " + r + " c: " + c);
			// 배열에 파이어볼 저장함
			arr[r][c].offer(new Pos(r, c, m, s, d));
		}

		// 파이어볼 전개!!
		for (int T = 0; T < K; T++) {
			//System.out.println(T+1 + " 번쨰 진행\n");
			//printQueue(arr);
			// 임시 파이어볼 배열 생성
			temArr = initQueue();
			// 파이어볼 이동 - 비어있지 않으면 해당 파이어볼이 있는것임
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (!arr[i][j].isEmpty()) {
						//System.out.println("i: " + i + " j: " + j + " 에서 발견!!");
						moveFireBall(i, j);
					}
				}
			}
			copyArr();

			// 다시 초기화
			temArr = initQueue();
			//System.out.println("쪼개기기전");
			//printQueue(arr);
			// 파이어볼 쪼개짐!
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (arr[i][j].size() >= 2) {
						shotFireBall(i, j);
					}
				}
			}
			zeroRemove();

			// 옮겨담기
			copyArr();
			
		}
		//System.out.println("최종 Queue 배열");
		//printQueue(arr);
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				while(!arr[i][j].isEmpty()) {
					result += arr[i][j].poll().m;
				}
			}
		}
		System.out.println(result);
	}

	private static void zeroRemove() {
		/*for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				int cnt = 0;
				if( !temArr[i][j].isEmpty() ) {
					for( Pos p : temArr[i][j] ) {
						if( p.m == 0 ) {
							temArr[i][j].poll(cnt);
						}
					}
				}
			}
		}*/
	}

	private static void printQueue(Queue<Pos>[][] arr2) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (!arr2[i][j].isEmpty()) {
					for (Pos p : arr2[i][j]) {
						System.out.println("x: " + p.x + " y: " + p.y + " m: " + p.m + "   " + "s: " + p.s + " d: " + p.d);
					}
				}
			}
		}
	}

	// 가짜판을 진짜판으로~
	private static void copyArr() {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				while(!temArr[i][j].isEmpty()) {
					arr[i][j].offer(temArr[i][j].poll());
				}
				//arr[i][j] = temArr[i][j];
			}
		}
	}

	private static Queue<Pos>[][] initQueue() {
		Queue<Pos>[][] arr2 = new ArrayDeque[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				arr2[i][j] = new ArrayDeque<>();
			}
		}
		return arr2;
	}

	// 파이어볼 발사 - 질량과 속도는 공식에 따라 분배되고, 방향은 4방향으로 쪼개짐
	private static void shotFireBall(int r, int c) {
		int sumM = 0;
		int sumS = 0;
		List<Integer> list = new ArrayList<Integer>();
		boolean sumD = false;
		int size = arr[r][c].size();
		// 2개 이상있는 파이어볼의 정보를 모두 더한다.
		while (!arr[r][c].isEmpty()) {
			Pos p = arr[r][c].poll();
			sumM += p.m;
			sumS += p.s;
			list.add(p.d);
		}
		//System.out.println("전체 질량: " + sumM);
		// 질량과 속도 정하기
		sumM = sumM / 5;
		sumS = sumS / size;
		if( sumM == 0 ) {
			return;
		}
		// 방향 정하기
		int first = list.get(0);
		if (first % 2 == 0) {
			sumD = calDirEven(list);
		} else {
			sumD = calDirOdd(list);
		}
		// 4방향으로 쪼개기
		if (sumD) {
			for (int i = 0; i <= 7; i += 2) {
				//moveFire(r, c, sumM, sumS, i);
				temArr[r][c].offer(new Pos(r, c, sumM, sumS, i));
				
			}
		} else {
			for (int i = 1; i <= 7; i += 2) {
				//moveFire(r, c, sumM, sumS, i);
				temArr[r][c].offer(new Pos(r, c, sumM, sumS, i));
			}
		}

	}

	private static void moveFire(int r, int c, int sumM, int sumS, int i) {
		// 파이어볼 이동 크기
/*		int x = (r + dx[i]);
		int y = (c + dy[i]);
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
		}*/
		temArr[r][c].offer(new Pos(r, c, sumM, sumS, i));
	}

	// 모두 다 홀수 인지 판단
	private static boolean calDirOdd(List<Integer> list) {
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) % 2 == 0) {
				return false;
			}
		}
		return true;
	}

	// 모두 다 짝수 인지 판단
	private static boolean calDirEven(List<Integer> list) {
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) % 2 != 0) {
				return false;
			}
		}
		return true;
	}

	// 파이어볼 이동
	private static void moveFireBall(int r, int c) {
		while( !arr[r][c].isEmpty() ) {
			Pos pos = arr[r][c].poll();
			int x = pos.x;
			int y = pos.y;
			// 파이어볼 이동 크기
			x += (dx[pos.d] * pos.s);
			y += (dy[pos.d] * pos.s);
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
			//System.out.println("이동 후 좌표 -> X:  " + x + " Y: " + y);
			temArr[x][y].offer(new Pos(x, y, pos.m, pos.s, pos.d));
		}
	}

	// 이동한 x 좌표 구하기(음수 일떄)
	private static int calMinus(int x) {
		while (x <= 0) {
			x += N;
		}
		if (x == 0) {
			return N;
		}
		return x;
	}

	// 이동한 y 좌표 구하기(양수 일떄)
	private static int calPlus(int x) {
		if (x % N == 0) {
			return N;
		}
		return x % N;
	}

	// 좌표를 저장할 클레스
	static class Pos {
		int x, y, m, s, d;

		public Pos(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}
}