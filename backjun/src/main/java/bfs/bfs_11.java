package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_11 {
	//상하좌우 이동 좌표
	static int[] dx = {0,0,-1,1}; 
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m]; //지도
		boolean[][][] visit = new boolean[n][m][2]; //방문확인-> 벽을 부순 경우와 안부순경우

		//지도 입력받음
		for(int i = 0; i < n ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < m; j++) {
				arr[i][j] = c[j] - '0';
			}
		}
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0,0,1,0)); //시작 지점 - (0,0) , 횟수: 1 ||  벽 부순 수=0
		//벽 안부순 경우: 0 부순 경우:1
		visit[0][0][0] = true;
		visit[0][0][1] = true;

		while(!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			//(n,m) 좌표에 도달 했다면
			if(x == n-1 && y == m-1) {
				System.out.println(pos.dis);
				return;
			}
			for(int i = 0; i < 4 ; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//이동 거리가 범위 안인지 확인함 + 방문하지 않았는지
				if(gox >= 0 && gox < n && goy >= 0 && goy < m ) {
					if(arr[gox][goy] == 0) {//다음이 벽이 아니네
						if( !visit[gox][goy][pos.wall] ) {
							q.add(new Pos(gox,goy,pos.dis+1,pos.wall));
							visit[gox][goy][pos.wall] = true;
						}

					}else {	 
						//다음이 벽이라면
						if( !visit[gox][goy][1] && pos.wall==0 ) {
							q.add(new Pos(gox,goy,pos.dis+1,1));
							visit[gox][goy][1] = true;
						}
					}
				}
			}

		}
		System.out.println(-1);
	}
	static class Pos{
		int x; //좌표
		int y;
		int dis; //거리
		int wall; //벽 부순 횟수
		Pos(int x,int y,int dis, int wall) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.wall = wall;	
		}
	}
}
