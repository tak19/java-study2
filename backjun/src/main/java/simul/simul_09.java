package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
//백준 낚시왕 - 17143
public class simul_09 {
	private static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Shark> pq = new PriorityQueue<>();
	static int N,M,cnt;
	static Shark[][] map;
	static int result;
	//상 하 우 좌
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};

	public static void main(String[] args) throws Exception{

		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  =new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //행
		M = Integer.parseInt(st.nextToken()); //열
		cnt = Integer.parseInt(st.nextToken()); //상어수
		
		map = new Shark[N+1][M+1];
		//상어 정보 입력 받음
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			//상어 입장 뚠뚜뚜뚜
			map[x][y] = new Shark(x, y, s, dir-1, size);
		}
		
		for(int i = 1 ; i <= M ; i++) {
			
			
			//상어 잡음
			catchShark(i);
			//잡은 상어 제외하고 큐에 다시 삽입
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= M; c++) {
					if(map[r][c] != null) {
						pq.offer(map[r][c]);
					}
				}
			}
			//맵 초기화
			map = new Shark[N+1][M+1];
			
			while(!pq.isEmpty()) {
				sharkMove();
			}
			
		}
		System.out.println(result);


	}
	//열 기준 제거 -> 매개변수 = 열 --> 열을 고정
	private static void catchShark(int col) {
		//행 탐색
		for(int i = 1 ; i <= N ; i++ ) {
			if( map[i][col] != null ) {
				result += map[i][col].size;
				map[i][col] = null;
				break;
			}
		}
	}

	//상어이동
	private static void sharkMove() {
		//이동할 상어 빼냄 --> 무게가 무거운거 순서
		Shark s = pq.poll();
		
		int move = 0;
		//상하 방향이라면
		if( s.dir == 0 || s.dir == 1 ) {
			//행 방향 왕복의 경우 제거
			move = s.s % ( (N-1) * 2 );
			
		}else { //좌우 방향이라면
			//열 방향 왕복의 경우 제거
			move = s.s % ( (M-1) * 2 );
			
		}
		//실제 이동 거리만큼 이동함
		for(int i = 0 ; i < move ; i++) {

			int temX = s.x + dx[s.dir];
			int temY = s.y + dy[s.dir];
			//벽이랑 만난다면 방향 전환하고, 값을 더함
			if( !ckArea(temX,temY) ) {
				chageDir(s);
			}
			s.x += dx[s.dir];
			s.y += dy[s.dir];
		}
		
		if(map[s.x][s.y] == null) {
			map[s.x][s.y] = s;
		}

	}
	//방향 전환
	private static void chageDir(Shark s) {
		switch (s.dir) {
		case 0: {
			s.dir = 1;
			break;
		}case 1: {
			s.dir = 0;
			break;
		}case 2: {
			s.dir = 3;
			break;
		}case 3: {
			s.dir = 2;
			break;
		}
		
		}
	}
	//방향 전환 유무
	private static boolean ckArea(int x, int y) {
		//경계선 안이라면 true
		if( x > 0 && x <= N  && y > 0 && y <= M ) {
			return true;
		}
		return false;
	}


	static class Shark implements Comparable<Shark>{
		int x,y,s,dir,size;

		public Shark(int x, int y, int s, int dir, int size) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			return o.size - this.size;
		}
		
	}
}

