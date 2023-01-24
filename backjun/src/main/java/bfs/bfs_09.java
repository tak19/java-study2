package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_09 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] visit;
	static int[][] people;
	static int n,min,max;
	static int avg,cnt;
	static Queue<Pos> change = new LinkedList<>(); //변화값을평균으로 바꾸기 위해
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// n: 땅 크기 min: 최소차 max = 최대차
		n = Integer.parseInt(st.nextToken());
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		
		visit = new boolean[n][n];
		people = new int[n][n];
		
		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int day=0;
		//탐색시작
		while(true) {
			visit = new boolean[n][n];
			int ck = 0; //연합이 진행되는지 확인하기 위함
			for(int i = 0; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					if( !visit[i][j] ) {
						ck++; //연합하지 않았다면 탐색하기 시작하기 때문에 +1
						bfs(i,j);
						avg /= cnt; //평균!!
						while( !change.isEmpty()) { //연합에 들어간 큐를 빼내면서 평균값으로 교체
							Pos p = change.poll();
							people[p.x][p.y] = avg;
						}
					}
					}
				}
			if( ck == n*n) { //방문횟수가 땅크기라면 아무 연합도 이뤄지지않기 때문에 탈출
				break;
			}
			day++;
		}
		System.out.println(day);
		
		
		
		
		
	}

	private static void bfs(int i, int j) {
		//큐 2개를 활용 -> 연합진행용과 연합 후 값 변경을 위한 큐
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(i,j));
		avg = people[i][j];
		cnt= 1;
		change.add(new Pos(i,j)); //change 큐는 연합 후 평균값 대입을 위해 활용
		visit[i][j] = true;
		while( !q.isEmpty() ) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int now = people[x][y];
			for(int go = 0 ; go < 4 ; go++) {
				int gox = x + dx[go];
				int goy = y + dy[go];
				// 땅 크기 범위 내이면서 연합한 적인 없는 땅을 찾음
				if(gox >= 0 && gox < n && goy >= 0 && goy < n && !visit[gox][goy]) {
					int sub = Math.abs(now - people[gox][goy]); 
					if( sub >= min && sub <= max ) { //차가 범위 안이라면
						cnt++;
						avg += people[gox][goy];
						visit[gox][goy] = true;
						q.add(new Pos(gox,goy));
						change.add(new Pos(gox,goy));
					}
				}
				
			}
			
		}
		
	}
	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}


