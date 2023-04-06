package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class etc_17 {
	//상하좌우 수
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map,dir;
	static int N,M,K,result;
	static PriorityQueue<Pos> pq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			//지도크기, 격리 시간, 미생물 수
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			//미생물이 많은거 먼저 빼냄
			pq = new PriorityQueue<Pos>( (o1,o2) -> {
				return o2.cnt - o1.cnt;
			});

			map = new int[N][N];
			dir = new int[N][N];
			//방향정보 -1로 초기화해둠
			for(int i = 0; i < N ; i++) {
				Arrays.fill(dir[i], -1);
			}
			for(int i = 0 ; i < K ; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				// 우선순위 큐에 넣어줌
				pq.add(new Pos(r,c,cnt,dir));

			}
			result = 0;
			int ttt = 1;
			//격리시간만큼 진행함
			while( M --> 0 ) {
				//이동함
				bfs();
				collection();
			}
			sb.append(result).append("\n");

		}
		System.out.println(sb);
	}
	//다시 미생물을 큐에 넣음
	private static void collection() {
		result = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if( map[i][j] > 0 ) {
					//System.out.println("미생물 발견");
					result += map[i][j];
					pq.offer(new Pos( i,j,map[i][j],dir[i][j] ));
					//값 넣고 다시 맵 복귀
					dir[i][j] = -1;
					map[i][j] = 0;
				}
			}
		}
	}
	//미생물 이동해유~~
	private static void bfs() {
		//미생물 하나씩 빼낸다
		while( !pq.isEmpty() ) {
			Pos pos = pq.poll();
			//이동 좌표
			int gox = pos.x + dx[pos.dir];
			int goy = pos.y + dy[pos.dir];
			//범위 안인지 확인함
			if( canGo(gox,goy) ) {
				//경계선이라면 2를 나눈 값을 넣어주고 넣어줌
				if( ckArea(gox,goy) ) {
					//먼저 나온 미생물이 없다면 즉, 자기가 가장 미생물을 많이 보유하는경우 - 변환해서 넣음
					if( map[gox][goy] == 0 ) {
						//반대방향 전환 후 방향 맵에 넣어줌
						chageDir(pos);
						dir[gox][goy] = pos.dir;
					}
					map[gox][goy] += (pos.cnt / 2);

				}else {
					//먼저 나온 미생물이 없다면 즉, 자기가 가장 미생물을 많이 보유하는경우 - 자기방향 넣음
					if( map[gox][goy] == 0 ) {
						//반대방향 전환 후 방향 맵에 넣어줌
						dir[gox][goy] = pos.dir;
					}
					map[gox][goy] += pos.cnt;
				}
			}

		}

	}
	//방향전환
	private static void chageDir(Pos pos) {
		int dir = pos.dir;
		//상하방향 반전
		if( dir <= 1 ) {
			dir = (dir == 1 ? 0 : 1);
		}else {
			// 좌우방향 반전
			dir = (dir == 2 ? 3 : 2);
		}
		pos.dir = dir;
	}
	//경계선인지 확인함
	private static boolean ckArea(int gox, int goy) {
		if( gox == 0 || gox == (N-1) || goy == 0 || goy == (N-1) ) {
			return true;
		}
		return false;
	}
	//범위 안인가요?
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	//미생물 위치정보 저장
	static class Pos{
		int x,y,cnt,dir;

		public Pos(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}

	}
}