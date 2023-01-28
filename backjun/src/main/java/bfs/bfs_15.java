package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_15 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] map;
	static int one,all;
	static boolean[][] visit;
	static int n,m;
	static int wallCnt;
	static ArrayList<Pos> virusList = new ArrayList<>();
	static int[] virus; // 뽑은 조합을 기록

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //n*n 연구소
		m = Integer.parseInt(st.nextToken()); //활성 바이러스 수

		map = new int[n][n];
		wallCnt=0;
		int blank=0;
		//연구실 상황을 입역받음
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) { //벽 개수 파악한다.
					wallCnt++;
				}
				else if(map[i][j] == 0) {
					blank++;
				}else if(map[i][j] == 2) {
					virusList.add(new Pos(i,j,0));
				}
			}
		}
		//바이러스가 퍼질 공간이 없다면 0 출력 후 종료
		if(blank == 0 ) {
			System.out.println(0);
			return;
		}
		
		//전체 시간을 범위내에 들지 않도록 최대값으로 선언
		all = 2501;
		virus = new int[m];
		selectVirus(0,0);

		if( all == 2501 ) {
			System.out.println(-1);
		}else {
			System.out.println(all);
		}
		
	}

	//바이러스 조합 뽑기
	private static void selectVirus(int cnt, int index) {
		//활성 바이러스 다 만들었으면 BFS 감염 시작함!
		if( cnt == m ) {
			infection();
			if( cleanAreaCnt() ) {
				all = Math.min(all, one);
			}
			return;
		}
		for (int i = index; i < virusList.size(); i++) {
			virus[cnt] = i;
			selectVirus(cnt+1, i+1);
		}
	}

	//감염 한다! --> BFS
	private static void infection() {
		one = 0;
		visit = new boolean[n][n];
		//스택을 매개변수로 받아 인자의 스택 값을 큐에 담음 --> 그래야 원래 스택 데이터 보존 가능
		Queue<Pos> q = new LinkedList<>();
		//뽑은 바이러스 리스트를 큐에 담고 방문처리한다.
		for (int i = 0; i < m; i++) {
			q.add(virusList.get(virus[i]));
			visit[virusList.get(virus[i]).x][virusList.get(virus[i]).y] = true;
		}
		

		while(!q.isEmpty()) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;

			if(map[x][y] == 0) { //비활성 바이러스에 감염시간은 빼주기 위해서 빈칸만 허용
				one = Math.max(one, p.time); //걸리는 시간 중 최대값만 저장함
			}
			
			//4방 탐색 시작
			for(int go =0; go < 4; go++) {
				int gox = x + dx[go];
				int goy = y + dy[go];
				//범위 안이라면
				if(gox >= 0 && gox < n && goy >= 0 && goy < n) {
					if(map[gox][goy] == 0 && !visit[gox][goy]) { //방문하지 않았고 빈방이라면
						if(all < p.time+1) {
							break;
						}
						
						visit[gox][goy] = true;
						q.add(new Pos(gox,goy,p.time+1));

					}else if(map[gox][goy] == 2 && !visit[gox][goy]) { //방문하지 않은 비활성 바이러스라면
						visit[gox][goy] = true;
						q.add(new Pos(gox,goy,p.time+1));
					}

				}

			}
		}

	}

	//빈칸에 감염된 곳의 정보를 탐색한다.-- 감염 시 벽 빼고 모든 곳을 다 탐색했음 - 벽의 개수가 나올것임
	private static boolean cleanAreaCnt() {

		int count = 0;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j ++) {
				if( !visit[i][j] ) {
					count++;
				}
			}
		}
		if ( count != wallCnt) {
			return false;
		}
		return true;
	}

	static class Pos{
		int x;
		int y;
		int time;

		Pos(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
