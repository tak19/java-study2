package bfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 3차원 맵을 써야할것 같음
 * 열쇠를 하나 찾으면 해당 열쇠를 가지고 있는지 여부에 따라 다음 차원 방문 여부 확인함
 * 
 */
public class bfs_27 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int R,C,result;
	static char[][] map;
	static boolean[][][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int startX = 0;
		int startY = 0;

		//지도 입력받기
		map = new char[R][C];
		for(int i = 0 ; i < R ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < C; j++) {
				map[i][j] = c[j];
				if( map[i][j] == '0' ) {
					//현재 위치 저장용
					startX = i;
					startY = j;
					map[i][j] = '.'; //자기 자리 빈칸으로 변경한다요
				}
			}
		}
		//오빠랑 달보러 가자
		bfs(startX,startY);
		result = result == 0 ? -1 : result;
		System.out.println(result);
	}
	private static void bfs(int startX, int startY) {
		Queue<Pos> q = new ArrayDeque();
		q.offer(new Pos(startX,startY,0,0));

		//알파벳을 찾은 수에 따른 차원 변경 필요 -> a~f임
		visit = new boolean[R][C][64];

		//찾은 열쇠가 있다면 탐색을 계속 진행한다.
		while( !q.isEmpty() ) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			if( map[x][y] == '1' ) {
				result = p.dis;
				break;
			}
			int key = p.key;
			visit[x][y][key] = true;
			
			for(int i = 0; i < 4; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//갈수 있나염? = 범위 안이면서 벽인지 판단함
				if( canGo(gox,goy) && map[gox][goy] != '#' ) {
					char now = map[gox][goy]; //현재 자리는 뭐지?
					//방문한적이 없는 곳이라믄!! - 키다
					if( !visit[gox][goy][key] ) {
						if( ckLower( now ) ) {
							int alpa = ( 1 << now - 'a' ) | key;
							//열쇠만나면 해당 열쇠를 가지고 있나 판단하고 새로운 열쇠를 가지는 다른차원으로 이동
							if( !visit[gox][goy][alpa] ) {
								q.offer(new Pos(gox,goy,alpa,p.dis+1 ));
								visit[gox][goy][alpa] = true;
							}

						}else if( ckUpper(now)) {
							//문이라면 -> 열쇠있는 확인하고 있다면 계속 탐색
							if( ((1 << now - 'A') & key) > 0 ) {
								visit[gox][goy][key] = true;
								q.offer(new Pos(gox,goy,key,p.dis+1));
							}
						}else {
							//그냥 벽이라면 방문처리하고 계속 탐색
							visit[gox][goy][key] = true;
							q.offer(new Pos(gox,goy,key,p.dis+1));
						}
					}
				}
			}
		}
	}
	//대문자인지 확인한다.
	private static boolean ckUpper(char d) {
		if( 'A' <= d && d <= 'F' ) {
			return true;
		}
		return false;
	}
	//소문자인지 확인한다.
	private static boolean ckLower(char d) {
		if( 'a' <= d && d <= 'f' ) {
			return true;
		}
		return false;
	}
	//범위 안인지 확인
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < R && goy >= 0 && goy < C ) {
			return true;
		}
		return false;
	}
	//좌표저장
	static class Pos{
		int x,y,key,dis;

		public Pos(int x, int y, int key,int dis) {
			this.x = x;
			this.y = y;
			this.key = key;
			this.dis = dis;
		}
	}
}

