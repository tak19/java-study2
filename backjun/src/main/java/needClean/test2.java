package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
<<<<<<< HEAD
import java.util.Queue;
=======
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
>>>>>>> branch 'master' of https://github.com/tak19/java-study2.git
import java.util.StringTokenizer;

public class test2 {

	//우좌상하 순서
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N,K,cnt;
	static int[][] map;
	static boolean ck;
	static List<Integer> list; //겹쳐진 숫자를 판단할 리스트임
	static Pos[] horse;
	static Stack[][] stack;

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if( n== 0 && m == 0 ) {
				break;
			}
			map = new int[n][m];
			visit = new boolean[n][m];
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < m ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = 0;
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < m ; j++) {
					if( map[i][j] == 1 && !visit[i][j] ) {
						result++;
						visit[i][j] = true;
						q = new ArrayDeque<>();
						q.add(new Pos(i,j));
						bfs();
					}
				}
			}
			sb.append(result).append("\n");
			
			
=======
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 체스판 크기
		K = Integer.parseInt(st.nextToken()); // 말 개수

		//체스판 입력 받기		
		map = new int[N][N];
		stack = new Stack[N][N];
		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				stack[i][j] = new Stack<Integer>();
			}
>>>>>>> branch 'master' of https://github.com/tak19/java-study2.git
		}
		
<<<<<<< HEAD
		System.out.println(sb);
=======
		
		
		//말의 정보를 입력 받는다! -> 행,열,이동방향 인덱스를 맞춰줌
		horse = new Pos[K];
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			
			horse[i] = new Pos(x,y,dir);
			//스택에 말을 저장함
			stack[x][y].add(i);
		}
		//구현 메소드
		letSimul();
		System.out.println(cnt >= 1000 ? -1 : cnt);
>>>>>>> branch 'master' of https://github.com/tak19/java-study2.git
	}
<<<<<<< HEAD

	private static void bfs() {
		
		while( !q.isEmpty() ) {
			Pos tem= q.poll();
			for(int i = 0 ; i < 8 ;i++) {
				int gox = tem.x + dx[i];
				int goy = tem.y + dy[i];
				if( canGo(gox,goy) ) {
					if( map[gox][goy] == 1 && !visit[gox][goy] ) {
						visit[gox][goy] = true;
						q.add(new Pos(gox,goy));
					}
				}
			}
		}
		
		
	}
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < n && goy >= 0 && goy < m ) {
			return true;
		}
		return false;
	}
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
=======
	
	//게임시작
	private static void letSimul() {
		cnt = 0;
		while( cnt < 1000 ) {
			cnt++;
			//말 전체 이동이 한턴임
			for(int i = 0 ; i < K ; i++) {
				moveHorse(i);
				if( ck ) {
					break;
				}
			}
			if( ck ) {
				break;
			}
			
		}
		
	}
	//말을 움직인다.
	private static void moveHorse(int num) {
		int x = horse[num].x;
		int y = horse[num].y;
		//이동 시킬 말의 위치
		int gox = horse[num].x + dx[horse[num].dir];
		int goy = horse[num].y + dy[horse[num].dir];
		//실제 방향을 확인함
		horse[num].dir = selectDir(gox,goy,horse[num].dir);
		// 실제 갈 방향을 더해서 좌표계산을 한다.
		gox = horse[num].x + dx[horse[num].dir];
		goy = horse[num].y + dy[horse[num].dir];
		
		//범위 안인지부터 체크함 + 파란색인지 확인
		if( canGo(gox, goy) && map[gox][goy] != 2 ) {
			//다음 이동 칸이 흰,빨 중에 하나인 경우만 계산
			if( map[gox][goy] == 0 ) {
				Queue<Integer> q = new ArrayDeque<>();
				//흰색 이라면 -> 딸린 식구 데리고 -- 현재 스택에서 자기 번호가 나올때까지 다른 말을 이동시킴 
				while( (int)stack[x][y].peek() != num ) {
					q.add((int)stack[x][y].pop());
				}
				// 자신 값 옮긴 후에 나머지 값도 옮긴다.
				stack[gox][goy].add(stack[x][y].pop());
				horse[num].x = gox;
				horse[num].y = goy;
				
				while( !q.isEmpty() ) {
					int n = q.peek();
					horse[n].x = gox;
					horse[n].y = goy;
					stack[gox][goy].add(q.poll());
				}
				
				
			}else {
				//빨간색이라면
				Queue<Integer> q = new ArrayDeque<>();
				// 딸린 식구 데리고 -- 현재 스택에서 자기 번호가 나올때까지 다른 말을 이동시킴 
				while( (int)stack[x][y].peek() != num ) {
					q.add((int)stack[x][y].pop());
				}
				// 자신 값 옮긴 후에 나머지 값도 옮긴다.
				stack[gox][goy].add(stack[x][y].pop());
				horse[num].x = gox;
				horse[num].y = goy;
				
				while( !q.isEmpty() ) {
					int n = q.peek();
					horse[n].x = gox;
					horse[n].y = goy;
					stack[gox][goy].add(q.poll());
				}
				//뒤집는 연산이 필요해
				int size = stack[gox][goy].size();
				Stack<Integer> tem = new Stack<>();
				for(int i = 0 ; i < size ; i ++) {
					tem.add((int)stack[gox][goy].pop());
				}
				stack[gox][goy] = tem;
			}

			if(stack[gox][goy].size() >= 4 ) {
				ck = true;
				return;
			}
		}else {
			//범위 밖 그냥 제자리에 있음 -> 제자리에 있으면 스택과 변화가 없겠지
			
		}
		
		
		
	}
	//이동 위치가 범위를 벗어나거나 파란색일 경우 방향을 바꿔준다.
	private static int selectDir(int gox, int goy, int dir) {
		if( !canGo(gox,goy) || map[gox][goy] == 2 ) {
			if( dir <= 1 ) {
				//우좌 방향이라면 반대전환
				return dir == 1 ? 0 : 1;
			}else {
				//상하방항이라면 반대 전환
				return dir == 2 ? 3 : 2;
			}
		}else {
			return dir;
		}
	}

	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N) {
			return true;
		}
		return false;
	}
	//좌표 저장
	static class Pos{
		int x,y,dir;

		public Pos(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

>>>>>>> branch 'master' of https://github.com/tak19/java-study2.git
}


