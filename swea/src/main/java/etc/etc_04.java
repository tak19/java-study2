package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class etc_04 {
	//상하좌우 순임
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m,time;
	static int[][] map;
	static boolean[][] visit;
	static Queue<Pos> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {

			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new int[n][m];
			//시작 위치 입력받음
			q.add(new Pos( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) );
			time = Integer.parseInt(st.nextToken());
			//지도 입력받기
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < m ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[n][m];
			visit[q.peek().x][q.peek().y] = true; //1시간 갈때를 대비해 true 처리해둠
			bfs(); //탐색 시작

			int result = 0;
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < m ; j++) {
					if( visit[i][j] ) {
						result += 1;
					}
				}
			}
			sb.append(result).append("\n");
			q.clear();

		}
		System.out.println(sb);

	}
	private static void bfs() {
		int now = 1; //현재 시간 - 시작전에 큐에 넣었기 때문에 1부터 시작

		while( now < time ) {
			int size = q.size(); //현재 횟수만큼 반복하기위해 --> for문에 넣으면 안됨!

			for(int i = 0 ; i < size ; i++) {
				Pos pos = q.poll(); //시작점
				int x = pos.x;
				int y = pos.y;
				visit[x][y] = true;

				run(x,y);

			}
			now++; //횟수 증가
		}

	}
	//도주로 확인
	private static void run(int x, int y) {

		switch (map[x][y]) {
		case 1: { //상하좌우 가능
			move(0,4,x,y,1);
			break;
		}case 2: { //상하 이동가능
			move(0,2,x,y,1);
			break;
		}case 3: { //좌우만 이동가능
			move(2,4,x,y,1);
			break;
		}case 4: { //상우
			move(0,4,x,y,3);
			break;
		}case 5: { //하우 이동가능
			move(1,4,x,y,2);
			break;
		}case 6: { //하좌 이동가능
			move(1,3,x,y,1);
			break;
		}case 7: { //상좌 이동가능
			move(0,3,x,y,2);
			break;
		}case 0:{
			break;
		}

		}


	}
	private static void move(int start, int end, int x, int y, int plus) {
		for(int i = start ; i < end ; i += plus) {
			int gox = x + dx[i];
			int goy = y + dy[i];
			//이동 위치가 터널이 있고 범위 안이라면
			if( canGo(gox,goy) && map[gox][goy] != 0) {
				//파이프가 연결되었는지 확인 후 보냄
				if( !visit[gox][goy] && connect(i,gox,goy) ) {//방문 x + 연결되어있나?
					visit[gox][goy] = true; //중복 방문을 예방하기 위해 미리 방문처리
					q.add(new Pos(gox, goy));
				}
			}

		}
	}
	//연결되어 있는지 확인
	private static boolean connect(int i, int gox, int goy) {
		switch (i) {
		case 0: { //상
			if( map[gox][goy] == 1 || map[gox][goy] == 2 || map[gox][goy] == 5 || map[gox][goy] == 6 ) {
				return true;
			}
			break;
		}case 1: { //하
			if( map[gox][goy] == 1 || map[gox][goy] == 2 || map[gox][goy] == 4 || map[gox][goy] == 7 ) {
				return true;
			}
			break;
		}case 2: { //좌
			if( map[gox][goy] == 1 || map[gox][goy] == 3 || map[gox][goy] == 4 || map[gox][goy] == 5 ) {
				return true;
			}
			break;
		}case 3: { //우
			if( map[gox][goy] == 1 || map[gox][goy] == 3 || map[gox][goy] == 6 || map[gox][goy] == 7 ) {
				return true;
			}
			break;
		}
		}
		return false;
	}
	//범위만 확인함
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < n && goy >= 0 && goy < m ) {//범위안이라면
			return true;
		}
		return false;
	}
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
}