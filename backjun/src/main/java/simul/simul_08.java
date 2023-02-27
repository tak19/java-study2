package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class simul_08 {
	static boolean[][] map; //스카프용
	static boolean[][] visit; //방문처리
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static Queue<Pos> q;
	static int minX,minY,maxX,maxY,result; //각 영역의 최소,최대 x,y 좌표저장

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); //천의 개수이다
		map = new boolean[101][101];
		StringTokenizer st = null;
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			int reft = Integer.parseInt(st.nextToken());
			int under = Integer.parseInt(st.nextToken());
			//해당 영역을 true값으로 표시함
			cal(reft,under);

		}

		result = 0;

		visit = new boolean[101][101]; //방문 처리했는지 확인
		q = new ArrayDeque<>();
		for(int i = 0; i < 101; i ++) {
			for(int j = 0; j < 101 ; j++) {
				//검은색 천이면서 방문 안했다면 BFS 돌린다!
				if( map[i][j] && !visit[i][j] ) {
					bfs(i,j);
				}
			}
		}
		System.out.println(result);

	}

	//겹치는 영역을 탐색함
	private static void bfs(int r, int c) {
		
		q.offer(new Pos(r,c));
		visit[r][c] = true;
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;

			for(int i = 0 ; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];

				if(canGo(gox,goy)) {
					//검은천이면서 방문 x라면!!
					if( map[gox][goy] && !visit[gox][goy] ) {
						visit[gox][goy] = true;
						q.offer(new Pos(gox,goy));
					}else if( !map[gox][goy]) {
						result++;
					}
					
				}

			}

		}

	}
	//흰천 영역 내에 존재하는지 확인
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < 101 && goy >= 0 && goy < 101) {
			return true;
		}
		return false;
	}
	//영역 그리기
	private static void cal(int reft, int under) {
		for(int i = reft ; i < reft + 10 ; i++) {
			for(int j = under ; j < under + 10 ; j++) {
				map[i][j] = true;
			}
		}
	}
	//좌표정보를 담을 class
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}