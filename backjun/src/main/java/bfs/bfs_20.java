package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_20 {
	static int n,m;
	static int[][] map;
	static Queue<Pos> q = new ArrayDeque<>();
	//북 동 남 서
	static int[] dx = { -1,  0, 1,  0};
	static int[] dy = {  0,  1, 0, -1};
	static boolean[][] visit;

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //헹
		m = Integer.parseInt(st.nextToken()); //열
		map = new int[n][m];
		visit = new boolean[n][m];

		st = new StringTokenizer(br.readLine());
		//청소기 좌표와 방향
		int robotX = Integer.parseInt(st.nextToken()); 
		int robotY = Integer.parseInt(st.nextToken());
		int robotDir = Integer.parseInt(st.nextToken());
		q.offer(new Pos(robotX, robotY, robotDir,0)); //움직인 거리는 1로 초기화 자기자리

		//지도 입력받음
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		System.out.println(sb);

	}
	//Pos - x, y, 방향, 청소횟수
	private static void bfs() {

		while( !q.isEmpty() ) {

			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			int dis = pos.dis;
			if( !visit[x][y] ) {
				dis += 1; //방문 안했던 곳이면 청소 횟수 증가 시킴
				visit[x][y] = true; //방문처리
			}
			int CurrentDir = pos.dir; //현재 보는 방향
			for(int i = 0 ; i < 4 ; i++) {
				//방문했다면 반시계로 90도 회전 -> 북쪽의 경우는 서쪽으로 가야하기때문에 3으로 수동처리
				CurrentDir -= 1;
				if(CurrentDir == -1 ) {
					CurrentDir = 3;
				}
					
				//현재 위치에서 보는 방향으로 전진했을때
				int gox = x + dx[CurrentDir];
				int goy = y + dy[CurrentDir];
				//가는 방향이 범위를 벗어 났다면 방향 회전
				if( gox >= 0 && gox < n && goy >= 0 && goy < m && map[gox][goy] != 1 && !visit[gox][goy] ) { // 범위 안이고, 벽 아니여야햄
					//범위안 + 벽아니면 방문했는지 판단
					q.add(new Pos(gox, goy, CurrentDir , dis));
					break; //들어가면 나머지는 안봄

				}

				//해당 경우는 주변에 다 청소 했을 경우임 -> 이때 후진함 바라보는 방향 반대.. 이게 벽이면 종료
				if( i == 3) {
					if( CurrentDir == 1) { //동--> 서로 후진함
						if( wallCk(x,y-1) ) {
							sb.append(dis);
							q.clear();
						}else {
							q.add(new Pos(x,y-1, CurrentDir ,dis));
						}

					}else if( CurrentDir == 2) { //남--> 북으로 후진함
						if( wallCk(x-1,y) ) {
							sb.append(dis);
							q.clear();
						}else {
							q.add(new Pos(x-1,y, CurrentDir ,dis));
						}

					}else if( CurrentDir == 3) { //서--> 동으로 후진함
						if( wallCk(x,y+1) ) {
							sb.append(dis);
							q.clear();
						}else {
							q.add(new Pos(x,y+1, CurrentDir ,dis));
						}

					}else {
						if( wallCk(x+1,y) ) {
							sb.append(dis);
							q.clear();
						}else {
							q.add(new Pos(x+1,y, CurrentDir ,dis)); //북 --> 남으로 후진							
						}
					}
				}
			}



		}



	}

	private static boolean wallCk(int x, int y) {

		if( map[x][y] == 1) {//벽이라면
			return true;
		}

		return false;
	}

	static class Pos{
		int x;
		int y;
		int dir; //바라보는 방향
		int dis; //움직인 거리
		Pos(int x, int y, int dir, int dis){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.dis = dis;
		}

	}
}

