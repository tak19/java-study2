package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_13 {
	static int N,startX,startY,result;
	//상하좌우 순
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static Pos[] posList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			N = Integer.parseInt(br.readLine()); // 게임장 크기
			map = new int[N][N]; //게임장 생성
			posList = new Pos[10];
			int wormCnt = 0;
			//맵 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					//Worm Hole
					if( map[i][j] >= 6 ) {
						//좌표정보와 웜홀 인덱스 전달함
						posList[wormCnt++] = new Pos(i,j,map[i][j]);
					}
				}
			}
			result = 0;
			//게임 시작
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//빈 공간이라면 시작점을 저장해 두고 탐색 시작한다.
					if(map[i][j] == 0) {
						startX = i;
						startY = j;
						for(int dir = 0 ; dir < 4 ; dir++) {
							dfs(i,j,0,dir);
						}
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);

	}

	//블록 별 전환 --> 1

	//공 굴러가유 -- 점수는 벽이나 블록에 부딪힌 횟수가 된다. 
	private static void dfs(int i, int j,int point, int dir) {
		//이동 좌표
		int temX = i + dx[dir];
		int temY = j + dy[dir];

		//범위 벗어나거나 5번 블록 만나면 반대로 똑같은 거리를 감 따라서 (거리 *2) 하고 종료
		if( !canGo(temX,temY) || map[temX][temY] == 5 ) {
			getPoint(point);
			return;
		}

		//블랙홀 이라면 종료
		if( map[temX][temY] == -1 ) {
			result = Math.max(result, point);
			return;
		}

		//이동 공간이 빈공간이 아니라면.. 후보: 블록(5번 블록 제외), 웜홀  (나는 	상하좌우 )
		/*
		 * {-1,1,0,0};
		 * {0,0,-1,1};
		 */
		if( 0 < map[temX][temY]  && map[temX][temY] <= 4) {
			switch (map[temX][temY]) {
			case 1: {
				//우 + 상 이랑 만나면 반대로 튕김
				if( dir == 0 || dir == 3 ) {
					getPoint(point);
					return;
				}else {
					point++;
					dir = chageDir(dir,1);
				}
				break;
			}
			case 2: {
				//우+하 랑 만나면 반대로 튕김
				if( dir == 1 || dir == 3 ) {
					getPoint(point);
					return;
				}else {
					point++;
					dir = chageDir(dir,2);
				}
				break;
			}
			case 3: {
				//하+좌 만나면 반대로 튕김
				if( dir == 1 || dir == 2 ) {
					getPoint(point);
					return;
				}else {
					point++;
					dir = chageDir(dir,3);
				}
				break;
			}
			case 4: {
				//상+좌 만나면 반대로 튕김
				if( dir == 0 || dir == 2 ) {
					getPoint(point);
					return;
				}else {
					point++;
					dir = chageDir(dir,4);
				}
				break;
			}
			}
		}
		//웜홀 이라면
		if( 5 < map[temX][temY] ) {
			for(int k = 0 ; k < 10 ; k++) {
				Pos p = posList[k];
				//같은 번호의 웜홀이면서 다른 위치에 있는 웜홀 이라면
				if( map[temX][temY] == p.wormNum && temX != p.x && temY != p.y ) {
					temX = p.x;
					temY = p.y;
					break;
				}

			}
		}
		dfs(temX, temY, point, dir);


	}
	//반대벽 방향 갈때 포인트 값 갱신 
	private static void getPoint(int point) {
		int temPoint = point + (point + 1);
		result = Math.max(result, temPoint); //최대값 갱신
	}

	//방향전환쓰 -> 90도 회전 -- (나는 상하좌우 )
	/*
	 * {-1,1,0,0};
	 * {0,0,-1,1};
	 */
	private static int chageDir(int dir, int block) {
		if( block == 1 ) {
			// 하, 좌 경우만 들어옴 --> 밑 방향이면 좌로 나머지는 위로 올림
			return dir == 1 ? 3 : 0;

		}else if( block == 2 ) {
			// 상, 좌 경우만 들어옴
			return dir == 0 ? 3 : 1;

		}else if( block == 3 ) {
			// 우, 상 경우만 들어옴
			return dir == 3 ? 1 : 2;
		}else {
			// 우, 하 경우만 들어옴 
			return dir == 3 ? 0 : 2;
		}

	}

	//범위 안이라면 true 반환
	private static boolean canGo(int temX, int temY) {
		if( temX >= 0 && temX < N && temY >= 0 && temY < N) {
			return true;
		}
		return false;
	}
	//웜홀 저장
	static class Pos {
		int x,y,wormNum;

		public Pos(int x, int y, int wormNum) {
			this.x = x;
			this.y = y;
			this.wormNum = wormNum;
		}
	}
}

