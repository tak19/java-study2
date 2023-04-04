package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * BFS 탐색후 Prim 알고리즘 적용함
 * 
 */
public class prim_03 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int R,C,index;
	static int[][] map;
	static boolean[][] visit;
	static boolean[] connect;
	static int[][] dis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//지도 크깅
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//지도입력받기
		map = new int[R][C];
		for(int i = 0 ; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < C ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ckIsland();

		dis = new int[index+1][index+1];
		int[] minEdge = new int[index+1]; // 다른 정점에서 자신으로 연결하는 간선비용 중 최소비용
		
		for(int i = 0 ; i < index ; i++) {
			Arrays.fill(dis[i], 101);
			
		}

		calDis();
		
		Arrays.fill(minEdge, 10000);

		//프림적용
		int Count = 0;
		int result = 0;
		minEdge[1] = 0;
		boolean[] select = new boolean[index+1];
		

		for(int c = 1; c <= index ; c++) { //N개의 정점을 모두 연결
			int min = Integer.MAX_VALUE;
			int minVertex = 0;

			for(int i = 1 ; i <= index ; i++) {
				if( !select[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}

			select[minVertex] = true;
			if(  min == 10000 ) {
				result = -1;
				break;
			}
			
			result += min;

			if( ++Count == index-1 ) {
				break;
			}
			for(int i = 1 ; i <= index ; i++) {

				//신장트리에 포함되지 않았고, 선택된 정점과 인접한 정점이며, 선택된 정점과의 비용이 이전까지의 최소비용보다 작다면
				if( !select[i] && dis[minVertex][i] != 101 && minEdge[i] > dis[minVertex][i] ) {
					//System.out.println(dis[minVertex][i]);
					minEdge[i] = dis[minVertex][i];
				}

			}

		}
		System.out.println(result);

	}

	//거리 측정함
	private static void calDis() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				//섬이다
				if( map[i][j] >= 1 ) {
					dfsRL(i,j,map[i][j]);
					dfsUD(i,j,map[i][j]);
				}
			}
		}
	}
	//상하 탐색
	private static void dfsUD(int x, int y, int index) {
		boolean[][] select = new boolean[R][C];

		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(x,y,0));
		select[x][y] = true;

		while( !q.isEmpty() ) {

			Pos pos = q.poll();

			for(int i = 2; i < 4; i++) {
				int gox = pos.x + dx[i];
				int goy = pos.y + dy[i];


				//범위 안이면서 바다라면
				if( canGo(gox,goy) ) {
					int iIndex = map[gox][goy];
					//자기자신이 아닌 다른섬이라면
					if( iIndex > 0 && iIndex != index ) {
						if( dis[index][iIndex] > pos.dis ) {
							if( pos.dis >= 2 ) {
								dis[index][iIndex] = pos.dis;
							}
						}
					}

					if( !select[gox][goy] && map[gox][goy] == 0 ) {
						q.offer(new Pos(gox,goy,pos.dis+1));
						select[gox][goy] = true;
					}

				}
			}
		}
	}
	//좌우탐색
	private static void dfsRL(int x, int y, int index) {
		boolean[][] select = new boolean[R][C];

		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(x,y,0));
		select[x][y] = true;

		while( !q.isEmpty() ) {

			Pos pos = q.poll();

			for(int i = 0; i < 2; i++) {
				int gox = pos.x + dx[i];
				int goy = pos.y + dy[i];


				//범위 안이면서 바다라면
				if( canGo(gox,goy) ) {
					int iIndex = map[gox][goy];
					//자기자신이 아닌 다른섬이라면
					if( iIndex > 0 && iIndex != index ) {
						if( dis[index][iIndex] > pos.dis ) {
							if( pos.dis >= 2 ) {
								dis[index][iIndex] = pos.dis;
							}
						}
					}

					if( !select[gox][goy] && map[gox][goy] == 0 ) {
						q.offer(new Pos(gox,goy,pos.dis+1));
						select[gox][goy] = true;
					}

				}
			}
		}
	}

	//섬을 찾는다
	private static void ckIsland() {
		index = 1;
		visit = new boolean[R][C];

		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				//섬이라믄
				if( map[i][j] == 1 && !visit[i][j]) {
					AreaCnt(i,j);
					//System.out.println(index);
					index++;
				}
			}
		}

	}
	//섬 크기만큼
	private static void AreaCnt(int x, int y) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(x,y));
		map[x][y] = index;
		visit[x][y] = true;

		while( !q.isEmpty() ) {
			Pos pos = q.poll();

			for(int i = 0 ; i < 4; i++) {
				int gox = pos.x + dx[i];
				int goy = pos.y + dy[i];
				//범위 안인가 + 섬인가예?
				if( canGo(gox,goy) && map[gox][goy] == 1 ) {
					//요 진짜 내랑 간적 읎나?
					if( !visit[gox][goy] ) {
						map[gox][goy] = index;
						visit[gox][goy] = true;
						q.offer(new Pos(gox,goy));
					}
				}

			}

		}

	}
	//범위 안인교?
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < R && goy >= 0 && goy < C ) {
			return true;
		}
		return false;
	}
	//좌표정보
	static class Pos{
		int x,y,dis;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Pos(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}


	}
}

