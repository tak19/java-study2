package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_12 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		Queue<Pos> people = new LinkedList<>();
		Queue<Pos> fire = new LinkedList<>();

		char[][] map = new char[r][c];
		boolean[][] visit = new boolean[r][c];
		boolean[][] fireRange = new boolean[r][c];


		for(int i = 0; i < r ; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0; j < c ; j++) {

				map[i][j] = line[j];

				if( map[i][j] == '#') { //벽이라면
					visit[i][j] = true;
				}else if(map[i][j] == 'F') { //불
					fireRange[i][j] = true;
					fire.add(new Pos(i,j));
				}else if(map[i][j] == 'J') { //사람
					visit[i][j] = true;
					people.add(new Pos(i,j,1));
				}
			}
		}



		int cnt = 1;
		Pos p;
		int px=0;
		int py=0;
		boolean ck = false;
		
		//BFS 실시
		while(true) {
			if( people.isEmpty() ) {
				cnt = -1;
				break;
			}
			int size = fire.size();
			//불 -- 불이 여러개 일 경우를 생각 해야했음!!
			for(int t = 0 ; t < size; t++) {
				p = fire.poll();
				px = p.x;
				py = p.y;
				for(int i = 0; i < 4 ; i++) {
					int gox = px + dx[i];
					int goy = py + dy[i];
					if( gox >= 0 && gox < r && goy >= 0 && goy < c) { //이동 공간이 범위 안이라면 
						if( map[gox][goy] != '#' && !fireRange[gox][goy]) { //벽이 아니거나 방문 or 불이 안붙은 곳이여야함
							fireRange[gox][goy] = true; //방문처리 후 큐에 넣음
							fire.add(new Pos(gox,goy));
						}
					}
				}
			}
			

			size = people.size();
			//사람 --> 여러번 탐색해야지
			for(int t = 0; t < size ; t++) {
				p = people.poll();
				px = p.x;
				py = p.y;
				
				if( px == 0 || px == r-1 || py == 0 || py ==c-1) { //가장 자리 라면!!
					cnt = p.dis;
					ck = true;
					break;
				}

				
				for(int i = 0; i < 4 ; i++) {
					int gox = px + dx[i];
					int goy = py + dy[i];
					if( gox >= 0 && gox < r && goy >= 0 && goy < c) { //이동 공간이 범위 안이라면 
						if( map[gox][goy] != '#' && !visit[gox][goy] && !fireRange[gox][goy]) { //벽이 아니거나 방문 or 불이 안붙은 곳이여야함
							visit[gox][goy] = true; //방문처리
							people.add(new Pos(gox,goy,p.dis+1));
						}
					}
				}
			}
			if(ck) {
				break;
			}


		}
		if( cnt == -1) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(cnt);
		}



	}

	static class Pos {
		int x;
		int y;
		int dis;
		Pos(int x, int y){
			this.x = x;
			this.y =y;
		}
		Pos(int x, int y,int dis){
			this.x = x;
			this.y =y;
			this.dis = dis;

		}
	}
}
